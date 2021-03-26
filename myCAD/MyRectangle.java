import java.awt.*;
import java.awt.geom.Rectangle2D;


/* MyRectangle.java */
/* Description: the rectangle */
public class MyRectangle extends MyRecLikeShape   {
    private static final long serialVersionUID = 1L;
    private Rectangle2D innerRectangle;     // Inner Rectangle2D member
    public MyRectangle(int x, int y, Color color) {
        super(x, y, color, ShapeCate.RECT);
        innerRectangle = new Rectangle2D.Double(x, y, 1, 1);
    }
    public MyRectangle(int x, int y, int width, int height, Color color, int stroke) {
        super(x, y, width, height, color, stroke, ShapeCate.RECT);
        innerRectangle = new Rectangle2D.Double(x, y, width, height);
    }
    public boolean contains(int x, int y) {
        innerRectangle.setFrame(this.x, this.y, this.width, this.height);
        return innerRectangle.contains(x, y);
    }
}
