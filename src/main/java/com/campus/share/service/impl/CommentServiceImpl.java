package com.campus.share.service.impl;

import com.campus.share.dao.CommentMapper;
import com.campus.share.model.Comment;
import com.campus.share.service.CommentService;
import com.campus.share.util.SimpleStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean add(Comment comment) {

        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        int x = commentMapper.insert(comment);
        if(x == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<Comment> getCommentsByEssayId(Long essayId, String serverPath) {
        List<Comment> comments = commentMapper.selectByEssayId(essayId);
        for(Comment comment : comments){
            SimpleStringUtil.setAvatarUrl(comment.getAuthor(),serverPath);
        }
        return comments;
    }
}
