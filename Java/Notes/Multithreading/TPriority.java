//Study of thread priority
//refer : multithreading.txt

class TPriority implements Runnable
{
 Thread t;
 TPriority(int x)
 {
   t = new Thread(this);
   //Assigning high priority to the thread t
   t.setPriority(x);
   t.start();
 }

 public void run()
 {
  int i;
  for(i =0 ; i < 5000; i++)
   System.out.print("T");

 }//run

 public static void main(String args[])
 {
  TPriority tp = new TPriority(8);

  //get handle to the current thread (main)
  Thread ct = Thread.currentThread();
  //lowering the priority of current thread
  ct.setPriority(3);


  int i;
  for(i =0 ; i < 5000; i++)
   System.out.print("M");
  
 }//main
}//TPriority
