import processing.core.PApplet;

public class Main extends PApplet {

    private final int width = 800;
    private final int height = 800;

    private int stones = 12;

    private Board board = new Board();
    private Figure[] schwarzeDamen = new Figure[stones];
    private Figure[] weisseDamen = new Figure[stones];
    private Figure aktiveDame;

    private Direction direction;

    private boolean turnWhite = true;
    private boolean finishedturn = true;

    Stone[] opportunitys = new Stone[14];
    Stone newFigure;

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void settings() {
        size(width, height);
    }

    public void setup() {
        background(255);
        createDamen();
    }



    public void draw() {
        drawText();
        drawField();
        drawFieldBox();
        drawDamen(weisseDamen);
        drawDamen(schwarzeDamen);
        interpretMouse();
    }

    private void interpretMouse() {
        mouseHover();

        if (mousePressed) {
            finishedturn = false;
        }
        if (!finishedturn) {
            mousePress();
        }
    }

    private void mousePress() {
        aktiveDame = mouseHover();
        markActive();
        showPossibility();
        jump();
    }

    private int countOpportunitys() {
        int count = 0;

        for (int i = 0; i < opportunitys.length; i++) {
            if(opportunitys[i] != null){
                count++;
            }
        }

        return count;
    }

    private void jump() {
        for (int i = 0; i < countOpportunitys(); i++) {
                if((mouseX > opportunitys[i].getX() * board.getBoxWidth() + board.getLeftMargin()) && (mouseX < (opportunitys[i].getX() + 1) * board.getBoxWidth() + board.getLeftMargin()) && (mouseY > opportunitys[i].getY() * board.getBoxLength() + board.getUpperMargin()) && (mouseY < (opportunitys[i].getY() + 1) * board.getBoxLength() + board.getUpperMargin())){
                    newFigure = opportunitys[i];
                    if(turnWhite){
                        for (int j = 0; j < weisseDamen.length; j++) {
                            if (aktiveDame == weisseDamen[j]) {
                                weisseDamen[j] = newFigure;
                            }
                        }
                    }
                    else{
                        for (int k = 0; k < schwarzeDamen.length; k++) {
                            if (aktiveDame == schwarzeDamen[k]) {
                                schwarzeDamen[k] = newFigure;
                            }
                        }
                    }
                    finishedturn = true;
                    turnWhite = !turnWhite;
                }
        }

    }

    private void markActive() {
        fill(255, 0, 0);
        ellipse(board.getLeftMargin() + (aktiveDame.getX() * board.getBoxLength()) + (board.getBoxLength() / 2), board.getUpperMargin() + (aktiveDame.getY() * board.getBoxWidth()) + (board.getBoxWidth() / 2), board.getBoxWidth() * 0.9f, board.getBoxLength() * 0.9f);

    }

    private void showPossibility() {
        int count = 0;

        if (aktiveDame instanceof Stone) {
            if (turnWhite) {
                fill(0, 255, 0);
                if(!blocked(-1, 1, weisseDamen)){
                    rect(board.getLeftMargin() + ((aktiveDame.getX() - 1) * board.getBoxLength()), board.getUpperMargin() + ((aktiveDame.getY() + 1) * board.getBoxWidth()), board.getBoxWidth(), board.getBoxLength());
                    opportunitys[count++] = new Stone((aktiveDame.getX() - 1), (aktiveDame.getY() + 1), Color.WHITE);
                }
                if(!blocked(1, 1, weisseDamen)) {
                    rect(board.getLeftMargin() + ((aktiveDame.getX() + 1) * board.getBoxLength()), board.getUpperMargin() + ((aktiveDame.getY() + 1) * board.getBoxWidth()), board.getBoxWidth(), board.getBoxLength());
                    opportunitys[count] = new Stone((aktiveDame.getX() + 1), (aktiveDame.getY() + 1), Color.WHITE);
                }
            }
            else{
                fill(0, 255, 0);
                if(!blocked(-1, -1, schwarzeDamen)) {
                    rect(board.getLeftMargin() + ((aktiveDame.getX() - 1) * board.getBoxLength()), board.getUpperMargin() + ((aktiveDame.getY() - 1) * board.getBoxWidth()), board.getBoxWidth(), board.getBoxLength());
                    opportunitys[count++] = new Stone((aktiveDame.getX() - 1), (aktiveDame.getY() - 1), Color.BLACK);
                }
                if(!blocked(1, -1, schwarzeDamen)) {
                    rect(board.getLeftMargin() + ((aktiveDame.getX() + 1) * board.getBoxLength()), board.getUpperMargin() + ((aktiveDame.getY() - 1) * board.getBoxWidth()), board.getBoxWidth(), board.getBoxLength());
                    opportunitys[count] = new Stone((aktiveDame.getX() + 1), (aktiveDame.getY() - 1), Color.BLACK);
                }
            }
        }
    }

    private boolean blocked(int xMore, int yMore, Figure[] figures) {
        boolean blocked = false;

        for (int i = 0; i < figures.length; i++) {
            if((figures[i].getX() == (aktiveDame.getX() + xMore) && (figures[i].getY() == (aktiveDame.getY() + yMore)))){
                blocked = true;
            }
            else if(((aktiveDame.getX() + xMore) < 0) || ((aktiveDame.getY() + yMore) < 0) || ((aktiveDame.getX() + xMore) >= board.getFieldBox().length) || ((aktiveDame.getY() + yMore) >= board.getFieldBox()[0].length)){
                blocked = true;
            }
        }

        return blocked;
    }

