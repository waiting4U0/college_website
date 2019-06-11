package cn.edu.swpu.info.college_website.domain;


import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

public class User extends BaseDomain {


  private String name;
  private String code;
  private String password;
  private String telephoneNumber;
  private String email;
  private String sex;
  private String role;






  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }




}
