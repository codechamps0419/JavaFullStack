/*
@author CodeChamps0419

Demonstrates Java 17 Text Blocks (Multi-line String Literals):
1. Highlights how triple quotes (""") simplify complex JSON, SQL, and HTML string definitions.
2. Demonstrates escape sequences (\s for single space preservation, \ to suppress newlines).
3. Shows dynamic string formatting using String.formatted().
*/

package com.codechamps.language;

public class TextBlocksDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 17 Text Blocks Demonstration ---\n");

        // 1. JSON TEXT BLOCK DEMO
        String jsonPayload = """
            {
              "name": "Hrushikesh",
              "role": "Java Full Stack Developer",
              "experienceYears": 6,
              "skills": ["Java 17", "Spring Boot", "Docker"]
            }
            """;

        System.out.println("1. Clean Multiline JSON Payload:");
        System.out.println(jsonPayload);

        // 2. SQL QUERY DEMO WITH DYNAMIC FORMATTING
        String tableName = "users";
        String status = "ACTIVE";

        String sqlQuery = """
            SELECT u.id, u.username, u.email, p.title
            FROM %s u
            JOIN permissions p ON u.permission_id = p.id
            WHERE u.status = '%s'
            ORDER BY u.created_at DESC
            """.formatted(tableName, status);

        System.out.println("2. Dynamic Formatted SQL Query:");
        System.out.println(sqlQuery);

        // 3. ESCAPE SEQUENCES DEMO (\s for space preservation, \ for line continuation)
        String formattedCodeBlock = """
            public class Application {\
                public static void main(String[] args) {\
                    System.out.println("Line continuation works!");\
                }\
            }
            """;

        System.out.println("3. Text Block with Line Continuation (\\):");
        System.out.println("   - Output: " + formattedCodeBlock);
    }
}

/*
Output:

--- Java 17 Text Blocks Demonstration ---

1. Clean Multiline JSON Payload:
{
  "name": "Hrushikesh",
  "role": "Java Full Stack Developer",
  "experienceYears": 6,
  "skills": ["Java 17", "Spring Boot", "Docker"]
}

2. Dynamic Formatted SQL Query:
SELECT u.id, u.username, u.email, p.title
FROM users u
JOIN permissions p ON u.permission_id = p.id
WHERE u.status = 'ACTIVE'
ORDER BY u.created_at DESC

3. Text Block with Line Continuation (\):
   - Output: public class Application {    public static void main(String[] args) {        System.out.println("Line continuation works!");    }}

*/