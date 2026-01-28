//Program to study : 
//Object Deallocation Process
//Garbage Collection Mechanism

//refer : Garbage Collection.txt
//see : GCDemo.png

import java.util.Random;

class Myclass extends Object
{
  int arr[];
  String label;

  Myclass(String l)
  {
   Random r = new Random();
   arr = new int[r.nextInt(10000)];
   label = l;
  }

  void display()
  {
   System.out.println("I am object : "+ label);
   System.out.println("My array is of size : "+ arr.length);
  } 
 
  //override finalize 
  protected void finalize()
  {
    System.out.println("In finalize for object : " + label);
  } 
}//Myclass

class GCDemo
{
 public static void main(String args[])
 {
  Myclass o1 = new Myclass("A");
  Myclass o2 = new Myclass("B");
  Myclass o3 = new Myclass("C");

  o1.display();
  o2.display();  
  o3.display();

  Myclass ref = o3;

  //drop the references
  o1 = o2 = o3 = null;

  //initiate Garbage Collection
  System.gc();

  //delay in main thread, to observe gc action
  try
  {
   Thread.sleep(3000);//3 sec delay
  }
  catch(InterruptedException ex)
  {}

 }//main
}//GCDemo