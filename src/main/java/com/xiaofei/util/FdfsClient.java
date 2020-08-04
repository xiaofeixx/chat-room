package com.xiaofei.util;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author xiaofei
 * @Classname FdfsClient
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Component
public class FdfsClient {

    private final Logger log = LoggerFactory.getLogger(FdfsClient.class);

    @Autowired
    private FastFileStorageClient storageClient;
    //注入缩略图
    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Autowired
    private FdfsWebServer fdfsWebServer;

    /***
     * * @param file
     *@return {@link String}
     *@throws
     *@description 上传文件，这里主要是图片
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.
                uploadFile(file.getInputStream(), file.getSize(), FilenameUtils
                        .getExtension(file.getOriginalFilename()), null);
        return getResAccessUrl(storePath);
    }

    /***
     * * @param file
     *@return {@link String}
     *@throws
     *@description 上传缩略图
     */
    public String upliadAndTumb(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(),file.getSize(),FilenameUtils
                .getExtension(file.getOriginalFilename()), null);
        return getResAccessUrl(storePath);
    }

    /***
     * * @param file
     *@return {@link String}
     *@throws
     *@description 文件方式上传
     */
    public String uploadFile(File file) throws IOException {

        StorePath storePath = storageClient.uploadFile(new FileInputStream(file), FileUtils.sizeOf(file),FilenameUtils
        .getExtension(file.getName()),null);
        return getResAccessUrl(storePath);
    }


    /***
     * * @param fileUrl
     *@return {@link }
     *@throws
     *@description 删除图片
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            log.warn(e.getMessage());
        }
    }

    /***
     * * @param storePath
     *@return {@link String}
     *@throws
     *@description 返回文件完整URL
     */
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        return fileUrl;
    }

}
