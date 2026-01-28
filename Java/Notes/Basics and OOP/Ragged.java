//Study of Multi Dimensional Ragged Array
//Interpret the code and comments
//Refer : the note below
//See : Ragged.png

class Ragged
{
 public static void main(String args[])
 {
  //declaring the ragged array
  int mat[][] = new int[3][];//last dimension is left unset

  //set the rows with varying number of columns
  mat[0] = new int[3];
  mat[1] = new int[4];
  mat[2] = new int[2];

  //using the ragged array

  int i,j;
  
  //assignment
  for(i =0 ; i< mat.length; i++)
    for(j =0; j < mat[i].length; j++) 
      mat[i][j] = i+j+10;

  //display
  System.out.println();
  for(i =0 ; i< mat.length; i++)
  {
    System.out.println(); 
    for(j =0; j < mat[i].length; j++) 
      System.out.print(mat[i][j] + "  ");
  }

  //attemp a row swap
  int temp[];

  temp = mat[1];
  mat[1] = mat[2];
  mat[2] = temp; 


  //display
  System.out.println();
  for(i =0 ; i< mat.length; i++)
  {
    System.out.println(); 
    for(j =0; j < mat[i].length; j++) 
      System.out.print(mat[i][j] + "  ");
  }

 }//main

}//Ragged

/*
In Java, a ragged array is a 2 dimensional array with rows having different number of columns.

The declaration statment :
   int mat[][] = new int[3][];

Observe that the allocation statement leaves the column size unset.
The array is not usable yet.

Single dimensional arrays of different sizes (of choice) must be allocated and associated with the ragged array.
Say,
 mat[0] = new int[3];
 mat[1] = new int[4];
 mat[2] = new int[2];
Next the ragged array can be processed like a 2 dimensional array.

*/