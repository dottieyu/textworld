public class Wumpus extends MovingEntity {
    public Wumpus(Graph.Node currentRoom) {
        super(currentRoom);
    }

    public Wumpus(String name, Graph.Node currentRoom) {
        super(currentRoom);
        this.name = name;
    }

    public Wumpus(String name, String description, Graph.Node currentRoom) {
        super(currentRoom);
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean moveToRandomRoom() {
        Graph.Node playerRoom = currentRoom.containsAPlayer();
        if (playerRoom == null) return false;

        do {
            this.moveToRoom(this.getRandomAdjacentRoom());
        } while (currentRoom == playerRoom);

        return true;

    }
}