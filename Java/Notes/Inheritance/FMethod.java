class A
{
 final void fx()
 {
  System.out.println("A final method cannot be overridden");
 }
}//A

class B extends A
{
/*
 //overridde
 void fx()
 {
  System.out.println("B fx()");
 }
*/
}

class FMethod
{
 public static void main(String args[])
 {
  B objB = new B();
  objB.fx();
 }
}