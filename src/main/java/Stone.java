public class Stone extends Figure {
    public Stone(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case NORTH_EAST:
                setX(getX() - 1);
                setY(getY() + 1);
                break;
            case NORTH_WEST:
                setX(getX() - 1);
                setY(getY() - 1);
                break;
            case SOUTH_EAST:
                setX(getX() + 1);
                setY(getY() + 1);
                break;
            case SOUTH_WEST:
                setX(getX() + 1);
                setY(getY() - 1);
                break;
        }
    }
}
