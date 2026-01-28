import java.io.*;

class IO
{
 static void ioTest1()
 {
  try
  {
   //read data from System.in
   int x;
   do
   {
      x = System.in.read();
      System.out.write(x);
   }while(System.in.available() > 0);
   System.out.flush();
  }//try
  catch(IOException ex)
  {
   System.out.println("Err : " + ex);
  }
 }//ioTest1


 static void ioTest2()
 {
  try
  {
    byte arr[] = new byte[100];
    int n;
    n = System.in.read(arr);
    System.out.write(arr, 0, n-2);
    System.out.flush();
  }
  catch(IOException ex)
  {
   System.out.println("Err : " + ex);
  }
 }//ioTest2

 public static void main(String args[])
 {
  System.out.println("Enter data : ");
  //ioTest1();
  ioTest2();
 }
}