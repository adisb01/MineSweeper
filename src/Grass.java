import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
/**
 * A Grass Tile can be revealed safely and upon its reveal, 
 * displays the number of adjacent Mines. The goal of the game
 * is to reveal all the Grass Tiles. 
 * @author adibhati
 */
public class Grass extends Tile{
		
		Image revealImg; 
		private final String DIRT_FILE = "pics/revealed.png";
		
		public Grass(int tileDim, int adj, int x, int y) {
			super(tileDim, adj, x, y);
			try {
	            if (revealImg == null) {
	                revealImg = ImageIO.read(new File(DIRT_FILE));
	            }
	        } catch (IOException e) {
	            System.out.println("Internal Error:" + e.getMessage());
	        }
		}

		@Override
		public boolean isMine() {
			return false;
		}

		@Override
		public void paintRevealed(Graphics g) {
			//draw grass
			g.drawImage(revealImg, x, y, tileDim, tileDim, null); 
			
			//write number of adjacent mines
			int fontSize = 40;
			setNumColor(g); 
			g.setFont(new Font("Arial", Font.BOLD, fontSize));
			String s = Integer.toString(adj); 
			int margin = (tileDim)/2; 
			if(adj != 0) {
				g.drawString(s, x + (margin*5)/9 , y + (margin*3)/2);

			}
		}
		
		/**
		 * Sets a specific color for the Grass Tile number depending on the number
		 * of adjacent mines. 
		 * @param g Graphics context of the Tile
		 */
		private void setNumColor(Graphics g) {
			switch(adj) {
			case 0: 
			case 1: 
				g.setColor(Color.white);
				break; 
	        case 2: 
	        	g.setColor(Color.green);
	        	break; 
	        case 3: 
	        	g.setColor(Color.pink);
	        	break;
	        case 4: 
	        	g.setColor(Color.yellow);
	        	break;
	        case 5: 
	        	g.setColor(Color.orange);
	        	break;
	        case 6: 
	        	g.setColor(Color.orange);
	        	break;
	        case 7: 
	        	g.setColor(Color.red);
	        	break;
	        case 8: 
	        	g.setColor(Color.DARK_GRAY);
	        	break;
	        default:
	        	g.setColor(Color.magenta);
			}
		}
			
	}