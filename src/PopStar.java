public class PopStar extends MovingEntity {
    public PopStar(Graph.Node currentRoom) {
        super(currentRoom);
    }

    public PopStar(String name, Graph.Node currentRoom) {
        super(currentRoom);
        this.name = name;
    }

    public PopStar(String name, String description, Graph.Node currentRoom) {
        super(currentRoom);
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean moveToRandomRoom() {
        for (Graph.Node n : currentRoom.getNeighborsOfNeighbors()) {
            if (n.containsAPlayer() != null) {
                this.moveToRoom(n);
                return true;
            }
        }
        return false;
    }
}
