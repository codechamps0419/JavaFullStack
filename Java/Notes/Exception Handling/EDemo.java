//Program to implement : Exception Handling
//See : Exception Handling.png
//Refer : Exception Handling.txt

class EDemo
{
 public static void main(String args[])
 {
  System.out.println("Program starts");
  try
  {
   int a, b, c;
   a = Integer.parseInt(args[0]);
   b = Integer.parseInt(args[1]);
   c = a/b;
   System.out.println(a + " / "+ b + " = " + c);
  }
  catch(NumberFormatException ex)
  {
   System.out.println("Wrong Input");
  }
  catch(ArrayIndexOutOfBoundsException ex)
  {
   System.out.println("Usage : java EDemo <val1> <val2>");
  }
  catch(ArithmeticException ex)
  {
   System.out.println("INFINITY");
  }
  finally
  {
   System.out.println("FINALLY");
  }
  System.out.println("Program ends");
 }//main
}


/*
Integer.parseInt(String)

parseInt is a public and static method
of class Integer.
class Interger belongs to package java.lang
java.lang is default imported for every program.

parseInt takes a String parameter.
Attempts conversion into an int.
On success returns an int.
On failure NumberFormatException gets raised.
--------------------------------

In Java, if an array is accessed out of
its boundaries then it is a reason for
"ArrayIndexOutOfBoundsException"
*/