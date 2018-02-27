package com.campus.share.service;

import com.campus.share.model.Comment;

import java.util.List;

public interface CommentService {

    boolean add(Comment comment);

    List<Comment> getCommentsByEssayId(Long essayId,String serverPath);

}
