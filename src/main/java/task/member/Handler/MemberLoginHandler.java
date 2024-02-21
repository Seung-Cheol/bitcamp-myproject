package task.member.Handler;

import task.member.dao.MemberDao;
import task.member.vo.Member;
import task.menu.AbstractMenuHandler;
import task.util.Prompt;

public class MemberLoginHandler extends AbstractMenuHandler {
MemberDao memberDao;
  public MemberLoginHandler(Prompt prompt,MemberDao memberDao) {
    super(prompt);
    this.memberDao = memberDao;
  }

  protected void action() {
    Member member = new Member();
    member.setEmail(this.prompt.input("이메일 : "));
    member.setPwd(this.prompt.input("비밀번호 : "));
    if(memberDao.findByEmailAndPwd(member)==1) {

    } else {

    }

  }

}
