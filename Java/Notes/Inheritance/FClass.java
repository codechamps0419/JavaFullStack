final class A
{
 void fx()
 {
  System.out.println("A final class cannot be inherited");
 }
}//A

/*
class B extends A
{}
*/
class FClass
{
 public static void main(String args[])
 {
  A objA = new A();
  objA.fx();
 }
}