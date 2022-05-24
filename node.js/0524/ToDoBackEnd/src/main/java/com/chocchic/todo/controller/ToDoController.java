package com.chocchic.todo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocchic.todo.service.ToDoService;

import lombok.RequiredArgsConstructor;

// 데이터를 리턴하기 위한 Controller를 만들기 위한 어노테이션
@RestController
// 공통된 URL작성 - localhost:port/todo
@RequestMapping("todo")
@RequiredArgsConstructor
public class ToDoController {
		private ToDoService service;
}
