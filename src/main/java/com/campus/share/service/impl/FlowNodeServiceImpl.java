package com.campus.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.campus.share.bean.vo.EssayVO;
import com.campus.share.bean.vo.FlowNodeEssayVO;
import com.campus.share.constant.FieldConstant;
import com.campus.share.dao.FlowNodeMapper;
import com.campus.share.model.Config;
import com.campus.share.model.FlowNode;
import com.campus.share.service.ConfigService;
import com.campus.share.service.EssayService;
import com.campus.share.service.FlowNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FlowNodeServiceImpl implements FlowNodeService {

    @Autowired
    private FlowNodeMapper flowNodeMapper;

    @Autowired
    private ConfigService configService;

    @Autowired
    private EssayService essayService;

    @Override
    public List<FlowNode> getFlowNodes(Long essayId) {
        return flowNodeMapper.selectGroupByessayId(essayId);
    }

    @Override
    public List<FlowNode> getDisplayFlowNodes(Long essayId) {
        List<FlowNode> nodes = this.getFlowNodes(essayId);

        List<Config> flowNodeConfigs = configService.getConfigsByType(FieldConstant.FLOW_NODE);
        if(flowNodeConfigs == null){
            return nodes;
        }
        List<FlowNode> displayNodes = new ArrayList<>();
        for(Config config : flowNodeConfigs){
            FlowNode displayNode = getFlowNode(config.getConfigKey(),nodes);
            if(displayNode == null){
                displayNode = new FlowNode();
                displayNode.setActorNum(0);
                displayNode.setNodeKey(config.getConfigKey());
                displayNode.setEssayId(essayId);
            }
            displayNode.setNodeValue(config.getConfigValue());
            displayNodes.add(displayNode);
        }
        return displayNodes;
    }

    @Override
    public boolean isReceive(Long essayId, Long userId) {
        if(flowNodeMapper.selectLastByessayIdAndUserId(essayId,userId) == null){
            return false;
        }
        return true;
    }

    @Override
    public FlowNode getLastNode(Long essayId, Long userId) {
        return flowNodeMapper.selectLastByessayIdAndUserId(essayId,userId);
    }

    @Override
    public int insert(FlowNode flowNode) {
        return flowNodeMapper.insert(flowNode);
    }
    @Override
    public int countInProgressNodeNum(Long essayId){
        return flowNodeMapper.countInProgressNodeNum(essayId);
    }

    @Override
    public JSONObject countEssayWithFlowNode(Long authorId) {
        JSONObject result = new JSONObject();
        List<Map> dbResult = flowNodeMapper.countAuthorEssayWithFlowNode(authorId);
        List<Config> flowNodeConfig = configService.getConfigsByType(FieldConstant.FLOW_NODE);

        for(Config config: flowNodeConfig){
            Map map = this.getNodeCountBykey(config.getConfigKey(),dbResult);
            JSONObject resultItem = new JSONObject();
            resultItem.put("nodeName",config.getConfigValue());
            if(map == null){
                resultItem.put("essayNum",0);
            }else{
                resultItem.put("essayNum",map.get("essayNum"));
            }
            result.put(config.getConfigKey(),resultItem);
        }

        return result;
    }

    @Override
    public List<FlowNodeEssayVO> getNodeEssay(Long authorId, String nodeKey) {
        if(FlowNode.ALL.equals(nodeKey)){
            return this.getMyPublishEssay(authorId);
        }
        List<FlowNodeEssayVO> list = flowNodeMapper.getNodeEssay(authorId,nodeKey);
        for(FlowNodeEssayVO vo : list){
            EssayVO essay = vo.getEssay();
            if(essay == null){
                continue;
            }
            essay.setEssayType(configService.getConfigValue(FieldConstant.ESSAY_TYPE,essay.getEssayTypeKey()));
            essay.setResourceType(configService.getConfigValue(FieldConstant.SOURCE_TYPE,essay.getResourceTypeKey()));
        }
        return list;
    }

    @Override
    public JSONObject countActorEssayFlowNode(Long actorId) {
        JSONObject result = new JSONObject();
        List<Map> dbResult = flowNodeMapper.countActorEssayWithFlowNode(actorId);
        List<Config> flowNodeConfig = configService.getConfigsByType(FieldConstant.FLOW_NODE);

        for(Config config: flowNodeConfig){
            Map map = this.getNodeCountBykey(config.getConfigKey(),dbResult);
            JSONObject resultItem = new JSONObject();
            resultItem.put("nodeName",config.getConfigValue());
            if(map == null){
                resultItem.put("essayNum",0);
            }else{
                resultItem.put("essayNum",map.get("essayNum"));
            }
            result.put(config.getConfigKey(),resultItem);
        }

        return result;
    }

    @Override
    public List<FlowNodeEssayVO> getActorNodeEssay(Long actorId, String nodeKey) {
        List<FlowNodeEssayVO> list;
        if(FlowNode.ALL.equals(nodeKey)){
            list = flowNodeMapper.getActorAllNodeEssay(actorId);
        }else {
            list = flowNodeMapper.getActorNodeEssay(actorId, nodeKey);
        }
        for(FlowNodeEssayVO vo : list){
            EssayVO essay = vo.getEssay();
            if(essay == null){
                continue;
            }
            vo.setNodeValue(configService.getConfigValue(FieldConstant.FLOW_NODE,vo.getNodeKey()));
            essay.setEssayType(configService.getConfigValue(FieldConstant.ESSAY_TYPE,essay.getEssayTypeKey()));
            essay.setResourceType(configService.getConfigValue(FieldConstant.SOURCE_TYPE,essay.getResourceTypeKey()));
            if(essay.getReward()!=null){
                essay.getReward().setRewardTypeValue(configService.getConfigValue(FieldConstant.REWARD_TYPE,essay.getReward().getRewardTypeKey()));
            }
        }
        return list;
    }

    private List<FlowNodeEssayVO> getMyPublishEssay(Long authorId){
        List<FlowNodeEssayVO> list = new ArrayList<>();
        List<EssayVO> essayList = essayService.getUserEssay(authorId);
        for(EssayVO essay : essayList){
            FlowNodeEssayVO nodeEssayVO = new FlowNodeEssayVO();
            nodeEssayVO.setEssay(essay);
            list.add(nodeEssayVO);
        }
        return list;
    }

    private Map getNodeCountBykey(String key,List<Map> nodes){
        if(key == null){
            return null;
        }
        for(Map map : nodes){
            if(key.equals(map.get("node_key"))){
                return map;
            }
        }
        return null;
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
}
