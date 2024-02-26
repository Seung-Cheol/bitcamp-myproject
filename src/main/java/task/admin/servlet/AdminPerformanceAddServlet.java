package task.admin.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import task.admin.dao.AdminPerformanceDao;
import task.admin.dao.JdbcAdminPerformanceDaoImpl;
import task.performance.vo.Performance;
import task.util.TransactionManager;

@WebServlet("/admin/performance/add")
public class AdminPerformanceAddServlet extends HttpServlet {
  AdminPerformanceDao adminPerformanceDao;
  String uploadDir;

  TransactionManager txManager;

  @Override
  public void init() {
    adminPerformanceDao = (AdminPerformanceDao) this.getServletContext().getAttribute("adminPerformanceDao");
    uploadDir = this.getServletContext().getRealPath("/upload/performance");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    List<Performance> list = adminPerformanceDao.findAll();

    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>비트캠프 데브옵스 5기</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h2>공연 목록</h2>");
    out.println("    <thead>");
    out.println("    <tr> <th>번호</th> <th>제목</th> <th>시작일</th> <th>마감일</th></tr>");
    out.println("    </thead>");
    out.println("    <tbody>");

    for (Performance performance : list) {
      out.printf(
        "<tr> <td>%d</td> <td><a href='/admin/performance/detail/add?performance_no=%1$d'>%s</a></td> <td>%s</td> <td>%s</td></tr>\n",
        performance.getNo(),
        performance.getTitle(),
        performance.getStarted_at(),
        performance.getEnded_at());
    }
    out.println(
      "<form action='/admin/performance/add' method='post' enctype='multipart/form-data'>");
    out.println("<div>");
    out.println("      제목: <input name='title' type='text'>");
    out.println("</div>");
    out.println("<div>");
    out.println("      내용: <textarea name='content'></textarea>");
    out.println("</div>");
    out.println("<div>");
    out.println("      이미지 파일: <input multiple name='files' type='file'>");
    out.println("</div>");
    out.println("  <div>");
    out.println("       공연 시작일: <input name='started_at' type='date'>");
    out.println("  </div>");
    out.println("  <div>");
    out.println("       공연 마감일: <input name='ended_at' type='date'>");
    out.println("  </div>");
    out.println("<div>");
    out.println("  <button>등록</button>");
    out.println("</div>");
    out.println("</form>");

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    Performance performance = new Performance();
    performance.setTitle(request.getParameter("title"));
    performance.setContent(request.getParameter("content"));
    performance.setStarted_at(Date.valueOf(request.getParameter("started_at")));
    performance.setEnded_at(Date.valueOf(request.getParameter("ended_at")));
    try {
      txManager.startTransaction();
      adminPerformanceDao.add(performance);
    } catch (Exception e) {

    }

  }
}
