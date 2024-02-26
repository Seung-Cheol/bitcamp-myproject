package task.performance.vo;

import java.sql.Date;
import java.util.List;

public class Reservation {
  private int no;
  private int performance_detail_no;
  private int member_no;
  private Date reserved_at;
  private List<ReservationSeat> reservation_seats;

  public void setNo(int no) {
    this.no = no;
  }

  public List<ReservationSeat> getReservation_seats() {
    return reservation_seats;
  }

  public void setReservation_seats(List<ReservationSeat> reservation_seats) {
    this.reservation_seats = reservation_seats;
  }

  public void setPerformance_detail_no(int performance_detail_no) {
    this.performance_detail_no = performance_detail_no;
  }

  public void setMember_no(int member_no) {
    this.member_no = member_no;
  }

  public void setReserved_at(Date reserved_at) {
    this.reserved_at = reserved_at;
  }

  public int getNo() {
    return no;
  }

  public int getPerformance_detail_no() {
    return performance_detail_no;
  }

  public int getMember_no() {
    return member_no;
  }

  public Date getReserved_at() {
    return reserved_at;
  }
}
