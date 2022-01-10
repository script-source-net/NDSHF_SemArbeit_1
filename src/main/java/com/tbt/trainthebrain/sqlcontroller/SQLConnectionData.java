package com.tbt.trainthebrain.sqlcontroller;

public class SQLConnectionData {
    private final static String URL = "jdbc:mysql://webdev.script-source.net:3306/trainthebrain";
    private final static String USER = "trainthebrain";
    private final static String PASSWORD = "dhKKNUOIxgG@7Ivx";

    public static String getURL(){
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
