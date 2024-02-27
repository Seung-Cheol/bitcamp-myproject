package task.admin.dao;

import java.util.List;
import task.performance.vo.Place;

public interface AdminPlaceDao {

  void add(Place place);
  List<Place> findAll();

}
