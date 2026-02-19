/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
Smart Home remote control.

A remote button can trigger different actions:
- Turn Light ON
- Turn Light OFF
- Turn AC ON

WITHOUT COMMAND PATTERN:
RemoteControl directly calls device methods such as light.turnOn()
or ac.turnOn().

Problems:
- Tight coupling between RemoteControl and devices.
- Adding new actions requires changing RemoteControl.
- Can lead to large if/else or switch logic.
- Undo, queueing, logging, retries and scheduling become difficult.

WITH COMMAND PATTERN:
Each request/action is wrapped inside a separate Command object.
RemoteControl only executes the Command and does not need to know
device-specific methods. 
Invoker executes a Command object, which calls the Receiver.

MAIN IDEA:
"Turn a request/action into an object."

This makes features such as undo, queueing, logging, retries and
scheduled execution easier.

STRUCTURE:

Client
  |
  v
Command
  |
  +--> LightOnCommand
  +--> LightOffCommand
  +--> AcOnCommand

Invoker  -> RemoteControl
Receiver -> Light / AirConditioner
*/

public class CommandPatternDemo {

    public static void main(String[] args) {
        System.out.println("====== SMART HOME USING COMMAND PATTERN ======\n");

        // Receivers
        Light livingRoomLight = new Light("Living Room");
        AirConditioner ac = new AirConditioner();

        // Command objects
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        Command acOn = new AcOnCommand(ac);

        // Invoker
        RemoteControl remote = new RemoteControl();

        remote.setCommand(lightOn);
        remote.pressButton();
        remote.pressUndo();

        System.out.println();

        remote.setCommand(lightOff);
        remote.pressButton();

        System.out.println();

        remote.setCommand(acOn);
        remote.pressButton();
        remote.pressUndo();
    }
}

// 1. COMMAND
interface Command {
    void execute();
    void undo();
}

// 2. RECEIVER
class Light {
    private final String location;

    public Light(String location) {
        this.location = location;
    }

    public void turnOn() {
        System.out.println("Light: " + location + " light turned ON.");
    }

    public void turnOff() {
        System.out.println("Light: " + location + " light turned OFF.");
    }
}

// 3. RECEIVER
class AirConditioner {
    public void turnOn() {
        System.out.println("AC: Air conditioner turned ON.");
    }

    public void turnOff() {
        System.out.println("AC: Air conditioner turned OFF.");
    }
}

// 4. CONCRETE COMMAND
class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

// 5. CONCRETE COMMAND
class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

// 6. CONCRETE COMMAND
class AcOnCommand implements Command {
    private final AirConditioner ac;

    public AcOnCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOn();
    }

    @Override
    public void undo() {
        ac.turnOff();
    }
}

// 7. INVOKER
class RemoteControl {
    private Command command;
    private Command lastExecutedCommand;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command == null) {
            throw new IllegalStateException("No command configured.");
        }
        command.execute();
        lastExecutedCommand = command;
    }

    public void pressUndo() {
        if (lastExecutedCommand == null) {
            System.out.println("Remote: Nothing to undo.");
            return;
        }
        System.out.println("Remote: Undo requested.");
        lastExecutedCommand.undo();
    }
}

/*
Output:

====== SMART HOME USING COMMAND PATTERN ======

Light: Living Room light turned ON.
Remote: Undo requested.
Light: Living Room light turned OFF.

Light: Living Room light turned OFF.

AC: Air conditioner turned ON.
Remote: Undo requested.
AC: Air conditioner turned OFF.

*/
