package com.kmeans.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kmeans.backend.repository.HistoryRepository;
import com.kmeans.converter.APIRequest;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryRepository repository;

    public HistoryController(HistoryRepository repository) {
        this.repository = repository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public APIRequest save(@RequestBody APIRequest req) {
        return this.repository.save(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get")
    public List<APIRequest> findAll() {
        return this.repository.findAll();
    }
}
