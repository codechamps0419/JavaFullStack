interface Iface1
{
  int x = 10;//final and static variable, initialize it along with declaration
  void afx_1(); //abstract method

  default void dfx()
  {
    System.out.println("Default methods of an interface can have a body");
  }

  static void sfx()
  {
    System.out.println("static methods of an interface can have a body");
  }

}//Iface1

interface Iface2
{
  void afx_2(); //abstract method
}

interface CIface extends Iface1, Iface2
{
  void afx_3(); //abstract method
}

class UseIface implements CIface
{
 public void afx_1()
 {
  System.out.println("I have to override afx_1");
 }

 public void afx_2()
 {
  System.out.println("I have to override afx_2");
 }

 public void afx_3()
 {
  System.out.println("I have to override afx_3");
 }
/*
 public void dfx()
 {
  System.out.println("I may or may not override dfx");
  efx();
 }
*/
 public void efx()
 {
  System.out.println("I have some extended code ");
 }

}

class InterfaceDemo
{
 public static void main(String args[])
 {
   //Type compatibility due to implement
   CIface ref = new UseIface();

   ref.afx_1();
   ref.afx_2();
   ref.afx_3();
   ref.dfx();
   Iface1.sfx();
 }
}