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
            this.moveToRoom(this.getRandomAdjacentOfAdjacentRoom());
        } while (currentRoom == playerRoom);
        // TODO: fix bug of infinite loop where wumpus has nowhere to go except currentroom
        return true;

    }
}
