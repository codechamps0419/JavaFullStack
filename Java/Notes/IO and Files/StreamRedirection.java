/*
Java allows redirection of the 3 preset streams :
 System.in
 System.out
 System.err
to a compatible source/target.
This is done using the predefined methods:
  System.setIn( objectOfInputStream )
  System.setOut( objectOfPrintStream )
  System.setErr( objectOfPrintStream )

After redirect the stream fetches/writes
from/to the redirected source/target.

FYI
Custom streams doesnt require redirection 
as they are established as per choice.
*/

import java.io.*;

class StreamRedirection
{
 public static void main(String args[])
 {
  try
  {
    System.err.println("I am outputting through System.err");
    System.err.println("Data is reaching the monitor");

    //Create a file
    PrintStream ps = new PrintStream("d:/q.txt");

    //Backup the System.err
    PrintStream temp = System.err;
  
    //Redirect now
    System.setErr(ps);

    //write now
    System.err.println("I am redirected to output to a file");
    System.err.println("Data reaching the file can be read later");

    //restore
    System.setErr(temp);
    System.err.println("I am back");
    System.err.println("Redirection is important");
   
    ps.close();
 
  }
  catch(Exception ex)
  {
   System.out.println(ex);
  }
 }//main
}//StreamRedirection