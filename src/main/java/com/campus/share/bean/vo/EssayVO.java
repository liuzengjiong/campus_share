package com.campus.share.bean.vo;

import com.campus.share.model.Essay;
import com.campus.share.model.FlowNode;
import com.campus.share.model.Reward;
import com.campus.share.model.UserInfo;

import java.util.List;

public class EssayVO extends Essay {

    private String resourceType;

    private String essayType;

    private List<FlowNode> flowNodes;

    private UserInfo currentActor;

    private Reward reward;

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getEssayType() {
        return essayType;
    }

    public void setEssayType(String essayType) {
        this.essayType = essayType;
    }

    public List<FlowNode> getFlowNodes() {
        return flowNodes;
    }

    public void setFlowNodes(List<FlowNode> flowNodes) {
        this.flowNodes = flowNodes;
    }

    public UserInfo getCurrentActor() {
        return currentActor;
    }

    public void setCurrentActor(UserInfo currentActor) {
        this.currentActor = currentActor;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }
}
