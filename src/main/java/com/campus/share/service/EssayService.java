package com.campus.share.service;

import com.campus.share.bean.vo.EssayVO;
import com.campus.share.model.Essay;
import com.campus.share.model.UserLogin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface EssayService {

    boolean add(EssayVO essay);


    PageInfo<EssayVO> searchEssay(String keyword, String essayType, String sourceType,int page,int pageSize,String serverPath);

    EssayVO selectById(Long essayId,String serverPath);

    Essay selectSimpleInfo(Long essayId);

    boolean changeStatus(Long essayId,Integer essayStatus);

    boolean receiveEssay(Essay essay, UserLogin userLogin);

    boolean completeEssay(Essay essay,UserLogin userLogin,String description);

    boolean comfirmEssay(Essay essay,UserLogin userLogin,Long receiverId);

    boolean checkEssayStatus(Long essayId,Integer essayStatus);

    List<EssayVO> getUserEssay(Long authorId);

}
