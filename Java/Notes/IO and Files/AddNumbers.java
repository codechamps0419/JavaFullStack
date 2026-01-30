//testing number inputs
import java.io.*;

class AddNumbers
{
 static double getNumber()
 {
  try
  {
   System.in.skip(System.in.available()); 
   byte arr[] = new byte[20];
   int n = System.in.read(arr);
   String s = new String(arr,0, n-2);
   return Double.parseDouble(s);
  }
  catch(Exception ex)
  {
   return 0; 
  } 
 }//getNumber

 public static void main(String args[])
 {
  double a, b , c;
  System.out.println("Enter 2 numbers ");
  a = getNumber();
  b = getNumber();
  c=  a+ b;
  System.out.println(a + " + " + b + " = " + c);
 }
}