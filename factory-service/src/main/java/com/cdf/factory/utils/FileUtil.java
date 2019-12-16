package com.cdf.factory.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.StringTokenizer;

@Slf4j
public class FileUtil {

    private static String BaseDir = "e:/";

    public static String createFile(String filePath, String fileName, String fileContent, boolean needEncrypted) {
        FileWriter fw = null;
        try {
            String filePathAndName = filePath + File.separator + fileName;
            String fileFullPathAndName = BaseDir + File.separator + filePathAndName;
            File file = new File(fileFullPathAndName);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            if (needEncrypted) {
                fileContent = DESUtil.encrypt(fileContent);
            }
            fw.write(fileContent);
            fw.flush();
            return filePathAndName;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return StringUtils.EMPTY;
    }

  
  

    public static int copyFile(String oldPathFile, String newPathFile) {
        return copyFile(new File(oldPathFile), new File(newPathFile));
    }

    public static int copyFile(File oldFile, File newFile) {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            File fileToDir = newFile.getParentFile();// 上传文件夹
            if (!fileToDir.exists() && !fileToDir.isDirectory()) {
                fileToDir.mkdirs();
            }
            int byteSum = 0;
            int byteRead = 0;
            if (oldFile.exists()) { // 文件存在时
                fr = new FileReader(oldFile); // 读入原文件
                fw = new FileWriter(newFile);
                char[] buffer = new char[1444];
                while ((byteRead = fr.read(buffer)) != -1) {
                    byteSum += byteRead; // 字节数 文件大小
                    fw.write(buffer, 0, byteRead);
                }
                fw.flush();
            }
            return 1;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return 0;
    }

  
    public static File getFile(String filePathAndName) {
        try {
            File file = new File(BaseDir + File.separator + filePathAndName);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String readTxt(String filePathAndName) throws IOException {
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        try {
            in = new BufferedReader(new FileReader(filePathAndName));
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    /**
     * 新建目录
     *
     * @param folderPath 目录
     * @return 返回目录创建后的路径
     */
    public static String createFolder(String folderPath) {
        String txt = folderPath;
        try {
            File myFilePath = new File(txt);
            if (!myFilePath.exists()) {
                myFilePath.mkdirs();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return txt;
    }

    /**
     * 多级目录创建
     *
     * @param folderPath 准备要在本级目录下创建新目录的目录路径 例如 c:myf
     * @param paths      无限级目录参数，各级目录以单数线区分 例如 a|b|c
     * @return 返回创建文件后的路径 例如 c:myfac
     */
    public static String createFolders(String folderPath, String paths) {
        String txts = folderPath;
        try {
            String txt;
            StringTokenizer st = new StringTokenizer(paths, "|");
            while (st.hasMoreTokens()) {
                txt = st.nextToken().trim();
                if (txts.endsWith(File.separator)) {
                    txts = createFolder(txts + txt);
                } else {
                    txts = createFolder(txts + File.separator + txt);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return txts;
    }

    public static String getFilePathByFileType(String fileType) {
        StringBuilder filePath = new StringBuilder();
        switch (fileType) {
            case ".pdf":
                filePath.append("pdf");
            case ".txt":
                filePath.append("txt");
            case ".xls":
            case ".xlsx":
                filePath.append("excel");
            case ".json":
                filePath.append("json");
            default:
                filePath.append("common");
        }
        return filePath.toString();
    }

    public static String getContentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase(".jpeg") ||
                filenameExtension.equalsIgnoreCase(".jpg") ||
                filenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase(".pptx") ||
                filenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase(".docx") ||
                filenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }
}

class FileBackupThread extends Thread {
    String fromFile;
    String toFile;

    public FileBackupThread(String fromFile, String toFile) {
        this.fromFile = fromFile;
        this.toFile = toFile;
    }

    @Override
    public void run() {
//        if (FileSystemConfig.backOpen) {
//            FileUtil.copyFile(fromFile, toFile);
//        }
    }
}
