package entity;

public class User implements EntityInterface {
    private String username;
    private String password;

    public User(){
        this.username = "joselitoperalta";
        this.password = "peraltaJose123*";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String[] ExportSetMethodNames(){
        String[] names = new String[]{"setPassword-string:josesito123"};
        return names;
    }

    @Override
    public String[] ExportGetMethodNames(){
        String[] names = new String[]{"getPassword", "getUsername"};
        return names;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
