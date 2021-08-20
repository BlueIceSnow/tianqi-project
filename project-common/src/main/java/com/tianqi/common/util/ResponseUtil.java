package com.tianqi.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/19 16:27
 * @Description:
 */
public class ResponseUtil {

    public static void resJson(HttpServletResponse response, Object obj) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        try {
            final ObjectMapper mapper = SpringUtil.getBean(ObjectMapper.class);
            response.getWriter().write(mapper.writeValueAsString(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
