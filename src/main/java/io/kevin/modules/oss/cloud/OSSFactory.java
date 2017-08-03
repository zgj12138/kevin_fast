package io.kevin.modules.oss.cloud;

import io.kevin.common.utils.ConfigConstant;
import io.kevin.common.utils.Constant;
import io.kevin.common.utils.SpringContextUtils;
import io.kevin.modules.sys.service.SysConfigService;

/**
 * 文件上传工厂类
 * @author ZGJ
 * @date 2017/8/3 22:28
 **/
public class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService bulid() {
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }
        return null;

    }
}
