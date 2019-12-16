package com.cdf.factory.service.file;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.cdf.factory.config.BossProperties;
import com.cdf.factory.utils.FileUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Random;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private OSS ossClient;

    @Autowired
    private BossProperties bossProperties;  


    @Override
    public String upload2OSS(MultipartFile file) {
        String url = StringUtils.EMPTY;
        BossProperties.Oss oss = bossProperties.getFileConf().getOss();
        try (InputStream inputStream = file.getInputStream()) {
            String originalFilename = file.getOriginalFilename();
            String subString = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            Random random = new Random();
            String fileName = random.nextInt(10000) + System.currentTimeMillis() + subString;
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(FileUtil.getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            String filePath = oss.getFileDir() + "/" + FileUtil.getFilePathByFileType(subString) + "/" + fileName;
            //上传文件
            PutObjectResult putResult = ossClient.putObject(oss.getBucketName(), filePath, inputStream, objectMetadata);
            url = filePath;
            return url;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return url;
    }

    @Override
    public OSSObject downloadFromOSS(String fileName) {
        BossProperties.Oss oss = bossProperties.getFileConf().getOss();
        //String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        return ossClient.getObject(oss.getBucketName(), fileName);
    }
}
