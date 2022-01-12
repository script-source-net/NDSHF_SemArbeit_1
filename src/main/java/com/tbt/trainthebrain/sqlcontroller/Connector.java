package com.tbt.trainthebrain.sqlcontroller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private String url;
    private String database;
    private String databaseType;
    private int port;
    private String user;
    private String pwd;
    private Connection connection = null;

    public Connector(String url, int port, String database,String databaseType, String user, String pwd) {
        this.url = url;
        this.database = database;
        this.databaseType = databaseType;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public Connector(){
        this.url = SQLConnectionData.getURL();
        this.database = SQLConnectionData.getDB();
        this.databaseType = SQLConnectionData.getDBType();
        this.port = SQLConnectionData.getPort();
        this.user = SQLConnectionData.getUSER();
        this.pwd = SQLConnectionData.getPASSWORD();
    }

    public Connection getConnection(){

        String address = "jdbc:"+databaseType+"://" + url;
        if(this.port != 0){
            address += ":"+this.port;
        }
        if(this.database != null && !this.database.isEmpty()){
            address += "/"+database;
        }
        System.out.println(address);
        try{
            connection = DriverManager.getConnection(address, this.user, this.pwd);
        }catch(SQLException sqle){
            System.out.println("SQL Exception beim Verbindungsaufbau aufgetreten");
            System.out.println(sqle.getMessage());
        }
        return connection;
    }
}