//Program to study inheritance of data
//Refer : inheritance.txt
//See : InheritData.png

class A
{
 int x, y;//non static data members

 {
  System.out.println("NSIB of A");
 }

 A()//default constructor
 {
  System.out.println("A()");
  x = 10;//preset value assigned
  y = 20;
 }

 A(int q, int w)//parameterized constructor
 {
  System.out.println("A(int,int)");
  x = q;//parameter value assigned
  y = w;
 }
 
 void display()
 {
  System.out.println(x +" " + y);
 }

}//A

class B extends A
{
 int z;//non static data members

 {
  System.out.println("NSIB of B");
 }

 B()//default constructor
 {
  //super();
  System.out.println("B()");
  z = x+y;
 }

 B(int q, int w)//parameterized constructor
 {
  super(q,w);//to invoke A(int,int)
  System.out.println("B(int,int)");
  z = x+y;
 }
 
 void display()
 {
  System.out.println(x + " + " + y + " = " + z);
 }

}//B

class InheritData
{
 public static void main(String args[])
 {
  System.out.println("------------");
  //B objB = new B();
  B objB = new B(1,2);
  System.out.println("------------");
  objB.display();
 }
}