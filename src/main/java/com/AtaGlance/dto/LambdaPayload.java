package com.AtaGlance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LambdaPayload {
    private Integer id;
    private String title;
    private String sourceUrl;

    // 생성자
    public LambdaPayload(Integer id, String title, String sourceUrl) {
        this.id = id;
        this.title = title;
        this.sourceUrl = sourceUrl;
    }

    // Getters & Setters (필요 시 추가)
}