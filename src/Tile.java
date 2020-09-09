
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*; 

@SuppressWarnings("serial")
/** 
 * A Tile is a cell on the board that can be revealed or flagged.
 * Tiles can either be deadly Mines or harmless Grass Tiles. 
 * The Board is composed of a 2D array of Tiles. 
 * @author adibhati
 *
 */
public abstract class Tile extends JComponent {
	
	protected boolean isFlagged = false; 
	protected boolean isRevealed = false; 
	private final String FLAG_FILE = "pics/flag.png";
	private final String GRASS_FILE = "pics/grass.png";
	
	protected int tileDim; 
	protected int adj; 
	protected int x; 
	protected int y; 
	
	protected Image grassImg; 
	protected Image flagImg; 
	
	/**
	 * Constructor for a Tile
	 * @param tileDim Dimension of the Tile in pixels
	 * @param adj The number of Mines adjacent to the Tile
	 * @param x The x position of the Tile on the Board (assuming top left origin)
	 * @param y The y position of the Tile on the Board (assuming top left origin)
	 */
	public Tile(int tileDim, int adj, int x, int y) {
		
		this.x = x;
		this.y = y; 
		this.adj = adj; 
		this.tileDim = tileDim ; 
		
		
		//Load png images from project files
		try {
            if (grassImg == null) {
                grassImg = ImageIO.read(new File(GRASS_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		
		try {
            if (flagImg == null) {
                flagImg = ImageIO.read(new File(FLAG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		
	}
	
	/**
	 * @return boolean stating whether or not the Tile is revealed
	 */
	public boolean isRevealed() {
		boolean x; 
		x = isRevealed; 
		return x; 
	}
	
	/**
	 * Flags the Tile
	 */
	public void flag() {
		this.isFlagged = !isFlagged;
		this.repaint();
	}
	
	/**
	 * Reveals the Tile
	 */
	public void reveal() {
		if(!isRevealed) {
			isRevealed = true; 
		}
		this.repaint();
	}
	
	
	/**
	 * Paints the Tile
	 */
	public void paintComponent(Graphics g) {
		if(this.isFlagged) {
			g.drawImage(flagImg, x, y, tileDim, tileDim, null); 
		}
		else if(this.isRevealed) {
			paintRevealed(g); 
		}
		else {
			g.drawImage(grassImg, x, y, tileDim, tileDim, null); 
		}
	}
	
	/**
	 * @return boolean stating whether or not the Tile has any adjacent
	 * Mines. 
	 */
	public boolean isClear() {
		return(adj == 0); 
	}
		
	
	/**
	 * Paints the Tile
	 * @param g Graphics context
	 */
	public abstract void paintRevealed(Graphics g); 
	
	/**
	 * @return boolean stating whether or not the Tile is a Mine
	 */
	public abstract boolean isMine(); 
	
	
}
