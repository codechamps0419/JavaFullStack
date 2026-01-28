//program to demonstrate use of
//RandomAccessFile object to 
//read/write Java primitive types

import java.io.*;

class RAF2
{
 public static void main(String args[])
 {
  try
  {
   String fname = "d:/b.txt";
   String mode = "rw";

   RandomAccessFile raf = new RandomAccessFile(fname, mode);

   int i1, i2;
   boolean b1,b2;
   String s1,s2;
   double d1,d2;
  
   i1 = -13;
   b1 = true;
   s1 = "Where is my computer?";
   d1 = 4.562;

   raf.writeInt(i1);   
   raf.writeBoolean(b1);
   raf.writeUTF(s1);
   raf.writeDouble(d1);

   raf.seek(0);//rewind to bof

   i2 = raf.readInt();
   b2 = raf.readBoolean();
   s2 = raf.readUTF();
   d2 = raf.readDouble();

   System.out.println(i1 + "  " + i2);
   System.out.println(b1 + "  " + b2);
   System.out.println(s1 + "  " + s2);
   System.out.println(d1 + "  " + d2);
   
   raf.close();   
  }//try
  catch(Exception ex)
  {
   System.out.println(ex);
  }
 }//main
}