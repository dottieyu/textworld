public class GoCommand extends Command {

    public GoCommand(String[] in, MovingEntity player) {
        super(in, player);
    }

    @Override
    public boolean execute() {
        if (in.length == 2) {
            if (in[1].equals("random")) {
                player.moveToRandomRoom();
            } else {
                return player.moveToRoom(in[1]);

            }
        }
        return false;
    }
}
