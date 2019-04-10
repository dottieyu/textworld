import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // create nodes and edges
        Graph g = new Graph();
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

        // create movingEntities
        ArrayList<Chicken> chickens = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            chickens.add(new Chicken("bob", "the chick", g.getNode("closet")));
        }

        // user
        String in;
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("You are in the " + player.getCurrentRoom().getName());
            System.out.print("What do you want to do? >");
            in = s.nextLine();

            String[] response = in.split(" ");

            if (response.length == 1) {
                if (response[0].equals("look")) {
                    System.out.println("you see entrances: " + player.getCurrentRoom().getNeighborNames());
                    System.out.println("you see items: " + player.getCurrentRoom().getItemInventory());
                    System.out.println("you hold items: " + player.getItemInventory());
                    System.out.println("you see creatures: " + player.getCurrentRoom().getMovingEntities());
                } else {
                    System.out.println("can't do that. available commands: go <room>, look, or addroom <room>?, take <itemName>, drop <itemName>");
                }
            } else if (response.length == 2) {
                if (response[0].equals("go") && response[1].equals("random")) {
                    player.moveToRandomRoom();
                } else if (response[0].equals("go") && player.getCurrentRoom().containsNeighbor(response[1])) {
                    player.moveToRoom(response[1]);
                    System.out.println("SUCCESS");
                } else if (response[0].equals("addroom")) {
                    g.addNode(response[1]);
                    g.addDirectedEdge(player.getCurrentRoom().getName(), response[1]);
                    System.out.println("SUCCESS");
                } else if (response[0].equals("take") && player.getCurrentRoom().containsItem(response[1])) {
                    player.addItem(player.getCurrentRoom().removeItem(response[1]));
                    System.out.println("SUCCESS");
                } else if (response[0].equals("drop") && player.containsItem(response[1])) {
                    player.getCurrentRoom().addItem(player.removeItem(response[1]));
                    System.out.println("SUCCESS");
                } else {
                    System.out.println("can't do that. available commands: go <room>, look, or addroom <room>?, take <itemName>, drop <itemName>");
                }
            } else {
                System.out.println("can't do that. available commands: go <room>, look, or addroom <room>?, take <itemName>, drop <itemName>");
            }

            for (Chicken chicken : chickens) {
                chicken.moveToRandomRoom();
            }

            System.out.println();

        } while (!in.equals("quit"));

    }
}
