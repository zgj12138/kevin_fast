package io.kevin.modules.oss.cloud;

import com.aliyun.oss.common.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * 云储存(支持七牛、阿里云、腾讯和又拍云)
 * @author ZGJ
 * @date 2017/8/3 21:55
 **/
public abstract class CloudStorageService {
    /**
     * 云存储配置信息
     */
    CloudStorageConfig config;

    /**
     * 文件路径
     * @param prefix 前缀
     * @return 返回文件上传路径
     */
    public String getPath(String prefix) {
        //生成路径
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateUtils.formatDate(new Date(), "yyyyMMdd") + "/" + uuid;

        if(StringUtils.isNotBlank(prefix)) {
            path = prefix + "/" + path;
        }
        return path;
    }

    /**
     * 文件上传
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     * @param data 文件字节数组
     * @return 返回http地址
     */
    public abstract String upload(byte[] data);

    /**
     * 文件上传
     * @param inputStream 字节流
     * @param path 文件路径，包含文件夹
     * @return 返回htttp地址
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * 文件上传
     * @param inputStream 字节码
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream);
}
