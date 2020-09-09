=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D arrays
  To represent the board, I used a 2D array of tile objects. 
  Depending on the type of tile object (explained in dynamic dispatch), the 
  tile will have a different behavior when it is revealed via a left click. 
  The game will end if it is a mine, and the tile will show a number representing 
  the number of adjacent mines. The board will be initialized at creation with
  the number of mines inputed being randomly distributed throughout the board.
  Furthermore, after the first click the board will be adjusted so the first
  click's location doesn't have a mine (if there is a mine, it is moved somewhere
  else). 

  2. Subtyping/Inheritance
  There will be an abstract class called tile that the “grass” and “mine” 
  classes extend. The tile class has methods for drawing the tile 
  before reveal, drawing the tile when flagging/deflagging, and drawing the 
  tile after being clicked on. Since the tiles all look the same before clicking,
   and when flagging, these methods will already be implemented in the abstract 
   class. The “grass” and “mine” classes is where I defined the behavior 
   for the reveal methods. This subtyping is useful because it allowed me to easily 
   create a 2D array for the field with both of these types of objects. It is also
   useful because it allows for a convenient organization of many methods. 
  

  3. Testable Component
  The main state of my game is the 2D board. Since most of the state of the game 
  is hidden (since the mines are hidden) I implemented methods to flag and reveal mines at 
  a specific position that can easily be called by test methods and by mouse event listeners. 
  I also created methods to get the arrays for the state of the board to check on specific tiles
  in order to check the desired behavior for edge cases. For example, needed to make sure recursive
  clear didn't reach the corners of a 5x5 board. I also tested to make sure there were the 
  correct number of mines on the board, even after moving the mine when clicked on it first. 
  I also tested to make sure the game ended when either all grass tiles were revealed, or a mine
  was revealed. Also made sure that a flagged cell couldn't be revealed and a revealed cell
  couldn't be flagged. 
  

  4. Recursion
  This was used for the "clear" function that is called when a Grass tile with no adjacent
  mines is called. It checks every adjacent mine and if it isn't flagged and isn't a mine, 
  reveals it, and then recursively calls the clear function on those until an area is cleared
  where all the boardering tiles have adjacent mines. Most useful at the beginning when often 
  times a big area will clear up.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Game:
  Main class that was required for our project and has the main method, making
  it the starting point for the game. It sets up the JFrame for the game and 
  initializes the Board object which is where the game occurs. It then proceeds
  to start the game by calling the Board's start() method.
  
  
  Board:
  This is controls the game state and displays everything on the board. Upon intialization,
  a JOptionPane to have a main menu with instructions and a start button.
  Once the start button is selected, then the user is prompted to enter a valid number for
  the dimension of the board and the number of mines. After this, the correct size arrays
  are generated with the correct number of mines dispersed throughout the board. 
  
  Once the arrays are initialized, the initializeTiles() method is called which randomly generates
  array of 1's and 0's that represent mines and lack of mines. This method calls setAdjacents()
  to correctly set 2D array representing number of adjacent mines for each corresponding tile,
  and then calls the setTiles() method which uses the other two arrays to generate appropriate
  2D array Tile objects. 
  
  There is also
  a mouse listener added that calls the flag method on a cell at the click point if it is 
  right click, and the reveal method on a cell if the click is a left click. 
  The flag method flags calls a flag method on the appropriate Tile if it isn't revealed.
  The reveal method calls the reveal method on the appropriate Tile if it isn't revealed.
  If it has no adjacent, then the clear method is called and recursively clears adjacent mines.
  If it is a mine, all mines are revealed and the game ends. Listener also is aware of whether 
  or not there has been a first click. If it is the first click, the setMines method is called
  that adjusts the board so the mine is not at the first click. 
  
  The tick() method runs on a loop and only repaints the board while the isPlaying boolean 
  is true and is always checking the game state (numFlagged, numRevealed, etc) and adjusted
  the status on the frame correctly and the isPlaying correctly. It 
  
  
  Tile: 
  This is the abstract class that allowed for a convenient creation of a 2D array
  of two types of cells. Has methods that the methods in the Board class call such as flag 
  and reveal that changes the boolean values which the paintComponent method uses to draw
  the the Tile. The flag drawing is handled by Tile, but the paintRevealed method is called
  to paint the subtypes which the Grass and Mine classes implement. Also has an unimplmented 
  method isMine() to check if something is a mine or not. 
  
  Grass:
  Created to handle behavior for empty cells. Has an implemented paintRevealed method that 
  specifies is specific drawing for being painted which involves displaying a number of adjacent
  mines if it isn't 0 and also drawing dirt underneath. It also has an implementation for isMine()
  that returns false always.
  
  Mine: 
  Created to handle behavior for empty cells. Has an implemented paintRevealed method that 
  specifies is specific drawing for being painted which involves displaying a mine with an explosion
  over a dirt tile. It also has an implementation for isMine()that returns true always.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
	
  Initially, I intialized the array of Tile objects all after the first click in order ensure
  that the first click wasn't a mine. However, this created significant lag with larger boards
  so I decided to initialize the array before, and only modify the neccessary tile after the click,
  which led to a much smoother game. 
  
  I also initially didn't have flag() and reveal() methods in the Board classes. I direcly had
  their implmentation in the mouselistener. This made it really hard to test the state of the game,
  so I decided to create these methods that take two inputs for the x and y coordinates so that
  I could easily test and also have more organized in the constructor.
  
  Furthermore, I had a decent amount of trouble when randomly generating the board. I couldn't figure
  out how to guarantee that there would be a certain number of mines placed in the board after
  running through it once, so I decided to do a while loop that keeps running through the board
  until there are enough mines. The implementation for this was also a bit tricking since there
  many if statements and nested loops and I had to check for suffiency at all of these points. As a
  result, I used a lot of print statements to debug. I'm still convinced there is a way to only
  go through the board once and set up the required number of mines randomly, but I'll let it be
  for now. 
  
  And of course, a big stumbling block was figuring out how to draw the images and organize it so
  it all looked good. This was especially tricky for figuring out the logic for the initial
  input screens as I wasn't sure how to make sure I always go back to the homescreen if I exit at
  any point. 
  

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

	For one, I would refactor the code for the JOptionPanes at the beginning since it makes the
	constructor very crowded by putting it in a separate method that can be called. However, I
	wasn't sure exactly sure how to do this because I'm not very familiar with them. 
	
	I would also change up the setMines method that corrects the first click mine because it 
	always puts the new mine at the first empty spot which makes the game slightly more predictable
	but also could at worse case involve traversing the whole board. Hopefully after taking algorithims
	I'll learn a better way to do this.
	
	Finally, I definitely should not have used objects to represent the state of the game. I couldn've
	used the 2D array of 1's and 0's and the game would've run a lot faster. Even though it did make
	coding a bit easier at times to have Tile Objects, overall it makes the program unnecessarily 
	taxing.  
	
	Besides these, I think my separation of functionality is pretty good. Most of my methods
	are pretty descriptive and are called by multiple other methods. Furthermore, since there 
	aren't many classes, private state is handled pretty well. The only getter methods are mainly
	for the test methods which all return clones of the data structures. Furthermore, all of the
	Board fields are private, and the Tile fields are protected so they can only be accessed by
	the Mine and Grass classes. 


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.

