package task.admin.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import task.exception.DaoException;
import task.performance.vo.Place;
import task.util.ConnectionPool;

public class JdbcAdminPlaceDaoImpl implements AdminPlaceDao {

  ConnectionPool connectionPool;
  public JdbcAdminPlaceDaoImpl(ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  public void add(Place place) {

  }

  public List<Place> findAll() {
    try(PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "select * from place"
    );) {
      ResultSet rs = stmt.executeQuery();
      List<Place> list = new ArrayList<>();
      while(rs.next()) {
        Place place = new Place();
        place.setPlace_name(rs.getString("place_name"));
        place.setContent(rs.getString("content"));
        place.setNo(rs.getInt("no"));
        place.setSeat(rs.getInt("seat"));
        list.add(place);
      }
      return list;
    } catch(Exception e) {
      throw new DaoException();
    }
  }
}
