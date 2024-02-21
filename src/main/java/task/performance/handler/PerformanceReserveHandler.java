package task.performance.handler;

import task.member.dao.MemberDao;
import task.member.vo.Member;
import task.menu.AbstractMenuHandler;
import task.performance.dao.PerformanceDao;
import task.util.Prompt;

public class PerformanceReserveHandler extends AbstractMenuHandler {
  PerformanceDao performanceDao;
  MemberDao memberDao;



  public PerformanceReserveHandler(Prompt prompt, PerformanceDao performanceDao, MemberDao memberDao) {
    super(prompt);
    this.performanceDao = performanceDao;
    this.memberDao= memberDao;
  }

  protected void action() {
    new PerformanceListHandler(prompt,performanceDao).action();
    int no = this.prompt.inputInt("예약할 공연번호? ");
    Member member = new Member();
    member.setEmail(this.prompt.input("아이디? "));
    member.setPwd(this.prompt.input("패스워드? "));
    int memberNo = memberDao.findByEmailAndPwd(member);
    if(memberNo==0) {
      System.out.println("아이디 비밀번호가 올바르지 않습니다");
      return;
    }
    if(this.performanceDao.changeSeat(no,-1)==1) {
      this.performanceDao.add(no, memberNo);
    } else {
      System.out.println("예약이 중지되었습니다 다시 시도해주세요.");
    }
  }


}
