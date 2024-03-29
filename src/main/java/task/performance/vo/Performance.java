package task.performance.vo;
import java.sql.Date;
import java.util.List;

public class Performance {
  private int no;
  private String title;
  private String content;
  private Date started_at;
  private Date ended_at;
  private List<PerformancePicture> performance_pictures;

  public List<PerformancePicture> getPerformance_pictures() {
    return performance_pictures;
  }

  public void setPerformance_pictures(
    List<PerformancePicture> performance_pictures) {
    this.performance_pictures = performance_pictures;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setStarted_at(Date started_at) {
    this.started_at = started_at;
  }

  public void setEnded_at(Date ended_at) {
    this.ended_at = ended_at;
  }

  public int getNo() {
    return no;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public Date getStarted_at() {
    return started_at;
  }

  public Date getEnded_at() {
    return ended_at;
  }
}
