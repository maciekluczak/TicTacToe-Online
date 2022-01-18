package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;

//Implementation of whole TicTacToe's grid board 3x3
//Runnable class contains in a thread methods for checking game states
public class GameBoard extends JPanel implements Runnable{

    private JPanel gamePanel;               //Main JPanel with board's grid 3x3 layout
    private GameField[][] boardField;       //2-Dimension Array of GameField object
    private String whoWin;                  //String variable with information about the winner
    private int boardSize;                  //Integer variable of board size
    private boolean gameEnd;                //Boolean variable answering the question "is the game done?"
    private GameBufor gameBufor;            //Class object GameBuffer, managing gameplay resources

    private final ImageIcon xWinsImg;
    private final ImageIcon oWinsImg;
    private final  ImageIcon drawImg;
    private JLabel  matchEndLabel;          //Label with game winner

    private TurnLabel turnLabel;            //Dynamic Label responding to "Whose Turn?"

    public GameBoard() {
        whoWin = "-";
        boardSize = 3;
        gameBufor = new GameBufor();
        turnLabel= new TurnLabel(150,150);

        //Creating board grid panel
        gamePanel = new JPanel(new GridLayout(3, 3, 7, 7));
        gamePanel.setBorder(BorderFactory.createLineBorder( new Color(246, 235, 220), 5));
        gamePanel.setBackground(new Color(246, 235, 220));
        gamePanel.setOpaque(true);

        //Set winner announcement JLabel
        xWinsImg= new ImageIcon("image\\xwins.png");
        oWinsImg= new ImageIcon("image\\owins.png");
        drawImg= new ImageIcon("image\\Draw!.png");

        matchEndLabel=new JLabel();
        matchEndLabel.setHorizontalAlignment(JLabel.CENTER);
        matchEndLabel.setVerticalAlignment(JLabel.TOP);

        //Creating board grid components, Game fields
        boardField = new GameField[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardField[row][col] = new GameField(140);
                gamePanel.add(boardField[row][col]);
            }
        }
        //Set main board's layout
        this.setLayout(new BorderLayout());
        this.add(gamePanel, BorderLayout.CENTER);
        this.setOpaque(false);
    }

    public void checkWinner() throws IOException {
    //Method shows final result of the game

        if (whoWin.equals("x")){

            System.out.println("X WIN +");
            matchEndLabel.setIcon(xWinsImg);

        }
        if (whoWin.equals("o")){
            System.out.println("O WIN +");

            matchEndLabel.setIcon(oWinsImg);

        }
        if (whoWin.equals("-")){
            System.out.println("It's a Draw +");
            matchEndLabel.setIcon(drawImg);

        }
        this.removeAll();
        this.add(matchEndLabel, BorderLayout.CENTER);
        gameBufor.endConnectServer();

        SwingUtilities.updateComponentTreeUI(this);     //Refresh, update component
    }

    @Override
    public void run() {
    //Runnable method (Thread) for checking game states

        int matchBound= (boardSize*boardSize);      //Total maximum number of moves in the game (both players)
        int oneMoreLoop=1;                          //Integer for last check of the state of the game to detect a draw
        int enemyMove;

        while (!gameEnd) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(gameBufor.getPlayerMark().equals("o") & gameBufor.getTurn()%2 == 1) {
                if(!gameBufor.getEnemyMove().equals("-")){

                    enemyMove = Integer.valueOf(gameBufor.getEnemyMove());
                    boardField[(enemyMove-1)/3][(enemyMove-1)%3].setFieldMark("x");
                    SwingUtilities.updateComponentTreeUI(this);}


            }



            else if(gameBufor.getPlayerMark().equals("x") & gameBufor.getTurn()%2 == 0){
                if(!gameBufor.getEnemyMove().equals("-")){

                    enemyMove = Integer.valueOf(gameBufor.getEnemyMove());
                    boardField[(enemyMove-1)/3][(enemyMove-1)%3].setFieldMark("o");
                    SwingUtilities.updateComponentTreeUI(this);}

            }




            for (int row = 0; row < boardSize; row++) {
                for (int col = 0; col < boardSize; col++) {
                    SwingUtilities.updateComponentTreeUI(boardField[row][col]);
                //For each win combination, check the mark (x,o,-) from under a single field on grid

                    if (boardField[2][0].getFieldMark().equals("x") &
                            boardField[1][1].getFieldMark().equals("x") &
                            boardField[0][2].getFieldMark().equals("x")) {
//                                                                                 X WIN
                        whoWin = "x";
                        gameEnd = true;
                        break;

                    } else if (boardField[2][0].getFieldMark().equals("o") &
                            boardField[1][1].getFieldMark().equals("o") &
                            boardField[0][2].getFieldMark().equals("o")) {
//                                                                                 O WIN
                        whoWin = "o";
                        gameEnd = true;
                        break;

                    } else if (boardField[0][0].getFieldMark().equals("x") &
                            boardField[1][1].getFieldMark().equals("x") &
                            boardField[2][2].getFieldMark().equals("x")) {
//                                                                                 X WIN
                        whoWin = "x";
                        gameEnd = true;
                        break;

                    } else if (boardField[0][0].getFieldMark().equals("o") &
                            boardField[1][1].getFieldMark().equals("o") &
                            boardField[2][2].getFieldMark().equals("o")) {
//                                                                                 O WIN
                        whoWin = "o";
                        gameEnd = true;
                        break;

                    } else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("x") &
                            boardField[row % boardSize][(col + 1) % boardSize].getFieldMark().equals("x") &
                            boardField[row % boardSize][(col + 2) % boardSize].getFieldMark().equals("x") &
                            boardField[row % boardSize][(col + 3) % boardSize].getFieldMark().equals("x")) {
//                                                                                 X WIN

                        whoWin = "x";
                        gameEnd = true;
                        break;

                    } else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("o") &
                            boardField[row % boardSize][(col + 1) % boardSize].getFieldMark().equals("o") &
                            boardField[row % boardSize][(col + 2) % boardSize].getFieldMark().equals("o") &
                            boardField[row % boardSize][(col + 3) % boardSize].getFieldMark().equals("o")) {
//                                                                                O WIN
                        whoWin = "o";
                        gameEnd = true;
                        break;

                    } else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("x") &
                            boardField[(row + 1) % boardSize][col % boardSize].getFieldMark().equals("x") &
                            boardField[(row + 2) % boardSize][col % boardSize].getFieldMark().equals("x") &
                            boardField[(row + 3) % boardSize][col % boardSize].getFieldMark().equals("x")) {
//                                                                              X WIN
                        whoWin = "x";
                        gameEnd = true;
                        break;

                    } else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("o") &
                            boardField[(row + 1) % boardSize][col % boardSize].getFieldMark().equals("o") &
                            boardField[(row + 2) % boardSize][col % boardSize].getFieldMark().equals("o") &
                            boardField[(row + 3) % boardSize][col % boardSize].getFieldMark().equals("o")) {
//                                                                      O WIN
                        whoWin = "o";
                        gameEnd = true;
                        break;
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(gameBufor.getTurn()==matchBound){
            //If the maximum number of moves has been reached, detect a draw

                if (oneMoreLoop==0){

                    this.removeAll();
                    gameEnd = true;}
                oneMoreLoop=0;
            }
        }
        //winner announcement
        try {
            checkWinner();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
