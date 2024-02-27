package task.admin.dao;

import java.util.List;
import task.performance.vo.PerformancePicture;

public interface AdminPerformanceFileDao {
  void add(List<PerformancePicture> pictures);

}
