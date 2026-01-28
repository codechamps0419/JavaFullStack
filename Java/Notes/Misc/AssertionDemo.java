/*
Assertion is a mechanism to generate 
an exception if value some variables 
value (state) fails in assertion test.

This allows a developer to detect various 
cases of exceptions, generally helping in 
debugging and further correcting the 
program logic.
---------------------------------

Syntax for introducing assertions in a program :

assert(boolean expression) : message

if boolen eval to false then AssertionException 
will be raised along with the message.

By default the assertions are disabled.

To enable the assertions exceute the program as :
 java -enableassertions <byteCodename> 
*/

class AssertionDemo
{
 public static void main(String args[])
 {
    String s;    
    int flag;
    try
    { 
        s = args[0];
        flag = 1;
    }
    catch(ArrayIndexOutOfBoundsException ex)
    {
        s = "User";
        flag = 0;
    }

//assertion test
    assert(flag >0 ) : "Problem in code 1, Commandline data is not sent";

    System.out.println("hello " + s);   
 }
}