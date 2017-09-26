import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class GridDisplay extends JPanel {
	private Grid grid; 
	public boolean nextStepReady = false; 
	
	public GridDisplay(Grid g) {
		super(); 
		grid = g; 
		Dimension size = new Dimension(500, 500);
		setMinimumSize(size);
		setPreferredSize(size);
		repaint(); 
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		
		
		final int radius = 5;
		final int gridDist = 20; 
		
		g2.translate(250, 250);
		g2.scale(1,-1);
		for(Node n : grid.getGrid().values()) {
			g2.setPaint(Color.BLACK);
			if(n.e_neigh != null) {
				g2.drawLine(n.c.x * gridDist, n.c.y * gridDist, n.e_neigh.c.x * gridDist, n.e_neigh.c.y * gridDist);
			}
			if(n.w_neigh != null) {
				g2.drawLine(n.c.x * gridDist, n.c.y * gridDist, n.w_neigh.c.x * gridDist, n.w_neigh.c.y * gridDist);
			}
			if(n.n_neigh != null) {
				g2.drawLine(n.c.x * gridDist, n.c.y * gridDist, n.n_neigh.c.x * gridDist, n.n_neigh.c.y * gridDist);
			}
			if(n.s_neigh != null) {
				g2.drawLine(n.c.x * gridDist, n.c.y * gridDist, n.s_neigh.c.x * gridDist, n.s_neigh.c.y * gridDist);
			}
		}
		for(Node n : grid.getGrid().values()) {
			if(n.isGoal) {
				g2.setPaint(Color.GREEN);
			} else if(n.isActive){
				g2.setPaint(Color.YELLOW);
			} else if(n.isExpanded) {
				g2.setPaint(Color.RED);
			} else {
				g2.setPaint(Color.BLUE);
			}
			g2.fillOval((n.c.x * gridDist) - radius, (n.c.y * gridDist) - radius, radius * 2, radius*2);			
		}
		nextStepReady = true; 
	}
	
	
	public void step() {
		nextStepReady = false; 
		repaint(); 
	}
}
