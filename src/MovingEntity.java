public abstract class MovingEntity extends ItemContainer {
    public String name, description;
    public Graph.Node currentRoom;
    public boolean movedFlag = false;

    public MovingEntity(Graph.Node currentRoom) {
        super();
        this.currentRoom = currentRoom;
        currentRoom.addMovingEntity(this);
    }

    public void setMovedFlag(boolean flag) {
        this.movedFlag = flag;
    }

    public boolean getMovedFlag() {
        return movedFlag;
    }

    public Graph.Node getRoom() {
        return currentRoom;
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

    public abstract boolean moveToRandomRoom();

    public Graph.Node getRandomAdjacentRoom() {
        Graph.Node n = currentRoom.getRandomNeighbor();
        return n;
    }

    public String toString() {
        return getName() + " (" + getDescription() + ")";
    }

}
