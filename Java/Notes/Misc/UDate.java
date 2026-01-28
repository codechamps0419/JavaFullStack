import java.util.*;
import java.text.*;


/*
The java.util.Date class represents date and time in Java. 
It provides constructors and methods to perform numerous operations on date and time.

Date()	
Creates a date object representing current date and time.

Date(long milliseconds)	
Creates a date object for the given milliseconds since January 1, 1970, 00:00:00 GMT.

boolean after(Date date)	
Tests if current date is after the given date. Returns a boolean.

boolean before(Date date)	
Tests if current date is before the given date. Returns a boolean.

int compareTo(Date date)	
Compares current date with given date.

boolean equals(Date date)	
Compares current date with given date for equality.

long getTime()	
Returns the time represented by this date object.

void setTime(long time)	
Changes the current date and time to given time.

---------------------------------
SimpleDateFormat is a class for formatting 
and parsing dates in a locale-sensitive manner. 

SimpleDateFormat allows you to start by 
choosing any user-defined patterns for 
date-time formatting.

SimpleDateFormat Format Codes

Character	Description		Example
G	Era designator		AD
y	Year in four digits		2001
M	Month in year		July or 07
d	Day in month		10
h	Hour in A.M./P.M. (1~12)	12
H	Hour in day (0~23)		22
m	Minute in hour		30
s	Second in minute		55
S	Millisecond		234
E	Day in week		Tuesday
D	Day in year		360
F	Day of week in month	2 would mean second Wed. of the month.
w	Week in year		40
W	Week in month		1
a	A.M./P.M. marker		PM
k	Hour in day (1~24)		24
K	Hour in A.M./P.M. (0~11)	10
z	Time zone			Eastern Standard Time

*/

class UDate
{
 public static void main(String args[])
 {
  Date d =new Date();  
  System.out.println(d);

  SimpleDateFormat sdf = new SimpleDateFormat ("E dd/MM/yyyy '@' hh:mm:ss a zzz");
  System.out.println("Current Date: " + sdf.format(d));

  SimpleDateFormat sdf1 = new SimpleDateFormat ("W");
  System.out.println("This is " + sdf1.format(d) + " week of the month");  
 
 }//main
}//UDate