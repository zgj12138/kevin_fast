package io.kevin.modules.oss.cloud;

import com.aliyun.oss.OSSClient;

import java.io.InputStream;

/**
 * 阿里云存储
 * @author ZGJ
 * @date 2017/8/3 21:54
 **/
public class AliyunCloudStorageService extends CloudStorageService {

    private OSSClient client;
    public AliyunCloudStorageService(CloudStorageConfig config) {
        this.config = config;
        //初始化
        init();
    }

    private void init() {
        client = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
                config.getAliyunAccessKeySecret());
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
