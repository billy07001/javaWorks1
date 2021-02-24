import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	private ArrayList<Point> points = new ArrayList <Point>();
	private ArrayList<Point> eraserpoints = new ArrayList <Point>();
	int tool;
	int brushsize = 4;
	boolean Fill;
	boolean clear = false;
	int x1=0,y1=0,x2=0,y2=0;
	private int drag=0;
	BufferedImage image;
	Color brushcolor =Color.BLACK;
	   
   public void setTool(int Tool) {
	   this.tool=Tool;
	   x1=x2=y1=y2=0;
   }
   public int gettool() {
	   return tool;
   }
   
   public void setSize(int Size) {
	   this.brushsize=Size;
   }
   public int getsize() {
	   return brushsize;
   }
	
   public void setFill(boolean Fill) {
	   this.Fill=Fill;
   }
   
   public void setClear(boolean Clear) {
	   this.clear=Clear;
   }
	
	
	
	public DrawPanel() {
		image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
		MouseHandler handler = new MouseHandler();
		addMouseMotionListener(handler); 
		addMouseListener(handler);
	}
	public void Clear() {
    	x1=x2=y1=y2=0;
    	points.clear();
    	image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
    	repaint();
	}

private class MouseHandler implements MouseListener, MouseMotionListener{ 	
	public void mouseDragged(MouseEvent event) {
		if(tool == 0) {
		    points.add(event.getPoint());
		    repaint();
		}
		if(tool == 2) {
			x2=event.getX();
			y2=event.getY();
			drag=1;
			repaint();
		}
		if(tool == 3) {
			x2=event.getX();
			y2=event.getY();
			drag=1;
			repaint();
		}
		if(tool == 4) {
			x2=event.getX();
			y2=event.getY();
			drag=1;
			repaint();
		}
		if (tool == 5) {
			eraserpoints.add(event.getPoint());
			repaint();
		}
		}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1=e.getX();
		y1=e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	   x2=e.getX();
	   y2=e.getY();
	   drag=0;
	   repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics gg = image.createGraphics();
	Graphics2D g2d = ( Graphics2D ) gg;
	g2d.setPaint(brushcolor);
	g2d.setStroke(new BasicStroke(brushsize, BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
    switch(tool) {
     case 0://筆刷
    	 g2d.setPaint(brushcolor);
         for (Point point : points)
             g2d.draw(new Ellipse2D.Double(point.getX(),point.getY(),brushsize,brushsize));
         break;
     case 1://直線
    	 if (Fill == true)
    	 g2d.draw(new Line2D.Double(x1,y1,x2,y2));
    	 if (Fill == false) {
    		 float []f={10f,10f};
    		 g2d.setStroke(new BasicStroke(brushsize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0F,f,0f));
    		 g2d.draw(new Line2D.Double(x1,y1,x2,y2));
    	 }
    	 
    	 break;
     case 2://橢圓形
		  if(drag == 1)
		  g.drawOval(Math.min(x1,x2), Math.min(y1,y2),Math.abs(x1-x2), Math.abs(y1-y2));
		 if(drag == 0) {
    	 if (Fill == true)
    	 g2d.fill(new Ellipse2D.Double(Math.min(x1, x2),Math.min(y1, y2),Math.abs(x2-x1),Math.abs(y2-y1)));
    	 if (Fill == false)
    	 g2d.draw(new Ellipse2D.Double(Math.min(x1, x2),Math.min(y1, y2),Math.abs(x2-x1),Math.abs(y2-y1)));
		 }
    	 break;
         
    case 3://矩形
    	if(drag == 1)
    		g.drawRect(Math.min(x1,x2), Math.min(y1,y2),Math.abs(x1-x2), Math.abs(y1-y2));
		 if(drag == 0) {
	    	 if (Fill == true)
	    	 g2d.fill(new Rectangle2D.Double(Math.min(x1, x2),Math.min(y1, y2),Math.abs(x2-x1),Math.abs(y2-y1)));
	    	 if (Fill == false)
	    	 g2d.draw(new Rectangle2D.Double(Math.min(x1, x2),Math.min(y1, y2),Math.abs(x2-x1),Math.abs(y2-y1)));
			 }
	    	 break;
    case 4://圓角矩形
    	if(drag == 1)
    		g.drawRoundRect(Math.min(x1,x2), Math.min(y1,y2),Math.abs(x1-x2), Math.abs(y1-y2),18,15);
		 if(drag == 0) {
	    	 if (Fill == true)
	    	 g2d.fill(new RoundRectangle2D.Double(Math.min(x1, x2),Math.min(y1, y2),Math.abs(x2-x1),Math.abs(y2-y1), 18, 15));
	    	 if (Fill == false)
	    	 g2d.draw(new RoundRectangle2D.Double(Math.min(x1, x2),Math.min(y1, y2),Math.abs(x2-x1),Math.abs(y2-y1), 18, 15));
			 }
	    	 break;
    case 5://橡皮擦
    	for (Point eraserpoint : eraserpoints) {
    		g2d.setPaint(Color.WHITE);
    		g2d.draw(new Ellipse2D.Double(eraserpoint.getX(),eraserpoint.getY(),brushsize,brushsize));
    	}
    	break;
	}
    g.drawImage(image, 0, 0, this);
}
}

//目前bug => 1.筆刷顏色按下橡皮擦之後就回不來了
//           2.筆刷大小只能從大變小 不能從小變大 => 目前想到的解法 大中小的筆刷都做成一個工具 用不同的Arraylist 存
