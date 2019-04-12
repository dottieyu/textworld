public class DropCommand extends Command {

    public DropCommand(String[] in, MovingEntity player) {
        super(in, player);
    }

    public boolean execute() {
        if (in.length == 2) {
            Item i = player.removeItem(in[1]);
            return player.getCurrentRoom().addItem(i);
        }
        return false;
    }
}
