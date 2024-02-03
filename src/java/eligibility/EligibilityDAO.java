package eligibility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utility.AnsiColors;

public class EligibilityDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/BLODOS?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Implement DAO methods:
    // * getAll()
    // * getById(int id)
    // * add(Eligibility eligibility)
    // * update(Eligibility eligibility)
    // * delete(int id)

    public EligibilityDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Do simple testing here
        EligibilityDAO eligibilityDAO = new EligibilityDAO();

        // Get last donor ID
        Integer lastDonorID = null;
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donor";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    lastDonorID = rs.getInt("id");
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test add()
        Eligibility eligibilityAdd = new Eligibility(lastDonorID, 8, 1, 0, 0);
        Boolean success = eligibilityDAO.add(eligibilityAdd);
        System.out.println(AnsiColors.ANSI_YELLOW + "Add Eligibility: " + success + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "ADD PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getAll()
        List<Eligibility> eligibilities = eligibilityDAO.getAll();
        Integer lastID = null;
        for (Eligibility eligibility : eligibilities) {
            System.out.println(AnsiColors.ANSI_YELLOW + "Eligibility " + eligibility.getId() + ": " + eligibility.toString() + AnsiColors.ANSI_RESET);
            lastID = eligibility.getId();
        }
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET ALL PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getById()
        Eligibility eligibility = eligibilityDAO.getById(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Get by ID: " + eligibility.toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET BY ID PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test update()
        eligibility.setSleepHours(9);
        eligibilityDAO.update(eligibility);
        System.out.println(AnsiColors.ANSI_YELLOW + "Update Eligibility: " + eligibilityDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "UPDATE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test delete()
        eligibilityDAO.delete(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Delete Eligibility: " + eligibilityDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "DELETE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Pass
        System.out.println(AnsiColors.ANSI_GREEN_BACKGROUND + AnsiColors.ANSI_BLUE + "PASSED ALL TESTS" + AnsiColors.ANSI_RESET);
    }

    // * getAll()
    public List<Eligibility> getAll() {
        List<Eligibility> eligibilities = new ArrayList<Eligibility>();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM eligibility";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Eligibility eligibility = new Eligibility();
                    eligibility.setId(rs.getInt("id"));
                    eligibility.setDonorId(rs.getInt("donor_id"));
                    eligibility.setSleepHours(rs.getInt("sleep_hours"));
                    eligibility.setMealBeforeDonation(rs.getInt("meal_before_donation"));
                    eligibility.setMedicalIllness(rs.getInt("medical_illness"));
                    eligibility.setHighRiskActivity(rs.getInt("high_risk_activity"));
                    eligibilities.add(eligibility);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eligibilities;
    }

    // * getById(int id)
    public Eligibility getById(int id) {
        Eligibility eligibility = new Eligibility();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM eligibility WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    eligibility.setId(rs.getInt("id"));
                    eligibility.setDonorId(rs.getInt("donor_id"));
                    eligibility.setSleepHours(rs.getInt("sleep_hours"));
                    eligibility.setMealBeforeDonation(rs.getInt("meal_before_donation"));
                    eligibility.setMedicalIllness(rs.getInt("medical_illness"));
                    eligibility.setHighRiskActivity(rs.getInt("high_risk_activity"));
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eligibility;
    }

    // * add(Eligibility eligibility)
    public Boolean add(Eligibility eligibility) {
        Boolean success = false;

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO eligibility (donor_id, sleep_hours, meal_before_donation, medical_illness, high_risk_activity) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, eligibility.getDonorId());
                stmt.setInt(2, eligibility.getSleepHours());
                stmt.setInt(3, eligibility.getMealBeforeDonation());
                stmt.setInt(4, eligibility.getMedicalIllness());
                stmt.setInt(5, eligibility.getHighRiskActivity());
                stmt.executeUpdate();
                success = true;
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    // * update(Eligibility eligibility)
    public void update(Eligibility eligibility) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE eligibility SET donor_id = ?, sleep_hours = ?, meal_before_donation = ?, medical_illness = ?, high_risk_activity = ? WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, eligibility.getDonorId());
                stmt.setInt(2, eligibility.getSleepHours());
                stmt.setInt(3, eligibility.getMealBeforeDonation());
                stmt.setInt(4, eligibility.getMedicalIllness());
                stmt.setInt(5, eligibility.getHighRiskActivity());
                stmt.setInt(6, eligibility.getId());
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
            String query = "DELETE FROM eligibility WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
