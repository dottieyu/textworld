public class ErrorCommand extends Command {

    public ErrorCommand(String[] in, MovingEntity player) {
        super(in, player);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
