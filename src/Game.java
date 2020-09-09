import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 * Class for running the game with Java Swing.  
 */
public class Game implements Runnable {

    
	int mines; 
	int dim; 
	int tileWidth;
	int frameDim; 
	final int FRAME_SIZE = 829; 
	
	@Override
	/**
	 * Method to set up the JFrame and appropriate
	 * Swing Components to start the game. 
	 */
	public void run() {
			dim = 12; 
			mines = 1; 
			tileWidth = 50; 
			frameDim = tileWidth*dim; 
			
			final JFrame frame = new JFrame("MINESWEEPER");
			frame.setTitle("Minesweeper"); 
			frame.setSize(frameDim, frameDim);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
			// Status panel
	        final JPanel status_panel = new JPanel();
	        frame.add(status_panel, BorderLayout.SOUTH);
	        final JLabel status = new JLabel("Running...");
	        status_panel.add(status);
	        
			//where game is played 
			Board board = new Board(status); 
			frame.add(board, BorderLayout.NORTH); 
			
			frame.setVisible(true);
			frame.setResizable(false);
			frame.pack();
			
			//start game
			board.start(); 
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game()); 
	}

}

