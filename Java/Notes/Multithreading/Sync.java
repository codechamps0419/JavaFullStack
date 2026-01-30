//Program to Study:
//Shared Resource and Critical Sections.
//See : Sync1.png and Sync2.png
//Refer : Multithreading.txt

class Server // extends Object
{
 String msg;
  
 synchronized void print(String s)
 {
  int i;
  for(i =0; i < 30; i++)
  {
   msg = s.toUpperCase();
   System.out.println();  
   System.out.print("{");
   System.out.print(" ");
   System.out.print(msg);
   System.out.print(" ");
   System.out.print("}");
  }//for
 }//print

 synchronized void display(String s)
 {
  int i;
  for(i =0; i < 20; i++)
  {
   msg = s.toLowerCase();
   System.out.println();  
   System.out.print("[");
   System.out.print(" ");
   System.out.print(msg);
   System.out.print(" ");
   System.out.print("]");
  }//for
 }//display

}//Server

class Client extends Thread
{
 Server svr;
 String data;

 Client(Server s, String m)
 {
  svr = s;
  data = m;
  start();
 }

 public void run()
 {
  if(data.equalsIgnoreCase("shared"))
    svr.print(data);
  else
    svr.display(data);
 }
}//Client

class Sync
{
 public static void main(String args[])
 {
  Server svr = new Server();
  Client c1 = new Client(svr, "Hello");
  Client c2 = new Client(svr, "Shared");
  Client c3 = new Client(svr, "Resource");

 }//main
}//Sync
