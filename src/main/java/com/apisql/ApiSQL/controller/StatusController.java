package com.apisql.ApiSQL.controller;

import com.apisql.ApiSQL.model.Status;
import com.apisql.ApiSQL.openapi.StatusOpenApi;
import com.apisql.ApiSQL.service.StatusService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatusController implements StatusOpenApi {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @Override
    @GetMapping
    public List<Status> getAll() {
        return statusService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Status> getById(
             @PathVariable Integer id) {
        return statusService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Status create(@RequestBody Status status) {
        return statusService.save(status);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (statusService.findById(id).isPresent()) {
            statusService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
