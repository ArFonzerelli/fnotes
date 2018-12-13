package ru.fonzy.fnotes.test;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class MyMessageDao {

    @Resource(lookup = "jdbc/MyDs")
    private DataSource ds;

    public int insertMessages(long requestId, List<Message> messages) {
        try (Connection con = ds.getConnection()) {
            con.setAutoCommit(false);
            lock(con, requestId);
            changeRequestStatus(con, requestId);
            insertMessages(con, requestId, messages);
            con.commit();
            return 0;
        } catch (SQLException | LockException e) {
            log.error("Error. " + requestId + ", " + messages, e);
            return 1;
        }
    }

    private void lock(Connection con, long requestId) throws SQLException, LockException {
        try (CallableStatement cs = con.prepareCall("{? = call LOCKS.lock_request(?)}")) {
            cs.setLong(1, requestId);
            cs.executeUpdate();
            int result = cs.getInt(1);

            if (result != 0) {
                throw new LockException("Can't lock registry with id=" + requestId);
            }
            con.commit();
        }
    }

    private void insertMessages(Connection con, long requestId, List<Message> messages)
            throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "INSERT INTO MESSAGES(PK,RQ_PK,MSG) VALUES(MAX(PK)+1, ?,?)")) {
            for (Message msg : messages) {
                ps.setLong(1, requestId);
                ps.setString(2, msg.getMessage());
                ps.executeUpdate();
            }
        }
        finally {
            unlock(con, requestId);
        }
    }

    private void unlock(Connection con, long registryId) throws SQLException {
        //....
    }

    private void changeRequestStatus(Connection con, long registryId) throws SQLException {
        //...
    }
}
