package com.renanrosas.blogspringangular.controllers;

import com.renanrosas.blogspringangular.domain.Comment;
import com.renanrosas.blogspringangular.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/news/{newsId}/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping
    public ResponseEntity<List<Comment>> findAll(){
        List<Comment> list = service.findAll();
        return  ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Comment> insert(@RequestBody Comment obj){
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
