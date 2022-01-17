package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

//Implementation of single field class from TicTacToe's board 3x3
public class GameField extends JLabel implements MouseListener {

    private String fieldMark;               //Char under the field (x,o,-)
    private GameBufor gameBufor;           //Class object GameBuffer, managing gameplay resources

    private final int fieldID;           //Field ID
    private static int fieldCount;      //Object static counter

    private final ImageIcon cross;
    private final ImageIcon circle;

    public GameField(int fieldSize){
        //For each field, allocate an ID for the game from static variable
        fieldID = (fieldCount%9) + 1;

        fieldMark=" ";
        gameBufor = new GameBufor();

        cross= new ImageIcon("image\\cancel128.png");
        circle= new ImageIcon("image\\circumference (2).png");

        //Font size increases grid's single field area
        Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, fieldSize);
        this.setText("     ");

        //Custom field design
        this.setHorizontalTextPosition(CENTER);
        this.setVerticalTextPosition(CENTER);
        this.setHorizontalAlignment(this.CENTER);
        this.setVerticalAlignment(this.CENTER);

        this.setFont(LABEL_FONT);

        this.setOpaque(true);

        this.setBackground(new Color(255, 255, 255));

        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setHorizontalAlignment(SwingConstants.CENTER);

        //Field response to mouse interaction
        this.addMouseListener(this);

        //Increment static value
        fieldCount ++;
    }

    public void setFieldMark(String fieldMark) {
    //Change field's mark after mouse click (turn-dependent char)

        if(this.fieldMark.equals(" ") ) {
            this.fieldMark = fieldMark;

            if (fieldMark.equals("x")) {
                this.setIcon(cross);
                gameBufor.setTurn(gameBufor.getTurn() + 1);
                //System.out.println("x"+ fieldID);

            } else if (fieldMark.equals("o")) {
                this.setIcon(circle);
                gameBufor.setTurn(gameBufor.getTurn() + 1);
               // System.out.println("o"+ fieldID);
            }
        }
    }

    public int getFieldID() {
        return fieldID;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    //Field respond to mouse click. Mark under field has changed (turn-dependent char)

        if(gameBufor.getTurn()%2 == 0){
            setFieldMark("o");
            try {
                gameBufor.playerMove(fieldMark+fieldID);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        } else if(gameBufor.getTurn()%2 == 1){
            setFieldMark("x");

            try {
                gameBufor.playerMove(fieldMark+fieldID);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        }
    }




    public String getFieldMark() {
        return fieldMark;
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
    //Field respond to mouse move. Color of field has changed to green (Entered)

        if(this.fieldMark.equals(" ") ) {
            this.setBackground(Color.GREEN);
        }
        else{
            this.setBackground( new Color(246, 235, 220));
        }
    }


    @Override
    public void mouseExited(MouseEvent e) {
    //Field respond to mouse move. Color of field has changed to default (Exited)

        this.setBackground(new Color(255, 255, 255));
    }
}

