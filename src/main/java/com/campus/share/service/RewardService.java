package com.campus.share.service;

import com.campus.share.model.Config;
import com.campus.share.model.Reward;

import java.util.List;

/**
 * 报酬
 */
public interface RewardService {

   int insert(Reward reward);

   Reward getReward(Long essayId);

}
