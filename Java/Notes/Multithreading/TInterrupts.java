/*
Thread Interrupts
Refer : Multithreading.txt
*/

class TInterrupts implements Runnable
{
 Thread t;
 TInterrupts()
 {
   t = new Thread(this);
   t.start();
 } 

//case : interrupt bringing thread out of sleep

/*
 public void run()
 {
   int i;
   for(i =0 ; i < 100; i++)
   {
     System.out.print(" T ");
     if(i == 10)
     {
       try
       {
         t.sleep(10000);
         System.out.println("I rested for 10 seconds");
       }
       catch(InterruptedException ex)
       {
         System.out.println("I was interrupted before 10 seconds");
       } 
     }//if
   }//for

 }//run

*/

 public void run()
 {
   int i;
   for(i =0 ; i < 1000; i++)
   {
     System.out.print(" T ");
     //if(t.isInterrupted())
    if(Thread.interrupted())
       System.out.print("i");
   }//for

 }//run

 public static void main(String args[])
 {
  TInterrupts ti = new TInterrupts();
  int i;
  for(i =0; i < 500; i++)
   System.out.print(" M ");

  System.out.println("Main Ends");
  ti.t.interrupt();//signal ti.t
 }//main
}//TInterrupts

