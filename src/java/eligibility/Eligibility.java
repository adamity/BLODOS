package eligibility;

import donor.*;

public class Eligibility {
    // Eligibility class represents a user in the database
    // - id
    // - donor_id
    // - sleep_hours
    // - meal_before_donation
    // - medical_illness
    // - high_risk_activity
    // - created_at
    // - updated_at
    // Note: id, created_at, updated_at are auto-generated on the database level

    protected int id;
    protected int donor_id;
    protected int sleep_hours;
    protected int meal_before_donation;
    protected int medical_illness;
    protected int high_risk_activity;

    public Eligibility() {
    }

    public Eligibility(
            int donor_id,
            int sleep_hours,
            int meal_before_donation,
            int medical_illness,
            int high_risk_activity) {
        this.donor_id = donor_id;
        this.sleep_hours = sleep_hours;
        this.meal_before_donation = meal_before_donation;
        this.medical_illness = medical_illness;
        this.high_risk_activity = high_risk_activity;
    }

    public Eligibility(
            int id,
            int donor_id,
            int sleep_hours,
            int meal_before_donation,
            int medical_illness,
            int high_risk_activity) {
        this.id = id;
        this.donor_id = donor_id;
        this.sleep_hours = sleep_hours;
        this.meal_before_donation = meal_before_donation;
        this.medical_illness = medical_illness;
        this.high_risk_activity = high_risk_activity;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getDonorId() {
        return donor_id;
    }

    public int getSleepHours() {
        return sleep_hours;
    }

    public int getMealBeforeDonation() {
        return meal_before_donation;
    }

    public int getMedicalIllness() {
        return medical_illness;
    }

    public int getHighRiskActivity() {
        return high_risk_activity;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDonorId(int donor_id) {
        this.donor_id = donor_id;
    }

    public void setSleepHours(int sleep_hours) {
        this.sleep_hours = sleep_hours;
    }

    public void setMealBeforeDonation(int meal_before_donation) {
        this.meal_before_donation = meal_before_donation;
    }

    public void setMedicalIllness(int medical_illness) {
        this.medical_illness = medical_illness;
    }

    public void setHighRiskActivity(int high_risk_activity) {
        this.high_risk_activity = high_risk_activity;
    }

    // Relationship
    public Donor getDonor() {
        DonorDAO donorDAO = new DonorDAO();
        return donorDAO.getById(donor_id);
    }

    // Other methods
    public String getStatus() {
        if (sleep_hours < 8) {
            return "Not Eligible";
        } else if (meal_before_donation < 1) {
            return "Not Eligible";
        } else if (medical_illness == 1) {
            return "Not Eligible";
        } else if (high_risk_activity == 1) {
            return "Not Eligible";
        } else {
            return "Eligible";
        }
    }

    // toString() method
    @Override
    public String toString() {
        return "Eligibility{" +
                "id=" + id +
                ", donor_id=" + donor_id +
                ", sleep_hours=" + sleep_hours +
                ", meal_before_donation=" + meal_before_donation +
                ", medical_illness=" + medical_illness +
                ", high_risk_activity=" + high_risk_activity +
                '}';
    }

    // toJSON() method
    public String toJSON() {
        return "{\"id\":" + id + ",\"donor_id\":" + donor_id + ",\"sleep_hours\":" + sleep_hours + ",\"meal_before_donation\":" + meal_before_donation + ",\"medical_illness\":" + medical_illness + ",\"high_risk_activity\":" + high_risk_activity + "}";
    }
}
