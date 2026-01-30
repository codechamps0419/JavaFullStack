//Program to study concurrent execution by 3 threads (2 custom + 1 main)

class My3Threads extends Thread
{
 int flag ;

 My3Threads(int x)
 {
  flag = x;
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
  int i;
  for(i =0 ; i < 500; i++)
   System.out.print("A" + i + " ");
 }

 void fx2()
 {
  int i;
  for(i =0 ; i < 500; i++)
   System.out.print("B" + i + " ");
 }

 public static void main(String args[])
 {

  My3Threads m1 = new My3Threads(1);
  My3Threads m2 = new My3Threads(2);

  int i;
  for(i =0 ; i < 500; i++)
   System.out.print("M" + i + " ");

 }
}