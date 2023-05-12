package mx.uv.fei.parkingcity.dao;

import java.sql.SQLException;

public interface IUserDAO {
    boolean areCredentialsValid(String username, String password) throws SQLException;
    String getAccessAccountTypeByUsername(String username) throws SQLException;
}
