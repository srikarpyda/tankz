package tankz.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TankzMainMenu extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1778972834916181412L; 
	private JPanel pane;
	
	private JButton newGame,options,help,about,exit;
	
	public TankzMainMenu () {
		super("Tankz");
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("/images/tank.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(image != null) {
			setIconImage(image);
		}
		
		
		pane = new JPanel();
		pane.setPreferredSize(new Dimension(300,200));
		pane.setLayout(new GridLayout(5,1));
		
		newGame = new JButton("New Game");
		newGame.addActionListener(this);
		options = new JButton("Options");
		options.addActionListener(this);
		help = new JButton("Help");
		help.addActionListener(this);
		about = new JButton("About");
		about.addActionListener(this);
		exit = new JButton("Exit");
		exit.addActionListener(this);
		
		pane.add(newGame);
		pane.add(options);
		pane.add(help);
		pane.add(about);
		pane.add(exit);
		
		add(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newGame) {
			
		}else if (e.getSource() == options) {
			
		}else if(e.getSource() == help) {
			
		}else if(e.getSource() == about) {
			
		}else if(e.getSource() == exit) {
			System.exit(0);
		}else {
			//Something odd has happened. This should never get called
		}
	}

}
