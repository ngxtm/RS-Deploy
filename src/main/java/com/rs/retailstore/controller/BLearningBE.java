package com.rs.retailstore.controller;

import com.rs.retailstore.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bl")
public class BLearningBE {
    ArrayList<Student> students = new ArrayList<>();
    @GetMapping("/student")
    public ResponseEntity<ArrayList<Student>> getStudent() {
        return ResponseEntity.ok(students);
    }
    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.ok(student);
    }
}
