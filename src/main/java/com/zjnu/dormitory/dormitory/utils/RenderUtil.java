package com.zjnu.dormitory.dormitory.utils;

import com.alibaba.fastjson.JSON;
import com.zjnu.dormitory.dormitory.handler.DorException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RenderUtil {
    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new DorException(4002,e.getMessage());
        }
    }
}
