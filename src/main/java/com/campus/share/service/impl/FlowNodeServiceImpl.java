package com.campus.share.service.impl;

import com.campus.share.constant.FieldConstant;
import com.campus.share.dao.FlowNodeMapper;
import com.campus.share.model.Config;
import com.campus.share.model.FlowNode;
import com.campus.share.model.UserInfo;
import com.campus.share.service.ConfigService;
import com.campus.share.service.FlowNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlowNodeServiceImpl implements FlowNodeService {

    @Autowired
    private FlowNodeMapper flowNodeMapper;

    @Autowired
    private ConfigService configService;

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
