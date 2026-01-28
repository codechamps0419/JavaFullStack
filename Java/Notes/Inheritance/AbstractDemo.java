abstract class TimeTable
{
 void period1()
 {
  System.out.println("-----Period1-----");
  System.out.println("TOC");
  System.out.println("----------------");
 }

 void period2()
 {
  System.out.println("-----Period2-----");
  System.out.println("OS");
  System.out.println("----------------");
 }

 //has the method, but the definition varies as per the group
 abstract void practical();
 abstract void games();

}

class Group1 extends TimeTable
{
 void practical()
 {
  System.out.println("-----Practical-----");
  System.out.println("Java");
  System.out.println("----------------");
  
 }

 void games()
 {
  System.out.println("-----Games-----");
  System.out.println("Football");
  System.out.println("--------------");
 }

}//Group1

class Group2 extends TimeTable
{
 void practical()
 {
  System.out.println("-----Practical-----");
  System.out.println("RDBMS");
  System.out.println("----------------");
  
 }

 void games()
 {
  System.out.println("-----Games-----");
  System.out.println("Basketball");
  System.out.println("--------------");
 }

}//Group2


class AbstractDemo
{
 static void executeSession(TimeTable ref)
 {
   System.out.println("College Starts");
   ref.period1();
   ref.period2();
   ref.practical();
   ref.games();
   System.out.println("College Ends");
 }
 public static void main(String args[])
 {
   Group1 g1 = new Group1();
   Group2 g2 = new Group2();

   executeSession(g1);
   executeSession(g2);   
 }
}