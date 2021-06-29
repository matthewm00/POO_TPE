package paint.backend.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ellipse extends Figure{

    private Point centerPoint, bottomRight, topLeft;

    public Ellipse(Point cP, Point bottomRight, Point topLeft) {
        this.centerPoint = cP;
        this.bottomRight = bottomRight;
        this.topLeft = topLeft;
    }

    @Override
    public String source() {
        return String.format("Elipse [Centro: %s]", centerPoint);
    }

    @Override
    public List<Point> getPoints() {
        return Arrays.asList(centerPoint, bottomRight, topLeft);
    }

}
