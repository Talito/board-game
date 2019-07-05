package game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum Orientation {
    NORTH(0), EAST(90), SOUTH(180), WEST(270);
    private final int grades;

    public static Orientation fromGrades(final int grades) {
        final int updatedGrades = (grades > 270? 0 : grades);
        return Stream.of(Orientation.values()).filter(x -> x.getGrades() == updatedGrades).findFirst().orElse(Orientation.NORTH);
    }

    public static String print(final Orientation Orientation) {
        switch(Orientation) {
            case NORTH:
                return "N";
            case EAST:
                return "E";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            default:
                return "";
        }
    }
}