package com.AtaGlance.controller;

import com.AtaGlance.dto.News;
import com.AtaGlance.service.CardNewsService;
import com.AtaGlance.service.NewsService;
import com.AtaGlance.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
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
    public ModelAndView getAllNews() {
        List<News> newsList = newsService.getAllNews();
        ModelAndView mav = new ModelAndView("cardnewsList");
        mav.addObject("newsList", newsList);
        return mav;
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
    public ModelAndView getNewsDetail(@RequestParam int newsId) {
        News news = newsService.getNewsById(newsId);
        String imageUrl = s3Service.getPresignedUrl(news.getVideoFilePath());
        List<String> summarySentences = cardNewsService.getSummarySentences(newsId);

        ModelAndView mav = new ModelAndView("cardnews");
        mav.addObject("news", news);
        mav.addObject("imageUrl", imageUrl);
        mav.addObject("summarySentences", summarySentences);
        return mav;
    }
}

