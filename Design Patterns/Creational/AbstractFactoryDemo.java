/*
@Author: Dynamic Duo Learners 

REAL WORLD EXAMPLE: CLOUD PROVIDER PLATFORM

An application needs related cloud services such as:
- Storage - Database

Different cloud providers provide their own compatible family:

AWS:
- AWS S3 Storage
- AWS RDS Database
GCP:
- GCP Cloud Storage
- GCP Cloud SQL

WITHOUT ABSTRACT FACTORY PATTERN:
Client directly creates provider-specific objects such as
AWSStorage, AWSDatabase, GCPStorage and GCPDatabase.

Problems:
- Client becomes tightly coupled to concrete cloud classes.
- Switching cloud providers requires changing client code.
- Related objects from different providers can accidentally be mixed.
- Adding another provider requires more provider-specific creation logic.

WITH ABSTRACT FACTORY PATTERN:
Client works with CloudFactory, Storage and Database abstractions.

Each concrete factory creates a complete family of related objects:

AWSFactory
   |
   +--> AWSStorage
   +--> AWSDatabase

GCPFactory
   |
   +--> GCPStorage
   +--> GCPDatabase


MAIN IDEA:
"Create families of related objects without exposing their concrete classes to the client."


STRUCTURE:

                 CloudFactory
                      |
              +-------+-------+
              |               |
         AWSFactory       GCPFactory
              |               |
       +------+------+   +----+---------+
       |             |   |              |
 AWSStorage   AWSDatabase GCPStorage  GCPDatabase

Products:
Storage
Database

*/

public class AbstractFactoryDemo {

    public static void main(String[] args) {

        CloudFactory factory = new AWSFactory();
        Storage storage = factory.createStorage();
        Database database = factory.createDatabase();

        storage.store();
        database.save();

	factory = new GCPFactory();
	storage = factory.createStorage();
        database = factory.createDatabase();

        storage.store();
        database.save();

    }
}

//Abstract Factory
interface CloudFactory {
    Storage createStorage();
    Database createDatabase();
}


interface Storage {
    void store();
}

interface Database {
    void save();
}

//AWS Family
class AWSFactory implements CloudFactory {

    public Storage createStorage() {
        return new AWSStorage();
    }

    public Database createDatabase() {
        return new AWSDatabase();
    }
}

class AWSStorage implements Storage {

    public void store() {
        System.out.println("AWS S3 Storage");
    }
}

class AWSDatabase implements Database {

    public void save() {
        System.out.println("AWS RDS Database");
    }
}

//GCP Family
class GCPFactory implements CloudFactory {

    public Storage createStorage() {
        return new GCPStorage();
    }

    public Database createDatabase() {
        return new GCPDatabase();
    }
}

class GCPStorage implements Storage {

    public void store() {
        System.out.println("GCP Cloud Storage");
    }
}

class GCPDatabase implements Database {

    public void save() {
        System.out.println("GCP Cloud SQL");
    }
}



/*

Output:

H:\Design Patterns\Creational>java AbstractFactoryDemo
AWS S3 Storage
AWS RDS Database
GCP Cloud Storage
GCP Cloud SQL

*/
