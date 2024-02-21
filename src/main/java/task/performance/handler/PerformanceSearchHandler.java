package task.performance.handler;

import java.util.List;
import task.menu.AbstractMenuHandler;
import task.performance.dao.PerformanceDao;
import task.performance.vo.Performance;
import task.util.Prompt;

public class PerformanceSearchHandler extends AbstractMenuHandler {
  PerformanceDao performanceDao;

  public PerformanceSearchHandler(Prompt prompt,PerformanceDao performanceDao) {
    super(prompt);
    this.performanceDao = performanceDao;
  }

  protected void action() {

    List<Performance> performances = performanceDao.findByWord(this.prompt.input("검색 키워드? "));
    System.out.printf("%-4s\t%-20s\t%s\t%s\t%s\n", "번호", "제목", "남은 좌석","시작일","마지막일");
    for (Performance performance : performances) {
      System.out.printf("%-4d\t%-20s,\t%d\t%s\t%s\n",
        performance.getNo(),
        performance.getTitle(),
        performance.getSeat(),
        performance.getStarted_at(),
        performance.getEnded_at());
    }

  }


}
