package donationdonationtype;

public class DonationDonationType {
    // DonationDonationType class represents a user in the database
    // - id
    // - donation_id
    // - donation_type_id
    // - created_at
    // - updated_at
    // Note: id, created_at, updated_at are auto-generated on the database level

    protected int id;
    protected int donation_id;
    protected int donation_type_id;

    public DonationDonationType() {
    }

    public DonationDonationType(int donation_id, int donation_type_id) {
        this.donation_id = donation_id;
        this.donation_type_id = donation_type_id;
    }

    public DonationDonationType(int id, int donation_id, int donation_type_id) {
        this.id = id;
        this.donation_id = donation_id;
        this.donation_type_id = donation_type_id;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getDonationId() {
        return donation_id;
    }

    public int getDonationTypeId() {
        return donation_type_id;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDonationId(int donation_id) {
        this.donation_id = donation_id;
    }

    public void setDonationTypeId(int donation_type_id) {
        this.donation_type_id = donation_type_id;
    }

    // toString() method
    @Override
    public String toString() {
        return "DonationDonationType{" +
                "id=" + id +
                ", donation_id='" + donation_id + '\'' +
                ", donation_type_id='" + donation_type_id + '\'' +
                '}';
    }

    // toJSON() method
    public String toJSON() {
        return "{\"id\":" + id + ",\"donation_id\":\"" + donation_id + "\",\"donation_type_id\":\"" + donation_type_id + "\"}";
    }
}
