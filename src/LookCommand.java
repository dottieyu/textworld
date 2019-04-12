public class LookCommand extends Command {

    public LookCommand(String[] in, MovingEntity player) {
        super(in, player);
    }

    @Override
    public boolean execute() {
        if (in.length == 1) {
            System.out.println("you see entrances: " + player.getCurrentRoom().getNeighborNames());
            System.out.println("you see items: " + player.getCurrentRoom().getItemInventory());
            System.out.println("you hold items: " + player.getItemInventory());
            System.out.println("you see creatures: " + player.getCurrentRoom().getMovingEntities());
            return true;
        }

        return false;
    }
}
