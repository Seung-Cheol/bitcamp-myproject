package task.admin.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import task.exception.DaoException;
import task.member.vo.Member;
import task.performance.vo.Performance;
import task.util.ConnectionPool;

public class JdbcAdminPerformanceDaoImpl implements AdminPerformanceDao{
  ConnectionPool connectionPool;

  public JdbcAdminPerformanceDaoImpl(ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }
  @Override
  public void add(Performance performance) {
    try(PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "insert into performance(title,content,started_at,ended_at) values("
        + "?,?,?,?,?)"
    );) {
      stmt.setString(1, performance.getTitle());
      stmt.setString(2, performance.getContent());
      stmt.setDate(4,performance.getStarted_at());
      stmt.setDate(5,performance.getEnded_at());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void delete(int no) {
    try(PreparedStatement stmt = con.prepareStatement(
      "delete from performance where no=?"
    );) {
      stmt.setInt(1,no);
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DaoException(e);
    }

  }

  @Override
  public void modify(Performance performance) {
    try(PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "update performance set title=?,content=?,started_at=?,ended_at=? where no=?"
    );) {
      stmt.setString(1, performance.getTitle());
      stmt.setString(2, performance.getContent());
      stmt.setDate(3,performance.getStarted_at());
      stmt.setDate(4,performance.getEnded_at());
      stmt.setInt(5,performance.getNo());
      stmt.executeUpdate();
      System.out.println("Adasd");
    } catch (Exception e) {
      System.out.println(e);
      throw new DaoException(e);
    }

  }

  @Override
  public List<Performance> findAll() {
    try(PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "select * from performance"
    );) {
      ResultSet rs = stmt.executeQuery();
      List<Performance> performances = new ArrayList<>();
      while(rs.next()){
        Performance performance = new Performance();
        performance.setNo(rs.getInt("no"));
        performance.setTitle(rs.getString(("title")));
        performance.setContent(rs.getString("content"));
        performance.setStarted_at(rs.getDate("started_at"));
        performance.setEnded_at(rs.getDate("ended_at"));
        performances.add(performance);
      }
      return performances;
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Member> findReserveMember(int performanceNo) {
    try(PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "select * from member where no "
        + "in (select member_no from reservation where performance_no=?)"
    );) {
      stmt.setInt(1,performanceNo);
      ResultSet rs = stmt.executeQuery();
      List<Member> members = new ArrayList<>();
      while (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("no"));
        member.setEmail(rs.getString(("Email")));
        member.setNickname(rs.getString("nickname"));
        members.add(member);
      }
      return members;
    } catch (Exception e) {
      throw new DaoException();
    }
  }
}
