package com.cdf.factory.service.file;

import com.aliyun.oss.model.OSSObject;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
   
    String upload2OSS(MultipartFile file);

    OSSObject downloadFromOSS(String fileName);
}
