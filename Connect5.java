import java.util.Random;

public class Connect5 {
    //class attributes
    public char [][] board;
    int winningPlayer;
    int playerTurn;
    int rows;
    int columns;

    //Constructor of the class.
    public Connect5(){
        rows = 6;
        columns = 9;
        winningPlayer = 0;
        playerTurn = 1;
        board = new char[rows][columns];
        newBoard();
        fillColumn(board,0);
        fillColumn(board, 8);
        displayBoard(board);
    }

    //this method creates a 2D array list when this class is initialized with initial value for all entries '_'
    public void newBoard(){
        for (int i=0; i<rows; i++){
            for(int j =0; j<columns; j++){
                board[i][j]= '_';
            }
        }
    }

    //This method printing out the current values of the 2D array list (the board)
    public void displayBoard(char[][] board){
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    //This method fills in outer columns with X or 0 using random class. Since there is a chance, that columns may be filled in with 5 in a row characters X or 0
    //the while loop is introduced: the method runs while there is the same value in each row 5 times nonstop. 
    public static void fillColumn(char[][] board, int column){
        for(int i = 5; i>3; i--) {
            while( (board[i][column]==board[i-1][column]) && (board[i][column]==board[i-2][column]) && (board[i][column]==board[i-3][column]) && (board[i][column]==board[i-4][column])) {
                for(int row = 5; row>=0; row--) {
                    Random rand = new Random();
                    int token = rand.nextInt(2);
                    if (token == 0) {
                        board[row][column] = 'X';
                    } else if (token == 1) {
                        board[row][column] = '0';
                    }
                }
            }
        }
    }


    //Method to place a token. It checks which playerTurn it is rigth now and assigns the appropriate token. Afterwards by using for loop on each row, 
    //it checks if a column of the raw is taken, and if not it places it to a next available row. The rows are decrementing, because the the 0 row of the 
    //2D list is the first one from the top, but in the original game Connect 5 the rows are revers.
    public static void placeToken(char[][] board, int column,int playerTurn){
        char token = '_';
        if(playerTurn == 1) {
            token = 'X';
        }else if(playerTurn == 2){
            token = '0';
        }
        for (int row=5; row>=0; row-- ) {
           if (board[row][column] == '_') {
                board[row][column] = token;
                break;
                }
        }
    }

    //this method is to swap the turn of the player 
    public void swapPlayerTurn(){
        if (playerTurn ==1){
            playerTurn =2;
        }else{
            playerTurn = 1;
        }
    }

    
    //following methods verifies if a column is already full or not and if a board is full. They both return true if column/the whole board is full
    //and false otherwise
    public boolean isColumnFull(char[][] board, int column){
        boolean full = false;
        if(board[0][column] == 'X' || board[0][column] == '0'){
            full=true;
        }
        return full;
    }
    public boolean isBoardFull(char[][] board) {
        boolean full = false;
                if(board[0][1]!='_' && board[0][2]!='_' && board[0][3]!='_' && board[0][4]!='_' && board[0][5]!='_'&& board[0][6]!='_' && board[0][7]!='_'){
                    full=true;
                }
        return full;
    }




    //There is four winning arangments of the tokens: 5 tokens in a column, 5 tokens in a raw, 5 tokens in a right diagonal and 5 tokens in a left diagonals.
   // Following 4 methods checks each of the arangement. 
   // They are constructed similarly using nested for loops. Each method returns a char symbol (_, X or 0)
   // Inspiration is taken from https://www.youtube.com/watch?v=0dNIJkn65xQ&t=989s
    public char columnsCheck(char[][] board){
        char symbol = '_';
        for (int i = 5; i>3; i--){
            for( int j = 0; j<9; j++){
                if((board[i][j] == board[i-1][j]) && (board[i][j] == board[i-2][j]) && (board[i][j] == board[i-3][j])
                && (board[i][j] == board[i-4][j])){
                    if (board[i][j] == 'X' || board[i][j] == '0'){
                        symbol = board[i][j];
                    }
                }
            }
        }
        return symbol;
    }

    public char rowCheck(char[][] board){
        char symbol = '_';
        for (int i = 5; i>=0; i--){
            for( int j = 0; j<5; j++){
                if((board[i][j] == board[i][j+1]) && (board[i][j] == board[i][j+2]) && (board[i][j] == board[i][j+3])
                        && (board[i][j] == board[i][j+4])){
                    if (board[i][j] == 'X' || board[i][j] == '0'){
                        symbol = board[i][j];
                    }
                }
            }
        }

        return symbol;
    }

    public char rDiagonalCheck(char[][] board){
        char symbol = '_';
        for (int i = 5; i>3; i--){
            for( int j = 0; j<4; j++){
                if((board[i][j] == board[i-1][j+1]) && (board[i][j] == board[i-2][j+2]) && (board[i][j] == board[i-3][j+3])
                        && (board[i][j] == board[i-4][j+4])){
                    if (board[i][j] == 'X' || board[i][j] == '0'){
                        symbol = board[i][j];
                    }
                }
            }
        }

        return symbol;
    }

    public char lDiagonalCheck(char[][] board){
        char symbol = '_';
        for (int i = 5; i>3; i--){
            for( int j = 5; j<9; j++){
                if((board[i][j] == board[i-1][j-1]) && (board[i][j] == board[i-2][j-2]) && (board[i][j] == board[i-3][j-3])
                        && (board[i][j] == board[i-4][j-4])){
                    if (board[i][j] == 'X' || board[i][j] == '0'){
                        symbol = board[i][j];
                    }
                }
            }
        }

        return symbol;
    }


    //this method calls for the checking methods shown before. It returns value of winninPlayer(0,1, or 2). The initial value of winningPlayer is 0,
    //but if one of the conditions is met (if one of the four checking methods returned 0 or X) the value winningPlayer becomes 1 or 2 accordingly, representilg player 
    //1 and 2.
    public int checkforWinner(){
        int winningPlayer = 0;
        char symbol = '_';
        if (columnsCheck(board)=='X'|| columnsCheck(board)=='0'){
            symbol = columnsCheck(board);
        }else if(rowCheck(board) =='X'||rowCheck(board)=='0'){
            symbol = rowCheck(board);
        }else if(rDiagonalCheck(board)=='X' || rDiagonalCheck(board)=='O'){
            symbol=rDiagonalCheck(board);
        }else if(lDiagonalCheck(board)=='X'|| lDiagonalCheck(board)=='0'){
            symbol=lDiagonalCheck(board);
        }
        if (symbol == 'X'){
            winningPlayer = 1;
        }else if (symbol == '0'){
            winningPlayer = 2;
        }
        return winningPlayer;
    }


    //this method prints winner
    public void showWinner(){
        System.out.println("WINNING PLAYER IS  : " + winningPlayer + "! CONGRATULATIONS");
    }

}