Grass pattern pic:
https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwijoJ3OwKTmAhWFzlkKHUsFAgIQjRx6BAgBEAQ&url=https%3A%2F%2Fdownloops.com%2Fstock-footage%2Fmosaic-green-free-download-magnified-pixels-pattern-video-loop%2F&psig=AOvVaw2hjyZOX7GhTi5jMQfkShin&ust=1575841322951277

Flag pic: 
https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjo_LvjwKTmAhVrxFkKHdgpArQQjRx6BAgBEAQ&url=https%3A%2F%2Fwww.pngfly.com%2Fpng-3km92x%2F&psig=AOvVaw3M9G4licgcfrohiPSYst-v&ust=1575841387218552
 
Mine pic:  
https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwiGheX8wKTmAhXRxFkKHZwHDsYQjRx6BAgBEAQ&url=https%3A%2F%2Fwww.pngfly.com%2Fpng-entbl3%2F&psig=AOvVaw198io2X73T8Djo2cSJPEdU&ust=1575841436303873

Explosion pic:
https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjcj9iVwaTmAhXCqFkKHZvZCDAQjRx6BAgBEAQ&url=https%3A%2F%2Fwww.pngix.com%2Fviewpng%2FJioTmi_cartoon-explosion-clipart-explosion-clipart-png-transparent-png%2F&psig=AOvVaw0c875n97yV3dqAKFnMVRaj&ust=1575841469011014

Tutorial for JOptionPanes:
https://alvinalexander.com/java/joptionpane-showinputdialog-examples
