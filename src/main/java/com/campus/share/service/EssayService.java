package com.campus.share.service;

import com.campus.share.bean.vo.EssayVO;
import com.campus.share.model.Essay;
import com.github.pagehelper.PageInfo;

public interface EssayService {

    boolean add(EssayVO essay);


    PageInfo<EssayVO> searchEssay(String keyword, String essayType, String sourceType,int page,int pageSize,String serverPath);

    EssayVO selectById(Long essayId,String serverPath);

    Essay selectSimpleInfo(Long essayId);

    boolean changeStatus(Long essayId,Integer essayStatus);

}
