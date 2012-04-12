package com.example.vaadin;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.QueryContainer;
import com.vaadin.ui.Table;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL {
    
    /* 
     * server on da-sisi2 
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
    }*/

    
    /* server on local   
     * lib sqljdbc4.jar
     */
     private static Connection  con = null;
     private final static String url = "jdbc:sqlserver://";
     private final static String serverName= "da-newdm";
     private final static String portNumber = "1433";
     private final static String databaseName= "db_prospection";
     private final static String userName = "sa";
     private final static String password = "limubai64";
     // Informs the driver to use server a side-cursor,
     // which permits more than one active statement
     // on a connection.
     private final static String selectMethod = "cursor";

     private static String getConnectionUrl(){
          return url+serverName+":"+portNumber+";databaseName="+databaseName+";selectMethod="+selectMethod+";";
     }

     private static Connection getConnection(){
          try{
               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
               con = DriverManager.getConnection(getConnectionUrl(),userName,password);
               if(con!=null) System.out.println("Connection Successful!");
          }catch(Exception e){
               e.printStackTrace();
               System.out.println("Error Trace in getConnection() : " + e.getMessage());
         }
          return con;
      }
    
    
    /*  returns a boolean value ( true/false [default false] ) */
    public static Boolean Bool(String strSQL) {
        Boolean result = false;
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = getConnection().createStatement();
            stmt.executeQuery(strSQL);
            rs = stmt.getResultSet();
            if (rs.next()) {
                result = rs.getBoolean(1);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
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
    public static String Str(String strSQL) {
        String result = "";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = getConnection().createStatement();
            stmt.executeQuery(strSQL);
            rs = stmt.getResultSet();
            if (rs.next()) {
                result = rs.getString(1);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    /* executes a command (insert/update/procedure) */
    public static void Do(String strSQL) {
        Statement stmt;
        
        try {
            stmt = getConnection().createStatement();
            stmt.executeUpdate(strSQL);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /*
     * returns a non-null date (default '31/12/1999 23:59:59' )
     */
    public static Timestamp Dat(String strSQL) throws ParseException {
        String timeDefault = "31/12/1999 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        java.util.Date date = sdf.parse(timeDefault);
        Timestamp result = new Timestamp(date.getTime());

        Statement stmt;
        ResultSet rs;

        try {
            stmt = getConnection().createStatement();
            stmt.executeQuery(strSQL);
            rs = stmt.getResultSet();
            if (rs.next()) {
                result = rs.getTimestamp(1);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /*
     * returns an array ( recordset -> array )
     */
    /*
     * table = new table() table.dataSource = sql.arr("select top 10 prenom, nom
     * from users")
     *
     * or *
     *
     * table.dataSource = sql.arr("select top 10 prenom, nom from
     * users").toHashMap then, arr("prenom") --> "Jon"
     *
     */
    public static ArrayList<HashMap> Arr(String strSQL) {
        Statement stmt;
        ResultSet rs;
        ArrayList list = new ArrayList<HashMap>();
        int columnCount;

        try {
            stmt = getConnection().createStatement();
            stmt.executeQuery(strSQL);
            System.out.println(strSQL);

            rs = stmt.getResultSet();
            ResultSetMetaData md = rs.getMetaData();
            columnCount = md.getColumnCount();
            while (rs.next()) {
                HashMap row = new HashMap(columnCount);
                for (int i = 1; i <= columnCount; ++i) {
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(row);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /*return a container for datasource*/
    /*
     * an example:
     *  Table t = new Table();
        t.setContainerDataSource(SQL.Container("select id, mail, nom from Prospecteurs"));
     */
    public static QueryContainer Container(String strSQL) {
        QueryContainer qcSQL = null;

        try {
            qcSQL= new QueryContainer(strSQL,getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qcSQL;
    }
      

    /* returns a vector ( 1 column recordset -> vector ) */
    /*
     for each value in sql.vec("select distinct mail from users")
        do something with the email
     next
     */
    public static ArrayList Vec(String strSQL) {
        Statement stmt;
        ResultSet rs;
        ArrayList list = new ArrayList();

        try {
            stmt = getConnection().createStatement();
            stmt.executeQuery(strSQL);
            System.out.println(strSQL);

            rs = stmt.getResultSet();
            while (rs.next()) {
                list.add(rs.getObject(1));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
      
}