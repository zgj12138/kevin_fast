package io.kevin.modules.oss.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 * @author ZGJ
 * @date 2017/8/3 21:38
 **/
public class SysOssEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID
    private Long id;
    //URL地址
    private String url;
    //创建时间
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
