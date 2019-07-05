import game.GameBoard;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameBoardTest {

    @Test
    public void playerShouldLookNorthByDefault() {
        assertThat(new GameBoard().playerMoves(new String[]{})).isEqualTo("0 0 N");
    }

    @Test
    public void gameBoardShouldDoNothingGivenInvalidInput() {
        assertThat(new GameBoard().playerMoves(new String[]{"T", "W", "Q"})).isEqualTo("0 0 N");
    }

    @Test
    public void playerShouldLookEast() {
        assertThat(new GameBoard().playerMoves(new String[]{"R"})).isEqualTo("0 0 E");
    }

    @Test
    public void playerShouldLookWest() {
        assertThat(new GameBoard().playerMoves(new String[]{"R", "R", "R"})).isEqualTo("0 0 W");
    }

    @Test
    public void playerShouldTurn() {
        assertThat(new GameBoard().playerMoves(new String[]{"R", "R", "R", "R"})).isEqualTo("0 0 N");
    }

    @Test
    public void playerShouldLookSouth() {
        assertThat(new GameBoard().playerMoves(new String[]{"R", "R"})).isEqualTo("0 0 S");
    }

    @Test
    public void playerShouldChainDifferentOrientationMoves() {
        assertThat(new GameBoard().playerMoves(new String[]{"R", "M", "M", "M", "L", "M", "M"})).isEqualTo("3 2 N");
    }

    @Test
    public void playerShouldNotWalkBeyondNorth() {
        assertThat(new GameBoard().playerMoves(new String[]{"M", "M", "M", "M", "M", "M", "M", "M"})).isEqualTo("0 4 N");
    }

    @Test
    public void playerShouldNotWalkBeyondEast() {
        assertThat(new GameBoard().playerMoves(new String[]{"R", "M", "M", "M", "M", "M", "M", "M", "M"})).isEqualTo("4 0 E");
    }

    @Test
    public void playerShouldNotWalkBeyondWest() {
        assertThat(new GameBoard().playerMoves(new String[]{"R", "R", "R", "M", "M", "M", "M", "M", "M", "M", "M"})).isEqualTo("0 0 W");
    }

    @Test
    public void playerShouldNotWalkBeyondSouth() {
        assertThat(new GameBoard().playerMoves(new String[]{"R", "R", "M", "M", "M", "M", "M", "M", "M", "M"})).isEqualTo("0 0 S");
    }
}
