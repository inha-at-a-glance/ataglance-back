package com.AtaGlance.controller;

import com.AtaGlance.dto.News;
import com.AtaGlance.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    @Autowired private NewsService newsService;
    /**
     * '/regist' 요청이 get 방식으로 들어왔을 때 regist로 연결한다.
     */
    @GetMapping("/register")
    public String showNewsRegistForm() {
        return "newsRegister";
    }

    /**
     * '/regist' 요청이 post 방식으로 들어왔을 때 전달된 User 객체를 regist_result로 연결한다.
     */
    @PostMapping("/register")
    public ResponseEntity<News> doNewsRegister(@RequestBody Map<String, Object> newsData) {
        // 필요한 값 추출
        Integer category = (Integer) newsData.get("category");
        Integer sourceBc = (Integer) newsData.get("sourceBc");
        String sourceUrl = (String) newsData.get("sourceUrl");

        // 값 검증 (Optional)
        if (category == null || sourceBc == null || sourceUrl == null) {
            return ResponseEntity.badRequest().build();
        }

        // News 객체 생성 및 저장
        News news = new News();
        news.setCategory(category);
        news.setSourceBc(sourceBc);
        news.setSourceUrl(sourceUrl);
        // 뉴스 저장
        News savedNews = newsService.saveNews(news);

        return ResponseEntity.ok(savedNews);
    }
}
