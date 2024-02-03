package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utility.AnsiColors;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/BLODOS?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Implement DAO methods:
    // * getAll()
    // * getById(int id)
    // * add(User user)
    // * update(User user)
    // * delete(int id)

    public UserDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Do simple testing here
        UserDAO userDAO = new UserDAO();

        // Test add()
        User userAdd = new User("admin", "Max", "Alex", "maxalex", "password");
        Boolean success = userDAO.add(userAdd);
        System.out.println(AnsiColors.ANSI_YELLOW + "Add Donor: " + success + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "ADD PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getAll()
        List<User> users = userDAO.getAll();
        Integer lastID = null;
        for (User user : users) {
            System.out.println(AnsiColors.ANSI_YELLOW + "User " + user.getId() + ": " + user.toString() + AnsiColors.ANSI_RESET);
            lastID = user.getId();
        }
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET ALL PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getById()
        User user = userDAO.getById(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Get by ID: " + user.toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET BY ID PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test update()
        user.setFirstname(user.getFirstname() + " Updated");
        userDAO.update(user);
        System.out.println(AnsiColors.ANSI_YELLOW + "Update User: " + userDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "UPDATE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test delete()
        userDAO.delete(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Delete User: " + userDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "DELETE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Pass
        System.out.println(AnsiColors.ANSI_GREEN_BACKGROUND + AnsiColors.ANSI_BLUE + "PASSED ALL TESTS" + AnsiColors.ANSI_RESET);
    }

    // Implement DAO methods here

    // * getAll()
    public List<User> getAll() {
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

    // * getById(int id)
    public User getById(int id) {
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

    // * add(User user)
    public Boolean add(User user) {
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

    // * update(User user)
    public void update(User user) {
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

    // * delete(int id)
    public void delete(int id) {
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
