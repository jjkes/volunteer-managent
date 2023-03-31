package com.zj.file.controller;

import com.zj.common.config.Result;
import com.zj.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;

/**
 * @author 赵健
 * @version 1.0
 * @description: xml相关的上传或下载文件
 * @date 2023/3/10 14:59
 */
@RestController
@RequestMapping(value = "/xml/")
@RequiredArgsConstructor
public class UploadXMlController {
    private final FileService fileService;
    @PostMapping(value = "uploadXml")
    public String uploadXml(MultipartFile file){
        if (file.getName().toUpperCase(Locale.ROOT).endsWith(".XML")) {

        }
        return null;
    }
    @GetMapping("download/{fileId}")
    public ResponseEntity<byte[]> downloadXmlFile(@PathVariable(value = "fileId")String fileId){
        return fileService.downLoadFile(fileId);
    }
    @PostMapping("upload")
    public Result uploadFile(MultipartFile file,String fileName,String remark){
        return fileService.uploadFile(file,fileName,remark);
    }
}
