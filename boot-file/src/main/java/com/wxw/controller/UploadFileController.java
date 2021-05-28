package com.wxw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/5/28
 */
@Slf4j
@RestController
@RequestMapping("/file/upload")
public class UploadFileController {


    /**
     * 单文件上传
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传之前，请先选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/Users/mac/IdeaProjects/wxw/Itcast-springboot/boot-file/document/upload/";
        File dest = new File(filePath + fileName);
        try {
            // transferTo 传入参数 定义为绝对路径
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return "上传失败！";
    }

}
