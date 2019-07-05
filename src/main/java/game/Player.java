package game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Player {
    private final int xPos;
    private final int yPos;
    private final Orientation orientation;

    String getPositionAndOrientation() {
        return String.valueOf(xPos).concat(" " + yPos).concat(" " + Orientation.print(orientation));
    }

    Player orientate(final String move) {
        Orientation newOrientation = getNewOrientation(move, this.getOrientation());
        return new Player(this.xPos, this.yPos, newOrientation);
    }

    private Orientation getNewOrientation(final String move, final Orientation orientation) {
        int pre = orientation.getGrades();
        if ("R".equalsIgnoreCase(move)) {
            return Orientation.fromGrades(pre + 90);
        } else if ("L".equalsIgnoreCase(move)) {
            return Orientation.fromGrades(pre - 90);
        }
        return orientation;
    }

    Player move() {
        switch(orientation) {
            case NORTH:
                return new Player(this.xPos, this.yPos + 1, this.orientation);
            case EAST:
                return new Player(this.xPos + 1, this.yPos, this.orientation);
            case SOUTH:
                return new Player(this.xPos, this.yPos - 1, this.orientation);
            case WEST:
                return new Player(this.xPos - 1, this.yPos, this.orientation);
            default:
                return new Player(this.xPos, this.yPos, this.orientation);
        }
    }
}