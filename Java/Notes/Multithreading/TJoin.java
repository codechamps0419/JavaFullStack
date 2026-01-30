//Program to study : Thread Join
//See : TJoin.png
//Refer : Multithreading.txt

class TJoin extends Thread
{
 TJoin()
 {
  start();
 }

 public void run()
 {
   System.out.println("run starts");
   int i;
   for(i =0; i < 1000; i++)
   {
     System.out.print(" T ");
   }
   System.out.println("run ends");
 }//run  

 public static void main(String args[])
 {
  System.out.println("main starts");
  TJoin tj = new TJoin();

  int i;
  for(i =0; i < 500; i++)
  {
    if(i == 100)
    {
      System.out.println("\n tj : " + tj.isAlive());
      try
      {
       tj.join();//main(current thread) suspends itself until "tj" gets over
      }
      catch(InterruptedException ex)
      {}
      System.out.println("\n tj : " + tj.isAlive());

    }//if
    System.out.print(" M ");
  }//for
  System.out.println("main ends");

 }//main
}//TJoin