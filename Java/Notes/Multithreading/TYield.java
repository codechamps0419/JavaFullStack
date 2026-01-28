//Study of yield
/*
Thread yield 


*/

class TYield implements Runnable
{
 Thread t;
 TYield()
 {
   t = new Thread(this);
   t.start();
 }

 public void run()
 {
  int i;
  for(i =0 ; i < 50; i++)
  {
   System.out.print("T");
   Thread.yield();
  }//for
 }//run

 public static void main(String args[])
 {
  TYield ty = new TYield();

  int i;
  for(i =0 ; i < 50; i++)
  {
   System.out.print("M");
   Thread.yield();
  }//for
  
 }//main
}//TYield
