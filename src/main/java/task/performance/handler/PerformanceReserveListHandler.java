package task.performance.handler;

import java.util.List;
import task.member.dao.MemberDao;
import task.member.vo.Member;
import task.menu.AbstractMenuHandler;
import task.performance.dao.PerformanceDao;
import task.performance.vo.Performance;
import task.util.Prompt;

public class PerformanceReserveListHandler extends AbstractMenuHandler {
  PerformanceDao performanceDao;
  MemberDao memberDao;
  public PerformanceReserveListHandler(Prompt prompt, PerformanceDao performanceDao,
    MemberDao memberDao) {
    super(prompt);
    this.performanceDao = performanceDao;
    this.memberDao = memberDao;
  }

  @Override
  protected void action() {
    Member member = new Member();
    member.setEmail(this.prompt.input("아이디? "));
    member.setPwd(this.prompt.input("패스워드? "));
    int memberNo = memberDao.findByEmailAndPwd(member);
    List<Performance> performances = this.performanceDao.findForReservation(memberNo);
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
