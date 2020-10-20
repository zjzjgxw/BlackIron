package com.gxw.store.project.common.utils.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.net.URL;
import java.util.Date;

public class AliyunOssHelper implements InitializingBean, DisposableBean {

    private final String endpoint = "oss-cn-shanghai.aliyuncs.com";
    private final String accessKeyId = "LTAI4Fz4bE9QeVwFys4eHpjd";
    private final String accessKeySecret = "qyzZcj1JDaRjrerOkTvXxeuFNR6iwW";
    private final String bucketName = "gxw-cloud";

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