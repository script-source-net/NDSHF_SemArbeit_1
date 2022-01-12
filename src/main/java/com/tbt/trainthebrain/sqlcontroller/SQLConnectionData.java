package com.tbt.trainthebrain.sqlcontroller;

public class SQLConnectionData {
    private final static String URL = "jdbc:mysql://webdev.script-source.net:3306/trainthebrain";
    private final static String USER = "trainthebrain";
    private final static String PASSWORD = "dhKKNUOIxgG@7Ivx";
    private final static String DATABASE = "trainthebrain";
    private final static String DATABASETYPE = "mysql";
    private final static int PORT =3306;

    public static String getURL(){
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getDB(){
        return DATABASE;
    }
    public static String getDBType(){
        return DATABASETYPE;
    }
    public static int getPort(){
        return PORT;
    }


}
