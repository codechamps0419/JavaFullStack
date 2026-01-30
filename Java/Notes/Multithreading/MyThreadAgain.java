/*
Implementing Multithreading by defining
a class that implements Runnable and
uses Thread object.

refer : multithreading.txt

*/

class MyThreadAgain implements Runnable
{
 Thread t;
 
 MyThreadAgain()
 {
   t= new Thread(this);
   t.start();//activation  
 }

 public void run()
 {
  int i;
  for(i =0 ; i < 500; i++)
   System.out.print(" T ");
 }//run

 public static void main(String args[])
 {
  MyThreadAgain mta = new MyThreadAgain();
  
  int i;
  for(i =0 ; i < 500; i++)
   System.out.print(" M ");

 }//main
}//MyThreadAgain


/*
public interface Runnable
{
 public abstract void run();
}

public class Thread implements Runnable
{
 Runnable ref;

 public Thread()
 {
   ref = this;
   ...
 }

 public Thread(Runnable r)
 {
   ref = r;
   ...
 }
 
 public void start()
 {
  thread activation work
  ...
  ref.run();
 }
 public void run()
 {} 
}
*/