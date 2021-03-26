import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import java.awt.*;
import java.awt.Color;

/* MyText.java */
/* Description: the text */
public class MyText extends MyShape {
    private static final long serialVersionUID = 1L;
    private int x;
    private int y;
    private String text;
    private int size = 20;
    private double width;
    private double height;
    private Rectangle2D innerRectangle;     // Inner rectangle for use
    

    public MyText(String text, int x, int y, Color color) {
        super(ShapeCate.TEXT, color);
        this.x = x;
        this.y = y;
        this.text = text;
    }
    public MyText(String text, int x, int y, int size, Color color) {
        super(ShapeCate.TEXT, color, 0);
        this.x = x;
        this.y = y;
        this.text = text;
        this.size = size;
    }

    // gets & sets
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getText() {
        return text;
    }
    public int getSize() {
        return size;
    }
    @Override
    public void drawShape(Graphics2D g) {
        Font font = new Font(null, Font.BOLD, size);
        g.setFont(font);
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D stringBounds = font.getStringBounds(text, context);
		height = stringBounds.getHeight() / 2;
		width = stringBounds.getWidth();
		g.drawString(text, x, y + (int) height);        
    }
    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    @Override
    public boolean sizeUp() {
        size = (int) Math.ceil(size * INCREASE); ///适当修改下
        return true;
    }
    @Override
    public boolean sizeDown() {
        int newSize = (int) (size * DECREASE);
		if (newSize > 0) {
			size = newSize;
			return true;
		}
		return false;
    }
    public void shapeDragged(int x1, int y1, int x2, int y2) {
        
    }

    public boolean contains(int x, int y) {
        innerRectangle = new Rectangle2D.Double(this.x, this.y, width, height);
        return innerRectangle.contains(x, y);
    }
   
   
    
}
