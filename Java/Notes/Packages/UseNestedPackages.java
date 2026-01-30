import outer.*;
import outer.inner.*;

class UseNestedPackages
{
 public static void main(String args[])
 {
  A objA = new A();
  outer.B objB1 = new outer.B();
  outer.inner.B objB2 = new outer.inner.B();
  C objC = new C();

  objA.fx1();
  objB1.fx2();
  objB2.fx3();
  objC.fx4();

 }
}