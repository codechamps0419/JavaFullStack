/* @Author: Dynamic Duo Learners */


public class CmdLine
{
 public static void main(String args[])
 {
  System.out.println("Number of commandline arguments : "+ args.length);

  int i;
  for(i =0 ; i < args.length; i++)
    System.out.println("args[" + i + "] : " + args[i]);

 }//main
}//CmdLine


/* 
Compile Java Application -
cmd> javac CmdLine.java

Run Java Application - (pass cmd arguments separated by spaces) 
cmd> java CmdLine 10 20 3.14 true Hello Codechamps 'H:\file.txt' PI.png 'Passionate Buddies' "Welcome, A&H"

Output:

Number of commandline arguments : 10
args[0] : 10
args[1] : 20
args[2] : 3.14
args[3] : true
args[4] : Hello
args[5] : Codechamps
args[6] : H:\file.txt
args[7] : PI.png
args[8] : Passionate Buddies
args[9] : Welcome, A&H


*/
