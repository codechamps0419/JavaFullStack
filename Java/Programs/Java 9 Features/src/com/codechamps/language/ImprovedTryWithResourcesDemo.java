/*
@author CodeChamps0419

Demonstrates Java 9 Improved Try-With-Resources.
*/

package com.codechamps.language;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;

public class ImprovedTryWithResourcesDemo {

    static class CustomResource implements AutoCloseable {
        private final String name;

        CustomResource(String name) {
            this.name = name;
            System.out.println("   [Resource Open] " + name + " initialized.");
        }

        public void work() {
            System.out.println("   [Resource Work] Executing operations on " + name);
        }

        @Override
        public void close() {
            System.out.println("   [Resource Close] " + name + " closed automatically.");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 9 Improved Try-With-Resources Demonstrations ---\n");

        // 1. SINGLE EFFECTIVELY FINAL RESOURCE DEMO
        // Passing an already-declared resource variable directly without creating a new alias variable (like reader2)
        System.out.println("1. Single Resource (Effectively Final):");
        BufferedReader reader = new BufferedReader(new StringReader("Line 1: Java 9 Feature\nLine 2: Try-With-Resources"));

        try (reader) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                System.out.println("   Read -> " + currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        // 2. MULTIPLE EFFECTIVELY FINAL RESOURCES DEMO
        // Managing multiple existing resource variables separated by semicolons
        System.out.println("2. Multiple Resources (Effectively Final):");
        CustomResource resourceOne = new CustomResource("Resource-A");
        CustomResource resourceTwo = new CustomResource("Resource-B");

        try (resourceOne; resourceTwo) {
            resourceOne.work();
            resourceTwo.work();
        }
        // Resources are automatically closed upon exiting the block in reverse order (LIFO)
    }
}

/*
Output:

H:\Passionate Buddies\Java\Programs\Java 9 Features\src\com\codechamps\language>java ImprovedTryWithResourcesDemo

--- Java 9 Improved Try-With-Resources Demonstrations ---

1. Single Resource (Effectively Final):
   Read -> Line 1: Java 9 Feature
   Read -> Line 2: Try-With-Resources

2. Multiple Resources (Effectively Final):
   [Resource Open] Resource-A initialized.
   [Resource Open] Resource-B initialized.
   [Resource Work] Executing operations on Resource-A
   [Resource Work] Executing operations on Resource-B
   [Resource Close] Resource-B closed automatically.
   [Resource Close] Resource-A closed automatically.

*/