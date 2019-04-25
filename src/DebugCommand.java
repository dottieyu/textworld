public class DebugCommand extends Command {
    Graph g;

    public DebugCommand(Graph g, String[] in, Player player) {
        super(in, player);
        this.g = g;
    }

    @Override
    public boolean execute() {
        if (in.length == 1) {
            System.out.println("MASTER GRAPH INFORMATION");
            System.out.println(g.toString());
            return true;
        }
        return false;
    }
}
