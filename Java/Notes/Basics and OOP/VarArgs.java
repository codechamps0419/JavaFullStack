/*
Variable Arguments to a method
                  or
Variable arity parameters and methods
-------------------------------

Java allows definition of "variable arity methods" that can accept variable number of arguments.

The parameters must be homogenous in type.
Internally system converts the varying number of parameters in one array and passes the array reference to the variable arity method.

The method receives the array reference in a "variable arity parameter".

A variable arity parameter takes following syntax :
void fx(dataType ...varName) { }

The variable arity parameter is internally an array reference and hence must be processed like an array.

*/
class VarArgs
{
 fn() {}

 static int fx(int ...numbers)//variable arity parameter (internally it is an array reference)
 {
   int i;
   int sum = 0;
   for(i =0; i < numbers.length; i++)
     sum += numbers[i];

   return sum;
 }

 public static void main(String args[])
 {
  int result;
  result = fx(1,2,3);//3 actual parameters (will be internally converted into an array of 3 and passed)
  System.out.println("1+2+3 = " + result);

  result = fx(10,20,30,40);//4 actual parameters (will be internally converted into an array of 4 and passed)
  System.out.println("10+20+30+40 = " + result);

  result = fx(100); //1 actual parameter (will be internally converted into an array of 1 and passed)
  System.out.println("100 = " + result);

 }
}