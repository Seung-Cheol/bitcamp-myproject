package task.admin.dao;

import java.util.List;
import task.member.vo.Member;
import task.performance.vo.Performance;
import task.performance.vo.PerformancePicture;

public interface AdminPerformanceDao {
  int add(Performance performance);

  void delete(int no);

  void modify(Performance performance);

  List<Performance> findAll();

  List<Member> findReserveMember(int performanceNo);

  void add(List<PerformancePicture> pictures);

  public List<Performance> findById(int performance_id);
}
