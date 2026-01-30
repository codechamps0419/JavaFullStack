//program to demonstrate use of
//RandomAccessFile object to 
//1) read/write bytes 
//2) seek
//3) File length and current pointer position

import java.io.*;

class RAF1
{
 public static void main(String args[])
 {
  try
  {
   String fname = "d:/a.txt";
   String mode = "rw";

   RandomAccessFile raf = new RandomAccessFile(fname, mode);

   byte arr[] = {97,98,99,100,101};
   raf.write(arr);//write a byte array
   raf.write(102);//write a byte
   raf.seek(3);//postion the file pointer at offset 3 from BOF. In case seek value > file size then the file expands to make the seek success.
   raf.write('X');//write a char

   raf.seek(0);//rewind to BOF
   do
   {
    System.out.write(raf.read());
   }while(raf.getFilePointer() < raf.length());
   System.out.flush();

//raf.getFilePointer() : current file pointer position as an offset from the bof
//raf.length() : the size of the file in bytes.

   raf.close();   
  }//try
  catch(Exception ex)
  {
   System.out.println(ex);
  }
 }//main
}