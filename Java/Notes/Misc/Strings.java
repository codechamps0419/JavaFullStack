import java.util.Arrays;	
//Program to demonstrate : java.lang.String

public class Strings
{
    public static void main(String[] args) 
    {
        String s = "elephant";
        System.out.println("String : " + s);
        System.out.println("char at pos(0) " + s.charAt(0));
        System.out.println("length " + s.length());
        System.out.println("Replacing e with E  "  +  s.replace('e', 'E'));
        System.out.println("Replacing All e with EEE  "  + s.replaceAll("e", "EEE"));
        System.out.println("substring(2) " + s.substring(2));//part of string from index 2 to the end
        System.out.println("substring(3 si, 7 ei) " + s.substring(3,7));//part of string from index 3 to 6
        System.out.println("lowercase " + s.toLowerCase());
        System.out.println("uppercase " + s.toUpperCase());
        System.out.println("concat(training) " + s.concat("training"));
        System.out.println("compareTo(apple) " + s.compareTo("apple"));
        System.out.println("compareTo(elephant) " + s.compareTo("elephant"));
        System.out.println("compareTo(west) " + s.compareTo("west"));
        System.out.println("equals(elephant) " + s.equals("elephant"));
        System.out.println("equalsIgnoreCase(ELEpHANt) " + s.equalsIgnoreCase("ELEpHANt"));
        System.out.println("indexOf(o) " + s.indexOf('o'));//search for "o" in "elephant", if found return index otherwise -1
        System.out.println("indexOf(ph) " + s.indexOf("ph"));//search for "ph" in "elephant", if found return index otherwise -1
        System.out.println("startsWith(Ele) " +  s.startsWith("Ele"));
        System.out.println("endsWith(ant) " +  s.endsWith("ant"));
        
        byte b[] = s.getBytes();//convert string into array of bytes

        System.out.println("bytes " + Arrays.toString(b));
        s = "this is a sample sentence";
        String words[]  = s.split(" ");//supports regex
        System.out.println("words " + Arrays.toString(words));

        
    }
}
