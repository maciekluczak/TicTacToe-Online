package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class MainScreen extends JFrame implements MouseListener, WindowListener {
    private CardLayout cl;
    private JPanel contentPanel;
    private HomePage homePage;
    private  GamePage gamePage;
    private WaitPage waitPage;
    private SearchPage searchPage;
    private GameBufor gameBufor;



    private ImageIcon windowIcon;

    public MainScreen() {

        // Frame Settings
        this.setTitle("TicTacToe Online");
        this.setSize(1100, 700);
        setResizable(false);
        windowIcon = new ImageIcon("image\\oX.jpg");
        this.setIconImage(windowIcon.getImage());

        //New CardLayout
        cl = new CardLayout();
        contentPanel = new JPanel();
        contentPanel.setLayout(cl);

        //Initialize menu pages to CardLayout
        homePage= new HomePage();
        gamePage= new GamePage();
        waitPage = new WaitPage();
        searchPage = new SearchPage();

        contentPanel.add(homePage, "1");
        contentPanel.add(gamePage,"2");
        contentPanel.add(waitPage, "3");
        contentPanel.add(searchPage, "4");

        //Get access to action buttons from pages
        homePage.getSearchButton().addMouseListener(this);
        homePage.getMatchButton().addMouseListener(this);
        gamePage.getExitButton().addMouseListener(this);
        waitPage.getExitButton().addMouseListener(this);
        waitPage.getBackButton().addMouseListener(this);
        searchPage.getBackButton().addMouseListener(this);
        this.addWindowListener(this);




        cl.show(contentPanel, "1");
        this.add(contentPanel);

        this.setVisible(true);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == homePage.getSearchButton()) {
//            Thread gameTicTacToe = new Thread(gamePage);
//            gameTicTacToe.start();
            cl.show(contentPanel, "4");
        }
        else if (e.getSource() == homePage.getMatchButton()) {
            gameBufor = new GameBufor();
            gameBufor.connectServer();
            Thread waitPageThread = new Thread(waitPage.getWaitLabel());
            waitPageThread.start();

            cl.show(contentPanel, "3");

        }
        if (e.getSource() == gamePage.getExitButton()) {
            try {
                gamePage.ExitGame();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            cl.show(contentPanel, "1");
        }
        if (e.getSource() == waitPage.getExitButton()) {
            waitPage.getWaitLabel().setShouldWait(false);
            try {
                gameBufor.endConnectServer();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            cl.show(contentPanel, "1");
        }
        if (e.getSource() == searchPage.getBackButton()) {
            cl.show(contentPanel, "1");
        }
        //TEST BUTTON
        if (e.getSource() == waitPage.getBackButton()) {
            waitPage.getWaitLabel().setShouldWait(false);
            Thread gameTicTacToe = new Thread(gamePage);
            gameTicTacToe.start();

//            Thread connectServer =new Thread(clientTCP);
//            connectServer.start();
            cl.show(contentPanel, "2");
        }

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {


    }

    @Override
    public void windowClosing(WindowEvent e) {

        waitPage.getWaitLabel().setShouldWait(false);
        try {
            gameBufor.endConnectServer();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
