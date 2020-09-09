import static org.junit.Assert.*;

import javax.swing.JLabel;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the game state of the board.
 * Various corner cases tested. 
 */
public class BoardTest {
	
	JLabel status;
	
	@Before
	public void setUp() {
		status = new JLabel("Testing...");
	}
	@Test
	public void countMaxNumMinesMaxBoard() {
		Board board = new Board(status, 98, 14); 
		board.setMines(2,4);
		int[][] boardArr = board.getHiddenBoard();
		assertEquals("click spot empty", boardArr[2][4], 0);
		int count = 0; 
		for(int i =0; i < 14; i++) {
			for(int j = 0; j < 14; j++) {
				if(boardArr[i][j] ==1) {
					count++;
				}
			}
		}
		assertEquals("correct num mines", 98, count); 
	}
	
	public void countMinesNormalClick() {
		Board board = new Board(status, 0, 3);
		board.setMine(1,1);
		board.setTiles();
		board.setMines(2, 2);
		int[][] boardArr = board.getHiddenBoard();
		int count = 0; 
		for(int i =0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(boardArr[i][j] ==1) {
					count++;
				}
			}
		}
		assertEquals("correct num mines", 1, count); 
	}
	
	public void countMinNumMinesMaxBoard() {
		Board board = new Board(status, 1, 14); 
		board.setMines(2,4);
		int[][] boardArr = board.getHiddenBoard();
		assertEquals("click spot empty", boardArr[2][4], 0);
		int count = 0; 
		for(int i =0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(boardArr[i][j] ==1) {
					count++;
				}
			}
		}
		assertEquals("correct num mines", 1, count); 
	}
	
	@Test
	public void countMaxMinesMinBoard() {
		Board board = new Board(status, 4, 3); 
		int count = 0; 
		int[][] boardArr = board.getHiddenBoard();
		for(int i =0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(boardArr[i][j] ==1) {
					count++;
				}
			}
		}
		assertEquals(4, count); 
		board.setMines(2,2);
		boardArr = board.getHiddenBoard();
		assertEquals("click spot empty", boardArr[2][2], 0);
		count = 0; 
		for(int i =0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(boardArr[i][j] ==1) {
					count++;
				}
			}
		}
		assertEquals(4, count); 
	}
	
	@Test
	public void countMinMinesMinBoard() {
		Board board = new Board(status, 1, 3); 
		board.setMines(2,2);
		int[][] boardArr = board.getHiddenBoard();
		assertEquals("click spot empty", boardArr[2][2], 0);
		int count = 0; 
		for(int i =0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(boardArr[i][j] ==1) {
					count++;
				}
			}
		}
		assertEquals("correct num mines", 1, count); 
	}
	
	@Test
	public void  countAdjacentMines() {
		Board board = new Board(status, 0, 3); 
		board.setMine(0, 0);
		board.setMine(0, 2);
		board.setMine(2, 1);
		int[][] adj = board.getAdjacents(); 
		assertEquals("corner tile 1", 1, adj[2][0]);
		assertEquals("corner tile 2", 1, adj[2][2]);
		assertEquals("edge tile", 2, adj[0][1]);
		assertEquals("center tile", 3, adj[1][1]); 
	}
	
	@Test
	public void gameFinishedRevealAll() {
		Board board = new Board(status, 0, 2);
		board.setMine(0, 0);
		board.setMine(0, 1);
		board.setTiles(); 
		board.reveal(1, 0);
		board.reveal(0,1); 
		assertFalse(board.isPlaying()); 
	}
	
	@Test
	public void gameFinishedExploded() {
		Board board = new Board(status, 0, 2); 
		board.setTiles();
		board.setMine(0, 0);
		board.setMine(1, 1);
		board.setTiles(); 
		board.reveal(0, 0);
		Tile[][] tiles = board.getTiles();
		assertFalse(board.isPlaying()); 
		assertTrue(tiles[0][0].isRevealed);
		assertTrue(tiles[1][1].isRevealed);
	}
	
	@Test
	public void revealFlaggedStopped() {
		Board board = new Board(status, 0, 2);
		board.setTiles();
		board.flag(0, 0);
		board.reveal(0, 0);
		Tile[][] tiles = board.getTiles(); 
		assertFalse(tiles[0][0].isRevealed); 
	}
	
	@Test 
	public void flagRevealedStopped() {
		Board board = new Board(status, 0, 2);
		board.setTiles();
		board.reveal(0, 0);
		board.flag(0, 0);
		Tile[][] tiles = board.getTiles(); 
		assertFalse(tiles[0][0].isFlagged); 
	}
	
	@Test 
	public void clearEverything() {
		Board b = new Board(status, 0, 2);
		b.setTiles();
		b.reveal(0, 0);
		assertEquals(4, b.getNumRevealed());
		assertFalse(b.isPlaying()); 
		
	}
	@Test
	public void recursiveClearNoCornerLeak() {
		Board board = new Board(status, 0, 5); 
		for(int i = 1; i < 4; i ++) {
			board.setMine(0, i);
			board.setMine(4, i);
			board.setMine(i, 0);
			board.setMine(i,4);
		}
		board.setTiles();
		board.reveal(2, 2);
		assertEquals(9, board.getNumRevealed()); 
		assertTrue(board.isPlaying()); 
		
		Tile[][] tiles = board.getTiles(); 
		assertFalse(tiles[0][4].isRevealed);
		assertFalse(tiles[4][0].isRevealed);
		assertFalse(tiles[0][0].isRevealed);
		assertFalse(tiles[4][4].isRevealed); 
	}
	
	@Test
	public void flagsNotClear() {
		Board board = new Board(status, 0, 2);
		board.flag(0, 1);
		board.flag(1,0);
		board.reveal(1, 1);
		assertEquals(2, board.getNumRevealed()); 
	}
	
	@Test
	public void flagBoundClear() {
		Board board = new Board(status, 0, 5);
		for(int i = 1; i < 5; i++) {
			board.flag(i, 1);
			board.flag(i, 3);
		}
		board.flag(1, 2);
		board.reveal(2, 2);
		assertEquals(3, board.getNumRevealed()); 
	}

}
