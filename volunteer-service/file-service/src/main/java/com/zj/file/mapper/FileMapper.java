package com.zj.file.mapper;

import com.zj.file.entity.FileEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/11 19:55
 */
@Mapper
public interface FileMapper {
    int saveFile(FileEntity fileEntity);

    int deleteFile(String id);

    FileEntity findById(String id);

    List<FileEntity> findAll(FileEntity fileEntity);
}
