public abstract class Command {
    public String[] in;
    public MovingEntity player;

    public Command(String[] in, MovingEntity player) {
        this.in = in;
        this.player = player;
    }

    public abstract boolean execute();
}
