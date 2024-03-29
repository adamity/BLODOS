package donation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utility.AnsiColors;

public class DonationDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/BLODOS?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Implement DAO methods:
    // * getAll()
    // * getById(int id)
    // * getDonationsByStaffUserId(int userId)
    // * getDonationsByDonorId(int donorId)
    // * getDonationsByDonationTypeId(int donationTypeId) // Bridge table donation->donation_donation_type<-donation_type
    // * add(Donation donation)
    // * update(Donation donation)
    // * delete(int id)

    public DonationDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Do simple testing here
        DonationDAO donationDAO = new DonationDAO();

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
        Donation donationAdd = new Donation(lastDonorID, lastUserId, "2021-01-01", "12:00:00", 470, "Pending");
        String success = donationDAO.add(donationAdd);
        System.out.println(AnsiColors.ANSI_YELLOW + "Add Donation: " + success + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "ADD PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getAll()
        List<Donation> donations = donationDAO.getAll();
        Integer lastID = null;
        for (Donation donation : donations) {
            System.out.println(AnsiColors.ANSI_YELLOW + "Donation " + donation.getId() + ": " + donation.toString() + AnsiColors.ANSI_RESET);
            lastID = donation.getId();
        }
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET ALL PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test getById()
        Donation donation = donationDAO.getById(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Get by ID: " + donation.toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "GET BY ID PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test update()
        donation.setQuantity(200);
        donationDAO.update(donation);
        System.out.println(AnsiColors.ANSI_YELLOW + "Update Donation: " + donationDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "UPDATE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Test delete()
        donationDAO.delete(lastID);
        System.out.println(AnsiColors.ANSI_YELLOW + "Delete Donation: " + donationDAO.getById(lastID).toString() + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW_BACKGROUND + AnsiColors.ANSI_BLUE + "DELETE PASSED" + AnsiColors.ANSI_RESET + "\n");

        // Pass
        System.out.println(AnsiColors.ANSI_GREEN_BACKGROUND + AnsiColors.ANSI_BLUE + "PASSED ALL TESTS" + AnsiColors.ANSI_RESET);
    }

    // * getAll()
    public List<Donation> getAll() {
        List<Donation> donations = new ArrayList<Donation>();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donation";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Donation donation = new Donation();
                    donation.setId(rs.getInt("id"));
                    donation.setDonorId(rs.getInt("donor_id"));
                    donation.setUserId(rs.getInt("user_id"));
                    donation.setDate(rs.getString("date"));
                    donation.setTime(rs.getString("time"));
                    donation.setQuantity(rs.getInt("quantity"));
                    donation.setStatus(rs.getString("status"));
                    donations.add(donation);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donations;
    }

    // * getById(int id)
    public Donation getById(int id) {
        Donation donation = new Donation();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donation WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    donation.setId(rs.getInt("id"));
                    donation.setDonorId(rs.getInt("donor_id"));
                    donation.setUserId(rs.getInt("user_id"));
                    donation.setDate(rs.getString("date"));
                    donation.setTime(rs.getString("time"));
                    donation.setQuantity(rs.getInt("quantity"));
                    donation.setStatus(rs.getString("status"));
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donation;
    }

    // * getDonationsByStaffUserId(int userId)
    public List<Donation> getDonationsByStaffUserId(int userId) {
        List<Donation> donations = new ArrayList<Donation>();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donation WHERE user_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Donation donation = new Donation();
                    donation.setId(rs.getInt("id"));
                    donation.setDonorId(rs.getInt("donor_id"));
                    donation.setUserId(rs.getInt("user_id"));
                    donation.setDate(rs.getString("date"));
                    donation.setTime(rs.getString("time"));
                    donation.setQuantity(rs.getInt("quantity"));
                    donation.setStatus(rs.getString("status"));
                    donations.add(donation);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donations;
    }

    // * getDonationsByDonationTypeId(int donationTypeId)
    public List<Donation> getDonationsByDonationTypeId(int donationTypeId) {
        List<Donation> donations = new ArrayList<Donation>();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donation JOIN donation_donation_type ON donation.id = donation_donation_type.donation_id WHERE donation_donation_type.donation_type_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, donationTypeId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Donation donation = new Donation();
                    donation.setId(rs.getInt("id"));
                    donation.setDonorId(rs.getInt("donor_id"));
                    donation.setUserId(rs.getInt("user_id"));
                    donation.setDate(rs.getString("date"));
                    donation.setTime(rs.getString("time"));
                    donation.setQuantity(rs.getInt("quantity"));
                    donation.setStatus(rs.getString("status"));
                    donations.add(donation);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donations;
    }

    // * getDonationsByDonorId(int donorId)
    public List<Donation> getDonationsByDonorId(int donorId) {
        List<Donation> donations = new ArrayList<Donation>();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM donation WHERE donor_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, donorId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Donation donation = new Donation();
                    donation.setId(rs.getInt("id"));
                    donation.setDonorId(rs.getInt("donor_id"));
                    donation.setUserId(rs.getInt("user_id"));
                    donation.setDate(rs.getString("date"));
                    donation.setTime(rs.getString("time"));
                    donation.setQuantity(rs.getInt("quantity"));
                    donation.setStatus(rs.getString("status"));
                    donations.add(donation);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donations;
    }

    // * getTopDonatedBloodType()
    public String getTopDonatedBloodType() {
        String bloodType = null;

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT blood_type FROM donor JOIN donation ON donor.id = donation.donor_id GROUP BY blood_type ORDER BY COUNT(*) DESC LIMIT 1";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    bloodType = rs.getString("blood_type");
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bloodType;
    }

    // * getTopDonationTypes()
    public List<String> getTopDonationTypes() {
        List<String> donationTypes = new ArrayList<String>();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT donation_type.type_name FROM donation JOIN donation_donation_type ON donation.id = donation_donation_type.donation_id JOIN donation_type ON donation_donation_type.donation_type_id = donation_type.id GROUP BY donation_type.type_name ORDER BY COUNT(*) DESC LIMIT 3;";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    donationTypes.add(rs.getString("type_name"));
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donationTypes;
    }

    // * add(Donation donation)
    public String add(Donation donation) {
        String insertId = null;

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO donation (donor_id, user_id, date, time, quantity, status) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, donation.getDonorId());
                stmt.setInt(2, donation.getUserId());
                stmt.setString(3, donation.getDate());
                stmt.setString(4, donation.getTime());
                stmt.setInt(5, donation.getQuantity());
                stmt.setString(6, donation.getStatus());
                stmt.executeUpdate();

                // Get last inserted ID
                query = "SELECT id FROM donation ORDER BY id DESC LIMIT 1";
                try (PreparedStatement stmt2 = conn.prepareStatement(query)) {
                    ResultSet rs = stmt2.executeQuery();
                    if (rs.next()) {
                        insertId = rs.getString("id");
                    }
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return insertId;
    }

    // * update(Donation donation)
    public void update(Donation donation) {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE donation SET donor_id = ?, user_id = ?, date = ?, time = ?, quantity = ?, status = ? WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, donation.getDonorId());
                stmt.setInt(2, donation.getUserId());
                stmt.setString(3, donation.getDate());
                stmt.setString(4, donation.getTime());
                stmt.setInt(5, donation.getQuantity());
                stmt.setString(6, donation.getStatus());
                stmt.setInt(7, donation.getId());
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
            String query = "DELETE FROM donation WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
