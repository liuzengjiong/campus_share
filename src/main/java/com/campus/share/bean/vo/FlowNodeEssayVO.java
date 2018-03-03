package com.campus.share.bean.vo;

import com.campus.share.model.Essay;
import com.campus.share.model.FlowNode;

import java.util.List;

public class FlowNodeEssayVO extends FlowNode {


    private List<FlowNode> essayFlowNodes;

    public List<FlowNode> getEssayFlowNodes() {
        return essayFlowNodes;
    }

    public void setEssayFlowNodes(List<FlowNode> essayFlowNodes) {
        this.essayFlowNodes = essayFlowNodes;
    }

}
