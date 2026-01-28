import java.io.*;

class FRead
{
 public static void main(String args[])
 {
  String fname = "";
  try
  {
   fname = args[0];
   //To open file for reading (in binary mode), create an object of FileInputStream
   FileInputStream fin = new FileInputStream(fname);//file must exist, otherwise FileNotFoundException is raised.

   //read the file
   int x;
   while( (x = fin.read()) != -1)
     System.out.write(x);

   System.out.flush();

   //To close the file
   fin.close();
  }
  catch(FileNotFoundException ex)
  {
   System.out.println("File : " + fname + " doesn't exist");
  }
  catch(IOException ex)
  {
   System.out.println("IOError ");
  }
  catch(ArrayIndexOutOfBoundsException ex)
  {
   System.out.println("Usage : java FRead <fileName>");
  } 
 }//main
}//FRead