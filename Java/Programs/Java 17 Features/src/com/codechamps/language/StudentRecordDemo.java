/*
@author CodeChamps0419

Demonstrates a simple Java 17 Record usage:
1. Declares a concise record for holding student details.
2. Automatically provides constructor, accessors, equals(), hashCode and toString().
*/

package com.codechamps.language;

public class StudentRecordDemo {

    // 1. DECLARE A SIMPLE RECORD
    public record Student(int id, String name, double gpa) {}

    public static void main(String[] args) {
        System.out.println("--- Simple Java 17 Record Demonstration ---\n");

        // 2. CREATE INSTANCES USING THE GENERATED CANONICAL CONSTRUCTOR
        Student student1 = new Student(101, "Hrushikesh", 8.9);
        Student student2 = new Student(102, "Aprajita", 9.2);
        Student student1Clone = new Student(101, "Hrushikesh", 8.9);

        // 3. AUTOMATIC toString()
        System.out.println("1. Automatic toString() Output:");
        System.out.println("   - Student 1: " + student1);
        System.out.println("   - Student 2: " + student2);
        System.out.println();

        // 4. AUTOMATIC ACCESSOR METHODS (no 'get' prefix)
        System.out.println("2. Accessor Methods:");
        System.out.println("   - ID:   " + student1.id());
        System.out.println("   - Name: " + student1.name());
        System.out.println("   - GPA:  " + student1.gpa());
        System.out.println();

        // 5. AUTOMATIC equals() AND hashCode()
        System.out.println("3. Automatic equals() & hashCode():");
        System.out.println("   - student1.equals(student1Clone): " + student1.equals(student1Clone));
        System.out.println("   - student1.equals(student2):       " + student1.equals(student2));
        System.out.println("   - student1.hashCode() == student1Clone.hashCode(): " + (student1.hashCode() == student1Clone.hashCode()));
    }
}

/*
Output:

--- Simple Java 17 Record Demonstration ---

1. Automatic toString() Output:
   - Student 1: Student[id=101, name=Hrushikesh, gpa=8.9]
   - Student 2: Student[id=102, name=Aprajita, gpa=9.2]

2. Accessor Methods:
   - ID:   101
   - Name: Hrushikesh
   - GPA:  8.9

3. Automatic equals() & hashCode():
   - student1.equals(student1Clone): true
   - student1.equals(student2):       false
   - student1.hashCode() == student1Clone.hashCode(): true


*/