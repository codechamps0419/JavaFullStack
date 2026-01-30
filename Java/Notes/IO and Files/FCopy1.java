import java.io.*;

class FCopy1
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
   int n;
   byte arr[] = new byte[1024]; 
   while( (n=fin.read(arr)) != -1){
     fout.write(arr, 0, n);  //it writes 1024 or given n bytes all at once instead of fin.write() which writes 1 byte of data at a time
   }

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
   System.out.println("Usage : java FCopy1 <sourceFileName> <targetFileName>");
  } 
 }//main
}//FCopy1


/*
The method write(arr, 0, n) writes a specific, targeted portion of a byte array 
into an output stream (like a file).

It is the industry standard way to write data from a buffer loop because it prevents file corruption on the final read cycle.
write( arr, 0 , n )
        │  │   │
        │  │   └─── len: How many bytes to write from that point.
        │  └───────── off: The starting index (offset) inside the array.
        └─────────────── b: The source byte array buffer.

*/