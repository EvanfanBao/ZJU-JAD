import java.awt.*;
import java.awt.geom.Ellipse2D;

/* MyEllipse.java */
/* Description: the Ellipse object having inner Ellipse2D member */
public class MyEllipse extends MyRecLikeShape  {
    private static final long serialVersionUID = 1L;
    private Ellipse2D innerEllipse;     // Inner Ellipse. it's contains method is used
    public MyEllipse(int x, int y, Color color) {
        super(x, y, color, ShapeCate.ELLIPSE);
        innerEllipse = new Ellipse2D.Double(x, y, 1, 1);
    }
    public MyEllipse(int x, int y, int width, int height, Color color, int stroke) {
        super(x, y, width, height, color, stroke, ShapeCate.ELLIPSE);
        innerEllipse = new Ellipse2D.Double(x, y, width, height);
    }
    public boolean contains(int x, int y) {
        innerEllipse.setFrame(this.x, this.y, this.width, this.height);
        return innerEllipse.contains(x, y);
    }
}
