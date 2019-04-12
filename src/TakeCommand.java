public class TakeCommand extends Command {

    public TakeCommand(String[] in, MovingEntity player) {
        super(in, player);
    }

    @Override
    public boolean execute() {
        if (in.length == 2) {
            Item i = player.getCurrentRoom().removeItem(in[1]);
            return player.addItem(i);
        }
        return false;
    }
}
