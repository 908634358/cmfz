package com.baizhi.util.service;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service // 结合spring使用时需要注入 我使用@service注入
/**
 * 文件上传的工具类
 *
 * */
public class UploadUtil {
    /**
     * 文件上传的方法
     */
    public Map<String, Object> uploadFile(HttpSession httpSession, MultipartFile multipartFile, String realPath) throws IOException, EncoderException {
        Map<String, Object> map = new HashMap<>();
        if (realPath != null) {
            //获取文件的路径
            String getRealPath = httpSession.getServletContext().getRealPath(realPath);
            if (multipartFile.getOriginalFilename() != null) {
                //文件上传
                File file = new File(getRealPath, multipartFile.getOriginalFilename());
                multipartFile.transferTo(file);
                //获取文件的大小
                BigDecimal size = new BigDecimal(multipartFile.getSize());
                // 设置除数 转换成相同类型
                BigDecimal dom = new BigDecimal(1024);
                // 计算文件的大小 换算成MB
                BigDecimal bigDecimal = size.divide(dom).divide(dom).setScale(2, BigDecimal.ROUND_HALF_UP);
                map.put("bigDecimal", bigDecimal);
                map.put("OriginalFilename", multipartFile.getOriginalFilename());
                if (file != null) {
                    //获取文件时常
                    Encoder encoder = new Encoder();
                    long duration = encoder.getInfo(file).getDuration();
                    String Duration = duration / 1000 / 60 + ":" + duration / 1000 % 60;
                    map.put("Duration", Duration);
                }
            }

        }

        return map;
    }
}
