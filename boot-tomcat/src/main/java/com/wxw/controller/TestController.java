package com.wxw.controller;

import com.wxw.config.EnvironmentConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ Author ：wxw.
 * @ Date ： 10:45 2020/8/4
 * @ Description：控制层测试接口
 * @ Version:   v_0.0.1
 */
@RestController
public class TestController {

    private static  Logger logger = LoggerFactory.getLogger(TestController.class);
    private static String fileBasePath = EnvironmentConfig.getProperty("file.category", "/data/upload");

    @GetMapping("getSuccess")
    public String testGet(){
        return "恭喜你, 测试通过!\n 文件路径："+fileBasePath;
    }

    @GetMapping("getFile")
    public void testDownFile(HttpServletResponse response){
        try {
            //File file = new File(fileBasePath);
            File file = new File("wxw.txt");
            if (!file.exists()){
                file.createNewFile();
            }
            String absolutePath = file.getAbsolutePath();
            /**
             * 可以在tomcat或 nginx 指定工作目录
             * 文件绝对路径：E:\xkw2020_doc\upload\wxw.txt
             */
            logger.info("文件绝对路径：{}",absolutePath);
            OutputStream outputStream = new FileOutputStream(absolutePath);

            // 设置信息给客户端不解析
            response.setContentType("application/force-download"); //应用程序强制下载
            response.addHeader("Content-type", "application/octet-stream"); // .*（ 二进制流，不知道下载文件类型）
            response.addHeader("Content-Disposition", "attachment;filename=" + file.getName()); // 客户端的弹出对话框，对应的文件名

            outputStream.flush();
            outputStream.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
