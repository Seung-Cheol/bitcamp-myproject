package task.admin.handler;

import java.util.List;
import task.admin.dao.AdminPerformanceDao;
import task.member.dao.MemberDao;
import task.member.vo.Member;
import task.menu.AbstractMenuHandler;
import task.performance.handler.PerformanceListHandler;
import task.performance.vo.Performance;
import task.util.Prompt;

public class AdminPerformMemberListHandler extends AbstractMenuHandler {
  AdminPerformanceDao adminPerformanceDao;
  MemberDao memberDao;

  public AdminPerformMemberListHandler(Prompt prompt,AdminPerformanceDao adminPerformanceDao,
    MemberDao memberDao) {
    super(prompt);
    this.adminPerformanceDao = adminPerformanceDao;
    this.memberDao = memberDao;
  }

  @Override
  protected void action() {
    Member member0 = new Member();
    member0.setEmail(this.prompt.input("관리자 아이디? "));
    member0.setPwd(this.prompt.input("관리자 비밀번호? "));
    if(memberDao.checkForadmin(member0)!=-1) {
      System.out.println("관리자가 아닙니다");
      return;
    }
    List<Performance> performances = adminPerformanceDao.findAll();
    System.out.printf("%-4s\t%-20s\t%s\t%s\t%s\n", "번호", "제목", "남은 좌석","시작일","마지막일");
    for (Performance performance : performances) {
      System.out.printf("%-4d\t%-20s\t%-4d\t%s\t%s\n",
        performance.getNo(),
        performance.getTitle(),
        performance.getSeat(),
        performance.getStarted_at(),
        performance.getEnded_at());
    }
    int no = this.prompt.inputInt("조회 할 공연? ");
    List<Member> members = adminPerformanceDao.findReserveMember(no);
    System.out.printf("%-4s\t%-20s\t%-4s\n", "번호", "이메일", "닉네임");
    for(Member member : members) {
      System.out.printf("%-4d\t%-10s\t%-10s\n",
        member.getNo(),
        member.getEmail(),
        member.getNickname());
    }
  }
}
