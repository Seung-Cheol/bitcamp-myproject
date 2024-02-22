package task.member.vo;

import java.sql.Date;

public class Member {

  private int no;
  private String email;
  private String nickname;
  private String pwd;
  private int auth;
  private Date createdAt;
  private String grade;

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public int getNo() {
    return no;
  }

  public String getEmail() {
    return email;
  }

  public String getNickname() {
    return nickname;
  }

  public String getPwd() {
    return pwd;
  }

  public int getAuth() {
    return auth;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public void setAuth(int auth) {
    this.auth = auth;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }



}
