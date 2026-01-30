/*
Program to study the Object Creation Process
Refer the note below
See the diagram : Person.png
*/

class Person
{
 String name;
 int age;

 //non static initializer block 
 {
  System.out.println("NSIB1");
  name = "baby";
  age = 0;
 }

 //Default Constructor (Parameterless)
 Person()
 {
  System.out.println("Person()");
  name = "Vikas"; //assign default value
  age = 10;//assign default value
 }

 //Parameterized Constructor
 Person(String n, int a)
 {
  System.out.println("Person(String , int)");
  name = n;//parameter value
  age = a;//parameter value
 }
 
 void display()
 {
  System.out.println("Name :  " + name);
  System.out.println("Age :  " + age);
 }

 {
  System.out.println("NSIB2");
  name = name.toUpperCase();//String method
  age++;
 }

 void Person()
 {
  System.out.println("Methods can have the same name as of the class");
 }
}//Person

class UsePerson
{
 public static void main(String args[])
 {
  System.out.println("----------");
  Person p = new Person();
  System.out.println("----------");
  Person p1 = new Person("Anil", 15);
  System.out.println("----------");

  p.display();
  p1.display();

  p.Person();
  p1.Person();
 }//main
}//UsePerson

/*
In Java, object creation is a three step process.

1) Allocation of memory .
An object is a dynamically allocated as a 
composite block in heap memory. 
It is formed by a collection of non static 
data members of the class.

2) Execution of a non static initializer block.

Non Static Initializer Block
---------------------------
A block of code defined inside the class.
It has no signature.
It executes automatically for every object created.
It may be used for object member initialization.

FYI
A class may have multiple non static initializer
blocks and all execute on creation of an object.
The order of their execution is the order of
their definition inside the class. The one defined
above would execute first.

3) Execution of constructor.

Constructor
-------------
A constructor is a special method of the class.
It has the same name as of the class and no return type specification.
It executes automatically for every object created.
It is used for object member initialization.

Constructor Overloading is defining multiple 
constructors for the class, with unique set 
of parameters each.

FYI
-------
When a class doesnt define any non static
initializer block and/or any constructor then
compiler auto generates the missing one with
empty definition.
This is because their execution is a part of
object creation process.

FYI
------
Java allows definition of methods that can
take the same name as of the class.
They differ from a constructor by their
return type.
They are invokable.
 */