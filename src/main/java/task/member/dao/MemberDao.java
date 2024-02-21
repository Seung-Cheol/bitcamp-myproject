package task.member.dao;

import task.member.vo.Member;

public interface MemberDao {
  int findByEmailAndPwd(Member member);

  int checkForadmin(Member member);

  void add(Member member);
}
