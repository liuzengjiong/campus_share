package com.campus.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.campus.share.bean.vo.EssayVO;
import com.campus.share.constant.CodeEnum;
import com.campus.share.constant.FieldConstant;
import com.campus.share.dao.EssayMapper;
import com.campus.share.dao.FlowNodeMapper;
import com.campus.share.dao.RewardMapper;
import com.campus.share.exception.BusinessException;
import com.campus.share.model.*;
import com.campus.share.service.ConfigService;
import com.campus.share.service.EssayService;
import com.campus.share.service.FlowNodeService;
import com.campus.share.util.SimpleStringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class EssayServiceImpl implements EssayService{

    private final Logger logger = LoggerFactory.getLogger(EssayServiceImpl.class);

    @Autowired
    private EssayMapper essayMapper;

    @Autowired
    private ConfigService configService;

    @Autowired
    private FlowNodeService flowNodeService;

    @Autowired
    private RewardMapper rewardMapper;

    @Autowired
    private FlowNodeMapper flowNodeMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public boolean add(EssayVO essay) {
        String summary = SimpleStringUtil.removeHtmlTag(essay.getContent());
        if(summary != null  && summary.length() > 50){
            summary = summary.substring(0,50);
        }
        essay.setSummary(summary);
        essay.setReadNum(0);
        essay.setCreateTime(new Date());
        essay.setUpdateTime(new Date());

        setKeyByValue(essay);

        logger.info("保存任务.......");
        if(essayMapper.insert(essay) != 1){
            return false;
        }

        if(essay.getEssayId() == null){
            logger.error("essayId为null，取消发布任务");
            throw  new BusinessException(CodeEnum.FAIL.getCode(),"抱歉，发布失败，请稍后重试");
        }

        Reward reward = essay.getReward();
        if(reward != null){
            if(StringUtils.isEmpty(reward.getRewardTypeValue())){
                throw new BusinessException(CodeEnum.FAIL.getCode(),"报酬类型不能为空");
            }
            reward.setRewardTypeKey(configService.getConfigKey(FieldConstant.REWARD_TYPE,reward.getRewardTypeValue()));
            reward.setEssayId(essay.getEssayId());
            logger.info("保存报酬.......");
            if(rewardMapper.insert(reward) != 1){
                logger.error("报酬保存失败，取消发布任务");
                throw  new BusinessException(CodeEnum.FAIL.getCode(),"抱歉，发布失败，请稍后重试");
            }
            FlowNode startNode = new FlowNode();
            startNode.setEssayId(essay.getEssayId());
            startNode.setNodeKey("published");
            startNode.setActorId(essay.getAuthorId());
            startNode.setActTime(new Date());
            startNode.setRemark("创建了任务，启动流程");
            startNode.setStatus(0);
            logger.info("保存流程.......");
            if(flowNodeMapper.insert(startNode) != 1){
                logger.error("流程保存失败，取消发布任务");
                throw  new BusinessException(CodeEnum.FAIL.getCode(),"抱歉，发布失败，请稍后重试");
            }
        }

        return true;
    }

    @Override
    public PageInfo<EssayVO> searchEssay(String keyword, String essayType, String sourceType,int page,int pageSize,String serverPath) {
        Map<String,String> param = new HashMap<>();
        if(keyword != null){
            param.put("keyword",keyword);
        }
        if(essayType != null){
            param.put("essayTypeKey",configService.getConfigKey(FieldConstant.ESSAY_TYPE,essayType));
        }
        if(sourceType != null){
            param.put("sourceTypeKey",configService.getConfigKey(FieldConstant.SOURCE_TYPE,sourceType));
        }
        PageHelper.startPage(page, pageSize);
        logger.info("开始获取任务列表");
        List<EssayVO> list = essayMapper.searchEssay(param);
        PageInfo<EssayVO> pageInfo = new PageInfo<>(list);
        logger.info("获取任务列表结束");
        if(pageInfo.getPages() < page){
            pageInfo.getList().clear();
        }

        for(EssayVO essay : pageInfo.getList()){
            SimpleStringUtil.setAvatarUrl(essay.getAuthor(),serverPath);
            setValueByKey(essay);
        }

        return pageInfo;
    }

    @Override
    public EssayVO selectById(Long essayId,String serverPath) {
        EssayVO essay = essayMapper.selectFullInfoByPrimaryKey(essayId);
        if(essay == null){
            return essay;
        }
        setFlowNode(essay);
        SimpleStringUtil.setAvatarUrl(essay.getAuthor(),serverPath);
        for(Comment comment : essay.getComments()){
            SimpleStringUtil.setAvatarUrl(comment.getAuthor(),serverPath);
        }
        essayMapper.addReadNum(essayId);
        setValueByKey(essay);
        return essay;
    }

    @Override
    public Essay selectSimpleInfo(Long essayId) {
        return essayMapper.selectByPrimaryKey(essayId);
    }

    @Override
    public boolean changeStatus(Long essayId, Integer essayStatus) {
        logger.info("修改任务状态");
        return essayMapper.changeStatus(essayId,essayStatus) > 0 ;
    }

    private void setValueByKey(EssayVO essay){
        essay.setResourceType(configService.getConfigValue(FieldConstant.SOURCE_TYPE,essay.getResourceTypeKey()));
        essay.setEssayType(configService.getConfigValue(FieldConstant.ESSAY_TYPE,essay.getEssayTypeKey()));
        setRewardType(essay.getReward());
    }

    private void setKeyByValue(EssayVO essay){
        essay.setEssayTypeKey(configService.getConfigKey(FieldConstant.ESSAY_TYPE,essay.getEssayType()));
        essay.setResourceTypeKey(configService.getConfigKey(FieldConstant.SOURCE_TYPE,essay.getResourceType()));
    }


    private FlowNode getFlowNode(String flowNodeKey,List<FlowNode> flowNodes){
        if(flowNodeKey == null || flowNodes == null || flowNodes.size() == 0){
            return null;
        }
        for(FlowNode node : flowNodes){
            if(flowNodeKey.equals(node.getNodeKey())){
                return node;
            }
        }
        return null;
    }

    private void setFlowNode(EssayVO essay){
        List<FlowNode> nodes = flowNodeService.getFlowNodes(essay.getEssayId());
        if(nodes == null || nodes.size() == 0){
            return;
        }
        List<Config> flowNodeConfigs = configService.getConfigsByType(FieldConstant.FLOW_NODE);
        if(flowNodeConfigs == null){
            return;
        }
        UserInfo currentActor = null;
        List<FlowNode> displayNodes = new ArrayList<>();
        for(Config config : flowNodeConfigs){
            FlowNode displayNode = getFlowNode(config.getConfigKey(),nodes);
            if(displayNode == null){
               displayNode = new FlowNode();
               displayNode.setActorNum(0);
               displayNode.setNodeKey(config.getConfigKey());
               displayNode.setEssayId(essay.getEssayId());
            }else{
                currentActor = displayNode.getActor();
            }
            displayNode.setNodeValue(config.getConfigValue());
            displayNodes.add(displayNode);
        }
        essay.setCurrentActor(currentActor);
        essay.setFlowNodes(displayNodes);
    }

    private void setRewardType(Reward reward){
        if(reward == null){
            return;
        }
        reward.setRewardTypeValue(configService.getConfigValue(FieldConstant.REWARD_TYPE,reward.getRewardTypeKey()));
    }

}
