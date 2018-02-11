package com.campus.share.service.impl;

import com.campus.share.bean.Result;
import com.campus.share.dao.EssayMapper;
import com.campus.share.model.Essay;
import com.campus.share.service.EssayService;
import com.campus.share.util.SimpleStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EssayServiceImpl implements EssayService{

    @Autowired
    private EssayMapper essayMapper;

    @Override
    public boolean add(Essay essay) {
        String summary = SimpleStringUtil.removeHtmlTag(essay.getContent());
        if(summary != null  && summary.length() > 50){
            summary = summary.substring(0,50);
        }
        essay.setSummary(summary);
        essay.setReadNum(0);
        essay.setCreateTime(new Date());
        essay.setUpdateTime(new Date());

        if(essayMapper.insert(essay) == 1){
            return true;
        }
        return false;

    }
}
