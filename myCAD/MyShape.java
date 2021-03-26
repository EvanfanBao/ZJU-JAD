import java.io.Serializable;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

/* MyShape.java */
/* Description: the shape abstarct clas */
enum ShapeCate {
    UNDEFINED, LINE, ELLIPSE, RECT, TEXT
}
public abstract class MyShape implements Serializable {
    private static final long serialVersionUID = 1L;
    protected static final double INCREASE = 1.05;
    protected static final double DECREASE = 0.95;
    private static final double STROKEMAX = 20;
    protected ShapeCate theShape = ShapeCate.UNDEFINED;
    private Color color = Color.BLACK;  //不设置颜色则默认为黑色
    private int stroke = 2;  // default stroke
    
    // get the shape category
    public ShapeCate getShapeCato() {
        return theShape;
    }
    // get the shape color
    public Color getColor() {
        return color;
    }
    // set the shape color
    public void setColor(Color color) {
        this.color = color;
    }
    // get teh shape stroke
    public int getStroke() {
        return stroke;
    }
    // increase the stroke
    public boolean strokeUp() {
        if (stroke < STROKEMAX * 2) {
            stroke++;
            return true;
        }
        return false;
    }
    // decrease the stroke
    public boolean strokeDown() {
        if(stroke > 1) {
            stroke--;
            return true;
        }
        return false;
    }
    // set color and stroke and draw
    public void Draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke)); 
        drawShape(g);

    }
    abstract public void drawShape(Graphics2D g);   // draw the shape   
    abstract public void move(int dx, int dy);      // move the shape
    abstract public boolean sizeUp();               // increase the shape size
    abstract public boolean sizeDown();             // decrease the shape size
    abstract public void shapeDragged(int x1, int y1, int x2, int y2); // draw shape by dragging
    public MyShape(ShapeCate category, Color color) {
        this.theShape = category;
        this.color = color;
    }
    public MyShape(ShapeCate category, Color color, int stroke) {
        this.theShape = category;
        this.color = color;
        this.stroke = stroke;
    }
    abstract boolean contains(int x, int y);        // test if the point is within the shape
}