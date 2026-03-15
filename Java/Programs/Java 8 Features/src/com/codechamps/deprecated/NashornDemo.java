/*
@author CodeChamps0419

Demonstrates the Nashorn JavaScript Engine introduced in Java 8.
*/

package com.codechamps.deprecated;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornDemo {

    public static void main(String[] args) {
        System.out.println("--- Nashorn Engine Demonstrations ---\n");

        // INITIALIZE NASHORN SCRIPT ENGINE
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        if (engine == null) {
            System.out.println("Nashorn engine is not available in your current JDK runtime (from JDK 15).");
            return;
        }

        try {
            // EXECUTING INLINE JAVASCRIPT CODE
            System.out.println("1. Executing JavaScript code string:");
            engine.eval("var message = 'Hello from Nashorn!'; print('   ' + message);");
            System.out.println();

            // PASSING DATA FROM JAVA TO JAVASCRIPT
            System.out.println("2. Passing variables to JavaScript:");
            engine.put("userRole", "ADMIN");
            engine.eval("print('   User role passed from Java: ' + userRole);");
            System.out.println();

            // INVOKING A JAVASCRIPT FUNCTION FROM JAVA
            System.out.println("3. Invoking a JS function from Java:");
            String jsCode = "function multiply(a, b) { return a * b; }";
            engine.eval(jsCode);

            Invocable invocable = (Invocable) engine;
            Object result = invocable.invokeFunction("multiply", 6, 7);
            System.out.println("   Result of multiply(6, 7): " + result);
            System.out.println();

            // CALLING JAVA API FROM JAVASCRIPT
            System.out.println("4. Calling Java API inside JavaScript:");
            String jsJavaCall = "var ArrayList = Java.type('java.util.ArrayList');"
                              + "var list = new ArrayList();"
                              + "list.add('Java');"
                              + "list.add('Nashorn');"
                              + "print('   ArrayList created in JS: ' + list);";
            engine.eval(jsJavaCall);

        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

/*
Output:

Java 8:
-------
H:\Passionate Buddies\Java>java NashornDemo

--- Nashorn Engine Demonstrations ---

1. Executing JavaScript code string:
   Hello from Nashorn!

2. Passing variables to JavaScript:
   User role passed from Java: ADMIN

3. Invoking a JS function from Java:
   Result of multiply(6, 7): 42.0

4. Calling Java API inside JavaScript:
   ArrayList created in JS: [Java, Nashorn]


Java 15+:
---------

--- Nashorn Engine Demonstrations ---

Nashorn engine is not available in your current JDK runtime (from JDK 15).

*/