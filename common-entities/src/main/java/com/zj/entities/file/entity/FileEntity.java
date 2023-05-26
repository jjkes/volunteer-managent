package com.zj.entities.file.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/11 19:59
 */
@Data
public class FileEntity {
    /**
    * 文件的id，uuid
    */
    private String id;

    /**
    * 文件名称
    */
    private String fileName;
    /**
    * 相对路径
    */
    private String filePath;
    /**
    * 文件的上传时间
    */
    private LocalDateTime uploadTime;
    /**
    * 上传人的id
    */
    private String userId;
    /**
    * 上传路径
    */
    private String remark;
    /**
    * 是否删除
    */
    private Integer isDeleted;

    public static Builder getBuilder(){
        return new Builder();
    }
    public final static class Builder{
        /**
         * 文件的id，uuid
         */
        private String id;
        /**
         * 文件名称
         */
        private String fileName;
        /**
         * 相对路径
         */
        private String filePath;
        /**
         * 文件的上传时间
         */
        private LocalDateTime uploadTime;
        /**
         * 上传人的id
         */
        private String userId;
        /**
         * 上传路径
         */
        private String remark;
        /**
         * 是否删除
         */
        private Integer isDeleted;

        public Builder setFileName(String fileName){
            this.fileName=fileName;
            return this;
        }

        public Builder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setRemark(String remark) {
            this.remark = remark;
            return this;
        }
        public FileEntity build(){
            FileEntity fileEntity = new FileEntity();
            fileEntity.setId(UUID.randomUUID().toString());
            fileEntity.setFileName(this.fileName);
            fileEntity.setFilePath(this.filePath);
            fileEntity.setRemark(this.remark);
            fileEntity.setUserId(this.userId);
            fileEntity.setUploadTime(LocalDateTime.now());
            fileEntity.setIsDeleted(0);
            return fileEntity;
        }
    }
}
