import java.io.InputStream;

class RTime
{
 public static void main(String args[])
 {
  try
  {
    //Get ref to java's runtime env.
    Runtime r = Runtime.getRuntime(); 
  
    System.out.println("available processors  " + r.availableProcessors());
    System.out.println("free memory for the process : " + r.freeMemory());
    System.out.println("total memory for the process : " + r.totalMemory());
    System.out.println("max memory for the process : " + r.maxMemory());

    //fork the current process and start a new child process
    Process p  = r.exec("mspaint");
    Thread.sleep(10000);
    p.destroy();//kill processes started by you
 
    //r.exec("notepad");
    p = r.exec("ping 127.0.0.1");
    try
    {    
     InputStream in = p.getInputStream();
     Thread.sleep(1000);
     while(in.available() > 0)
     {
      System.out.write(in.read());
      Thread.sleep(10);
     }
     System.out.flush();
    } 
    catch(Exception ex)
    {
     System.out.println(ex);
    }
/*
    Process p = r.exec("javac c:/java/FileCopy.java");
    p.waitFor(); //wait until compilation gets completed
    int x = p.exitValue();
    System.out.println(x); //0 means success, 1 means failure
*/
  }
  catch(Exception e)
  {}
 }
}