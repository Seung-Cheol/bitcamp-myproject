package task.admin.dao;

import java.util.List;
import task.member.vo.Member;
import task.performance.vo.Performance;

public interface AdminPerformanceDao {
  void add(Performance performance);

  void delete(int no);

  void modify(Performance performance);

  List<Performance> findAll();

  List<Member> findReserveMember(int performanceNo);
}
