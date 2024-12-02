package com.AtaGlance.repository.mybatis;

import com.AtaGlance.dto.TextProc;
import com.AtaGlance.dto.VideoProc;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoProcMapper {
    VideoProc getVideoProcByNewsId(int newsId);
}
