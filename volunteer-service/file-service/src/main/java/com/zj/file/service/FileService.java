package com.zj.file.service;

import com.zj.common.config.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/11 19:47
 */

public interface FileService {

    /**
     * <h1>下载文件</h1>
     * @param fileId 文件的uuid
     * @author 赵健
     * @date 2023/3/11 21:42
     */
    ResponseEntity downLoadFile(String fileId);

    /**
     * <h1>上传文件</h1>
     * @author 赵健
     * @date 2023/3/11 21:45
     */
    Result<String> uploadFile(MultipartFile file,String fileName,String remark);
}
