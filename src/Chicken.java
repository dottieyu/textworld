public class Chicken extends MovingEntity {

    public Chicken(Graph.Node currentRoom) {
        super(currentRoom);
    }

    public Chicken(String name, Graph.Node currentRoom) {
        super(currentRoom);
        this.name = name;
    }

    public Chicken(String name, String description, Graph.Node currentRoom) {
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
