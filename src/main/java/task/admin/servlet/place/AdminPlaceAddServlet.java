package task.admin.servlet.place;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import task.admin.dao.AdminPerformanceDao;
import task.admin.dao.AdminPlaceDao;
import task.performance.vo.Performance;
import task.performance.vo.PerformancePicture;
import task.performance.vo.Place;

@WebServlet("admin/place/add")
public class AdminPlaceAddServlet extends HttpServlet {
  AdminPlaceDao adminPlaceDao;
  @Override
  public void init() {
    adminPlaceDao = (AdminPlaceDao) this.getServletContext().getAttribute("adminPlaceDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    List<Place> list = adminPlaceDao.findAll();

    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>비트캠프 데브옵스 5기</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h2>공연장 목록</h2>");
    out.println("    <thead>");
    out.println("    <tr> <th>번호</th> <th>이름</th> <th>설명</th> <th>좌석수</th></tr>");
    out.println("    </thead>");
    out.println("    <tbody>");

    for (Place place : list) {
      out.printf(
        "<tr> <td>%d</td> <td>%s</td> <td>%s</td> <td>%d</td></tr>\n",
        place.getNo(),
        place.getPlace_name(),
        place.getContent(),
        place.getSeat());
    }
    out.println(
      "<form action='/admin/place/add' method='post' enctype='multipart/form-data'>");
    out.println("<div>");
    out.println("      공연장이름: <input name='place_name' type='text'>");
    out.println("</div>");
    out.println("<div>");
    out.println("      내용: <textarea name='content'></textarea>");
    out.println("</div>");
    out.println("  <div>");
    out.println("       좌석 수: <input name='seat' type='text'>");
    out.println("  </div>");
    out.println("<div>");
    out.println("  <button>등록</button>");
    out.println("</div>");
    out.println("</form>");

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    try {
      request.setCharacterEncoding("UTF-8");
      Place place = new Place();
      place.setPlace_name("place_name");
      place.setContent(request.getParameter("content"));
      place.setSeat(Integer.parseInt(request.getParameter("seat")));
      adminPlaceDao.add(place);
    } catch (Exception e) {

    }

  }

}
