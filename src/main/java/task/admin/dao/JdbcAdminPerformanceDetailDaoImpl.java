package task.admin.dao;

import task.util.ConnectionPool;

public class JdbcAdminPerformanceDetailDaoImpl implements AdminPerformanceDetailDao{
  ConnectionPool connectionPool;
  public JdbcAdminPerformanceDetailDaoImpl(ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }
}
