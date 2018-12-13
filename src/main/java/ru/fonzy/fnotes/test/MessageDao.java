package ru.fonzy.fnotes.test;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class MessageDao {

    @Resource(lookup = "jdbc/MyDs")
    private DataSource ds;

//    В качестве аргумента лучше принимать интерфейс List а не ArrayList
    public int insertMessages(long requestId, ArrayList<Message> messages) {
        try (Connection con = ds.getConnection()) {
            try {
                lock(requestId);
                changeRequestStatus(con, requestId);
                insertMessages(con, requestId, messages);
                unlock(requestId);
                con.commit();
                return 0;
                //            Отлавливать лучше конкретные исключения SQLException и LockException
            } catch (Exception e) {
                con.rollback();
                throw e;
            }
//            Отлавливать лучше конкретные исключения SQLException и LockException
        } catch (Exception e) {
            log.error("Error. " + requestId + ", " + messages, e);
            return 1;
        }
    }

    // Второй раз вызывается ds.getConnection, я бы предложил передавать connection в качестве аргумента.
    private void lock(long requestId) throws SQLException {
        try (Connection con = ds.getConnection()) {
            try (CallableStatement cs = con.prepareCall("{? = call LOCKS.lock_request(?)}")) {
                cs.setLong(1, requestId);
                cs.executeUpdate();
//                Тут первым индексом будет 1.
                int result = cs.getInt(0);
                if (result != 0) {
                    throw new LockException("Can't lock registry with id=" + requestId);
                }
                con.commit();
//                Здесь могут выброситься только SqlException и LockExsception, они выбрасываются из метода lock, соответственно их не нужно перехватывать
            } catch (Exception e) {
                con.rollback();
                throw new LockException("Can't lock registry with id=" + requestId);
            }
        }
    }

//    Лучше передавать интерфейс List вместо класса ArayList
    private void insertMessages(Connection con, long requestId, ArrayList<Message> messages)
            throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "INSERT INTO MESSAGES(PK,RQ_PK,MSG) VALUES(MAX(PK)+1, ?,?)")) {
            for (Message msg : messages) {
                ps.setLong(1, requestId);
                ps.setString(2, msg.getMessage());
                ps.executeUpdate();
            }
        }
    }

//    Лучше передавать Connection в качестве аргумента
    private void unlock(long registryId) throws SQLException {
        //....
    }

    private void changeRequestStatus(Connection con, long registryId) throws SQLException {
        //...
    }
}

