package com.AtaGlance.controller;

import com.AtaGlance.dto.News;
import com.AtaGlance.service.CardNewsService;
import com.AtaGlance.service.NewsService;
import com.AtaGlance.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cardnews")
@RequiredArgsConstructor
public class CardNewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private S3Service s3Service;
    @Autowired
    private CardNewsService cardNewsService;

    // 전체 뉴스 출력
    @GetMapping("/all")
    public List<News> getAllNews() {
//        List<News> newsList = newsService.getAllNews();
//        ModelAndView mav = new ModelAndView("CardnewsList");
//        mav.addObject("newsList", newsList);
//        return mav;
        return newsService.getAllNews();
    }

    // 카테고리별 뉴스 출력
    @GetMapping("/category")
    public ModelAndView getNewsByCategory(@RequestParam int category) {
        ModelAndView mav = new ModelAndView("cardnewsList");
        mav.addObject("newsList", newsService.getNewsByCategory(category));
        return mav;
    }

    // 카드뉴스 상세
    @GetMapping("/news_id")
    @ResponseBody
    public Map<String, Object> getNewsDetail(@RequestParam int newsId) {
        News news = newsService.getNewsById(newsId);
        List<String> summarySentences = cardNewsService.getSummarySentences(newsId);

        // JSON 반환을 위한 Map 생성
        Map<String, Object> response = new HashMap<>();
        response.put("newsId", news.getNewsId());
        response.put("sourceBc", news.getSourceBc());
        response.put("sourceUrl", news.getSourceUrl());
        response.put("title", news.getTitle());
        response.put("category", news.getCategory());
        response.put("cardsPath", news.getCardsPath());
        response.put("summarySentences", summarySentences);
        response.put("newsAt", news.getNewsAt());
        response.put("createdAt", news.getCreatedAt());
        response.put("updatedAt", news.getUpdatedAt());

        return response;
    }
}

