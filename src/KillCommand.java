public class KillCommand extends Command {
    public KillCommand(String[] in, MovingEntity player) {
        super(in, player);
    }


    @Override
    public boolean execute() {
        if (in.length == 2)  {
            for (MovingEntity movingEntity : player.getCurrentRoom().getMovingEntities()) {
                if (movingEntity.getName().equals(in[1])) {
                    return player.getCurrentRoom().destroyMovingEntity(movingEntity);
                }
            }
        }
        return false;
    }
}
