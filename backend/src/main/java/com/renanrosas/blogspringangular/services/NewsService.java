package com.renanrosas.blogspringangular.services;

import com.renanrosas.blogspringangular.domain.News;
import com.renanrosas.blogspringangular.repositories.NewsRepository;
import com.renanrosas.blogspringangular.services.exceptions.DatabaseException;
import com.renanrosas.blogspringangular.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
        return obj.orElseThrow(() -> new ObjectNotFoundException("Entity not found"));
    }

    @Transactional
    public News update(Long id, News obj){
        try {
            News newObj = findById(id);
            updateData(newObj, obj);
            return repository.save(newObj);
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException("Id not found ");
        }
    }

    @Transactional
    public News insert(News obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ObjectNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void updateData(News newObj, News obj) {
        newObj.setDate(obj.getDate());
        newObj.setContent(obj.getContent());
        newObj.setAuthorName(obj.getAuthorName());
        newObj.setTitle(obj.getTitle());
        newObj.setTags(obj.getTags());
    }
}
