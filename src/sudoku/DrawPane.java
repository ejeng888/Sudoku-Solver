package sudoku;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//run this file for display to show up
public class DrawPane extends JPanel{
	public static JFrame frame = new JFrame("Sudoku");
	

	public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
 
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(0, 300, 900, 300);
        g2d.drawLine(0, 600, 900, 600);
        g2d.drawLine(300, 0, 300, 900);
        g2d.drawLine(600, 0, 600, 900);
        g2d.setStroke(new BasicStroke(0));
        
        int x1=0,y1=0,x2=900,y2=0;
        for(int i = 0; i < 9; ++i) {
        	g2d.drawLine(x1,y1 += 100,x2,y2 += 100);
        }
        x1=0;y1=0;x2=0;y2=900;
        for(int j = 0; j < 9; ++j) {
        	g2d.drawLine(x1 += 100, y1, x2 += 100, y2);
        }
        drawStartingNumber(g);
	}
	public void drawStartingNumber(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        int numberx = 50, numbery = 75;
        
        for(int i = 0; i < 9; i++) {
        	for(int j = 0; j < 9; j++) {
        		if(Sudoku.grid[i][j] != 0) {
        			String drawNumber = Integer.toString(Sudoku.grid[i][j]);
        			g.drawString(drawNumber, numberx, numbery);
        		}
        		numberx += 100;
        		if(numberx >= 900) {
            		numberx = 50;
            		numbery += 100;
            	}
            	
        	}
        }
        
	}
	private static void init() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1000, 1050));
		frame.setLocation(0,0);
		frame.pack();
		frame.setResizable(true);
		frame.add(new DrawPane());
		frame.setVisible(true);
		frame.getContentPane();
		Jbutton();
		frame.revalidate();
	}
	private static void Jbutton() {
		JLabel label = new JLabel("Some info");
		label.revalidate();
		JButton b = new JButton("Solve");
		b.setPreferredSize(new Dimension(40,100));
		frame.add(b, BorderLayout.SOUTH);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean x = Sudoku.backtrack(Sudoku.grid, Sudoku.grid.length);
				if(x == true) {
					frame.repaint();
					b.setText("Solved");
				}
				else {
					b.setText("No Answer");
				}
			}
			
		});
	}
	public static void main(String[] args) {
		init();
	}
}