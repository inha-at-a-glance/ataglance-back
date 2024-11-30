package com.AtaGlance.controller;

import com.AtaGlance.dto.News;
import com.AtaGlance.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
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
    public ModelAndView doNewsRegist(News news) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("newsList");
        // 전달할 객체
        //News news1 =
        newsService.saveNews(news);
        mav.addObject("news", news);
        return mav;
    }
}
