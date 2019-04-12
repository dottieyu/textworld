import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Graph g;

    public static void main(String[] args) {

        // create nodes and edges
        g = new Graph();
        g.addNode("hall", "a long dark hallway");
        g.addNode("closet", "where the monsters live");
        g.addNode("dungeon");
        g.addNode("bedroom1", "my bedroom");
        g.addNode("bedroom2", "brother's bedroom");
        g.addNode("bathroom", "go pee");
        g.addNode("kitchen", "smells yummy");
        g.addNode("living room");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        g.addUndirectedEdge("hall", "bedroom1");
        g.addUndirectedEdge("hall", "bedroom2");
        g.addUndirectedEdge("hall", "bathroom");
        g.addUndirectedEdge("bedroom1", "bathroom");
        g.addUndirectedEdge("hall", "kitchen");
        g.addUndirectedEdge("living room", "kitchen");
        g.addUndirectedEdge("bedroom1", "bedroom2");

        // create player
        Player player = new Player("dottie", "best player", g.getNode("hall"));
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("book"));
        items.add(new Item("puppy"));
        items.add(new Item("ball"));
        player.addItems(items);

        g.getNode("closet").addMovingEntities(50, "chicken");

        // user
        String[] in;
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("You are in the " + player.getCurrentRoom().getName());
            System.out.print("What do you want to do? >");
            in = s.nextLine().split(" ");
            Command command = parseCommand(in, player);

            boolean flag = command.execute();
            if (flag) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("can't do that. available commands: go <room>, look, or addroom <room>?, take <itemName>, drop <itemName>");
            }

            g.moveAllMovingEntitiesToRandomRoom();

            System.out.println();

        } while (!in[0].equals("quit"));

    }

    public static Command parseCommand(String[] in, Player player) {
        if (in[0].equals("go")) return new GoCommand(in, player);
        if (in[0].equals("look")) return new LookCommand(in, player);
        if (in[0].equals("addroom")) return new AddroomCommand(g, in, player);
        if (in[0].equals("take")) return new TakeCommand(in, player);
        if (in[0].equals("drop")) return new DropCommand(in, player);

        return new ErrorCommand(in, player);
    }
}
