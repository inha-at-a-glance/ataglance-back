package com.AtaGlance.repository.mybatis;

import com.AtaGlance.dto.TextProc;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TextProcMapper {
    TextProc getTextProcByNewsId(int newsId);
}