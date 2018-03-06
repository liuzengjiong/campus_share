package com.campus.share.bean.vo.req;

import com.alibaba.fastjson.JSONArray;

public class SearchEssayReq {

    private String essayType;

    private JSONArray resourceTypes;

    private JSONArray rewardTypes;

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public JSONArray getRewardTypes() {
        return rewardTypes;
    }

    public void setRewardTypes(JSONArray rewardTypes) {
        this.rewardTypes = rewardTypes;
    }

    public JSONArray getResourceTypes() {
        return resourceTypes;
    }

    public void setResourceTypes(JSONArray resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    public String getEssayType() {
        return essayType;
    }

    public void setEssayType(String essayType) {
        this.essayType = essayType;
    }
}
