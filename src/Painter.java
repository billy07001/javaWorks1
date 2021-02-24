import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;

public class Painter extends JFrame {
	   private JPanel box;
	   private JPanel PainterJPanel;
	   private JPanel ToolJPanel;
	   private JPanel BrushJPanel;
	   private JPanel BrushJPanel1;
	   private JPanel BrushJPanel2;
	   private JPanel FillJPanel;
	   private JPanel FillJPanel1;
	   private JPanel FillJPanel2;
	   private JPanel ButtonJPanel; 
	   private JPanel ButtonJPanel1;
	   private JPanel ButtonJPanel2;
	   private JPanel ButtonJPanel3;
	   private JButton button1;
	   private JButton button2;
	   private JButton button3;
	   private DrawPanel draw;
	   private JComboBox<String> ToolJComboBox;
	   private String[] names = 
	   {" ���� "," ���u "," ���� "," �x�� "," �ꨤ�x�� "};
	   private JRadioButton s;//used to Radio Button
	   private JRadioButton m;
	   private JRadioButton l;
	   private ButtonGroup radioGroup;
	   private JCheckBox fillJCheckBox;//used to checkbox
	   private JLabel statusBar;
	   private String info;
   public Painter() {
	      super("�p�e�a");
	      DrawPanel draw = new DrawPanel();
	      JPanel ToolJPanel = new JPanel();
	      ToolJPanel.setLayout(new GridLayout(2,1));
	      
	      ToolJComboBox = new JComboBox<String>(names);//select tool
	      ToolJComboBox.setMaximumRowCount(5);
	      
	      ToolJPanel.add(new JLabel("ø�Ϥu��"));
	      ToolJPanel.add(ToolJComboBox);
	      ToolJComboBox.addItemListener(
	    		  new ItemListener(){
	    			  public void itemStateChanged(ItemEvent event) {
	    				  if (event.getStateChange() == ItemEvent.SELECTED) {
	    					  draw.setTool(ToolJComboBox.getSelectedIndex());
	    				  }
	    			  }
	    		  }
	    		  
	    		  );
	      BrushJPanel = new JPanel();
	      BrushJPanel1 = new JPanel();
	      BrushJPanel2 = new JPanel();
	      BrushJPanel.setLayout(new GridLayout(2,1));
	  
	      s = new JRadioButton("�p", false);//select brush size
		  m = new JRadioButton("��", false);
		  l = new JRadioButton("�j", false);
	      s.addItemListener(e -> {draw.setSize(3);});
	      m.addItemListener(e -> {draw.setSize(6);});
	      l.addItemListener(e -> {draw.setSize(10);});
		  ButtonGroup radioGroup = new ButtonGroup();
		  radioGroup.add(s);
		  radioGroup.add(m);
		  radioGroup.add(l);
		  
		  BrushJPanel1.add(new JLabel("����j�p"));
		  BrushJPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		  BrushJPanel2.add(s);
		  BrushJPanel2.add(m);
		  BrushJPanel2.add(l);
		  BrushJPanel2.setLayout(new GridLayout(1,3));
		  BrushJPanel.add(BrushJPanel1,BorderLayout.NORTH);
		  BrushJPanel.add(BrushJPanel2,BorderLayout.SOUTH);
		  
		  fillJCheckBox = new JCheckBox();//check to fill
		  FillJPanel = new JPanel();
		  FillJPanel.setLayout(new GridLayout(2,1));
		  FillJPanel1 = new JPanel();
		  FillJPanel2 = new JPanel();
		  FillJPanel1.add(new JLabel("��"));
		  FillJPanel2.add(fillJCheckBox);
		  FillJPanel.add(FillJPanel1,BorderLayout.NORTH);
		  FillJPanel.add(FillJPanel2,BorderLayout.SOUTH);
		  fillJCheckBox.addItemListener(e -> {if (fillJCheckBox.isSelected()) {draw.setFill(true);} else {draw.setFill(false);}});
		  
		  button1 = new JButton("�����C��");
		  button2 = new JButton("�M���e��");
		  button3 = new JButton("�����");
		  ButtonJPanel = new JPanel();
		  ButtonJPanel1 = new JPanel();
		  ButtonJPanel2 = new JPanel();
		  ButtonJPanel3 = new JPanel();
		  ButtonJPanel1.add(button1);
		  ButtonJPanel2.add(button2);
		  ButtonJPanel3.add(button3);
		  ButtonJPanel.add(ButtonJPanel1);
		  ButtonJPanel.add(ButtonJPanel2);
		  ButtonJPanel.add(ButtonJPanel3);
		 
		  button1.addActionListener(e -> {draw.brushcolor=JColorChooser.showDialog(Painter.this,"Choose a color", draw.brushcolor);});
	      button2.addActionListener(e -> {draw.Clear();});
	      button3.addActionListener(e ->{draw.setTool(5);});
		  		  
		  box = new JPanel();//put all panel in BOX panel
		  box.setLayout(new GridLayout(1,5));
		  box.add(ToolJPanel);
		  box.add(BrushJPanel);
		  box.add(FillJPanel);
		  box.add(ButtonJPanel);
		  add(box,BorderLayout.NORTH);//ToolBox
		   
		  add(draw,BorderLayout.CENTER);
		  draw.setBackground(Color.WHITE);
           statusBar = new JLabel("Mouse outside JPanel");
           draw.addMouseMotionListener(
            		   new MouseMotionAdapter() {
            				public void mouseMoved(MouseEvent event) {
            					int xPos = event.getX();
            			        int yPos = event.getY();
            			        info = String.format("Moved %d,%d",xPos,yPos);
            			        statusBar.setText(info);
            		   }
            				public void mouseDragged(MouseEvent event) {
            					int xPos = event.getX();
            			        int yPos = event.getY();
            			        info = String.format("Dragged %d,%d",xPos,yPos);
            			        statusBar.setText(info);
            				}
            		   }
            		   );
           
           add(statusBar, BorderLayout.SOUTH);
   }
}


