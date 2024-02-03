package donationdonationtype;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utility.AnsiColors;

public class DonationDonationTypeDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/BLODOS?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Implement DAO methods:
    // * getAll()
    // * getById(int id)
    // * add(DonationDonationType donationDonationType)
    // * update(DonationDonationType donationDonationType)
    // * delete(int id)

    public static void main(String[] args) {
        // Do simple testing here
        DonationDonationTypeDAO donationDonationTypeDAO = new DonationDonationTypeDAO();

        // Get last donation ID
        Integer lastDonationID = null;
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donation";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    lastDonationID = rs.getInt("id");
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Get last donation type ID
        Integer lastDonationTypeID = null;
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donation_type";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    lastDonationTypeID = rs.getInt("id");
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test add()
        DonationDonationType donationDonationTypeAdd = new DonationDonationType(lastDonationID, lastDonationTypeID);
        Boolean success = donationDonationTypeDAO.add(donationDonationTypeAdd);
        System.out.println(AnsiColors.ANSI_YELLOW + "Add Donation Donation Type: " + success + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "ADD PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getAll()
        List<DonationDonationType> donationDonationTypes = donationDonationTypeDAO.getAll();
        Integer lastID = null;
        for (DonationDonationType donationDonationType : donationDonationTypes) {
            System.out.println(AnsiColors.ANSI_YELLOW + "Donation Donation Type " + donationDonationType.getId() + ": " + donationDonationType.toString() + AnsiColors.ANSI_RESET);
            lastID = donationDonationType.getId();
        }
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET ALL PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getById()
        DonationDonationType donationDonationType = donationDonationTypeDAO.getById(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Get by ID: " + donationDonationType.toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET BY ID PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test update()
        donationDonationTypeDAO.update(donationDonationType);
        System.out.println(AnsiColors.ANSI_YELLOW + "Update Donation Donation Type: " + donationDonationTypeDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "UPDATE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test delete()
        donationDonationTypeDAO.delete(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Delete Donation Donation Type: " + donationDonationTypeDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "DELETE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Pass
        System.out.println(AnsiColors.ANSI_GREEN_BACKGROUND + AnsiColors.ANSI_BLUE + "PASSED ALL TESTS" + AnsiColors.ANSI_RESET);
    }

    // * getAll()
    public List<DonationDonationType> getAll() {
        List<DonationDonationType> donationDonationTypes = new ArrayList<DonationDonationType>();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donation_donation_type";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    DonationDonationType donationDonationType = new DonationDonationType();
                    donationDonationType.setId(rs.getInt("id"));
                    donationDonationType.setDonationId(rs.getInt("donation_id"));
                    donationDonationType.setDonationTypeId(rs.getInt("donation_type_id"));
                    donationDonationTypes.add(donationDonationType);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donationDonationTypes;
    }

    // * getById(int id)
    public DonationDonationType getById(int id) {
        DonationDonationType donationDonationType = new DonationDonationType();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donation_donation_type WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    donationDonationType.setId(rs.getInt("id"));
                    donationDonationType.setDonationId(rs.getInt("donation_id"));
                    donationDonationType.setDonationTypeId(rs.getInt("donation_type_id"));
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donationDonationType;
    }

    // * add(DonationDonationType donationDonationType)
    public Boolean add(DonationDonationType donationDonationType) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO donation_donation_type (donation_id, donation_type_id) VALUES (?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, donationDonationType.getDonationId());
                stmt.setInt(2, donationDonationType.getDonationTypeId());
                stmt.executeUpdate();
            }

            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // * update(DonationDonationType donationDonationType)
    public void update(DonationDonationType donationDonationType) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE donation_donation_type SET donation_id = ?, donation_type_id = ? WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, donationDonationType.getDonationId());
                stmt.setInt(2, donationDonationType.getDonationTypeId());
                stmt.setInt(3, donationDonationType.getId());
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
            String query = "DELETE FROM donation_donation_type WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
