package com.study.webtodo.controller;


import com.study.webtodo.dto.ResponseDTO;
import com.study.webtodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


}
