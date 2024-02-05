package donor;

import user.*;

public class Donor {
    // Donor class represents a user in the database
    // - id
    // - user_id
    // - referrer_donor_id
    // - ic_number
    // - fullname
    // - dob
    // - gender
    // - weight
    // - height
    // - blood_type
    // - created_at
    // - updated_at
    // Note: id, created_at, updated_at are auto-generated on the database level

    protected int id;
    protected int user_id;
    protected Integer referrer_donor_id; // Updated to accept null value
    protected String ic_number;
    protected String fullname;
    protected String dob;
    protected String gender;
    protected int weight;
    protected int height;
    protected String blood_type;

    public Donor() {
    }

    public Donor(
            int user_id,
            Integer referrer_donor_id, // Updated to accept null value
            String ic_number,
            String fullname,
            String dob,
            String gender,
            int weight,
            int height,
            String blood_type) {
        this.user_id = user_id;
        this.referrer_donor_id = referrer_donor_id;
        this.ic_number = ic_number;
        this.fullname = fullname;
        this.dob = dob;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.blood_type = blood_type;
    }

    public Donor(
            int id,
            int user_id,
            Integer referrer_donor_id, // Updated to accept null value
            String ic_number,
            String fullname,
            String dob,
            String gender,
            int weight,
            int height,
            String blood_type) {
        this.id = id;
        this.user_id = user_id;
        this.referrer_donor_id = referrer_donor_id;
        this.ic_number = ic_number;
        this.fullname = fullname;
        this.dob = dob;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.blood_type = blood_type;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public Integer getReferrerDonorId() { // Updated to return Integer type
        return referrer_donor_id;
    }

    public String getIcNumber() {
        return ic_number;
    }

    public String getFullname() {
        return fullname;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public String getBloodType() {
        return blood_type;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setReferrerDonorId(Integer referrer_donor_id) { // Updated to accept Integer type
        this.referrer_donor_id = referrer_donor_id;
    }

    public void setIcNumber(String ic_number) {
        this.ic_number = ic_number;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBloodType(String blood_type) {
        this.blood_type = blood_type;
    }

    // Other methods
    public int getTotalDonation() {
        // TODO: Implement this method
        return 0;
    }

    public int getTotalEligibility() {
        // TODO: Implement this method
        return 0;
    }

    // Relationship
    public User getStaffUser() {
        UserDAO userDAO = new UserDAO();
        return userDAO.getById(user_id);
    }

    public Donor getReferrerDonor() {
        if (referrer_donor_id != null) {
            DonorDAO donorDAO = new DonorDAO();
            return donorDAO.getById(referrer_donor_id);
        } else {
            return null;
        }
    }

    // toString() method
    @Override
    public String toString() {
        return "Donor{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", referrer_donor_id=" + referrer_donor_id +
                ", ic_number='" + ic_number + '\'' +
                ", fullname='" + fullname + '\'' +
                ", dob='" + dob + '\'' +
                ", gender=" + gender + '\'' +
                ", weight=" + weight + '\'' +
                ", height=" + height + '\'' +
                ", blood_type=" + blood_type + '\'' +
                '}';
    }

    // toJSON() method
    public String toJSON() {
        return "{\"id\":" + id + ",\"user_id\":" + user_id + ",\"referrer_donor_id\":" + referrer_donor_id + ",\"ic_number\":\"" + ic_number + "\",\"fullname\":\"" + fullname + "\",\"dob\":\"" + dob + "\",\"gender\":\"" + gender + "\",\"weight\":" + weight + ",\"height\":" + height + ",\"blood_type\":\"" + blood_type + "\"}";
    }
}
