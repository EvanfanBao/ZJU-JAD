import java.awt.*;
import java.awt.geom.Line2D;

/* MyLine.java */
/* Description: the line */
public class MyLine extends MyShape  {
    private static final long serialVersionUID = 1L;
    // Line point position
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private Line2D innerLine;
    public MyLine(int x, int y, Color color) {
        super(ShapeCate.LINE, color);
        innerLine = new Line2D.Double(x, y, x, y);
        x1 = x2 = x;
        y1 = y2 = y;
    }
    public MyLine(int x1, int y1, int x2, int y2, Color color, int stroke) {
        super(ShapeCate.LINE, color, stroke);
        innerLine = new Line2D.Double(x1, y1, x2, y2);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    //sets and gets
    @Override
    public void drawShape(Graphics2D g) {
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }

    @Override
    public void move(int dx, int dy) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
    }

    // sizeUp. Not so precise
    @Override
    public boolean sizeUp() {
        
        double centerX = (x1 + x2)/2;
        double centerY = (y1 + y2)/2;
        double disX = Math.abs(centerX - x1);        
        double disY = Math.abs(centerY - y1);

        disX *= INCREASE;
        disY *= INCREASE;
        if(x1 < centerX) {
            x1 = centerX - disX;
            x2 = centerX + disX;
        }
        else { 
            x1 = centerX + disX;
            x2 = centerX - disX;
        }
        if(y1 < centerY) { 
            y1 = centerY - disY;
            y2 = centerY + disY;
        }
        else {
            y2 = centerY - disY;
            y1 = centerY + disY;
        }

        if(x1 > 0 && x2 > 0 && y1 > 0 && y2 > 0) {
            return true;
        } else return false;
    }
    //sizeDown. Not so precise
    public boolean sizeDown() {

        double centerX = (x1 + x2)/2;
        double centerY = (y1 + y2)/2;
        double disX = Math.abs(centerX - x1);        
        double disY = Math.abs(centerY - y1);

        disX *= DECREASE;
        disY *= DECREASE;
        if(x1 < centerX) {
            x1 = centerX - disX;
            x2 = centerX + disX;
        }
        else { 
            x1 = centerX + disX;
            x2 = centerX - disX;
        }
        if(y1 < centerY) { 
            y1 = centerY - disY;
            y2 = centerY + disY;
        }
        else {
            y2 = centerY - disY;
            y1 = centerY + disY;
        }
       return true;
    }

    // Draw line by Dragging
    public void shapeDragged(int x1, int y1, int x2, int y2) {
        this.x1 = x1;this.x2 = x2;this.y1 = y1;this.y2 = y2;
    }

    // Check if a point is near the line
    public boolean nearLine(int x, int y) {
        innerLine.setLine(x1, y1, x2, y2);
        return innerLine.ptLineDist(x ,y) < 5;
       // 距离足够小就返回 
    }
    public boolean contains(int x, int y){
        return false;//no use now
    }
}
