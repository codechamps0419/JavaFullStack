/*
Array of Secondary Type (class)
Read the note below
*/

class Student
{
  int rno;
  String name;

  //Default Constructor  
  Student()
  {
    System.out.println("Student()");
    rno = 10;
    name  = "Vikas";
  }

  //Parameterized Constructor
  Student(String n, int r)
  {
   System.out.println("Student(String, int)");
   name = n;
   rno = r;
  }
  
  void display()
  {
    System.out.println("[ " + rno + ", " + name + " ]" );
  }
}//Student


class CArray
{
 public static void main(String args[])
 {
  Student arr[] = new Student[3];  
  arr[0] = new Student("Manoj",  11);
  arr[1] = new Student();
  arr[2] = new Student("Anil",  1);

  //advantage1 : group processing 
  //advantage2 : application of algorithms
  int i;
  for(i =0 ; i < arr.length ; i++)
    arr[i].display();
 

 }//main
}//CArray


/*
Java allows declaration of array of primary and secondary types.
Syntax :
  type arr[] = new type[size];

In case the type is primary the array elements are variables of the type.
In case the type is secondary the array elements are references (not objects) of the type.
The references are "null" by default and the array is not usable.
Hence explict object creation per element and association with the element is mandatory.

Refer the code and the diagram.
*/