package io.kevin.modules.oss.cloud;

import com.aliyun.oss.OSSClient;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.InputStream;

/**
 * 七牛云存储
 * @author ZGJ
 * @date 2017/8/3 22:39
 **/
public class QiniuCloudStorageService extends CloudStorageService {
    private UploadManager uploadManager;
    private String token;
    
    public QiniuCloudStorageService(CloudStorageConfig config) {
        this.config = config;
        
        //初始化
        init();
    }

    private void init() {
        uploadManager  = new UploadManager(new Configuration(Zone.autoZone()));
        token = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey())
                .uploadToken(config.getQcloudBucketName());
    }

    @Override
    public String upload(byte[] data, String path) {
        return null;
    }

    @Override
    public String upload(byte[] data) {
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        return null;
    }

    @Override
    public String upload(InputStream inputStream) {
        return null;
    }
}
