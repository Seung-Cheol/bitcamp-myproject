package task.admin.handler;

import task.admin.dao.AdminPerformanceDao;
import task.member.dao.MemberDao;
import task.member.vo.Member;
import task.menu.AbstractMenuHandler;
import task.performance.dao.PerformanceDao;
import task.performance.vo.Performance;
import task.util.Prompt;

public class AdminPerformanceAddHandler extends AbstractMenuHandler {


  AdminPerformanceDao adminPerformanceDao;
  MemberDao memberDao;

  public AdminPerformanceAddHandler(Prompt prompt, AdminPerformanceDao adminPerformanceDao, MemberDao memberDao) {
    super(prompt);
    this.adminPerformanceDao =  adminPerformanceDao;
    this.memberDao = memberDao;
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
    Performance performance = new Performance();
    performance.setTitle(this.prompt.input("공연명? "));
    performance.setContent(this.prompt.input("내용? "));
    performance.setSeat(this.prompt.inputInt("좌석수? "));
    performance.setStarted_at(this.prompt.inputDate("공연 시작일?(예: 2023-12-25) "));
    performance.setEnded_at((this.prompt.inputDate("공연 마감일?(예: 2023-12-25) ")));
    adminPerformanceDao.add(performance);

  }
}
