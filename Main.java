import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("WELCOME TO THE GAME CONNECT 5! ");
        System.out.println("PLAYER 1 PLAYS WITH X AND PLAYER 2 WITH 0. ");
        
        //Initializing Connect5 class
        Connect5 game = new Connect5();
       
       //The game is not stopping while there is no winning player. When winningplayer=1 the player 1 wins, when it is 2, the player 2 wins.
        while(game.winningPlayer == 0 ) {
           
            //Declearing a Scanner variable to read input from the keyboard
            Scanner sc = new Scanner(System.in);
            System.out.println("Player " + game.playerTurn + ", enter the column number (1-7):  ");

            //If each of the verifications displayed below, player is not loosing his turn. 
            //This condition verifies that input is an integer. 
            if (sc.hasNextInt()){
                int pos = sc.nextInt();
                //this condition verifies that the integer is within range 1-7.
                if(pos<8 && pos>0){
                    //this condition verifies that the colums player chooses are not full.
                    if(game.isColumnFull(game.board, pos)){
                        System.out.println("This column is full. Chooose another column");
                        game.swapPlayerTurn();
                    }

                Connect5.placeToken(game.board, pos, game.playerTurn);

                }else{
                    System.out.println("PLEASE INSERT VALID INPUT!YOUR ENTERED NUMBER IS NOT WITHIN THE RANGE OF COLUMNS. TRY AGAIN");
                    game.swapPlayerTurn();
                }

            }else{
                System.out.println("PLEASE INSERT VALID INPUT! IT MUST BE A NUMBER. TRY AGAIN. ");
                game.swapPlayerTurn();
            }


                //when the token is placed, board is displayed, players turn is swapped and winning consitions are checked.
                game.displayBoard(game.board);
                game.swapPlayerTurn();
                game.winningPlayer = game.checkforWinner();

                //if there is a winner, while loop breaks.
                if (game.winningPlayer == 1 || game.winningPlayer == 2){
                    game.showWinner();
                    break;
                }

                //if statement which breaks the while loop when the board is full. 
                if(game.isBoardFull(game.board)&& game.winningPlayer == 0){
                    System.out.println("The board is full!! Nobody won!!!!");
                    break;
                }

        }
    }

}

