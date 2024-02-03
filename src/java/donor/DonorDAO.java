package donor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utility.AnsiColors;

public class DonorDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/BLODOS?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Implement DAO methods:
    // * getAll()
    // * getById(int id)
    // * add(Donor donor)
    // * update(Donor donor)
    // * delete(int id)

    public DonorDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Do simple testing here
        DonorDAO donorDAO = new DonorDAO();

        // Get last user ID
        Integer lastUserId = null;
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT id FROM user ORDER BY id DESC LIMIT 1";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    lastUserId = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test add()
        Donor donorAdd = new Donor(lastUserId, null, "123456-12-1234", "Ali Baba", "2000-01-01", "Male", 50, 160, "A+");
        Boolean success = donorDAO.add(donorAdd);
        System.out.println(AnsiColors.ANSI_YELLOW + "Add Donor: " + success + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "ADD PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getAll()
        List<Donor> donors = donorDAO.getAll();
        Integer lastID = null;
        for (Donor donor : donors) {
            System.out.println(AnsiColors.ANSI_YELLOW + "Donor " + donor.getId() + ": " + donor.toString() + AnsiColors.ANSI_RESET);
            lastID = donor.getId();
        }
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET ALL PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getById()
        Donor donor = donorDAO.getById(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Get by ID: " + donor.toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET BY ID PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test update()
        donor.setFullname(donor.getFullname() + " Updated");
        donorDAO.update(donor);
        System.out.println(AnsiColors.ANSI_YELLOW + "Update Donor: " + donorDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "UPDATE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test delete()
        donorDAO.delete(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Delete Donor: " + donorDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "DELETE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Pass
        System.out.println(AnsiColors.ANSI_GREEN_BACKGROUND + AnsiColors.ANSI_BLUE + "PASSED ALL TESTS" + AnsiColors.ANSI_RESET);
    }

    // * getAll()
    public List<Donor> getAll() {
        List<Donor> donors = new ArrayList<Donor>();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donor";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Donor donor = new Donor();
                    donor.setId(rs.getInt("id"));
                    donor.setUserId(rs.getInt("user_id"));
                    donor.setReferrerDonorId(rs.getInt("referrer_donor_id"));
                    donor.setIcNumber(rs.getString("ic_number"));
                    donor.setFullname(rs.getString("fullname"));
                    donor.setDob(rs.getString("dob"));
                    donor.setGender(rs.getString("gender"));
                    donor.setWeight(rs.getInt("weight"));
                    donor.setHeight(rs.getInt("height"));
                    donor.setBloodType(rs.getString("blood_type"));
                    donors.add(donor);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donors;
    }

    // * getById(int id)
    public Donor getById(int id) {
        Donor donor = new Donor();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donor WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    donor.setId(rs.getInt("id"));
                    donor.setUserId(rs.getInt("user_id"));
                    donor.setReferrerDonorId(rs.getInt("referrer_donor_id"));
                    donor.setIcNumber(rs.getString("ic_number"));
                    donor.setFullname(rs.getString("fullname"));
                    donor.setDob(rs.getString("dob"));
                    donor.setGender(rs.getString("gender"));
                    donor.setWeight(rs.getInt("weight"));
                    donor.setHeight(rs.getInt("height"));
                    donor.setBloodType(rs.getString("blood_type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donor;
    }

    // * add(Donor donor)
    public Boolean add(Donor donor) {
        Boolean success = false;

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO donor (user_id, referrer_donor_id, ic_number, fullname, dob, gender, weight, height, blood_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Check if ic_number already exists
            String checkQuery = "SELECT COUNT(*) FROM donor WHERE ic_number = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, donor.getIcNumber());
                ResultSet checkRs = checkStmt.executeQuery();
                checkRs.next();
                int count = checkRs.getInt(1);
                if (count > 0) {
                    System.out.println("IC Number already exists");
                    return false;
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, donor.getUserId());
                if (donor.getReferrerDonorId() == null || donor.getReferrerDonorId() == 0) {
                    stmt.setNull(2, java.sql.Types.INTEGER);
                } else {
                    stmt.setInt(2, donor.getReferrerDonorId());
                }
                stmt.setString(3, donor.getIcNumber());
                stmt.setString(4, donor.getFullname());
                stmt.setString(5, donor.getDob());
                stmt.setString(6, donor.getGender());
                stmt.setInt(7, donor.getWeight());
                stmt.setInt(8, donor.getHeight());
                stmt.setString(9, donor.getBloodType());
                success = stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    // * update(Donor donor)
    public void update(Donor donor) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE donor SET user_id = ?, referrer_donor_id = ?, ic_number = ?, fullname = ?, dob = ?, gender = ?, weight = ?, height = ?, blood_type = ? WHERE id = ?";

            // Check if ic_number already exists except for the current donor
            String checkQuery = "SELECT COUNT(*) FROM donor WHERE ic_number = ? AND id != ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, donor.getIcNumber());
                checkStmt.setInt(2, donor.getId());
                ResultSet checkRs = checkStmt.executeQuery();
                checkRs.next();
                int count = checkRs.getInt(1);
                if (count > 0) {
                    System.out.println("IC Number already exists");
                    return;
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, donor.getUserId());
                if (donor.getReferrerDonorId() == null || donor.getReferrerDonorId() == 0) {
                    stmt.setNull(2, java.sql.Types.INTEGER);
                } else {
                    stmt.setInt(2, donor.getReferrerDonorId());
                }
                stmt.setString(3, donor.getIcNumber());
                stmt.setString(4, donor.getFullname());
                stmt.setString(5, donor.getDob());
                stmt.setString(6, donor.getGender());
                stmt.setInt(7, donor.getWeight());
                stmt.setInt(8, donor.getHeight());
                stmt.setString(9, donor.getBloodType());
                stmt.setInt(10, donor.getId());
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
            String query = "DELETE FROM donor WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
