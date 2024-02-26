package task.performance.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import task.exception.DaoException;
import task.member.vo.Member;
import task.performance.vo.Performance;
import task.performance.vo.Reservation;
import task.performance.vo.ReservationSeat;
import task.util.ConnectionPool;

public class JdbcPerformanceDaoImpl implements PerformanceDao {
  ConnectionPool connectionPool;
  public JdbcPerformanceDaoImpl(ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public void add(int performanceDetailNo, int memberNo) {
    try(PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "insert into reservation(performance_detail_no, member_no) values(?,?)"
    );) {
      stmt.setInt(1, performanceDetailNo);
      stmt.setInt(2, memberNo);
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  public void seatAdd(int reservationNo, String seatNo) {
    try (PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "insert into reservation_seat(reservation_no, seat_no) values(?,?)"
    );) {
      stmt.setInt(1, reservationNo);
      stmt.setString(2, seatNo);
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  public List<ReservationSeat> findAllSeat(int performanceDetailNo) {
    try (PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "select "
        + "a.no as reservation_no,"
        + "a.performance_detail_no as performance_detail_no,"
        + "a.reserved_at as reserved_at,"
        + "b.seat_no as seat_no"
        + "from reservation as a inner join reservatioseat as b "
        + "on a.no = b.reservation_no"
        + "where performance_detail_no=?"
    );) {
      stmt.setInt(1,performanceDetailNo);
      ResultSet rs = stmt.executeQuery();
      List<ReservationSeat> list = new ArrayList<>();
      while(rs.next()) {
        ReservationSeat rsvs = new ReservationSeat();
        rsvs.setReservation_no(rs.getInt("reservation_no"));
        rsvs.setSeat_no(rs.getString("seat_no"));
        list.add(rsvs);
      }
      return list;
    } catch (Exception e) {
      throw new DaoException("시트 리스트 에러");
    }
  }

  public List<Performance> findForReservation(int memberNo) {
    try(PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "select * from performance where no "
        + "in (select performance_no from reservation where member_no=?)"
    );) {
      stmt.setInt(1, memberNo);
      ResultSet rs = stmt.executeQuery();
      List<Performance> performances = new ArrayList<>();
      while (rs.next()) {
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
      throw new DaoException();
    }
  }
  @Override
  public void delete(int performanceNo, int memberNo) {
    try( PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "delete from reservation where performance_no = ? and member_no = ?"
    );) {
      stmt.setInt(1, performanceNo);
      stmt.setInt(2, memberNo);
      stmt.executeUpdate();
    } catch(Exception e) {
      throw new DaoException();
    }
  }

  @Override
  public List<Performance> findAll() {
    try(PreparedStatement stmt = connectionPool.getConnection().prepareStatement("select * from performance");) {

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
  public List<Performance> findByWord(String word) {
    try(PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "select * from performance where title like ?"
    );) {
      stmt.setString(1, "%" + word + "%");
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
  public int changeSeat(int performanceNo,int num) {
    try(PreparedStatement stmt = connectionPool.getConnection().prepareStatement(
      "select * from performance where no=?"
    );
      PreparedStatement stmt2 = connectionPool.getConnection().prepareStatement(
        "update performance set seat = ? where no = ?"
      );
    ) {
      stmt.setInt(1, performanceNo);
      ResultSet rs = stmt.executeQuery();
      if(rs.next()) {
        int seat = rs.getInt("seat");
        if(seat>0) {
          seat = seat+num;
          stmt2.setInt(1, seat);
          stmt2.setInt(2,performanceNo);
          return 1;
        } else {
          return 0;
        }
      }
      return 0;
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }
}
