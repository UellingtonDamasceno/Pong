package game.pong.util;

import game.pong.model.Pong;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import penguine.game.base.Measurable;

/**
 *
 * @author Uellington Damasceno
 */
public class PointsUtils {

    public static Point2D[] getReferencePoints(Measurable mesurable) {
        var referencePoints = new Point2D[9];

        double x = mesurable.getMinX();
        double y = mesurable.getMinY();
        double width = mesurable.getX();
        double height = mesurable.getY();

        referencePoints[2] = new Point2D((x + width), y);//9
        referencePoints[5] = new Point2D((x + width), y + (height / 2));//6
        referencePoints[8] = new Point2D((x + width), (y + height));//3

        referencePoints[0] = new Point2D(x, y); //7
        referencePoints[3] = new Point2D(x, y + (height / 2));//4
        referencePoints[6] = new Point2D(x, (y + height));//1

        referencePoints[1] = new Point2D(x + (width / 2), y);//8
        referencePoints[4] = new Point2D(x + (width / 2), y + (height / 2));//5
        referencePoints[7] = new Point2D(x + (width / 2), (y + height));//2 
        return referencePoints;
    }

    public static void showReferencePoints(Measurable measurable, GraphicsContext graphic) {
        PointsUtils.showReferencePoints(measurable, graphic, Color.RED);
    }

    public static void showReferencePoints(Measurable measurable, GraphicsContext graphic, Paint color) {
        var referencePoints = PointsUtils.getReferencePoints(measurable);
        graphic.save();
        graphic.setFill(color);
        for (var referencesPoint : referencePoints) {
            graphic.fillOval(referencesPoint.getX() - 2.5, referencesPoint.getY() - 2.5, 5, 5);
        }
        graphic.restore();
    }

    public static Point2D alignCenter(Measurable object, double size) {
        return PointsUtils.alignCenter(object, size, size);
    }

    public static Point2D alignCenter(Measurable object, double xSize, double ySize) {
        double x = (object.getX() / 2) - (xSize / 2);
        double y = (object.getY() / 2) - (ySize / 2);
        return new Point2D(x, y);
    }
}
