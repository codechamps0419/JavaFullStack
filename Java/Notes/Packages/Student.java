import study.*;//import (get access to) all public classes and interfaces of the package study

class Student
{
 public static void main(String args[]) 
 {
   System.out.println("---Lets know about various languages---");
   C objC = new C();
   Cpp objCpp = new Cpp();
   HTML objHTML = new HTML();
   Java objJava = new Java();

   objC.aboutC();
   objCpp.aboutCpp();
   objHTML.aboutHTML();
   objJava.aboutJava();
   System.out.println("---Thank You---");

 }//main
}