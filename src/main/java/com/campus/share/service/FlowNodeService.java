package com.campus.share.service;

import com.alibaba.fastjson.JSONObject;
import com.campus.share.bean.vo.FlowNodeEssayVO;
import com.campus.share.model.FlowNode;

import java.util.List;

public interface FlowNodeService {
    List<FlowNode> getFlowNodes(Long essayId);

    List<FlowNode> getDisplayFlowNodes(Long essayId);

    boolean isReceive(Long essayId,Long userId);

    FlowNode getLastNode(Long essayId,Long userId);

    int insert(FlowNode flowNode);

    int countInProgressNodeNum(Long essayId);

    JSONObject countEssayWithFlowNode(Long authorId);

    List<FlowNodeEssayVO> getNodeEssay(Long authorId,String nodeKey);

    JSONObject countActorEssayFlowNode(Long actorId);

    List<FlowNodeEssayVO> getActorNodeEssay(Long actorId,String nodeKey);
}


