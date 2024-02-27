package task.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import task.admin.dao.AdminPerformanceDao;
import task.admin.dao.AdminPlaceDao;
import task.admin.dao.JdbcAdminPerformanceDaoImpl;
import task.admin.dao.JdbcAdminPlaceDaoImpl;
import task.member.dao.JdbcMemberDaoImpl;
import task.member.dao.MemberDao;
import task.performance.dao.JdbcPerformanceDaoImpl;
import task.performance.dao.PerformanceDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
  // 웹애플리케이션이 사용할 자원을 준비시키고 해제시키는 역할


  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("웹애플리케이션 자원 준비!");

    // DB 커넥션, DAO, 트랜잭션 관리자 생성
    DBConnectionPool connectionPool = new DBConnectionPool(
        "jdbc:mysql://localhost/studydb", "study", "Bitcamp!@#123");

    MemberDao memberDao = new JdbcMemberDaoImpl(connectionPool);
    AdminPerformanceDao adminPerformanceDao = new JdbcAdminPerformanceDaoImpl(connectionPool);
    AdminPlaceDao adminPlaceDao = new JdbcAdminPlaceDaoImpl();
    PerformanceDao performanceDao = new JdbcPerformanceDaoImpl(connectionPool);
    TransactionManager txManager = new TransactionManager(connectionPool);

    // 서블릿에서 사용할 수 있도록 웹애플리케이션 저장소에 보관한다.
    ServletContext 웹애플리케이션저장소 = sce.getServletContext();
    웹애플리케이션저장소.setAttribute("memberDao", memberDao);
    웹애플리케이션저장소.setAttribute("adminPerformanceDao", adminPerformanceDao);
    웹애플리케이션저장소.setAttribute("adminPerformanceDao", adminPerformanceDao);
    웹애플리케이션저장소.setAttribute("performanceDao", performanceDao);
    웹애플리케이션저장소.setAttribute("txManager", txManager);
  }
}
