package org.zzzzzz.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.zzzzzz.exception.BusinessException;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Value("${spring.servlet.multipart.location}")
    String fileCachePath;

    @Value("${personal.info}")
    String fileName;

    @RequestMapping(path = "/upload-page", method = RequestMethod.GET)
    public String getUploadPage() {
        return "/upload";
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String getErrorPage(HttpServletRequest request, Model model) {
        model.addAttribute("exceptionName", request.getParameter("exceptionName"));
        return "/error-site/error";
    }

    @RequestMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file,
          HttpServletRequest request, Model model) {

        // 获取上传文件的类型（后缀）
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        // 给上传的文件进行重新命名：UUID + 个人信息 + 标明类型
        String targetFileName = System.currentTimeMillis() + "-" + fileName + fileType;

        // 新的文件存放的目录（本地）
        String newFile = fileCachePath + File.separator + targetFileName;

        File targetFile = new File(newFile);
        targetFile.getParentFile().mkdirs();

        try {
            file.transferTo(targetFile);
//            throw new Exception();
        } catch (Exception e) {
            throw new BusinessException(e.toString());
        }

        model.addAttribute("fileName", targetFileName);

        return "success";
    }

}
