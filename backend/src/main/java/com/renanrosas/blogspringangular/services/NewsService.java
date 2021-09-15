package com.renanrosas.blogspringangular.services;

import com.renanrosas.blogspringangular.domain.News;
import com.renanrosas.blogspringangular.repository.NewsRepository;
import com.renanrosas.blogspringangular.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    private NewsRepository repository;

    @Transactional(readOnly = true)
    public List<News> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public News findById(Long id){
        Optional<News> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
    @Transactional
    public News update(News obj){
        News newObj = findById((obj.getId()));
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    @Transactional
    public News insert(News obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    private void updateData(News newObj, News obj) {
        newObj.setDate(obj.getDate());
        newObj.setContent(obj.getContent());
        newObj.setAuthorName(obj.getAuthorName());
        newObj.setTitle(obj.getTitle());
    }
}
