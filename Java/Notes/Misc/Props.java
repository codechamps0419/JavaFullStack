import java.util.Enumeration;
import java.util.Properties;
import java.io.*;

public class Props
{
    public static void main(String[] args) 
    {
     try
     {
        Properties p = new Properties();//a table of (unique key)-(value) pairs
        p.put("a", "one");
        p.put("b", "two");
        p.put("c", "three");
        p.put("a", "apple");

        //get an enumeration of keys	
        Enumeration en = p.keys();

        String k, v; 

        while(en.hasMoreElements()) //until the enumeration has elements
        {
            k = (String) en.nextElement();//fetch a key (order not gauranteed)
            v = (String) p.get(k);

            System.out.println( k + " : " + v);
        }//loop
 
	p.remove("a");

	FileOutputStream fos = new FileOutputStream("d:/a.txt");
	p.store(fos, "q");
	fos.close();

	FileInputStream fin = new FileInputStream("d:/a.txt");
	Properties p1 = new Properties();
	p1.load(fin);
 	fin.close();

	en = p1.keys();

        	while(en.hasMoreElements()) //until the enumeration has elements
        	{
             k = (String) en.nextElement();//fetch a key (order not gauranteed)
             v = (String) p1.get(k);
             System.out.println( k + " : " + v);
	}//loop
      }
      catch(Exception ex)
      {
 	System.out.println(ex);
      }
    }
}
