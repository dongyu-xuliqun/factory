package com.cdf.factory.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "com.rocky.fintech.boss")
@Getter
@Setter
public class BossProperties {

    private Constant constant = new Constant();

    private Map<String, String> extendRequestParameterMap = new HashMap<>();

    private FileConfig fileConf = new FileConfig();

    @Getter
    @Setter
    public static class Constant {
        private Integer diff;
        private int timeDifference;
        private int week;
    }

    @Getter
    @Setter
    public static class FileConfig {
        private Oss oss = new Oss();
        private Nas nas = new Nas();
        //private String baseDir = "/tmp";
        //文件上传默认最大10M
        private int fileMaxSize = 1024 * 1024 * 10;

    }

    @Getter
    @Setter
    public static class Oss {
        private String bucketName = StringUtils.EMPTY;
        private String fileDir = StringUtils.EMPTY;
    }

    @Getter
    @Setter
    public static class Nas {
        private String fileDir = StringUtils.EMPTY;
        private String fileBackupDir = StringUtils.EMPTY;
        private boolean fileBackupOpen = false;
    }
}
