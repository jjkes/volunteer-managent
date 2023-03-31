package com.zj.file.service;


import com.zj.common.config.Result;
import com.zj.common.config.SysProperties;
import com.zj.common.constant.StateEnum;
import com.zj.common.exception.MyException;
import com.zj.common.utils.FileUtil;
import com.zj.file.entity.FileEntity;
import com.zj.file.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/11 19:47
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileMapper fileMapper;
    private final SysProperties sysProperties;
    /**
     * <h1>下载文件</h1>
     * @param fileId 文件的uuid
     * @author 赵健
     * @date 2023/3/11 21:42
     */
    @Override
    public ResponseEntity<byte[]> downLoadFile(String fileId) {
        if(StringUtils.isNotBlank(fileId)){
            FileEntity fileEntity = fileMapper.findById(fileId);
            if(fileEntity != null){
                String absoluteFilePath = sysProperties.getBaseFilePath()+File.separator+fileEntity.getFilePath();
                try {
                    byte[] fileByte = FileUtil.getFileByteByAbsolutePath(absoluteFilePath);
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+new String(fileEntity.getFileName().getBytes(),"ISO8859-1"));
                    httpHeaders.set(HttpHeaders.CONTENT_TYPE,"multipart/form-data");
                    ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(fileByte,httpHeaders, HttpStatus.OK);
                    return responseEntity;
                } catch (MyException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
    }

    /**
     * <h1>上传文件</h1>
     *
     * @param file
     * @author 赵健
     * @date 2023/3/11 21:45
     */
    @Override
    public Result<String> uploadFile(MultipartFile file,String fileName,String remark) {
        Result<String> result = new Result<String>();
        try {
            byte[] bytes = file.getBytes();
            String name = file.getOriginalFilename();
            String path = FileUtil.saveFile(bytes, file.getOriginalFilename(), sysProperties.getBaseFilePath());
            FileEntity fileEntity = FileEntity.getBuilder()
                    .setFileName(fileName == null?file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf(".")):fileName)
                    .setFilePath(path)
                    .setUserId("sdafsdf")
                    .setRemark(remark).build();
            int i = fileMapper.saveFile(fileEntity);
            if(i>0){
                result.setResultEnum(StateEnum.SUCCESS);
            }else{
                result.setResultEnum(StateEnum.FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setResultEnum(StateEnum.FILE_SAVE_ERROR);
        }
        return result;
    }
}
