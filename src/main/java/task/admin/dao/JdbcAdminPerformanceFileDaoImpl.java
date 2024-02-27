package task.admin.dao;

import java.sql.PreparedStatement;
import java.util.List;
import task.performance.vo.PerformancePicture;
import task.util.ConnectionPool;

public class JdbcAdminPerformanceFileDaoImpl implements AdminPerformanceFileDao{

  ConnectionPool connectionPool;

  public JdbcAdminPerformanceFileDaoImpl(ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  public void add(List<PerformancePicture> pictures) {
    try(PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "insert into performancePicture(picture_name,picture_code,performance_no) values(?,?,?)"
    );) {
      for(PerformancePicture picture : pictures) {
        stmt.setString(1,picture.getPicture_name());
        stmt.setString(2,picture.getPicture_code());
        stmt.setInt(3,picture.getPerformance_no());
        stmt.executeQuery();
      }

    } catch (Exception e) {

    }
  }


}
