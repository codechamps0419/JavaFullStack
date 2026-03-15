package com.codechamps.language;

/*
@author CodeChamps0419

Demonstrates Java 8 Interface Enhancements: Default and Static Methods.
*/

public class DefaultAndStaticMethodDemo {

    interface Vehicle {
        void startEngine();

        default void honk() {
            System.out.println("Default Vehicle Honk: Beep! Beep!");
        }

        static void displayVehicleInfo() {
            System.out.println("Static Utility: Vehicles provide personal and commercial mobility.");
        }
    }

    static class Car implements Vehicle {
        @Override
        public void startEngine() {
            System.out.println("Car engine started with a push button.");
        }

        @Override
        public void honk() {
            System.out.println("Car Custom Honk: Customized sports horn sound!");
        }
    }

    static class Bike implements Vehicle {
        @Override
        public void startEngine() {
            System.out.println("Bike engine started with a self-start kick.");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 8 Default and Static Methods Demonstrations ---\n");

        System.out.print("1. Calling Interface Static Method -> ");
        Vehicle.displayVehicleInfo();
        System.out.println();

        System.out.println("2. Car Implementation:");
        Vehicle car = new Car();
        car.startEngine();
        car.honk();
        System.out.println();

        System.out.println("3. Bike Implementation:");
        Vehicle bike = new Bike();
        bike.startEngine();
        bike.honk();
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.language.DefaultAndStaticMethodDemo

--- Java 8 Default and Static Methods Demonstrations ---

1. Calling Interface Static Method -> Static Utility: Vehicles provide personal and commercial mobility.

2. Car Implementation:
Car engine started with a push button.
Car Custom Honk: Customized sports horn sound!

3. Bike Implementation:
Bike engine started with a self-start kick.
Default Vehicle Honk: Beep! Beep!

*/
