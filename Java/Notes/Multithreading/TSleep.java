//Study of sleep
/*
Sleep ceases execution of current thread
for specific time period.

Accuracy of sleep depends on Systems
timers and schedulers.

Call to sleep throws InterruptedException
that must be handled.
*/

class TSleep implements Runnable
{
 Thread t;
 TSleep()
 {
   t = new Thread(this);
   t.start();
 }

 public void run()
 {
  int i;
  for(i =0 ; i < 10; i++)
  {
   System.out.println(System.currentTimeMillis());
   try
   {
     Thread.sleep(1000);//1000 milliseconds
   }
   catch(InterruptedException ex)
   {}
  }//for
 }//run

 public static void main(String args[])
 {
  TSleep ts = new TSleep();
  
 }//main
}//TSleep
