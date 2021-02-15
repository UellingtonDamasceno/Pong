package game.pong.util;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import penguine.game.base.Measurable;

/**
 *
 * @author Uellington Damasceno
 */
public class PointUtils {

    
    public static Point2D[] getReferencePoints(Measurable mesurable) {
        var referencePoints = new Point2D[9];
   
        double x = mesurable.getX();
        double y = mesurable.getY();
        double width = mesurable.getWidth();
        double height = mesurable.getHeight();

        referencePoints[2] = new Point2D((x + width), y);//9sw
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
    
    public static Point2D getReferencePointByIndex(Measurable mesurable, int index){
        index = (index < 0 ) ? 0 : (index > 9) ? 9 : index;
        return PointUtils.getReferencePoints(mesurable)[index];
    }

    public static void showReferencePoints(Measurable measurable, GraphicsContext graphic) {
        PointUtils.showReferencePoints(measurable, graphic, Color.RED);
    }

    public static void showReferencePoints(Measurable measurable, GraphicsContext graphic, Paint color) {
        var referencePoints = PointUtils.getReferencePoints(measurable);
        graphic.save();
        graphic.setFill(color);
        for (var referencesPoint : referencePoints) {
            graphic.fillOval(referencesPoint.getX() - 2.5, referencesPoint.getY() - 2.5, 5, 5);
        }
        graphic.restore();
    }

    public static Point2D alignCenter(Measurable object, double size) {
        return PointUtils.alignCenter(object, size, size);
    }

    public static Point2D alignCenter(Measurable object, double width, double height) {
        double x = (object.getWidth() / 2) - (width / 2);
        double y = (object.getHeight()/ 2) - (height / 2);
        return new Point2D(x, y);
    }
}
