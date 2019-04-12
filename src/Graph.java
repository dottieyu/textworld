import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private HashMap<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public void addNode(String name) {
        nodes.put(name, new Node(name));
    }

    public void addNode(String name, String description) {
        nodes.put(name, new Node(name, description));
    }

    public boolean addDirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        if (n1 == null || n2 == null) {
            return false;
        } else {
            n1.addNeighbor(n2);
            return true;
        }
    }

    public boolean addUndirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        if (n1 == null || n2 == null) {
            return false;
        } else {
            n1.addNeighbor(n2);
            n2.addNeighbor(n1);
            return true;
        }
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public Node getRandomNode() {
         ArrayList<Node> nodes = new ArrayList<>(this.nodes.values());
         int i = (int) (Math.random() * nodes.size());
         return nodes.get(i);
    }

    public boolean moveAllMovingEntitiesToRandomRoom() {
        boolean flag = false;   // because moving a creature removes it from the ArrayList<MovingEntity> for each node,
                                // this flag signals to subtract 1 from i (i = i-1) to ensure that every MovingEntity is moved.
                                // each creature also has its own movedFlag field that turns true when the creature is moved,
                                // so that it doesn't get moved twice. All flags are reset to false at the end of the loops.
                                // This method is really messy sorry I couldn't think of a better way than using flags
        for (String key : nodes.keySet()) {
            ArrayList<MovingEntity> movingEntities = nodes.get(key).getMovingEntities();

            for (int i = 0; i < movingEntities.size(); i++) {
                MovingEntity creature = movingEntities.get(i);

                if (creature.getClass() != Player.class && !creature.getMovedFlag()) {
                    flag = creature.moveToRandomRoom();
                    creature.setMovedFlag(true);
                }

                if (flag) i = i-1;
                flag = false;
            }
        }
        resetFlags();
        return true;
    }

    private void resetFlags() {
        for (String key : nodes.keySet()) {
            ArrayList<MovingEntity> movingEntities = nodes.get(key).getMovingEntities();
            for (MovingEntity movingEntity : movingEntities) {
                movingEntity.setMovedFlag(false);
            }
        }
    }

    public class Node extends ItemContainer {
        private String name;
        private HashMap<String, Node> neighbors;
        private String description;
        private ArrayList<MovingEntity> movingEntities;

        public Node(String name) {
            super();
            this.name = name;
            this.neighbors = new HashMap<>();
            description = "no description";
            this.movingEntities = new ArrayList<>();
        }

        public Node(String name, String description) {
            super();
            this.description = description;
            this.name = name;
            this.neighbors = new HashMap<>();
            this.movingEntities = new ArrayList<>();
        }

        public void addNeighbor(Node n) {
            this.neighbors.put(n.getName(), n);
        }

        /**
         * Return neighb or whose name is name. Returns null otherwise.
         * @param name name of neighboring node to return.
         * @return returns neighboring node with correct name
         */
        public Node getNeighbor(String name) {
            return neighbors.get(name);
        }

        public Node getRandomNeighbor() {
            if (this.neighbors.size() == 0) return this;
            int i = (int) (Math.random() * this.neighbors.size());
            String key = (String) this.neighbors.keySet().toArray()[i];
            return this.neighbors.get(key);
        }

        /**
         * Returns a String of the names of all the neighbors of this node.
         * @return
         */
        public String getNeighborNames() {
            String str = "";
            for (String key : neighbors.keySet()) {
                str += key + " (" + getNeighbor(key).getDescription() + "); ";
            }
            return str;
        }

        public ArrayList<Node> getNeighborRooms() {
            return new ArrayList<>(this.neighbors.values());
        }

        public boolean containsNeighbor(String name) {
            return neighbors.containsKey(name);
        }

        public boolean containsNeighbor(Graph.Node name) {
            return neighbors.containsValue(name);
        }

        public boolean containsPlayer(String name) {
            for (MovingEntity movingEntity : movingEntities) {
                if (movingEntity.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }

        public Node containsAPlayer() {
            for (MovingEntity movingEntity : movingEntities) {
                if (movingEntity.getClass() == Player.class) {
                    return movingEntity.getRoom();
                }
            }
            return null;
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

        public ArrayList<MovingEntity> getMovingEntities() {
            return this.movingEntities;
        }

        public void addMovingEntity(MovingEntity movingEntity) {
            this.movingEntities.add(movingEntity);
        }

        /**
         *
         * @param m1
         * @return null if input isn't in the room
         */
        public MovingEntity removeMovingEntity(MovingEntity m1) {
            boolean flag = this.movingEntities.remove(m1);
            if (flag) {
                return m1;
            } else {
                return null;
            }
        }

        public boolean destroyMovingEntity(MovingEntity m) {
            return this.movingEntities.remove(m);
        }

        public boolean addMovingEntities(int n, String type) {
            if (type.equals("chicken")) {
                for (int i = 0; i < n; i++) {
                    new Chicken("chick" + i, this);
                }
                return true;
            } else if (type.equals("wumpus")) {
                for (int i = 0; i < n; i++) {
                    new Wumpus("wumpus" + i, this);
                }
                return true;
            } else if (type.equals("popstar")) {
                for (int i = 0; i < n; i++) {
                    new PopStar("popstar" + i, this);
                }
                return true;
            }
            return false;
        }

        public String toString() {
            return getName() + " (" + getDescription() + ")";
        }
    }

    public String toString() {
        ArrayList<Node> nodes = new ArrayList<>(this.nodes.values());
        String s = "Nodes in graph: ";
        for (Node node : nodes) {
            s += node.getName() + "; ";
        }
        return s;
    }
}
