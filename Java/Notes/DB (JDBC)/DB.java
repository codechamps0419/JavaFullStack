/* @Author: Dynamic Duo Learners */

import java.sql.*;
import java.io.*;

class DB
{
 Connection conn;
 Statement stmt;
 
 DB() throws Exception
 {
   String userName = "system";
   String password = "manager";
   String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 

// Load the database driver
   DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
// Get the connection
   conn = DriverManager.getConnection(URL, userName, password); 
// To execute SQL statements, create a Statement Object
   //stmt = conn.createStatement();
   stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
   
 }//DB

 void close()throws Exception  
 {
   stmt.close();
   conn.close();
 }

 static String getString()
 {
  try
  {
   System.in.skip(System.in.available());
   byte arr[] = new byte[1024];
   int n;
   n = System.in.read(arr);
   return new String(arr, 0, n-2);
  }
  catch(IOException ex)
  {
    return " ";
  }
 } 

 void executeSelect(String sql)
 {
  try
  {
    int i,n;

    ResultSet rs = stmt.executeQuery(sql);

    //ResultSetMetaData holds schema information about the resultset
    ResultSetMetaData rsmd = rs.getMetaData();
    n = rsmd.getColumnCount();

    //next() : advances the resultset pointer to next row
    //returns true when it lands a data row
    //returns false when it lands a virtual row (AFTERLAST)
    while(rs.next())
    {
      System.out.println();
      //columns
      for(i = 1; i <= n; i++)
      {
        //fetch the resultSet[currentRow][coli] in String form 
        System.out.print(rs.getString(i) + "  ");
      }//for
    }//while

    while(rs.previous())
    {
      System.out.println();
      //columns
      for(i = 1; i <= n; i++)
      {
        //fetch the resultSet[currentRow][coli] in String form 
        System.out.print(rs.getString(i) + "  ");
      }//for
    }//while

    rs.close();
  }
  catch(Exception ex)
  {
   System.out.println(ex.getMessage());
  }
 }//executeSelect


 void executeNonSelect(String sql)
 {
  try
  {
    stmt.executeUpdate(sql);
  }
  catch(Exception ex)
  {
   System.out.println(ex.getMessage());
  }
 }//executeNonSelect

 void interact()
 {
  boolean flag = true;
  String sql, temp;
  do
  {
   System.out.print("\n JSQL> ");
   sql = getString();
   temp = sql.toLowerCase();

   if(temp.equals("exit"))
    flag = false;
   else if(temp.startsWith("select"))
    executeSelect(sql);
   else
    executeNonSelect(sql);    
  }while(flag);
 }

 public static void main(String args[])
 {
  try
  {
    DB db = new DB();
    db.interact();
    db.close();
  }
  catch(Exception ex)
  {
   System.out.println(ex);
  }
 }//main
}//DB