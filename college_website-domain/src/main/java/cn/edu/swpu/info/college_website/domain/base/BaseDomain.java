package cn.edu.swpu.info.college_website.domain.base;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class BaseDomain extends BaseQuery {
    private static final long serialVersionUID = 1L;
    private Long id;// 编号

    private Date createDate;// 创建日期

    private Date modifyDate;// 最后修改日期

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JSONField(serialize = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JSONField(serialize = false)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }


}
