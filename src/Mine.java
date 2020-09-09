import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
/**
 * A Mine is a Tile that causes the game to end when revealed. 
 * @author adibhati
 */
public class Mine extends Tile{
	
		Image mineImg; 
		private final String MINE_FILE = "pics/mine.png";
		
		
		public Mine(int tileDim, int adjacent, int x, int y) {
			super(tileDim, adjacent, x, y); 
			try {
		            if (mineImg == null) {
		                mineImg = ImageIO.read(new File(MINE_FILE));
		            }
		        } catch (IOException e) {
		            System.out.println("Internal Error:" + e.getMessage());
		        }
		}
		
		
		@Override
		public boolean isMine() {
			return true; 
		}


		@Override
		public void paintRevealed(Graphics g) {
			g.drawImage(mineImg, x, y , tileDim, tileDim, null); 
		}
		
	}