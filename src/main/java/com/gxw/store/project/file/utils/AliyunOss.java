package com.gxw.store.project.file.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.gxw.store.project.common.utils.DateUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * 阿里云oss
 */
@Component
public class AliyunOss implements InitializingBean, DisposableBean {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    private String baseDir = "";

    private static OSS ossClient = null;

    private static final Long  EXPIRATION_TIME = 1000L *3600; // url 有效期，默认1小时

    @Override
    public void afterPropertiesSet() throws Exception {
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //判断bucketName 是否存在.
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.shutdown();
            throw new Exception("bucket does not exist");
        }
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    /**
     * 上传文件，默认访问权限为私有
     *
     * @param file
     * @return
     */
    public String uploadFile(File file) {
        return uploadFile(file, true);
    }

    /**
     * 上传文件
     *
     * @param file
     * @return 返回文件key
     */
    public String uploadFile(File file, boolean isPrivate) {
        String key = baseDir + "/" + DateUtils.datePath() + "/" + file.getName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        ObjectMetadata metadata = new ObjectMetadata();
        //设置文件类型
        String extension = FilenameUtils.getExtension(file.getName());
        if (extension != null ) {
            String contentType = getContentType(extension);
            if(contentType != null){
                metadata.setContentType(contentType);
            }
        }
        //设置访问权限，一般建议private ,如果是图片可以考虑使用PublicRead
        if (isPrivate) {
            metadata.setObjectAcl(CannedAccessControlList.Private);
        } else {
            metadata.setObjectAcl(CannedAccessControlList.PublicRead);
        }
        putObjectRequest.setMetadata(metadata);
        ossClient.putObject(putObjectRequest);
        return key;
    }


    private String getContentType(String extension) {
        if (extension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (extension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (extension.equalsIgnoreCase("jpeg") ||
                extension.equalsIgnoreCase("jpg") ||
                extension.equalsIgnoreCase("png")) {
            return "image/jpg";
        }
        if (extension.equalsIgnoreCase("xls")) {
            return "application/vnd.ms-excel";
        }
        if (extension.equalsIgnoreCase("xlsx")) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
        if (extension.equalsIgnoreCase("mp3")) {
            return "audio/mp3";
        }
        if (extension.equalsIgnoreCase("mp4")) {
            return "video/mpeg4";
        }
        if (extension.equalsIgnoreCase("wav")) {
            return "audio/wav";
        }
        if (extension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (extension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (extension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (extension.equalsIgnoreCase("pptx") ||
                extension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (extension.equalsIgnoreCase("docx") ||
                extension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (extension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return null;
    }

    /**
     * 返回文件路径
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        Date expiration = new Date(new Date().getTime() + EXPIRATION_TIME);
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    public String getSnapshotUrl(String key){
        String style = "video/snapshot,t_1000,f_png,w_800,h_600";
        Date expiration = new Date(new Date().getTime() + EXPIRATION_TIME);
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName,key);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL url = ossClient.generatePresignedUrl(req);
        if (url != null) {
            return url.toString();
        }
        return null;
    }


    @Override
    public void destroy() throws Exception {
        ossClient.shutdown();
    }


}
