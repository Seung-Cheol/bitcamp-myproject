package task.performance.vo;

import java.util.Date;
import java.util.List;

public class PerformanceDetail {
  private int no;
  private int performance_no;
  private int place_no;
  private Date started_at;
  private Date ended_at;

  public Date getStarted_at() {
    return started_at;
  }

  public void setStarted_at(Date started_at) {
    this.started_at = started_at;
  }

  public Date getEnded_at() {
    return ended_at;
  }

  public void setEnded_at(Date ended_at) {
    this.ended_at = ended_at;
  }

  private List<PerformanceActor> performance_actors;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getPerformance_no() {
    return performance_no;
  }

  public void setPerformance_no(int performance_no) {
    this.performance_no = performance_no;
  }

  public int getPlace_no() {
    return place_no;
  }

  public void setPlace_no(int place_no) {
    this.place_no = place_no;
  }

  public List<PerformanceActor> getPerformance_actors() {
    return performance_actors;
  }

  public void setPerformance_actors(List<PerformanceActor> performance_actors) {
    this.performance_actors = performance_actors;
  }
}
