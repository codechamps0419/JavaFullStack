//Implementing multithreading by extending the Thread class
//Refer : Multithreading.txt

import java.awt.Toolkit;

class MyThread extends Thread
{

 public void run()
 {//assign the task to the thread
   Toolkit t = Toolkit.getDefaultToolkit();

   int i;
   for(i =0; i < 25; i++)
   {
     t.beep();
     try
     {
       Thread.sleep(1000);//delay
     }
     catch(InterruptedException ex)
     {}
   }
    //System.out.print("T"+ i + " ");
 }

 public static void main(String args[])
 {
  MyThread mt = new MyThread();//a custom thread
  mt.start();//activate it
  System.out.println("Number of Active Threads : " + Thread.activeCount());

  int i;
  for(i =0; i < 20000; i++)
    System.out.print("M"+i + " ");

 }//main
}//MyThread