public class AddroomCommand extends Command {
    private Graph g;

    public AddroomCommand(Graph g, String[] in, MovingEntity player) {
        super(in, player);
        this.g = g;
    }

    @Override
    public boolean execute() {
        g.addNode(in[1]);
        g.addDirectedEdge(player.getCurrentRoom().getName(), in[1]);
        return true;
    }
}
