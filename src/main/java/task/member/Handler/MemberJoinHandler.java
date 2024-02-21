package task.member.Handler;

import task.member.dao.MemberDao;
import task.member.vo.Member;
import task.menu.AbstractMenuHandler;
import task.util.Prompt;

public class MemberJoinHandler extends AbstractMenuHandler {
  MemberDao memberDao;
  public MemberJoinHandler(Prompt prompt,MemberDao memberDao) {
    super(prompt);
    this.memberDao = memberDao;
  }

  protected void action() {
    Member member = new Member();
    member.setEmail(this.prompt.input("이메일 : "));
    member.setNickname(this.prompt.input("닉네임 : "));
    member.setPwd(this.prompt.input("비밀번호 : "));
    memberDao.add(member);
  }

}
