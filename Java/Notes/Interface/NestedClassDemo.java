/*
Java allows declaration of nested classes.
A nested class is a class defined inside 
a class or functions or block of code.

---------------------------------------
A nested class defined inside a class 

1) Acts as a member of the class.
2) Can directly access all other members 
(variables, functions, nested classes) of 
the enclosing class.
3) Can be non-static(default) or static .

3.a) non static nested class (inner class)
* can be used (by the members of enclosing class) through object of the class.
* can access all the members of the class.
* cannot have static members, other than static constant variables.

3.b) static nested class 
* can directly access static members of the enclosing class.
* can have static and non static members.
* allows direct use of its static members
* allows use of its non static members through its object.

4) Bytecode name : outer$inner.class
---------------------------------------
A nested class defined inside a function or a block of code
is also called as "local inner class"

1) Acts as a local entity of the function.
2) Can be non-static only.
3) Cannot have static members, other than static constant variables.
4) Can access all that the function can access.
5) Can be used in the local function/block of code only.
6) Bytecode name : outer$Ninner.class where N is an autoincremented number starting from 1.
*/

class Outer
{
 //DATA MEMBERS
 int x, y; //non static member variables
 static int z;//static member variable

 //METHODS, ...
 Outer()
 {
  x = 10;
  y = 20;
 }
 
 static
 {
  z = 100;
 }

 void display()
 {
  System.out.println(" x " + x);
  System.out.println(" y " + y);
  System.out.println(" z " + z);

  //using non static nested class in a non static member function
  Inner1 i1 = new Inner1();
  i1.display();  
  Inner2 i2 = new Inner2();
  i2.SquareOfTotal();
  SNested.message();
  SNested sn = new SNested();
  sn.display();

 }//display

 void methodWithClasses()
 {
  System.out.println("=================");
  int w = 1;

  class LN
  {
   void showGreater()
   {
     System.out.println("w : " + w);
     if(x > y && x > z)     
      System.out.println("Greatest : " +x);
     else if(y > z)
      System.out.println("Greatest : " +y);
     else
      System.out.println("Greatest : " +z);
   }
  }//LN

  class AnotherLN
  {
    void useSibling()
    {
     LN objLN = new LN();
     objLN.showGreater(); 
    }
  }

  //Use local nested class
  AnotherLN objALN = new AnotherLN();
  objALN.useSibling();
  System.out.println("=================");
 }

 //NESTED CLASSES
 class Inner1
 {
  int tot;
  Inner1()
  {
    tot = x+y+z;   
  } 

  void display()
  {
   System.out.println("Addition : " + tot);
  }
 }//Inner1  

 class Inner2
 {
  void SquareOfTotal()
  {
    Inner1 i1 = new Inner1();
    int sqr = i1.tot * i1.tot;
    System.out.println("Square of Total : " + sqr);
  }
 }//Inner2

 static class SNested 
 {
   int q;
   SNested()
   {
     q = z + 10;
   }

   void display()
   {
     System.out.println("q : " + q);
   }

   static void message()
   {
    System.out.println("A static nested class can have static members");
   }   
 }//SNested
}//Outer


class NestedClassDemo
{
 public static void main(String args[])
 {
  Outer o = new Outer();
  o.display();
  o.methodWithClasses();
 }//main
}//NestedClassDemo