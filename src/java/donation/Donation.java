package donation;

import donor.*;
import user.*;

public class Donation {
    // Donation class represents a user in the database
    // - id
    // - donor_id
    // - user_id
    // - date
    // - time
    // - quantity
    // - status
    // - created_at
    // - updated_at
    // Note: id, created_at, updated_at are auto-generated on the database level

    protected int id;
    protected int donor_id;
    protected int user_id;
    protected String date;
    protected String time;
    protected int quantity;
    protected String status;

    public Donation() {
    }

    public Donation(int donor_id, int user_id, String date, String time, int quantity, String status) {
        this.donor_id = donor_id;
        this.user_id = user_id;
        this.date = date;
        this.time = time;
        this.quantity = quantity;
        this.status = status;
    }

    public Donation(int id, int donor_id, int user_id, String date, String time, int quantity, String status) {
        this.id = id;
        this.donor_id = donor_id;
        this.user_id = user_id;
        this.date = date;
        this.time = time;
        this.quantity = quantity;
        this.status = status;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getDonorId() {
        return donor_id;
    }

    public int getUserId() {
        return user_id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDonorId(int donor_id) {
        this.donor_id = donor_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time= time;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Relationship
    public Donor getDonor() {
        DonorDAO donorDAO = new DonorDAO();
        return donorDAO.getById(donor_id);
    }

    public User getStaffUser() {
        UserDAO userDAO = new UserDAO();
        return userDAO.getById(user_id);
    }

    // toString() method
    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", donor_id=" + donor_id +
                ", user_id=" + user_id +
                ", date=" + date +
                ", time=" + time +
                ", quantity=" + quantity +
                ", status=" + status +
                '}';
    }

    // toJSON() method
    public String toJSON() {
        return "{\"id\":" + id + ",\"donor_id\":" + donor_id + ",\"user_id\":" + user_id + ",\"date\":\"" + date + "\",\"time\":\"" + time + "\",\"quantity\":" + quantity + ",\"status\":\"" + status + "\"}";
    }
}
