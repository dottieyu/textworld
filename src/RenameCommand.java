public class RenameCommand extends Command {
    Graph g;

    public RenameCommand(Graph g, String[] in, Player player) {
        super(in, player);
        this.g = g;
    }

    @Override
    public boolean execute() {
        if (in.length == 3) {
            for (Graph.Node n : g.getNodes()) {
                MovingEntity movingEntity = n.getMovingEntity(in[1]);
                if (movingEntity != null) {
                    movingEntity.setName(in[2]);
                    return true;
                }
            }
        }
        return false;
    }
}
