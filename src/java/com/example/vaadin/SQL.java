package com.example.vaadin;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class SQL {

    private static DataSource dataSource;

    static {
        try {
            dataSource = (DataSource) new InitialContext().lookup("jdbc/Prospection");
        } catch (NamingException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection()
            throws SQLException {
        return dataSource.getConnection();
    }

    /*  returns a boolean value ( true/false [default false] ) */
    //public static String Bool(String strSQL) {
    
    /* returns a non-null integer, default 0 */
    public static int Int(String strSQL) {
        int result = 0;
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = getConnection().createStatement();
            stmt.executeQuery(strSQL);
            rs = stmt.getResultSet();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    /* returns a non-null string, default '' (empty) */
    //public static String Str(String strSQL) {
    
    /* executes a command (insert/update/procedure) */
    //public static void Do(String strSQL) {
    
    /* returns a non-null date (default '31/12/1999 23:59:59' ) */
    //public static java.sql.Timestamp Dat(String strSQL) {
    
    /* returns an array ( recordset -> array ) */
    /*
     table = new table()
     table.dataSource = sql.arr("select top 10 prenom, nom from users")
     
     * or *
     
     table.dataSource = sql.arr("select top 10 prenom, nom from users").toHashMap
     then, arr("prenom") --> "Jon"
     
     */
    //public static void Arr(String strSQL) {
    
    /* returns a vector ( 1 column recordset -> vector ) */
    /*
     for each value in sql.vec("select distinct mail from users")
        do something with the email
     next
     */
    //public static void Vec(String strSQL) {
    
}