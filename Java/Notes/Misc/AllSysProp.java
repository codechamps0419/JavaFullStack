//fetch all System Properties

import java.util.Enumeration;
import java.util.Properties;

public class AllSysProp
{
    public static void main(String[] args) 
    {
        Properties p = System.getProperties();
        Enumeration en = p.keys();

        String k,v; 

        while (en.hasMoreElements()) 
        {
            k = (String) en.nextElement();
            v= (String) p.get(k);

            System.out.println(k + " : " + v);
        }

        System.out.println("------------"); 	
        System.out.println("*"+ p.get("os.name"));
    }
}
