package com.enes.service;

import com.enes.converter.CategoryConverter;
import com.enes.dto.request.CreateCategoryRequestDto;
import com.enes.dto.request.PutRequestDto;
import com.enes.dto.response.CategoryResponseDto;
import com.enes.exception.BlogException;
import com.enes.exception.ErrorType;
import com.enes.repository.CategoryRepository;
import com.enes.repository.entity.Category;
import com.enes.repository.entity.User;
import com.enes.utility.UtilService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    private final CategoryConverter converter;

    private final @Qualifier("CategoryUtilService") UtilService<Category, CategoryResponseDto, CategoryRepository, CategoryConverter> utilService;

    public CategoryService(CategoryRepository repository,
                           CategoryConverter converter,
                           UtilService<Category, CategoryResponseDto, CategoryRepository, CategoryConverter> utilService) {
        this.repository = repository;
        this.converter = converter;
        this.utilService = utilService;
    }

    public Category createOrSave(String name) {
        Optional<Category> optionalCategory = repository.findByName(name);
        return optionalCategory.orElseGet(() -> repository.save(Category.builder()
                .name(name)
                .build()));
    }

    public Category findByIdCategory(Long categoryId) {
        Optional<Category> optionalCategory = repository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            throw new BlogException(Category.class.getName(), ErrorType.NOT_FOUND);
        }
    }

    public List<CategoryResponseDto> findAll(String name) {
        if (name != null){
            return repository.findAll().stream().map(converter::entityToDto).filter(categoryResponseDto -> categoryResponseDto.getName().equals(name)).collect(Collectors.toList());
        }
        return repository.findAll().stream().map(converter::entityToDto).collect(Collectors.toList());
    }

    public CategoryResponseDto findById(Long id) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (optionalCategory.isPresent()) {
            return converter.entityToDto(optionalCategory.get());
        }
        throw new BlogException(Category.class.getName(), ErrorType.NOT_FOUND);
    }

    public boolean save(CreateCategoryRequestDto dto) {
        Category category = repository.save(converter.createCategoryRequestDtoToCategory(dto));
        return category.getId() != null;
    }

    public CategoryResponseDto update(Long categoryId, PutRequestDto dto) {
        if (Objects.equals(categoryId, dto.getId())) {
            return utilService.update(dto);
        }
        throw new BlogException("Id", ErrorType.NOT_VALID);
    }

    public boolean delete(Long categoryId) {
        Optional<Category> optionalCategory = repository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            repository.delete(optionalCategory.get());
            return repository.findById(categoryId).isEmpty();
        } else {
            throw new BlogException(Category.class.getName(), ErrorType.NOT_FOUND);
        }
    }

    public Category findByName(String categoryName) {
        Optional<Category> optionalCategory = repository.findByName(categoryName);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            throw new BlogException(Category.class.getName(), ErrorType.NOT_FOUND);
        }
    }
}
