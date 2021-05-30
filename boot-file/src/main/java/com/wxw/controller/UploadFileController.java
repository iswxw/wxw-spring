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
     * localhost:8081/file/upload/one-file-1
     * @param file 上传文件会自动绑定到MultipartFile中
     */
    @PostMapping("/one-file-1")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传之前，请先选择文件";
        }
        // 上传文件名
        String fileName = file.getOriginalFilename();
        // 上传文件路径
        String filePath = System.getProperty("user.dir") + "/document/upload/";
        File dest = new File(filePath + File.separator + fileName);
        try {
            // 将上传文件保存到一个目标文件当中
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return "上传失败！";
    }

}
