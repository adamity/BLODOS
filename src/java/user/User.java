package user;

public class User {
    // User class represents a user in the database
    // - id
    // - role
    // - firstname
    // - lastname
    // - username
    // - password
    // - created_at
    // - updated_at
    // Note: id, created_at, updated_at are auto-generated on the database level

    protected int id;
    protected String role;
    protected String firstname;
    protected String lastname;
    protected String username;
    protected String password;

    public User() {
    }

    public User(String role, String firstname, String lastname, String username, String password) {
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public User(int id, String role, String firstname, String lastname, String username, String password) {
        this.id = id;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role.toLowerCase();
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Other methods
    public boolean isValid() {
        return role != null && !role.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !username.isEmpty() && !password.isEmpty();
    }

    // toString() method
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    // toJSON() method
    public String toJSON() {
        return "{\"id\":" + id + ",\"role\":\"" + role + "\",\"firstname\":\"" + firstname + "\",\"lastname\":\"" + lastname + "\",\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
    }
}
