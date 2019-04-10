public abstract class MovingEntity extends ItemContainer {
    public String name, description;
    public Graph.Node currentRoom;

    public MovingEntity(Graph.Node currentRoom) {
        super();
        this.currentRoom = currentRoom;
        currentRoom.addMovingEntity(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Graph.Node getCurrentRoom() {
        return currentRoom;
    }

    // use moveToRoom(); setRoom() has same function so does not exist
    public boolean moveToRoom(String name) {
        if (currentRoom.containsNeighbor(name)) {
            currentRoom.removeMovingEntity(this);
            currentRoom = currentRoom.getNeighbor(name);
            currentRoom.addMovingEntity(this);
            return true;
        } else {
            return false;
        }
    }

    public boolean moveToRoom(Graph.Node name) {
        if (currentRoom.containsNeighbor(name)) {
            currentRoom.removeMovingEntity(this);
            currentRoom = name;
            currentRoom.addMovingEntity(this);
            return true;
        } else {
            return false;
        }
    }

    public abstract void moveToRandomRoom();

    public Graph.Node getRandomAdjacentRoom() {
        return currentRoom.getRandomNeighbor();
    }

    public boolean isRoomCloserTo(Player p, Graph.Node n) {
        // TODO: idfk how to implement this :/
        return false;
    }

    public String toString() {
        return getName() + " (" + getDescription() + ")";
    }

}
