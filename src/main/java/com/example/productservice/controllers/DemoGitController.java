package com.example.productservice.controllers;

import com.example.productservice.dto.DemoGitDTO;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.services.DemoGitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Add thêm commetn
 * 
 * - Đây là COntroller test Git
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo-gits")
public class DemoGitController {

    private final DemoGitService demogitService;

    @GetMapping
    public ResponseEntity<Page<DemoGitDTO>> getAllDemoGit(@RequestParam(defaultValue = "1", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "limit") int limit) {
        Page<DemoGitDTO> demogitDTOS = demogitService.getAllDemoGit(PageRequest.of(page - 1, limit));
        return ResponseEntity.ok(demogitDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemoGitDTO> getDemoGitById(@PathVariable Long id) {
        DemoGitDTO demogit = demogitService.getDemoGitById(id);
        if (demogit == null) {
            throw new CategoryNotFoundException("DemoGit not found with id: " + id);
        }
        return ResponseEntity.ok(demogit);
    }

    @GetMapping("/name/{name}")
    public List<DemoGitDTO> getDemoGitByName(@PathVariable String name) {
        List<DemoGitDTO> demogit = demogitService.getDemoGitByName(name);
        if (demogit == null) {
            throw new CategoryNotFoundException("DemoGit not found with name: " + name);
        }
        return demogit;
    }

    @PostMapping
    public ResponseEntity<?> addDemoGit(@Valid @RequestBody DemoGitDTO demogitDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(),
                            fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        demogitService.addDemoGit(demogitDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDemoGit(@PathVariable Long id, @Valid @RequestBody DemoGitDTO demogitDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(),
                            fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        demogitService.updateDemoGit(id, demogitDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> moveToTrash(@PathVariable Long id) {
        demogitService.moveToTrash(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/in-trash/{id}")
    public ResponseEntity<?> deleteDemoGit(@PathVariable Long id) {
        demogitService.deleteDemoGit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/trash")
    public ResponseEntity<?> getInTrashDemoGit(@RequestParam(defaultValue = "1", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "limit") int limit) {
        return ResponseEntity.ok(demogitService.getInTrash(PageRequest.of(page - 1, limit)));
    }
}
