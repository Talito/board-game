package game;

public class GameBoard {

    private int[][] boardPositions = generateMap();
    private Player singlePlayer;
    private static final String MOVE_COMMAND = "M";
    private static final String[] ORIENTATION_COMMANDS = new String[]{"L", "R"};

    public GameBoard() {
        initPlayer();
    }

    public String playerMoves(final String[] moves) {
        return processMoves(moves);
    }

    private void initPlayer() {
        this.singlePlayer = new Player(0, 0, Orientation.NORTH);
        boardPositions[this.singlePlayer.getXPos()][this.singlePlayer.getYPos()] = 1;
    }

    private String processMoves(final String[] moves) {
        for (String move: moves) {
            orientatePlayer(move);
            walkPlayer(move);
        }
        return singlePlayer.getPositionAndOrientation();
    }

    private void orientatePlayer(final String move) {
        if (isIn(ORIENTATION_COMMANDS, move)) {
            this.singlePlayer = singlePlayer.orientate(move);
        }
    }

    private void walkPlayer(final String move) {
        if (MOVE_COMMAND.equalsIgnoreCase(move) && moveIsAllowed(singlePlayer)) {
            final int[][] previousCoords = new int[][]{{this.singlePlayer.getXPos()}, {this.singlePlayer.getYPos()}};
            this.singlePlayer = singlePlayer.move();
            final int[][] currentCords = new int[][]{{this.singlePlayer.getXPos()}, {this.singlePlayer.getYPos()}};
            updateBoard(previousCoords, currentCords);
        }
    }

    private boolean moveIsAllowed(final Player singlePlayer) {
        switch(singlePlayer.getOrientation()) {
            case NORTH:
                return singlePlayer.getYPos() + 1 < boardPositions[singlePlayer.getYPos()].length;
            case EAST:
                return singlePlayer.getXPos() + 1 < boardPositions[singlePlayer.getXPos()].length;
            case SOUTH:
                return singlePlayer.getYPos() - 1 >= 0;
            case WEST:
                return singlePlayer.getXPos() - 1 >= 0;
            default:
                return false;
        }
    }

    private void updateBoard(final int[][] previousCoords, final int[][] currentCoords) {
        boardPositions[previousCoords[0][0]][previousCoords[1][0]] = 0;
        boardPositions[currentCoords[1][0]][currentCoords[1][0]] = 1;
    }

    private static int[][] generateMap() {
        return new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
    }

    private boolean isIn(final String[] orientationCommands, final String move) {
        for (String command: orientationCommands) {
            if (command.equalsIgnoreCase(move)) {
                return true;
            }
        }
        return false;
    }
}
