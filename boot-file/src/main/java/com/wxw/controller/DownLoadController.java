package com.wxw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/5/28
 */
@Slf4j
@RestController
@RequestMapping("/file/download")
public class DownLoadController {

    /**
     * 二进制的文件下载
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/one-file-2")
    public ResponseEntity<byte[]> downLoad_2(HttpServletRequest request,
                                             @RequestHeader("User-Agent") String userAgent) throws IOException {
        // 下载文件路径
        String path = request.getServletContext().getRealPath("/upload/");
        log.info("path = {}", path);
        String filename = "ios-crash.zip";
        String filePath = System.getProperty("user.dir") + "/boot-file/document/upload/";
        File file = new File(filePath + File.separator + filename);
        log.info("file is exist = {}",file.exists());
        // ok表示Http协议中的状态 200
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        // 内容长度
        builder.contentLength(file.length());
        // application/octet-stream ： 二进制流数据（最常见的文件下载）。
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        // 使用URLDecoder.decode对文件名进行解码
        filename = URLEncoder.encode(filename, "UTF-8");
        // 设置实际的响应文件名，告诉浏览器文件要用于【下载】、【保存】attachment 以附件形式
        // 不同的浏览器，处理方式不同，要根据浏览器版本进行区别判断
        if (userAgent.indexOf("MSIE") > 0) {
            // 如果是IE，只需要用UTF-8字符集进行URL编码即可
            builder.header("Content-Disposition", "attachment; filename=" + filename);
        } else {
            // 而FireFox、Chrome等浏览器，则需要说明编码的字符集
            // 注意filename后面有个*号，在UTF-8后面有两个单引号！
            builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + filename);
        }
        return builder.body(FileCopyUtils.copyToByteArray(file));
    }

    /**
     * 从服务器下载单个文件
     * http://localhost:8081/file/download/one-file-1
     *
     * @param response
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/one-file-1")
    public void downLoad_1(HttpServletResponse response) throws UnsupportedEncodingException {
        String filename = "ios-crash.zip";
        String filePath = "/Users/mac/IdeaProjects/wxw/Itcast-springboot/boot-file/document/upload/";
        File file = new File(filePath + File.separator + filename);
        if (file.exists()) {
            response.setContentType("application/octet-stream");
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "utf8"));
            byte[] buffer = new byte[1024];
            //输出流
            OutputStream os = null;
            try (FileInputStream fis = new FileInputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(fis);) {
                os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
