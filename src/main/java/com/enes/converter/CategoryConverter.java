package com.enes.converter;

import com.enes.dto.request.CreateCategoryRequestDto;
import com.enes.dto.response.CategoryResponseDto;
import com.enes.repository.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<Category, CategoryResponseDto> {

    @Override
    public CategoryResponseDto entityToDto(Category category){
        if (category == null){
            return null;
        }
        return CategoryResponseDto.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public Category createCategoryRequestDtoToCategory(CreateCategoryRequestDto dto) {
        if (dto == null){
            return null;
        }
        return Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

}
