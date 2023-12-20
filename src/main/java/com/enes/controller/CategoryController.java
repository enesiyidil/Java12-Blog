package com.enes.controller;

import com.enes.dto.request.CreateCategoryRequestDto;
import com.enes.dto.request.PutRequestDto;
import com.enes.dto.response.CategoryResponseDto;
import com.enes.exception.BlogException;
import com.enes.exception.ErrorType;
import com.enes.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.enes.constant.EndPoints.*;
import static com.enes.constant.Messages.*;

@RestController
@RequestMapping(ROOT + CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAll(@RequestParam(required = false) String name){
        return ResponseEntity.ok(service.findAll(name));
    }

    @GetMapping(CATEGORY_ID)
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long categoryId){
        return ResponseEntity.ok(service.findById(categoryId));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CreateCategoryRequestDto dto){
        if (service.save(dto)){
            return ResponseEntity.ok(REGISTRATION_SUCCESSFUL_MESSAGE);
        }
        throw new BlogException(ErrorType.REGISTRATION_FAILED);
    }

    @PutMapping(CATEGORY_ID)
    public ResponseEntity<CategoryResponseDto> update(@PathVariable Long categoryId, @RequestBody PutRequestDto dto){
        return ResponseEntity.ok(service.update(categoryId, dto));
    }

    @DeleteMapping(CATEGORY_ID)
    public ResponseEntity<String> delete(@PathVariable Long categoryId){
        if (service.delete(categoryId)){
            return ResponseEntity.ok(DELETION_SUCCESSFUL_MESSAGE);
        }
        throw new BlogException(ErrorType.DELETION_FAILED);
    }
}
