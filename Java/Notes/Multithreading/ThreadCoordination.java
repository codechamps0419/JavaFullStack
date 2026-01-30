/*
Thread Coordination

When multiple threads act on a shared
resource and they rely on the outcome of 
one anothers process then their access 
(to the shared resource) must be coordinated.

To do so:
1) Define the shared resource and the threads.
2) Define the modules that utilize the shared resource as "critical sections".
3) Use logical flags to decide the order of actions.
4) Use wait(), notify() or notifyAll() to implement the order of actions.
*/

import java.util.Random;
class NumberHost
{
 int number;
 boolean isDataAvailable;

 NumberHost()
 { 
  isDataAvailable = false;
 }

 //Writer
 synchronized void setNumber(int q)
 {
   if(isDataAvailable == true)
   {//writer is in, but data is not consumed yet
    //so writer gives up the critical section ownership
    //and suspends ownself until notified.
    try
    {
     wait(); 
    }
    catch(InterruptedException ex)
    {}
   }
   number = q;
   System.out.println("set : " + number);
   isDataAvailable = true;
   notify();//to bring any suspended thread out of wait()
 }

 //Reader
 synchronized void displayNumber()
 {
  if(isDataAvailable == false)
  {//reader is in, but data is not produced yet
   //so reader gives up the critical section ownership
   //and suspends ownself until notified.
   try
   {
    wait(); 
   }
   catch(InterruptedException ex)
   {}
  }

  System.out.println("number : " + number);
  isDataAvailable = false;
  notify();//to bring any suspended thread out of wait()
  
 }//displayNumber
}

class Writer extends Thread
{
 NumberHost nHost;

 Writer(NumberHost nh)
 {
  nHost = nh;
  start();
 }

 public void run()
 {
   Random r = new Random();
   int i, x;
  
   for(i =0 ; i < 10; i++)
   {
    x = r.nextInt(100);//generate a random int in range 0-99
    nHost.setNumber(x);
   }//for
 }//run 
}//Writer


class Reader extends Thread
{
 NumberHost nHost;

 Reader(NumberHost nh)
 {
  nHost = nh;
  start();
 }

 public void run()
 {
   int i;
  
   for(i =0 ; i < 10; i++)
   {
    nHost.displayNumber();
    try  
    {
         Thread.sleep(1000);
    }
    catch(InterruptedException ex)
    {}
   }//for

 }//run 
}//Reader


 
class ThreadCoordination
{
 public static void main(String args[])
 {
  NumberHost nh = new NumberHost();
  Writer wr = new Writer(nh);
  Reader rd = new Reader(nh);
 }
}