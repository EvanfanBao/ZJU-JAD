import java.awt.*;

/* MyRecLikeShape.java */
/* Description: rectangle-like shape */
abstract public class MyRecLikeShape extends MyShape {
    private static final long serialVersionUID = 1L;
    
    protected int x;
    protected int y;
    protected int width = 1;
    protected int height = 1;

    public MyRecLikeShape(int x, int y, Color color, ShapeCate theshape) {
        super(theshape, color);
        this.x  = x;
        this.y = y;
    }
    
    public MyRecLikeShape(int x, int y, int width, int height, Color color, int stroke, ShapeCate theshape) {
        super(theshape, color, stroke);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    // gets & sets
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    @Override
    public void drawShape(Graphics2D g) {
        if(theShape == ShapeCate.ELLIPSE)
            g.drawOval(x, y, width, height);
        else if(theShape == ShapeCate.RECT)
            g.drawRect(x, y, width, height);
    }
    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    @Override
    public boolean sizeUp() {
        width = (int) Math.ceil(width * INCREASE) ;
		height = (int) Math.ceil(height * INCREASE);
		return true;
    }
    @Override
    public boolean sizeDown() {
        int newWidth = (int) (width * DECREASE);
		int newHeight = (int) (height * DECREASE);
		if (newWidth > 0 && newHeight > 0) {
			width = newWidth;
			height = newHeight;
			return true;
		}
		return false;
    }
    public void shapeDragged(int x1, int y1, int x2, int y2) {
        x = Math.min(x1, x2);
        y = Math.min(y1, y2);
        width = Math.abs(x1 - x2);
        height = Math.abs(y1 - y2);
    }
    abstract public boolean contains(int x, int y);

}
