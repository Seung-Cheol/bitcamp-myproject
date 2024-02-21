package task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import task.admin.dao.AdminPerformanceDao;
import task.admin.dao.JdbcAdminPerformanceDaoImpl;
import task.admin.handler.AdminPerformMemberListHandler;
import task.admin.handler.AdminPerformanceAddHandler;
import task.admin.handler.AdminPerformanceDeleteHandler;
import task.admin.handler.AdminPerformanceUpdateHandler;
import task.member.Handler.MemberJoinHandler;
import task.member.dao.JdbcMemberDaoImpl;
import task.member.dao.MemberDao;
import task.menu.MenuGroup;
import task.performance.dao.JdbcPerformanceDaoImpl;
import task.performance.dao.PerformanceDao;
import task.performance.handler.PerformanceCancelHandler;
import task.performance.handler.PerformanceListHandler;
import task.performance.handler.PerformanceReserveHandler;
import task.performance.handler.PerformanceReserveListHandler;
import task.performance.handler.PerformanceSearchHandler;
import task.util.Prompt;

public class ClientApp {

  Prompt prompt = new Prompt(System.in);
  PerformanceDao performanceDao;
  MemberDao memberDao;
  AdminPerformanceDao adminPerformanceDao;

  MenuGroup mainMenu;


  ClientApp() {
    prepareDatabase();
    prepareMenu();
  }
  void prepareDatabase() {
    try {
      //JVM이 JDBC 드라이버파일(.jar)에 설정된대로 자동으로 처리한다.
      //Driver driver = new com.mysql.cj.jdbc.Driver();
      //DriverManager.registerDriver(driver);
      Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost/studydb", "study", "Bitcamp!@#123"
      );
      memberDao = new JdbcMemberDaoImpl(con);
      performanceDao = new JdbcPerformanceDaoImpl(con);
      adminPerformanceDao = new JdbcAdminPerformanceDaoImpl(con);

    } catch (Exception e) {
      System.out.println("통신 오류!");
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    new ClientApp().run();
  }

  void prepareMenu() {
    mainMenu = MenuGroup.getInstance("메인");

    MenuGroup loginMenu = mainMenu.addGroup("회원");
    loginMenu.addItem("회원가입",new MemberJoinHandler(prompt,memberDao));


    MenuGroup performanceMenu = mainMenu.addGroup("공연");
    performanceMenu.addItem("공연 목록", new PerformanceListHandler(prompt, performanceDao));
    performanceMenu.addItem("공연 검색", new PerformanceSearchHandler(prompt, performanceDao));
    performanceMenu.addItem("예약 등록", new PerformanceReserveHandler(prompt, performanceDao, memberDao));
    performanceMenu.addItem("예약 목록", new PerformanceReserveListHandler(prompt, performanceDao, memberDao));
    performanceMenu.addItem("예약 취소", new PerformanceCancelHandler(prompt, performanceDao, memberDao));

    MenuGroup adminMenu = mainMenu.addGroup("관리자");
    adminMenu.addItem("공연 등록", new AdminPerformanceAddHandler(prompt, adminPerformanceDao, memberDao));
    adminMenu.addItem("예약자 조회", new AdminPerformMemberListHandler(prompt, adminPerformanceDao, memberDao));
    adminMenu.addItem("공연 수정", new AdminPerformanceUpdateHandler(prompt, adminPerformanceDao, memberDao));
    adminMenu.addItem("공연 삭제", new AdminPerformanceDeleteHandler(prompt, adminPerformanceDao, memberDao));
  }


  void run() {
    while (true) {
      try {
        mainMenu.execute(prompt);
        prompt.close();
        break;
      } catch (Exception e) {
        System.out.println("예외 발생!");
      }
    }
  }
}