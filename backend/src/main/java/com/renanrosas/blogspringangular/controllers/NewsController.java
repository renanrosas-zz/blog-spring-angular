package com.renanrosas.blogspringangular.controllers;

import com.renanrosas.blogspringangular.domain.News;
import com.renanrosas.blogspringangular.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsService service;

    @GetMapping
    public ResponseEntity<List<News>> findAll(){
        List<News> list = service.findAll();
        return  ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<News> findAById(@PathVariable Long id){
        News obj = service.findById(id);
        return  ResponseEntity.ok().body(obj);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@RequestBody News obj){
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody News obj){
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
