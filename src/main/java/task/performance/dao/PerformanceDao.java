package task.performance.dao;

import java.util.List;
import task.performance.vo.Performance;

public interface PerformanceDao {

  void add(int performanceNo, int memberNo);

  void delete(int performanceNo, int memberNo);

  List<Performance> findAll();

  List<Performance> findByWord(String word);

  List<Performance> findForReservation(int memberNo);

  int changeSeat(int performanceNo, int num);
}
