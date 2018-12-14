public class Board {
    private final int upperMargin = 100;
    private final int leftMargin = 100;
    private int boardLength = 0;
    private int boardWidth = 0;
    private int boxLength = 0;
    private int boxWidth = 0;
    private int[][] fieldBox = new int[8][8];

    //region Getter and Setter
    public int getUpperMargin() {
        return upperMargin;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public int getBoardLength() {
        return boardLength;
    }

    public void setBoardLength(int boardLength) {
        this.boardLength = boardLength;
    }

    public int getBoxLength() {
        return boxLength;
    }

    public void setBoxLength(int boxLength) {
        this.boxLength = boxLength;
    }

    public int[][] getFieldBox() {
        return fieldBox;
    }

    public void setFieldBox(int[][] fielBox) {
        this.fieldBox = fielBox;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getBoxWidth() {
        return boxWidth;
    }

    public void setBoxWidth(int boxWidth) {
        this.boxWidth = boxWidth;
    }
    //endregion
}
