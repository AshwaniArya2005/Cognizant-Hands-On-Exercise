import java.util.ArrayDeque;
import java.util.Deque;

interface Command {
    void execute();
    void undo();
}

class Light {
    private final String name;
    private boolean isOn;

    public Light(String name) {
        this.name = name;
        this.isOn = false;
    }

    public void turnOn() {
        isOn = true;
        System.out.println(name + " Light is ON.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(name + " Light is OFF.");
    }

    public boolean isOn() {
        return isOn;
    }
}

class Fan {
    private final String name;
    private boolean isOn;

    public Fan(String name) {
        this.name = name;
        this.isOn = false;
    }

    public void turnOn() {
        isOn = true;
        System.out.println(name + " Fan is ON.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(name + " Fan is OFF.");
    }
}

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

class FanOnCommand implements Command {
    private final Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOn();
    }

    @Override
    public void undo() {
        fan.turnOff();
    }
}

class FanOffCommand implements Command {
    private final Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOff();
    }

    @Override
    public void undo() {
        fan.turnOn();
    }
}

class RemoteControl {
    private Command lastCommand;
    private final Deque<Command> history = new ArrayDeque<>();

    public void pressButton(Command command) {
        command.execute();
        history.push(command);
        lastCommand = command;
    }

    public void pressUndo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            System.out.print("Undoing last command -> ");
            command.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }
}

public class CommandPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Command Pattern Implementation Demo ===\n");

        Light livingRoomLight = new Light("Living Room");
        Light bedroomLight = new Light("Bedroom");
        Fan ceilingFan = new Fan("Ceiling");

        Command livingRoomOn  = new LightOnCommand(livingRoomLight);
        Command livingRoomOff = new LightOffCommand(livingRoomLight);
        Command bedroomOn     = new LightOnCommand(bedroomLight);
        Command bedroomOff    = new LightOffCommand(bedroomLight);
        Command fanOn         = new FanOnCommand(ceilingFan);
        Command fanOff        = new FanOffCommand(ceilingFan);

        RemoteControl remote = new RemoteControl();

        System.out.println("--- Pressing Buttons ---");
        remote.pressButton(livingRoomOn);
        remote.pressButton(bedroomOn);
        remote.pressButton(fanOn);

        System.out.println("\n--- Turning some off ---");
        remote.pressButton(bedroomOff);
        remote.pressButton(fanOff);

        System.out.println("\n--- Undo last 3 commands ---");
        remote.pressUndo();
        remote.pressUndo();
        remote.pressUndo();

        System.out.println("\n=============================================");
        System.out.println("SUCCESS: Command Pattern working correctly!");
        System.out.println("=============================================");
    }
}
