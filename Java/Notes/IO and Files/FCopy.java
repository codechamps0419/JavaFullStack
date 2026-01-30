import java.io.*;

class FCopy
{
 public static void main(String args[])
 {
  String fname1 = "";
  String fname2 = "";
  try
  {
   fname1 = args[0];
   fname2 = args[1];

   //open the source file for reading (in binary mode)
   FileInputStream fin = new FileInputStream(fname1);//file must exist, otherwise FileNotFoundException is raised.

   //open the target file for writing (in binary mode)
   FileOutputStream fout = new FileOutputStream(fname2);//file will be created or overwritten. IllegalPath, Permission Denied would cause IOException


   //copy the file
   int x;
   while( (x=fin.read()) != -1)
     fout.write(x);

   fout.flush();

   //close the file
   fin.close();
   fout.close();
   System.out.println("File Copied");
  }
  catch(FileNotFoundException ex)
  {
   System.out.println(ex.getMessage());
  }
  catch(IOException ex)
  {
   System.out.println("IOError ");
  }
  catch(ArrayIndexOutOfBoundsException ex)
  {
   System.out.println("Usage : java FCopy <sourceFileName> <targetFileName>");
  } 
 }//main
}//FCopy