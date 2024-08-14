package utez.edu.mx.repaso.dao;

import utez.edu.mx.repaso.entity.Employee;
import utez.edu.mx.repaso.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EmployeeDao {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private final DBConnection DB_CONNECTION = new DBConnection();
    private final String[] QUERIES = {
            "SELECT * FROM employee;",
            "SELECT * FROM employee WHERE id = ?;",
            "INSERT INTO employee(name, surname, lastname, department, " +
                    "status, created_at) VALUES(?, ?, ?, ?, ?, ?);",
            "UPDATE employee SET name = ?, surname = ?, lastname = ?, department = ? WHERE id = ?;",
            "UPDATE employee SET status = ? WHERE id = ?;",
            "DELETE FROM employee WHERE id = ?"
    };

    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        try {
            con = DB_CONNECTION.getConnection();
            pstm = con.prepareStatement(QUERIES[0]);
            rs = pstm.executeQuery();
            while(rs.next()) {
                Employee e = new Employee(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("lastname"),
                        rs.getString("department"),
                        rs.getString("created_at"),
                        rs.getBoolean("status")
                );

                list.add(e);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public Employee findById(long id) {
        try {
            con = DB_CONNECTION.getConnection();
            pstm = con.prepareStatement(QUERIES[1]);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            if(rs.next()) {
                return new Employee(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("lastname"),
                        rs.getString("department"),
                        rs.getString("created_at"),
                        rs.getBoolean("status")
                );
            } else {
                return null;
            }
        } catch (SQLException err) {
            err.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    public boolean create(Employee e) {
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd",
                new Locale("es-MX")
        );
        try {
            con = DB_CONNECTION.getConnection();
            pstm = con.prepareStatement(QUERIES[2]);
            pstm.setString(1, e.getName());
            pstm.setString(2, e.getSurname());
            pstm.setString(3, e.getLastname());
            pstm.setString(4, e.getDepartment());
            pstm.setBoolean(5, true);
            pstm.setString(6, sdf.format(new Date()));
            return pstm.executeUpdate() == 1;
        } catch (SQLException err) {
            err.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    public boolean update(Employee e) {
        Employee found = findById(e.getId());
        if(found != null) {
            try {
                con = DB_CONNECTION.getConnection();
                pstm = con.prepareStatement(QUERIES[3]);
                pstm.setString(1, e.getName());
                pstm.setString(2, e.getSurname());
                pstm.setString(3, e.getLastname());
                pstm.setString(4, e.getDepartment());
                pstm.setLong(5, e.getId());
                return pstm.executeUpdate() == 1;
            } catch (SQLException err) {
                err.printStackTrace();
                return false;
            } finally {
                closeConnection();
            }
        } else {
            return false;
        }
    }

    public boolean changeStatus(long id) {
        Employee found = findById(id);
        if(found != null) {
            try {
                con = DB_CONNECTION.getConnection();
                pstm = con.prepareStatement(QUERIES[4]);
                pstm.setBoolean(1, !found.isStatus());
                pstm.setLong(2, id);
                return pstm.executeUpdate() == 1;
            } catch (SQLException err) {
                err.printStackTrace();
                return false;
            } finally {
                closeConnection();
            }
        } else {
            return false;
        }
    }

    public boolean delete(long id) {
        Employee found = findById(id);
        if(found != null && !found.isStatus()) {
            try {
                con = DB_CONNECTION.getConnection();
                pstm = con.prepareStatement(QUERIES[5]);
                pstm.setLong(1, id);
                return pstm.executeUpdate() == 1;
            } catch (SQLException err) {
                err.printStackTrace();
                return false;
            } finally {
                closeConnection();
            }
        } else {
            return false;
        }
    }

    public void closeConnection() {
        try {
            if(con != null) {
                con.close();
            }
            if(rs != null) {
                rs.close();
            }
            if(pstm != null) {
                pstm.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
}
