public class LookCommand extends Command {
    Graph g;

    public LookCommand(Graph g, String[] in, MovingEntity player) {
        super(in, player);
        this.g = g;
    }

    @Override
    public boolean execute() {
        if (in.length == 1) {
            System.out.println("YOU CAN ENTER: " + player.getCurrentRoom().getNeighborNames());
            System.out.println("YOU CAN TAKE: " + player.getCurrentRoom().getItemInventory());
            System.out.println("YOU CAN DROP: " + player.getItemInventory());
            System.out.println("YOU SEE CREATURES: " + player.getCurrentRoom().getMovingEntities());
            return true;
        }

        return false;
    }
}
