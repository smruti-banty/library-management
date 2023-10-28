package com.lms.backend.controller;

import java.util.List;

import com.lms.backend.dto.BatchRequestDto;
import com.lms.backend.model.Batch;
import com.lms.backend.services.BatchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/batch")
@Tag(name = "Batch controller", description = "Manage batch")
public class BatchController {

    private final BatchService batchService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Create batch", description = "create a new batch")
    public Batch addBatch(@RequestBody BatchRequestDto batchRequestDto) {
        var batch = new Batch();
        BeanUtils.copyProperties(batchRequestDto, batch);
        return batchService.addBatch(batch);
    }

    @GetMapping
    @Operation(summary = "Get batches", description = "To retrive all batch data")
    public List<Batch> getAllBatch() {
        return batchService.getAllBatch();
    }

    @GetMapping("/{batchId}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @Operation(summary = "get batch by id", description = "Retrive a batch by it's batch id")
    public Batch getBatchById(@PathVariable String batchId) {
        return batchService.getBatchById(batchId);
    }

    @GetMapping("/name/{batchName}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @Operation(summary = "get batch by name", description = "Retrive all the batch containing this string")
    public List<Batch> getBatchByName(@PathVariable String batchName) {
        return batchService.getBatchByName(batchName);
    }

    @DeleteMapping("/{batchId}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @Operation(summary = "Delete batch", description = "Inactive a batch by batch id")
    public Batch deleteBatchById(@PathVariable String batchId) {
        return batchService.deleteBatchById(batchId);
    }

    @GetMapping("/active")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @Operation(summary = "Get all active batch", description = "To retrive all the active batch")
    public List<Batch> getAllActiveBatch() {
        return batchService.getAllActiveBatch();
    }

    @GetMapping("/inactive")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @Operation(summary = "Get all inactive batch", description = "To retrive all the inactive batch")
    public List<Batch> getAllInActiveBatch() {
        return batchService.getAllInActiveBatch();
    }

    @PutMapping("/{batchId}")
    @Operation(summary = "Update batch", description = "Update batch basic details")
    public Batch updateBatch(@RequestBody Batch batch, @PathVariable String batchId) {
        return batchService.updateBatch(batch, batchId);
    }

    @PutMapping("/activate/{batchId}")
    @Operation(summary = "Make inactive batch active", description = "Update as active through batchId")
    public Batch markAsActive(@PathVariable String batchId) {
        Batch batch = batchService.getBatchById(batchId);
        return batchService.markAsActive(batch);
    }
}
