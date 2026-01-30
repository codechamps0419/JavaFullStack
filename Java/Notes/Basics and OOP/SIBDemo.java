class OnTest
{
 int x, y; //non static member variables
 static int cnt; //static member variables

 static
 {
  System.out.println("In SIB of OnTest");
  cnt = 0;
 }

 {
  System.out.println("NSIB");
  x=y=1;
 }

 OnTest()
 {
   System.out.println("OnTest()");
   x++;//can access non static members
   y++;//can access non static members
  
   cnt++;//can access static members
 }//OnTest()

 void display()
 {
  System.out.println(x + " " + y);
  System.out.println("Object number : "+ cnt);
 } 
}//OnTest

class SIBDemo
{
 static
 {
   System.out.println("In SIB of SIBDemo");
 }

 public static void main(String args[])
 {
  System.out.println("---- main ----");

  System.out.println("--------------");
  OnTest obj1 = new OnTest();
  System.out.println("--------------");
  OnTest obj2 = new OnTest();
  System.out.println("--------------");
  
  obj1.display();
  obj2.display();

 }//main
}//SIBDemo