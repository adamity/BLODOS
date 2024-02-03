package donationtype;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utility.AnsiColors;

public class DonationTypeDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/BLODOS?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Implement DAO methods:
    // * getAll()
    // * getById(int id)
    // * add(DonationType donationType)
    // * update(DonationType donationType)
    // * delete(int id)

    public DonationTypeDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Do simple testing here
        DonationTypeDAO donationTypeDAO = new DonationTypeDAO();

        // Test add()
        DonationType donationTypeAdd = new DonationType("Plasma");
        Boolean success = donationTypeDAO.add(donationTypeAdd);
        System.out.println(AnsiColors.ANSI_YELLOW + "Add Donation Type: " + success + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "ADD PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getAll()
        List<DonationType> donationTypes = donationTypeDAO.getAll();
        Integer lastID = null;
        for (DonationType donationType : donationTypes) {
            System.out.println(AnsiColors.ANSI_YELLOW + "Donation Type " + donationType.getId() + ": " + donationType.toString() + AnsiColors.ANSI_RESET);
            lastID = donationType.getId();
        }
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET ALL PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getById()
        DonationType donationType = donationTypeDAO.getById(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Get by ID: " + donationType.toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET BY ID PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test update()
        donationType.setTypeName(donationType.getTypeName() + " Updated");
        donationTypeDAO.update(donationType);
        System.out.println(AnsiColors.ANSI_YELLOW + "Update Donation Type: " + donationTypeDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "UPDATE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test delete()
        donationTypeDAO.delete(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Delete Donation Type: " + donationTypeDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "DELETE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Pass
        System.out.println(AnsiColors.ANSI_GREEN_BACKGROUND + AnsiColors.ANSI_BLUE + "PASSED ALL TESTS" + AnsiColors.ANSI_RESET);
    }

    // * getAll()
    public List<DonationType> getAll() {
        List<DonationType> donationTypes = new ArrayList<DonationType>();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donation_type";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    DonationType donationType = new DonationType();
                    donationType.setId(rs.getInt("id"));
                    donationType.setTypeName(rs.getString("type_name"));
                    donationTypes.add(donationType);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donationTypes;
    }

    // * getById(int id)
    public DonationType getById(int id) {
        DonationType donationType = new DonationType();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donation_type WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    donationType.setId(rs.getInt("id"));
                    donationType.setTypeName(rs.getString("type_name"));
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donationType;
    }

    // * add(DonationType donationType)
    public Boolean add(DonationType donationType) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO donation_type (type_name) VALUES (?)";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, donationType.getTypeName());
                stmt.executeUpdate();
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // * update(DonationType donationType)
    public void update(DonationType donationType) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE donation_type SET type_name = ? WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, donationType.getTypeName());
                stmt.setInt(2, donationType.getId());
                stmt.executeUpdate();
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // * delete(int id)
    public void delete(int id) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "DELETE FROM donation_type WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
