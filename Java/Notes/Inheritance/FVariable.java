import java.util.Random;

class A
{
 final int x;//final member variable
 int sum;
 final int arr[];

 A()//default constructor
 {
   x= 5;
   sum =0 ;
   arr = new int[x];
 }

 A(int q)//parameterized constructor
 {
  x = q;
  sum = 0;
  arr = new int[x];
 }

 //Observe that array can be assigned any number of times
 //but the array reference is final.
 //Being final the array reference cannot be made to refer to any other array.

 void assign()
 {
   //x++;error as final member (x) cannot be reassigned
   //arr = new int[10]; error as final member (arr) cannot be reassigned

   //a random number generator
   Random r = new Random();
   int i;
 
   sum =0;
   for(i =0 ; i < arr.length; i++)
   {
     arr[i] = r.nextInt(100);//0 to 99
     sum += arr[i];
   }

 }//assign

 void display()
 {
  System.out.println("Array size : "+ x);

  int i;
  for(i =0; i < x; i++)
   System.out.print(arr[i] + "  ");
 
  System.out.println();
  System.out.println("Sum : " + sum);
 }
}//A

class FVariable
{
 public static void main(String args[])
 {
  A objA = new A();
  objA.assign();
  objA.display();

  objA.assign();
  objA.display();

  System.out.println("-----------------");
  final int q;
  q = 10;
  //here after q is read only
  System.out.println("q : " +q);
 }
}