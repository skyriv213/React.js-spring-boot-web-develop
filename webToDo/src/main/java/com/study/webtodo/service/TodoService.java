package com.study.webtodo.service;

import com.study.webtodo.model.TodoEntity;
import com.study.webtodo.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;
    public String testService(){
        TodoEntity entity = TodoEntity.builder().title("first todo item").build();
        repository.save(entity);
        TodoEntity saveEntity = repository.findById(entity.getId()).get();
        return saveEntity.getTitle();
    }
}
