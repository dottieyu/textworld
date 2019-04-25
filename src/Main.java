import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Graph g;
    public static Player player;

    public static void main(String[] args) {
        createWorld();

        String[] in;
        Scanner s = new Scanner(System.in);

        do {
            System.out.println();

            System.out.println("You are in the " + player.getCurrentRoom().getName());
            System.out.print("What do you want to do? >");
            in = s.nextLine().split(" ");
            Command command = parseCommand(in);

            boolean flag = command.execute();
            if (flag) {
                System.out.println("***SUCCESS***");
            } else {
                System.out.println("can't do that. available commands: \n\tkill <movingEntity> \n\tgo <room> \n\tlook \n\taddroom <room> \n\taddconnection <room> \n\ttake <itemName> \n\tdrop <itemName> \n\trename <movingEntityOldName> <movingEntityNewName> \n\tdebughelp");
            }

        } while (!in[0].equals("quit"));

    }

    public static Command parseCommand(String[] in) {
        if (in[0].equals("go")) return new GoCommand(g, in, player);
        if (in[0].equals("look")) return new LookCommand(g, in, player);
        if (in[0].equals("addroom")) return new AddroomCommand(g, in, player);
        if (in[0].equals("take")) return new TakeCommand(in, player);
        if (in[0].equals("drop")) return new DropCommand(in, player);
        if (in[0].equals("kill")) return new KillCommand(in, player);
        if (in[0].equals("rename")) return new RenameCommand(g, in, player);
        if (in[0].equals("addconnection")) return new AddconnectionCommand(g, in, player);
        if (in[0].equals("debughelp")) return new DebugCommand(g, in, player);

        return new ErrorCommand(in, player);
    }

    public static void createWorld(int numChicken, int numPopstar, int numWumpus) {

        // create nodes and edges
        g = new Graph();
        g.addNode("hall", "a long dark hallway");
        g.addNode("closet", "where the monsters live");
        g.addNode("dungeon");
        g.addNode("bedroom1", "my bedroom");
        g.addNode("bedroom2", "brother's bedroom");
        g.addNode("bathroom", "go pee");
        g.addNode("kitchen", "smells yummy");
        g.addNode("livingroom");
        g.addNode("door");
        g.addNode("garden");
        g.addNode("secretfoodstash");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        g.addUndirectedEdge("hall", "bedroom1");
        g.addUndirectedEdge("hall", "bedroom2");
        g.addUndirectedEdge("hall", "bathroom");
        g.addUndirectedEdge("bedroom1", "bathroom");
        g.addUndirectedEdge("hall", "kitchen");
        g.addUndirectedEdge("livingroom", "kitchen");
        g.addUndirectedEdge("bedroom1", "bedroom2");
        g.addUndirectedEdge("livingroom", "door");
        g.addUndirectedEdge("door", "garden");
        g.addUndirectedEdge("garden", "secretfoodstash");

        // create player
        player = new Player("dottie", "best player", g.getNode("hall"));
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("book"));
        items.add(new Item("puppy"));
        items.add(new Item("ball"));
        player.addItems(items);

        g.getNode("closet").addMovingEntities(numChicken, "chicken");
        g.getNode("secretfoodstash").addMovingEntities(numPopstar, "popstar");
        g.getNode("bathroom").addMovingEntities(numWumpus, "wumpus");
    }

    public static void createWorld() {
        Scanner s = new Scanner(System.in);
        System.out.println("WELCOME TO TEXTWORLD! In this game there are creatures, objects, and rooms you can interact with.");
        System.out.print("How many chickens? Chickens are born in the closet. >");
        int numChicken = Integer.parseInt(s.nextLine());
        System.out.print("How many popstars? Popstars are born in the secretfoodstash. >");
        int numPopstar = Integer.parseInt(s.nextLine());
        System.out.print("How many wumpuses? Wumpuses are born in the bathroom. >");
        int numWumpus = Integer.parseInt(s.nextLine());
        createWorld(numChicken, numPopstar, numWumpus);
    }
}
