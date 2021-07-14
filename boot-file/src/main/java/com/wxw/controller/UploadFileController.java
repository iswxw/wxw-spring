package com.wxw.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

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
     * curl --form file=@/Users/mac/Documents/document/docs/ttt_20210714_110342 --form press=OK localhost:8082/file/upload/one-file-1
     * curl -X POST localhost:8082/file/upload/one-file-1
     *
     * @param file 上传文件会自动绑定到MultipartFile中
     */
    @SneakyThrows
    @PostMapping("/one-file-1")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传之前，请先选择文件";
        }
        // 上传文件名
        String fileName = file.getOriginalFilename();
        // 上传文件路径
        String filePath = System.getProperty("user.dir") + "/boot-file/document/upload/";
        File dest = new File(filePath + File.separator + fileName);
        if (!dest.exists()) {
            FileUtils.forceMkdirParent(dest);
        }
        try {
            // 将上传文件保存到一个目标文件当中
            file.transferTo(dest);
        } catch (IOException e) {
            log.error(e.toString(), e);
            return "上传失败！";
        }
        log.info("dest = {}",dest.getAbsolutePath());
        return "上传成功";
    }

    @PostMapping("/one-file-2")
    public ResponseEntity<String> upload(InputStream inputStream) throws IOException {
        log.info(" 开始接收 file = {}",inputStream);
        String filePath = "./a.gzip";
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        IOUtils.copy(inputStream,fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        byte[] header = new byte[]{};
        modifyFileHeader(header,filePath);

        return ResponseEntity.ok("成功");
    }


    public static void modifyFileHeader(byte[] header, String filePath) {
        if (header.length == 2) {
            try (RandomAccessFile src = new RandomAccessFile(filePath, "rw")) {
                int srcLength = (int)src.length();
                // 略过前两个字节
                src.skipBytes(2);
                byte[] buff = new byte[srcLength - 2];
                // 读取除前两个字节之后的字节
                src.read(buff);
                src.seek(0);
                src.write(header);
                src.seek(header.length);
                src.write(buff);
            } catch (Exception e) {
                log.error("修改文件{}的前两个字节失败!", filePath);
            }
        }
    }
}
