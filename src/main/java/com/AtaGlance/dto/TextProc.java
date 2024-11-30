package com.AtaGlance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class TextProc {
    private Integer textId;

    @NotNull
    private Integer newsId;

    private String summaryTxt;

    private List<String> timeStamp;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Constructor
//    public TextProc(Integer textId, Integer newsId, List<String> summaryTxt,
//                    List<String> timeStamp, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.textId = textId;
//        this.newsId = newsId;
//        this.summaryTxt = summaryTxt;
//        this.timeStamp = timeStamp;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }

    public TextProc(Integer textId, Integer newsId, String summaryTxt) {
        this.textId = textId;
        this.newsId = newsId;
        this.summaryTxt = summaryTxt;
    }

    // Getters and Setters
}
