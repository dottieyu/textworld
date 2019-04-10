public class Player extends MovingEntity {

    public Player(Graph.Node currentRoom) {
        super(currentRoom);
    }

    public Player(String name, Graph.Node currentRoom) {
        super(currentRoom);
        this.name = name;
    }

    public Player(String name, String description, Graph.Node currentRoom) {
        super(currentRoom);
        this.name = name;
        this.description = description;
    }

    public void moveToRandomRoom() {
        if (currentRoom.getNeighborRooms().size() == 0) return;
        int i = (int) (Math.random() * currentRoom.getNeighborRooms().size());
        this.moveToRoom(currentRoom.getNeighborRooms().get(i));
    }
}
