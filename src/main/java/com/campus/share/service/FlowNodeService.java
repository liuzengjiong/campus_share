package com.campus.share.service;

import com.campus.share.model.FlowNode;

import java.util.List;

public interface FlowNodeService {
    List<FlowNode> getFlowNodes(Long essayId);

    List<FlowNode> getDisplayFlowNodes(Long essayId);

    boolean isReceive(Long essayId,Long userId);

    FlowNode getLastNode(Long essayId,Long userId);

    int insert(FlowNode flowNode);

    int countInProgressNodeNum(Long essayId);
}


