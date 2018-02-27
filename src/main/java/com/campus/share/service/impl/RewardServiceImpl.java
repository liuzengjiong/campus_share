package com.campus.share.service.impl;

import com.campus.share.dao.RewardMapper;
import com.campus.share.model.Reward;
import com.campus.share.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardMapper rewardMapper;

    @Override
    public int insert(Reward reward) {
        return rewardMapper.insert(reward);
    }

    @Override
    public Reward getReward(Long essayId) {
        return rewardMapper.selectByEssayId(essayId);
    }
}
