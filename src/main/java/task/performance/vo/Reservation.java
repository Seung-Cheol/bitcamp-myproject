package task.performance.vo;

import java.sql.Date;

public class Reservation {
  private int no;
  private int performance_no;

  public void setNo(int no) {
    this.no = no;
  }

  public void setPerformance_no(int performance_no) {
    this.performance_no = performance_no;
  }

  public void setMember_no(int member_no) {
    this.member_no = member_no;
  }

  public void setReserved_at(Date reserved_at) {
    this.reserved_at = reserved_at;
  }

  private int member_no;
  private Date reserved_at;

  public int getNo() {
    return no;
  }

  public int getPerformance_no() {
    return performance_no;
  }

  public int getMember_no() {
    return member_no;
  }

  public Date getReserved_at() {
    return reserved_at;
  }
}
