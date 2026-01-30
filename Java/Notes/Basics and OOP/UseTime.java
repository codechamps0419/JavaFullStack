/*
Program to study : Encapsultation, class, object and this reference.
Read the note below.
Refer : class, object and this.png
*/

class Time//entity
{
 int h, m, s; //data (attributes)

 void setTime(int a, int b, int c) //method (operation)
//void setTime(Time this, int a, int b, int c) //compiled method (operation)
 {
   //int h; //would shadow data member h

   h = a;//this.h = a;
   m = b;//this.m = b;
   s = c;//this.s = c;
 }

 void displayTime()//method (operation)
 {
   System.out.println(h + " : " + m + " : " + s);
 }


}//Time


//Time is a secondary datatype
//Instantiate it and use the object

class UseTime
{
 public static void main(String args[])
 {
  //Object of class Time
  Time t = new Time();

  //using the object
  t.setTime(1,2,3);//setTime(t,1,2,3);
  t.displayTime();
 }//main
}//UseTime


/*
Encapsulation
----------------
Encapsulation is OOP concept that suggests binding of operations (code) and attributes (data) of an entity into one unit.
The unit, so formed, is termed as a class.

class
-------
class is a unit the encloses code(methods) and data(variables, arrays, ...)of an entity.
It represents a secondary datatype.
Being a datatype it is ideally used through instantiation.
i.e. through its objects.

Object
--------
An object is an instance of a class.
It can store and process data.
Its storage ability is defined by the non static data members of the class.
Its process ability is defined by the non static methods of the class.

In memory an object is a composite block that contains non static data members of the class.
It is required to invoke non static methods of the class.


Method Invokation
--------------------
Object of the class is required for invokation of non static memthods of the class.
System implicitly passes reference to the caller object, as a hidden parameter to the method.
System redesigns the method to implicitly receive the reference to the caller object, in "this" reference.
System implicitly applies uses "this" to access other members of the class.

this
-------
this is a system defined formal parameter for non static methods of the class.
It is initialized with reference to the caller object.
It is used to access other members of the class.
It is a final reference, cannot be modified.
Its datatype is current class.

*/