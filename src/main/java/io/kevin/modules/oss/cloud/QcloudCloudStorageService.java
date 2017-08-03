package io.kevin.modules.oss.cloud;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.sign.Credentials;

import java.io.InputStream;

/**
 * 腾讯也存储
 * @author ZGJ
 * @date 2017/8/3 22:40
 **/
public class QcloudCloudStorageService extends CloudStorageService {
    private COSClient client;

    public QcloudCloudStorageService(CloudStorageConfig config) {
        this.config = config;

        //初始化
        init();
    }

    private void init() {
        Credentials credentials = new Credentials(config.getQcloudAppId(), config.getQcloudSecretId(),
                config.getQcloudSecretKey());
        //初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        //设置bucket所在的区域，华南：gz 华北： tj 华东：sh
        clientConfig.setRegion(config.getQcloudRegion());

        client = new COSClient(clientConfig, credentials);
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
