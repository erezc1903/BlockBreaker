package BlockBreaker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class Main{

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		JPanel totalGUI = new JPanel();
		Gameplay gamePlay = new Gameplay();
		frame.setBounds(10, 10, 700, 650);
		frame.setTitle("Block Breaker");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//game menus
		
		JMenuBar menuBar = new JMenuBar();
		
		//File menu - includes "Exit", "New Game", "Scores"
		JMenu fileMenu = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}});
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gamePlay.startNewGame();
			}
			
		});
		fileMenu.add(newGame);
		fileMenu.add(exit);
		menuBar.add(fileMenu);
		
		
		
		
		totalGUI.setLayout(new BorderLayout());
		totalGUI.add(menuBar, BorderLayout.PAGE_START);
		totalGUI.add(gamePlay, BorderLayout.CENTER);
		frame.setContentPane(totalGUI);
//		frame.add(gamePlay);
//		frame.add(panel);
		
//		panel.setVisible(true);
//		panel.add(fileButton);
//		panel.add(label);
//		frame.add(panel);
		
		
		
		
		//change image in top left corner to a ball picture
		//add a panel for buttons
		//add buttons for "file", "about", "settings"
		//add menu in "file" a "scores" button, "exit" button
		//add option to pause game by clicking 'p'
		//add a frame for scores and a data structure to save the top ten scores
		//check how to make an executable file
		//check how to save score history.
	}
	
	public void initializeMenus() {
		
	}
	
	// make the GUI thread-safe
	
//	public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
//    
//    private static void createAndShowGUI() {
//        //Create and set up the frame. 
//        //The string passed as an argument will be displayed 
//        //as the title.
//    
//        JFrame frame = new JFrame("[=] Hello World [=]");
//    
//        //Display the window.
//        frame.setSize(400, 100);
//        frame.setVisible(true);
//    }  

}
