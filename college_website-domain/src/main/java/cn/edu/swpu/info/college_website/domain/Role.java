package cn.edu.swpu.info.college_website.domain;


import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

public class Role extends BaseDomain {


  private String name;
  private String description;
  private java.sql.Timestamp createDate;
  private java.sql.Timestamp modifyDate;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public java.sql.Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(java.sql.Timestamp createDate) {
    this.createDate = createDate;
  }


  public java.sql.Timestamp getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(java.sql.Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }

}
