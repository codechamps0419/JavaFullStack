/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
Configuration Manager.

An application may need one shared configuration object containing
values such as:

- Environment
- Application properties
- Feature flags
- Common configuration

WITHOUT SINGLETON PATTERN:
Different parts of the application may create separate
ConfigurationManager objects.

Problems:
- Multiple unnecessary instances may be created.
- Configuration values may become inconsistent between instances.
- Extra memory/resources may be used.
- There is no single controlled access point to shared configuration.


WITH SINGLETON PATTERN:
Only ONE instance of ConfigurationManager is created and the same
instance is shared throughout the application.

MAIN IDEA:
"Ensure only one instance of a class exists and provide a global
 access point to that instance."


COMMON SINGLETON IMPLEMENTATIONS:

1. EAGER
   -> Instance created during class initialization.
   -> Simple and thread-safe.
   -> Object is created even if never used.

2. LAZY
   -> Instance created only when first requested.
   -> Not thread-safe without synchronization.

3. SYNCHRONIZED
   -> Thread-safe lazy initialization.
   -> Synchronization happens on every getInstance() call.

4. DOUBLE-CHECKED LOCKING
   -> Thread-safe with better performance.
   -> Uses synchronized block only during initialization.
   -> Instance should be volatile.

5. BILL PUGH
   -> Uses static inner Holder class.
   -> Lazy and thread-safe without explicit synchronization.

6. ENUM
   -> Very simple and thread-safe Singleton implementation.
   -> JVM guarantees only one enum constant instance.


STRUCTURE:

Client
  |
  v
getInstance()
  |
  v
Singleton
  |
  v
Single Shared Instance
*/

public class SingletonPatternDemo {

    public static void main(String[] args) {

        System.out.println("===== Eager =====");
        System.out.println(
                EagerConfigManager.getInstance().getEnvironment());

        System.out.println("\n===== Lazy =====");
        System.out.println(
                LazyConfigManager.getInstance().getEnvironment());

        System.out.println("\n===== Synchronized =====");
        System.out.println(
                SyncConfigManager.getInstance().getEnvironment());

        System.out.println("\n===== DCL =====");
        System.out.println(
                DCLConfigManager.getInstance().getEnvironment());

        System.out.println("\n===== Bill Pugh =====");
        System.out.println(
                BillPughConfigManager.getInstance().getEnvironment());

        System.out.println("\n===== Enum =====");
        System.out.println(
                EnumConfigManager.INSTANCE.getEnvironment());
    }
}


//1. EAGER SINGLETON: Object created during class loading.

class EagerConfigManager {

    private static final EagerConfigManager INSTANCE =
            new EagerConfigManager();

    private String environment;

    private EagerConfigManager() {
        environment = "PROD";
        System.out.println("Config Loaded (Eager)");
    }

    public static EagerConfigManager getInstance() {
        return INSTANCE;
    }

    public String getEnvironment() {
        return environment;
    }
}


//2. LAZY SINGLETON: Object created when first requested.

class LazyConfigManager {

    private static LazyConfigManager instance;

    private String environment;

    private LazyConfigManager() {
        environment = "PROD";
        System.out.println("Config Loaded (Lazy)");
    }

    public static LazyConfigManager getInstance() {

        if (instance == null) {
            instance = new LazyConfigManager();
        }

        return instance;
    }

    public String getEnvironment() {
        return environment;
    }
}


//3. SYNCHRONIZED SINGLETON: Thread Safe but slower.

class SyncConfigManager {

    private static SyncConfigManager instance;

    private String environment;

    private SyncConfigManager() {
        environment = "PROD";
        System.out.println("Config Loaded (Sync)");
    }

    public static synchronized SyncConfigManager getInstance() {

        if (instance == null) {
            instance = new SyncConfigManager();
        }

        return instance;
    }

    public String getEnvironment() {
        return environment;
    }
}


//4. DOUBLE CHECKED LOCKING: Thread Safe + Better Performance.

class DCLConfigManager {

    private static volatile DCLConfigManager instance;

    private String environment;

    private DCLConfigManager() {
        environment = "PROD";
        System.out.println("Config Loaded (DCL)");
    }

    public static DCLConfigManager getInstance() {

        if (instance == null) {

            synchronized (DCLConfigManager.class) {

                if (instance == null) {
                    instance = new DCLConfigManager();
                }
            }
        }

        return instance;
    }

    public String getEnvironment() {
        return environment;
    }
}


//5. BILL PUGH SINGLETON

class BillPughConfigManager {

    private String environment;

    private BillPughConfigManager() {
        environment = "PROD";
        System.out.println("Config Loaded (Bill Pugh)");
    }

    private static class Holder {

        private static final BillPughConfigManager INSTANCE =
                new BillPughConfigManager();
    }

    public static BillPughConfigManager getInstance() {
        return Holder.INSTANCE;
    }

    public String getEnvironment() {
        return environment;
    }
}


//6. ENUM SINGLETON: Recommended traditional Singleton. Thread Safe + Lazy Loading.

enum EnumConfigManager {

    INSTANCE;

    private final String environment;

    EnumConfigManager() {
        environment = "PROD";
        System.out.println("Config Loaded (Enum)");
    }

    public String getEnvironment() {
        return environment;
    }
}


/*
Output:

H:\Design Patterns\Creational>java SingletonPatternDemo
===== Eager =====
Config Loaded (Eager)
PROD

===== Lazy =====
Config Loaded (Lazy)
PROD

===== Synchronized =====
Config Loaded (Sync)
PROD

===== DCL =====
Config Loaded (DCL)
PROD

===== Bill Pugh =====
Config Loaded (Bill Pugh)
PROD

===== Enum =====
Config Loaded (Enum)
PROD

*/