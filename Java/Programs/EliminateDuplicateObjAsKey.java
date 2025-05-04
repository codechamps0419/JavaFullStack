import java.util.Map;
import java.util.LinkedHashMap;

public class EliminateDuplicateObjAsKey {
  public static void main(String[] args) {
    LinkedHashMap<Employee, Integer> emplMap =
        new LinkedHashMap<Employee, Integer>();

    emplMap.put(new Employee(101, "Aprajita"), 1);
    emplMap.put(new Employee(102, "Hrushi"), 2);
    emplMap.put(new Employee(103, "Test"), 3);

    System.out.println("\nBefore adding duplicate employee - ");
    for (Map.Entry<Employee, Integer> entry : emplMap.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }
    System.out.println();

    emplMap.put(new Employee(103, "Test"), 3);
    emplMap.put(new Employee(104, "TestEmployee"), 3);

    System.out.println("\nAfter adding duplicate employee - ");
    for (Map.Entry<Employee, Integer> entry : emplMap.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }
  }
}

class Employee {
  
  private int id;
  private String name;

  public Employee(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int hashCode() {
    System.out.println("In hashcode method");
    int hashcode = 0;
    return hashcode;
  }

  public boolean equals(Object obj) {
    System.out.println("In equals method");
    if (obj instanceof Employee) {
      Employee emp = (Employee) obj;
      return (emp.id == this.id && emp.name.equals(this.name));
    } else {
      return false;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String toString() {
    return "Employee Id: " + id + "  Name: " + name;
  }
  
}


/*

Output:

In hashcode method
In hashcode method
In equals method
In hashcode method
In equals method
In equals method

Before adding duplicate employee - 
Employee Id: 101  Name: Aprajita -> 1
Employee Id: 102  Name: Hrushi -> 2
Employee Id: 103  Name: Test -> 3

In hashcode method
In equals method
In equals method
In equals method
In hashcode method
In equals method
In equals method
In equals method

After adding duplicate employee - 
Employee Id: 101  Name: Aprajita -> 1
Employee Id: 102  Name: Hrushi -> 2
Employee Id: 103  Name: Test -> 3
Employee Id: 104  Name: TestEmployee -> 3

*/
