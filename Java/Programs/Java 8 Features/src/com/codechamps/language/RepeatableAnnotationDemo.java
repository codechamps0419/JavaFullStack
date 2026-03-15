package com.codechamps.language;

/*
@author CodeChamps0419

Demonstrates Java 8 Repeatable Annotations (@Repeatable).
*/

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface Roles {
    Role[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Roles.class)
@interface Role {
    String value();
}

@Role("ADMIN")
@Role("AUDITOR")
class AdminService {
}

public class RepeatableAnnotationDemo {
    public static void main(String[] args) {
        System.out.println("--- Java 8 Repeatable Annotations Demonstrations ---\n");

        Class<AdminService> clazz = AdminService.class;
        
        Role[] roles = clazz.getAnnotationsByType(Role.class);

        System.out.print("Roles assigned to AdminService: ");
        for (Role role : roles) {
            System.out.print(role.value() + " ");
        }
        System.out.println();
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.language.RepeatableAnnotationDemo

--- Java 8 Repeatable Annotations Demonstrations ---

Roles assigned to AdminService: ADMIN AUDITOR 

*/
