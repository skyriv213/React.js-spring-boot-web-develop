package com.study.webtodo.service;

import com.study.webtodo.model.TodoEntity;
import com.study.webtodo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {
        TodoEntity entity = TodoEntity.builder().title("first todo item").build();
        repository.save(entity);
        TodoEntity saveEntity = repository.findById(entity.getId()).get();
        return saveEntity.getTitle();
    }

    /***
     *
     * @param entity
     * @return Todo 리스트 반환
     * 해당 entity가 이미 존재하는지 validate 호출 후
     * repository에 저장 및 로그 출력
     * 리스트 반환
     */
    public List<TodoEntity> create(final TodoEntity entity) {
        //validations
        validate(entity);
        repository.save(entity);
        log.info("Entity Id: {} is saved", entity.getId());
        return repository.findByUserId(entity.getUserId());
    }

    /***
     *
     * @param userId
     * @return repository.findByUserId(userId) → repository에서 userId로 Todo 리스트 반환
     *
     */
    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity) {
        // 데이터 유효 검사
        validate(entity);
        // entity id를 이용해 TodoEntity를 가져옴. 존재하지 않는 엔티티는 업데이트 불가
        Optional<TodoEntity> original = repository.findById(entity.getId());
        // orginal을 새로 업데이트
        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());
            repository.save(todo);
        });
        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity) {
        validate(entity);
        try {
            repository.delete(entity);
        } catch (Exception e) {
            log.error("error deleting entity", entity.getId(), e);
            throw new RuntimeException("error deleting entity" + entity.getId());
        }
        return retrieve(entity.getUserId());

    }

    /***
     *
     * @param entity
     * 해당 entity가 존재하는지 안하는지 검사하는 메서드
     *
     */
    private void validate(TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        if (entity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }


}
