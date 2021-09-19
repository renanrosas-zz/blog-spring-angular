package com.renanrosas.blogspringangular.services;

import com.renanrosas.blogspringangular.domain.Comment;
import com.renanrosas.blogspringangular.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Transactional(readOnly = true)
    public List<Comment> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Comment insert(Comment obj) {
        return repository.save(obj);
    }
}
