package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/BLODOS?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Implement DAO methods:
    // * getAllUsers()
    // * getUserById(int id)
    // * addUser(User user)
    // * updateUser(User user)
    // * deleteUser(int id)

    public static void main(String[] args) {
        // Test createUser()
        // User user = new User("admin", "Jane", "Doe", "janedoe", "password");
        // UserDAO userDAO = new UserDAO();
        // Boolean success = userDAO.addUser(user);
        // System.out.println(success);

        // Test getAllUsers()
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getAllUsers();
        for (User user : users) System.out.println(user.toString());

        // Test getUserById()
        // UserDAO userDAO = new UserDAO();
        // User user = userDAO.getUserById(1);
        // System.out.println(user);

        // Test updateUser()
        // UserDAO userDAO = new UserDAO();
        // User user = userDAO.getUserById(1);
        // user.setFirstname("Jane");
        // userDAO.updateUser(user);

        // Test deleteUser()
        // UserDAO userDAO = new UserDAO();
        // userDAO.deleteUser(1);
    }

    // Implement DAO methods here

    // * getAllUsers()
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM user";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setRole(rs.getString("role"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    users.add(user);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // * getUserById(int id)
    public User getUserById(int id) {
        User user = new User();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM user WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    user.setId(rs.getInt("id"));
                    user.setRole(rs.getString("role"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // * addUser(User user)
    public Boolean addUser(User user) {
        Boolean success = false;

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO user (role, firstname, lastname, username, password) VALUES (?, ?, ?, ?, ?)";

            // Check if username already exists
            String checkQuery = "SELECT COUNT(*) FROM user WHERE username = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, user.getUsername());
                ResultSet checkRs = checkStmt.executeQuery();
                checkRs.next();
                int count = checkRs.getInt(1);
                if (count > 0) {
                    System.out.println("Username already exists");
                    return false;
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, user.getRole());
                stmt.setString(2, user.getFirstname());
                stmt.setString(3, user.getLastname());
                stmt.setString(4, user.getUsername());
                stmt.setString(5, user.getPassword());
                success = stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    // * updateUser(User user)
    public void updateUser(User user) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE user SET role = ?, firstname = ?, lastname = ?, username = ?, password = ? WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, user.getRole());
                stmt.setString(2, user.getFirstname());
                stmt.setString(3, user.getLastname());
                stmt.setString(4, user.getUsername());
                stmt.setString(5, user.getPassword());
                stmt.setInt(6, user.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // * deleteUser(int id)
    public void deleteUser(int id) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "DELETE FROM user WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
