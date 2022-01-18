package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
//Main game layout- CardLayout
public class MainScreen extends JFrame implements MouseListener, WindowListener {
    private static CardLayout cl;               //Pages initialization
    private static JPanel contentPanel;
    private  HomePage homePage;
    private  static GamePage gamePage;
    private WaitPage waitPage;
    private GameBufor gameBufor;
    private SearchPage searchPage;



    private ImageIcon windowIcon;

    public MainScreen() {               //Set Frame

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
        homePage.getPlayButton().addMouseListener(this);
        gamePage.getExitButton().addMouseListener(this);
        waitPage.getExitButton().addMouseListener(this);
        searchPage.getBackButton().addMouseListener(this);
        searchPage.getConnectButton().addMouseListener(this);

        this.addWindowListener(this);




        cl.show(contentPanel, "1");
        this.add(contentPanel);

        this.setVisible(true);


    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == homePage.getPlayButton()) {
            cl.show(contentPanel, "4");

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
        else if  (e.getSource() == searchPage.getConnectButton()) {
            if(!(!searchPage.getIpTextField().equals("Enter Server IP") ^ !searchPage.getIpTextField().isBlank())){ //Check if is Ip valid

                gameBufor = new GameBufor();
                gameBufor.setServerIp(searchPage.getIpTextField());
                System.out.println(gameBufor.getServerIp());
                gameBufor.connectServer();


                Thread waitPageThread = new Thread(waitPage.getWaitLabel());
                waitPageThread.start();

                cl.show(contentPanel, "3");
            }

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
        if (gameBufor != null){                 // end connection when window closing (during connection with server)
            waitPage.getWaitLabel().setShouldWait(false);
            try {
                gameBufor.endConnectServer();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


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
    public static void letsPlay(){ // Game Thread
        Thread gameTicTacToe = new Thread(gamePage);
        gameTicTacToe.start();
        cl.show(contentPanel, "2");

    }


}
