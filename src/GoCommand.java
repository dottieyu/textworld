public class GoCommand extends Command {
    Graph g;

    public GoCommand(Graph g, String[] in, MovingEntity player) {
        super(in, player);
        this.g = g;
    }

    @Override
    public boolean execute() {
        if (in.length == 2) {
            if (in[1].equals("random")) {
                player.moveToRandomRoom();
                g.moveAllMovingEntitiesToRandomRoom();
                return true;
            } else {
                boolean b = player.moveToRoom(in[1]);
                g.moveAllMovingEntitiesToRandomRoom();
                return b;
            }
        }
        return false;
    }
}
