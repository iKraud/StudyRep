package hw15Task1;

public class User {
    private int id;
    private String name;
    private String birthday;
    private int loginID;
    private String city;
    private String email;
    private String description;

    public User(int id, String name, String birthday, int loginID, String city, String email, String description) {
        setId(id);
        setName(name);
        setBirthday(birthday);
        setLoginID(loginID);
        setCity(city);
        setEmail(email);
        setDescription(description);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public int getLoginID() {
        return loginID;
    }
    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}