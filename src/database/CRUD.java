package database;

import java.sql.ResultSet;
import java.util.List;

public interface CRUD {
    public ResultSet findAll(String tableName);
    public ResultSet findById(String tableName, int id);
    public ResultSet create(String tableName, Object object);
    public boolean delete(String tableName, int id);
    public boolean update(String tableName, Object object);
}
