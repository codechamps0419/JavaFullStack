/*
Implementing Singleton class.

A Singleton class is a class that can be instantiated only once.
i.e. its only one object can be created.

Ideally it refers to a resource that is one across many.

Steps to make a Singleton class.
1) Hide all the constructors of the class.
2) Provide a static method that creates one object and returns the same everytime.

*/

class Ston
{
 private static Ston flag = null; 
 int x, y; //data members

 private Ston()//not accessible outside the class
 {
   System.out.println("Ston()");
   x = 10; 
   y = 20;
 }

 void display()
 {
   System.out.println(x + "  " + y);
 }

 static Ston getObject()
 {
  System.out.println("Ston getObject()");
  if(flag == null)
   flag = new Ston();

  return flag;
 }
}//Ston

class UseSton
{
 public static void main(String args[])
 {
  Ston s1,s2;
   
  s1 = Ston.getObject();
  s2 = Ston.getObject();

  s1.display();
  s2.display();

  if(s1 == s2)
   System.out.println("2 references refer to the same object");
  else
   System.out.println("2 references refer to the different objects");

 }
}//UseSton
