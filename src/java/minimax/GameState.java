package minimax;

public class GameState {

    private byte position[][];

    /**
     * @param position
     */
    public GameState(byte[][] position) {
        this.position = position;
    }

    public byte getPositionValue(byte i, byte j) {
        return (position[i][j]);
    }

    public void setPositionValue(byte i, byte j, byte value) {
        position[i][j] = value;
    }

    public byte[][] getPosition() {
        return position;
    }

    public void setPosition(byte[][] position) {
        this.position = position;
    }

}
