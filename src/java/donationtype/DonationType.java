package donationtype;

import java.util.List;
import donation.*;

public class DonationType {
    // DonationType class represents a user in the database
    // - id
    // - type_name
    // - created_at
    // - updated_at
    // Note: id, created_at, updated_at are auto-generated on the database level

    protected int id;
    protected String type_name;

    public DonationType() {
    }

    public DonationType(String type_name) {
        this.type_name = type_name;
    }

    public DonationType(int id, String type_name) {
        this.id = id;
        this.type_name = type_name;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTypeName() {
        return type_name;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTypeName(String type_name) {
        this.type_name = type_name;
    }

    // Relationship
    public List<Donation> getDonations() {
        DonationDAO donationDAO = new DonationDAO();
        return donationDAO.getDonationsByDonationTypeId(id);
    }

    // toString() method
    @Override
    public String toString() {
        return "DonationType{" +
                "id=" + id +
                ", type_name='" + type_name + '\'' +
                '}';
    }

    // toJSON() method
    public String toJSON() {
        return "{\"id\":" + id + ",\"type_name\":\"" + type_name + "\"}";
    }

    public static String toJSONArray(List<DonationType> donationTypeList) {
        StringBuilder jsonArray = new StringBuilder("[");
        for (int i = 0; i < donationTypeList.size(); i++) {
            jsonArray.append(donationTypeList.get(i).toJSON());
            if (i < donationTypeList.size() - 1) {
                jsonArray.append(",");
            }
        }
        jsonArray.append("]");
        return jsonArray.toString();
    }
}
