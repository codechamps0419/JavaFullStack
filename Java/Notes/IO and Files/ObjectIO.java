//Object Serialization and DeSerialization
/*
Serialization is the process of converting
an object in a series of bytes so that it 
can be stored (in a file) or transferred 
using a stream.

Deserialization is the process of converting
a series of bytes, that come from a file 
or a stream, into an object.

For serialization and deserialization

1) The class of the object must implement Serializable interface.
It is an empty interface and is implemented for type compatibility only.

2) Use the methods :
  writeObject(obj) of the class ObjectOutputStream.
  obj = readObject() of the class ObjectInputStream.

FYI
----
transient fields are not serialized.

*/

import java.io.*;

class Student implements Serializable
{
 int rno;
 String name;
 /*transient*/ double percentage;

 Student()
 {
   //rno = 10;
   //name = "vikas";
   //percentage = 66.3;
   this(10, "vikas", 66.3);//constructor delegation
 }

 Student(int r, String s, double d)
 {
   rno = r;
   name = s;
   percentage = d;
 }

 void display()
 {
  System.out.println("Rno : " + rno);
  System.out.println("Name : " + name);
  System.out.println("Percentage : " + percentage);

 }
}//Student

class ObjectIO
{
 public static void main(String args[])
 {
  try
  {
   Student s = new Student(12, "manoj", 87.01);
   s.display();
   //serialize

   String fname = "d:/student.txt";

   FileOutputStream fos = new FileOutputStream(fname);
   ObjectOutputStream oos = new ObjectOutputStream(fos);   
   oos.writeObject(s);
   oos.flush(); 
   oos.close();

   FileInputStream fis = new FileInputStream(fname);
   ObjectInputStream ois = new ObjectInputStream(fis);   
   Student s1 = (Student) ois.readObject();
   ois.close();

   s1.display();
  }
  catch(Exception ex)
  {  
   System.out.println(ex);
  }
 }//main
}//ObjectIO