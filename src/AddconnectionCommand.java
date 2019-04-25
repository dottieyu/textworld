public class AddconnectionCommand extends Command {
    Graph g;

    public AddconnectionCommand(Graph g, String[] in, Player player) {
        super(in, player);
        this.g = g;
    }

    @Override
    public boolean execute() {
        if (in.length != 2) return false;

        for (Graph.Node n : g.getNodes()) {
            if (n.getName().equals(in[1])) {
                player.getCurrentRoom().addNeighbor(n);
                return true;
            }
        }
        return false;
    }
}
