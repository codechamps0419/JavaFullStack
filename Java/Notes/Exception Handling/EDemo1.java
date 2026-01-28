//Program to implement
//Exception handling across dependent methods of an operation
//Nested try blocks

//See : Exception Handling.png and Nested Exceptions.png
//Refer : Exception Handling.txt

class EDemo1
{
 int a, b, c;

 EDemo1()
 {
   a = 10;
   b = 5;
   c = 0;
 }//EDemo1

 EDemo1(String arr[]) throws NumberFormatException, ArrayIndexOutOfBoundsException
 {
   a = Integer.parseInt(arr[0]);
   b = Integer.parseInt(arr[1]);
   c = 0;
 }//EDemo1

 void process() throws ArithmeticException
 {
  c = a/b;
 }

 void display()
 {
   System.out.println(a + " / "+ b + " = " + c);
 }

 public static void main(String args[])
 {
  System.out.println("Program starts");

  try
  {
   EDemo1 e1;
   try
   {
     e1 = new EDemo1(args);
   }
   catch(ArrayIndexOutOfBoundsException ex)
   {
    e1 = new EDemo1();
   }

   e1.process();
   e1.display();
  }
  catch(NumberFormatException ex)
  {
   System.out.println("Wrong Input");
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
}//EDemo1
