/*
Program to demonstrate : Type Compatibility, Loose Coupling and Runtime Binding
Refer : Inhertiance.txt
See : TypeCompatibility.png
*/

class A
{
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
 void fx2()
 {//gateway to extended code
  System.out.println("B fx2 starts");
  fx3();
  System.out.println("B fx2 starts");
 }

 void fx3()
 {
  System.out.println("B fx3");
 }
}//B

class C extends A
{
 void fx2()
 {
  System.out.println("C fx2");
 }
}//C


class TypeCompatibility
{

 static void testCompatibility(A ref)//loose coupling
 {
  System.out.println("----------------");
  ref.fx1();//inherited
  ref.fx2();//overridden (runtime bound)
  System.out.println("----------------");
 }

 public static void main(String args[])
 {
   A objA = new A();
   B objB = new B();
   C objC = new C();

   testCompatibility(objA);
   testCompatibility(objB);
   testCompatibility(objC);

 }//main
}//TypeCompatibility