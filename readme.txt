This is my implementation of checkers/draughts! It follows all the basic rules of checkers, except I sadly wasn't able
to successfully implement chain jumps in time (although I plan on doing that after this class ends). When you run
Game.java, it will take you to the start screen, from which you can either view the instructions or go straight to
the actual game. On the game screen, players can play checkers by clicking and dragging checkers. If a move is valid,
then the piece will move. Player 1 is blue and player 2 is red. Additionally, there are buttons on the right to
undo moves, save games, load games, start new games, or go back to the start screen.

Here are how my four design concepts were implemented.

2-D Arrays: The state of the game is saved in an 8x8 2-D array. This array is created in Board.java and is modified
almost entirely there as well. In this array, 0 represents an empty square, 1 represents a blue piece, 2 represents
a crowned blue piece, 3 represents a red piece, and 4 represents a crowned red piece. This array is updated whenever
a new game starts, when a player makes a move, and when a player undos a move.

Collections: Each move is pushed to a stack (deque) that allows users to undo moves. When users undo a move, I
pop the stack to see the last recorded move so I can undo it. The stack stores values of type Move, which is a class I
created that stores starting coordinate, ending coordinate, and what kind of piece it did or did not capture. I also use
a LinkedList in my validMoves() function to output all the moves that a piece can make.

File I/O: When players click on the save game button, they are able to save games to files that they can open again
later and start playing again from the same state. The first line of the file is the player's turn, and the next 8 lines
are all displaying the state of the array.

Testable Component: Board.java is almost entirely testable because it relies heavily on the 2-D array that represents
the state of the game. All the functions that modify the state of the array are testable as well.

I hope you like this game!