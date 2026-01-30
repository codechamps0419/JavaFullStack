import java.io.*;

class FRead1
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
   int n;
   byte arr[] = new byte[1024];
   while( (n = fin.read(arr)) != -1) //it reads up to 1024 bytes all at once instead of fin.read() which reads 1 byte of data at a time
     System.out.write(arr, 0, n);

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
   System.out.println("Usage : java FRead1 <fileName>");
  } 
 }//main
}//FRead1

/*
read(arr) :
The method returns an integer (int), which represents the actual number of bytes 
that were successfully read into the array during that specific turn.

This number is critical because of three possible scenarios:

1. The Full Buffer: If the file has plenty of data left, it will completely 
fill your array and return 1024.

2. The Partial Buffer (End of File near): If there are only, say, 300 bytes left in the file, 
it will put those 300 bytes into the array and return 300.

3. The End of File (EOF): If you have already read everything and there is 
absolutely nothing left to read, it returns -1.

*/