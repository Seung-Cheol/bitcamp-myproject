package task.performance.vo;

import java.util.List;

public class Place {
  private int no;
  private String place_name;
  private String content;
  private int seat;

  public int getSeat() {
    return seat;
  }

  public void setSeat(int seat) {
    this.seat = seat;
  }

  private List<PerformanceDetail> performance_details;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getPlace_name() {
    return place_name;
  }

  public void setPlace_name(String place_name) {
    this.place_name = place_name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<PerformanceDetail> getPerformance_details() {
    return performance_details;
  }

  public void setPerformance_details(
    List<PerformanceDetail> performance_details) {
    this.performance_details = performance_details;
  }
}
