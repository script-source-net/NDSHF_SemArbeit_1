package com.tbt.trainthebrain.sqlcontroller;

import java.sql.*;

public class SQLConnectionTest {
    public static void main(String[] args) {
        try(Connection con = DriverManager.getConnection(
                SQLConnectionData.getURL(),
                SQLConnectionData.getUSER(),
                SQLConnectionData.getPASSWORD())
        ){
            DatabaseMetaData meta = con.getMetaData();
            System.out.println(meta.getDriverName() + "\n" + meta.getDriverVersion() +"\nURL: "+ meta.getURL()
            + "\nUsername: " + meta.getUserName());
            System.out.println("Connection OK!");
        } catch (SQLException e){
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
        }
    }
}
