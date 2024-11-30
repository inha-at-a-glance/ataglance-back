package com.AtaGlance.repository.mybatis;

import com.AtaGlance.dto.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewsMapper {
    void saveNews(News news);
    List<News> getAllNews();

    News getNewsById(int newsId); // Fetch a single news by its ID

    List<News> getNewsByCategory(int category); // Fetch news by category

    void updateNews(@Param("id") int id, @Param("updateParam") News updateParam);
    void deleteNews(int newsId);
}
