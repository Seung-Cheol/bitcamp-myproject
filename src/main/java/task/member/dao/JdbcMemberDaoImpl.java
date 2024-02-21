package task.member.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import task.exception.DaoException;
import task.member.vo.Member;

public class JdbcMemberDaoImpl implements MemberDao {
  Connection con;

  public JdbcMemberDaoImpl(Connection con) {
    this.con = con;
  }

  public int findByEmailAndPwd(Member member) {
    try {
      PreparedStatement stmt = con.prepareStatement();
      String sql = String.format(
        "SELECT no, email, auth FROM member WHERE email='%s' AND pwd='%s'"
        ,member.getEmail(),member.getPwd());
      ResultSet rs = stmt.executeQuery(sql);
      if(rs.next()) {
        return rs.getInt("no");
      } else {
        return 0;
      }
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }
  public int checkForadmin(Member member) {
    try(PreparedStatement stmt = con.prepareStatement(
      "SELECT no, email, auth FROM member WHERE email=? AND pwd=?"
    );) {
      stmt.setString(1,member.getEmail());
      stmt.setString(2,member.getPwd());
      ResultSet rs = stmt.executeQuery();
      if(!rs.next()) {
        return 0;
      }
        if (rs.getInt("auth") == 2) {
          return -1;
        } else {
          return 0;
        }
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }



  public void add(Member member) {
    try(PreparedStatement stmt = con.prepareStatement(
      "insert into member(email,nickname,pwd) values(?,?,?)"
    );) {
      stmt.setString(1,member.getEmail());
      stmt.setString(2,member.getNickname());
      stmt.setString(3,member.getPwd());
      stmt.executeUpdate();
    } catch(Exception e) {
      throw new DaoException();
    }
  }
}
