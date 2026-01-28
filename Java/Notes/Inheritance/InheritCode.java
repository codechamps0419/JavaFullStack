/*
Program to demonstarte : Inheritance of Code
Refer : Inheritance.txt
See : Inheritance.png

*/

class A
{
 static
 {
  System.out.println("SIB A");
 }

 void fx1()
 {
  System.out.println("A fx1");
 }

 void fx2()
 {
  System.out.println("A fx2");
 }
}//A


class B extends A
{
 static
 {
  System.out.println("SIB B");
 }

 //overriding
 //redefining an inherited method
 void fx2()
 {
  System.out.println("B fx2");
 }

 void fx3()
 {
  System.out.println("B fx3");
 }
}

class InheritCode
{
 public static void main(String args[])
 {
  System.out.println("-------------");
  B objB = new B();
  System.out.println("-------------");
  objB.fx1();//inherited code
  objB.fx2();//inherited code
  objB.fx3();//extended code

 }//main
}//InheritCode