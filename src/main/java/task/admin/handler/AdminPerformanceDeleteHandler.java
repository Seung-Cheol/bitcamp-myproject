package task.admin.handler;

import java.util.List;
import task.admin.dao.AdminPerformanceDao;
import task.member.dao.MemberDao;
import task.member.vo.Member;
import task.menu.AbstractMenuHandler;
import task.performance.vo.Performance;
import task.util.Prompt;

public class AdminPerformanceDeleteHandler extends AbstractMenuHandler {
  MemberDao memberDao;

  AdminPerformanceDao adminPerformanceDao;

  public AdminPerformanceDeleteHandler(Prompt prompt, AdminPerformanceDao adminPerformanceDao,MemberDao memberDao) {
    super(prompt);
    this.memberDao = memberDao;
    this.adminPerformanceDao = adminPerformanceDao;
  }

  @Override
  protected void action() {
    Member member = new Member();
    member.setEmail(this.prompt.input("관리자 아이디? "));
    member.setPwd(this.prompt.input("관리자 비밀번호? "));
    if(memberDao.checkForadmin(member)!=-1) {
      System.out.println("관리자가 아닙니다");
      return;
    }
    List<Performance> performances = adminPerformanceDao.findAll();
    System.out.printf("%-4s\t%-20s\t%s\t%s\t%s\n", "번호", "제목", "남은 좌석","시작일","마지막일");
    for (Performance performance : performances) {
      System.out.printf("%-4d\t%-20s\t%d\t%s\t%s\n",
        performance.getNo(),
        performance.getTitle(),
        performance.getSeat(),
        performance.getStarted_at(),
        performance.getEnded_at());
    }
    adminPerformanceDao.delete(this.prompt.inputInt("삭제할 공연 no? "));

  }
}
