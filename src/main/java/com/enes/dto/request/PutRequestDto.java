package com.enes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PutRequestDto {

    private Long id;

    private Map<String, Object> fields;
}
