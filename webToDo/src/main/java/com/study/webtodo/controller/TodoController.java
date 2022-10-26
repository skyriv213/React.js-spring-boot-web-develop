package com.study.webtodo.controller;


import com.study.webtodo.dto.ResponseDTO;
import com.study.webtodo.dto.TodoDTO;
import com.study.webtodo.model.TodoEntity;
import com.study.webtodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

    //testTodo 메서드
    @GetMapping("/test")
    public ResponseEntity<?> testControllerEntity() {
        String str = service.testService(); // service의 testService 실행 후 해당 값 저장
        List<String> list = new ArrayList<>();
        list.add(str); // list에 해당 str 저장장
       ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
        try {
            String temporaryUserId = "temporary-user";
            // 1. TodoEntity로 dto를 변환한다
            TodoEntity entity = TodoDTO.toEntity(dto);
            // 2. 변환된 entity에 id를 null로 초기화 한다
            entity.setId(null);
            // 3. entity에 지정된 userId를 넣어준다
            entity.setUserId(temporaryUserId);
            // 4. service의 create 메서드를 사용하여 레포지토리에 저장 후 TodoEntity를 담는 리스트 객체를 만들어준다
            List<TodoEntity> entities = service.create(entity);
            // 5. 자바의 스트림을 활용하여 만들어진 엔티티 리스트를 DTO 리스트로 변환해준다.
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
            // 6. 변환된 DTO 리스트를 ResponseDTO로 초기화한다
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
            // 7. 만들어진 response를 반환한다.
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }


}
