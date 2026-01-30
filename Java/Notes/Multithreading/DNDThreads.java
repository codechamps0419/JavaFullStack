//Program to study : Daemon and NonDaemon Threads
//See : DNDThreads.png
//Refer : Multithreading.txt

class DNDThreads extends Thread
{
 int flag ;

 DNDThreads(int x)
 {
  flag = x;
  if(flag == 2)
   setDaemon(true);

  start();//activate the thread
 }

 public void run()
 {
  if(flag == 1)
    fx1();
  else if(flag == 2)
    fx2();

 }

 void fx1()
 {
  System.out.println(" fx1 starts " );
  int i;
  for(i =0 ; i < 200; i++)
   System.out.print("A" + i + " ");
  System.out.println(" fx1 ends " );
 }

 void fx2()
 {
  System.out.println(" fx2 starts " );
  int i;
  for(i =0 ; i < 2000; i++)
   System.out.print("B" + i + " ");
  System.out.println(" fx2 ends " );
 }

 public static void main(String args[])
 {
  System.out.println(" main starts " );
  DNDThreads t1 = new DNDThreads(1);
  DNDThreads t2 = new DNDThreads(2);

  int i;
  for(i =0 ; i < 100; i++)
   System.out.print("M" + i + " ");

  System.out.println(" main ends " );
 }
}