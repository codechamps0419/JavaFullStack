class InputException extends Exception
{
 String message;
 
 InputException (String  s)
 {
   message = s.toUpperCase();
 }
 
 void display()
 {
  System.out.println("InputException : " + message);
 }

 //override
 public String toString()
 {
   return "InputException : " + message;
 } 
}//InputException


class Myself
{
 int age;
 String name;

 Myself(String nm, int ag) throws InputException
 {
  setName(nm);
  setAge(ag);
 }

 void setAge(int a) throws InputException
 {
  if(a >=0 && a <=100)
    age = a; 
  else
  {//raise an exception
    InputException ie = new InputException("Invalid Age " + a);   
    throw ie;
  }  
 }//setAge
 
 void setName(String n) throws InputException
 {
  int i;
  char temp;
  for(i =0 ; i < n.length(); i++)
  {
   temp = n.charAt(i); 
   if( ! ( (temp>='a' && temp <='z') || (temp>='A' && temp <='Z') ))
   {//a non char in the name
     InputException ie = new InputException("Invalid Name " + n);
     throw ie;
   }
  }//for 

  name = n;
 }//setName

 void display()
 {
   System.out.println("Name : "+  name);
   System.out.println("Age : "+ age);
 }
}//Myself

class UserDefinedExceptions
{
 public static void main(String args[]) 
 {
  try
  {
   String a;
   int b;
   a = args[0];//name
   b = Integer.parseInt(args[1]);//age

   Myself m = new Myself(a,b);
   m.display();
  }
  catch(ArrayIndexOutOfBoundsException ex)
  {
   System.out.println("Usage : Java Myself <Name> <Age>" );
  }
  catch(NumberFormatException ex)
  {
   System.out.println("Usage : Java Myself <Name> <Age>" );
  }
  catch(InputException ex)
  {
   ex.display();
  }
  catch(Exception ex)//generic exception handler
  {
    System.out.println(ex);
  }

 }//main
}//UserDefinedExceptions