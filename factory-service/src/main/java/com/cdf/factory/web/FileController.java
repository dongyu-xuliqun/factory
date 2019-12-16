package com.cdf.factory.web;

import com.aliyun.oss.model.OSSObject;
import com.cdf.factory.common.enums.ResponseDTO;
import com.cdf.factory.service.file.FileService;
import com.cdf.factory.utils.RestResponseUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload/oss")
    public ResponseDTO<String> upload2Oss(MultipartFile file) {
        if (file == null) {
            return RestResponseUtil.response(HttpStatus.BAD_REQUEST,
                    this.messageService.getMessage("request.file.upload.not.existed"), StringUtils.EMPTY);
        }
        if (file.getSize() > properties.getFileConf().getFileMaxSize()) {
            return RestResponseUtil.response(HttpStatus.BAD_REQUEST,
                    this.messageService.getMessage("request.file.too.large"), StringUtils.EMPTY);
        }
        String url = this.fileService.upload2OSS(file);
        if (StringUtils.isBlank(url)) {
            return RestResponseUtil.response(HttpStatus.BAD_REQUEST,
                    this.messageService.getMessage("request.file.upload.failed"), StringUtils.EMPTY);
        }
        return RestResponseUtil.ok(this.messageService.getMessage("request.file.upload.success"), url);
    }

    @GetMapping("/download/oss/{baseDir:\\w+}/{dir:\\w+}/{fileName:\\w+}.{fileType:\\w+}")
    public void downloadFromOss(
            @PathVariable("baseDir") String baseDir,
            @PathVariable("dir") String dir,
            @PathVariable("fileName") String fileName,
            @PathVariable("fileType") String fileType,
            HttpServletResponse response) {
        try (OSSObject ossObject = this.fileService.downloadFromOSS(baseDir + "/" + dir + "/" + fileName + "." + fileType);
             InputStream inputStream = ossObject.getObjectContent();
             OutputStream outputStream = response.getOutputStream();) {
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + "." + fileType, "utf-8"));
            byte[] bytes = new byte[5 * 1024 * 1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }
}

