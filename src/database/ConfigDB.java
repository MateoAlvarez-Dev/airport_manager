package database;

public enum ConfigDB {
    USERNAME("uirnq6zhde2i5pnf"),
    PASSWORD("eUqH0rVEizjzHNFahZ7O"),
    URL("jdbc:mysql://b6aggcgdvevluf65e2zs-mysql.services.clever-cloud.com:3306/b6aggcgdvevluf65e2zs");

    private String value;

    ConfigDB(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