    private Figure mouseHover() {
        if(turnWhite){
            for (int i = 0; i < weisseDamen.length; i++) {
                if((mouseX > weisseDamen[i].getX() * board.getBoxWidth() + board.getLeftMargin()) && (mouseX < (weisseDamen[i].getX() + 1) * board.getBoxWidth() + board.getLeftMargin()) && (mouseY > weisseDamen[i].getY() * board.getBoxLength() + board.getUpperMargin()) && (mouseY < (weisseDamen[i].getY() + 1) * board.getBoxLength() + board.getUpperMargin())){
                    aktiveDame = weisseDamen[i];
                    fill(255, 108, 0);
                    ellipse(board.getLeftMargin() + (weisseDamen[i].getX() * board.getBoxLength()) + (board.getBoxLength() / 2), board.getUpperMargin() + (weisseDamen[i].getY() * board.getBoxWidth()) + (board.getBoxWidth() / 2), board.getBoxWidth() * 0.9f, board.getBoxLength() * 0.9f);
                }
            }
        }
        else{
            for (int i = 0; i < schwarzeDamen.length; i++) {
                if((mouseX > schwarzeDamen[i].getX() * board.getBoxWidth() + board.getLeftMargin()) && (mouseX < (schwarzeDamen[i].getX() + 1) * board.getBoxWidth() + board.getLeftMargin()) && (mouseY > schwarzeDamen[i].getY() * board.getBoxLength() + board.getUpperMargin()) && (mouseY < (schwarzeDamen[i].getY() + 1) * board.getBoxLength() + board.getUpperMargin())){
                    fill(255, 108, 0);
                    ellipse(board.getLeftMargin() + (schwarzeDamen[i].getX() * board.getBoxLength()) + (board.getBoxLength() / 2), board.getUpperMargin() + (schwarzeDamen[i].getY() * board.getBoxWidth()) + (board.getBoxWidth() / 2), board.getBoxWidth() * 0.9f, board.getBoxLength() * 0.9f);
                    aktiveDame = schwarzeDamen[i];
                }
            }
        }

        return aktiveDame;
    }

    private void createDamen() {
        int whiteNumber = 0;
        int blackNumber  = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < board.getFieldBox().length; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        weisseDamen[whiteNumber++] = new Stone(j + 1 , i, Color.WHITE);
                    }
                    else{
                        schwarzeDamen[blackNumber++] = new Stone(board.getFieldBox().length - j - 1, board.getFieldBox()[0].length - i - 1, Color.BLACK);
                    }
                }
                else {
                    if (j % 2 == 0) {
                        schwarzeDamen[blackNumber++] = new Stone(board.getFieldBox().length - j - 1, board.getFieldBox()[0].length - i - 1, Color.BLACK);
                    }
                    else{
                        weisseDamen[whiteNumber++] = new Stone(j - 1, i, Color.WHITE);
                    }
                }
            }
        }
    }

    private void drawText() {
        fill(0);
        rect(board.getLeftMargin(), board.getUpperMargin() / 4f,width - (2 * board.getLeftMargin()), board.getUpperMargin() / 2f);

        if (turnWhite) {
            fill(255);
            text("Weiß ist am Zug", board.getLeftMargin() * 3.5f, board.getUpperMargin() / 2f);
        }
        else{
            fill(255);
            text("Schwarz ist am Zug", board.getLeftMargin() * 3.5f, board.getUpperMargin() / 2f);
        }
    }

    private void drawField() {
        board.setBoardLength(height - (2 * board.getUpperMargin()));
        board.setBoardWidth(width - (2 * board.getLeftMargin()));
        board.setBoxLength(board.getBoardLength() / board.getFieldBox()[0].length);
        board.setBoxWidth(board.getBoardWidth() / board.getFieldBox().length);

        fill(138, 137, 136);
        rect(board.getLeftMargin(), board.getUpperMargin(), board.getBoardWidth(), board.getBoardLength());
    }

    private void drawFieldBox() {

        for (int i = 0; i < board.getFieldBox().length; i++) {
            for (int j = 0; j < board.getFieldBox()[i].length; j++) {

                if(((j % 2 == 1) && (i % 2 == 0)) || (j % 2 == 0) && (i % 2 == 1)){
                    fill(74, 46, 7);
                }
                else{
                    fill(212, 103, 20);
                }

                rect(board.getLeftMargin() + j * board.getBoxWidth(), board.getUpperMargin() + i * board.getBoxLength(), board.getBoxWidth(), board.getBoxLength());
            }
        }
    }

    private void drawDamen(Figure[] damen) {
        for (int i = 0; i < damen.length; i++) {
            if (damen[i].getColor() == Color.BLACK) {
                fill(0);
            }
            else{
                fill(255);
            }
            ellipse(board.getLeftMargin() + (damen[i].getX() * board.getBoxLength()) + (board.getBoxLength() / 2), board.getUpperMargin() + (damen[i].getY() * board.getBoxWidth()) + (board.getBoxWidth() / 2), board.getBoxWidth() * 0.9f, board.getBoxLength() * 0.9f);
        }
    }
}