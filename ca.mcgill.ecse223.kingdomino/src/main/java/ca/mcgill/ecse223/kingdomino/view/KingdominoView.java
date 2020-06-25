package ca.mcgill.ecse223.kingdomino.view;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.LoadGame;
import ca.mcgill.ecse223.kingdomino.controller.SaveGame;
import ca.mcgill.ecse223.kingdomino.controller.SetGameOptions;
import ca.mcgill.ecse223.kingdomino.model.Draft;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Gameplay;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.Gamestatus;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusInitializing;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusOnNextTurn;
import ca.mcgill.ecse223.kingdomino.model.Player.PlayerColor;
import ca.mcgill.ecse223.kingdomino.model.User;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileSystemView;

public class KingdominoView {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KingdominoView window = new KingdominoView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void setVisible(boolean b) {
		if (b == true) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						KingdominoView window = new KingdominoView();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
	 * Create the application.
	 */
	public KingdominoView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		/**
		 * uniform look 
		 * @author antaki 
		 */

        try {
        	frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setSize(new Dimension(4, 4));
		lblNewLabel.setLocation(new Point(0, 2));
		lblNewLabel.setPreferredSize(new Dimension(40, 11));
		lblNewLabel.setMaximumSize(new Dimension(40, 4));
		//lblNewLabel.setIcon(new ImageIcon("/Users/amelia/Downloads/media-942.nl.png"));
		//lblNewLabel.setIcon(new ImageIcon(KingdominoView.class.getResource("/ca/mcgill/ecse223/kingdomino/src/media-942.nl.png")));
		btnNewButton = new JButton("Registration");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Registration.setVisible(true);
				
			}
			
		});
			
		
		btnNewButton_1 = new JButton("User Profile");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserProfile r = new UserProfile();
				//Registration r = new Registration();
				r.setVisible(true);
				
			}
		});
		lblNewLabel_1 = new JLabel("Welcome!");
		lblNewLabel_1.setForeground(new Color(139, 0, 0));
		lblNewLabel_1.setFont(new Font("STSong", Font.BOLD, 29));
		Kingdomino kingdomino = new Kingdomino();
		KingdominoApplication.setKingdomino(kingdomino);
		
		JButton btnNewButton_2 = new JButton("Load Game");
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = browseForload();
				if (path == "") return;
				Game game = LoadGame.loadGame(path);
				Draft curr = game.getCurrentDraft();
				if(game != null){
					/**
					 * provide user profile feature in load game 
					 * @author antaki, yutian fu, amelia cui, antonio shen, ruixin su
					 */
					HashMap<String, User> users = new HashMap<String, User>();
					ArrayList<String> usernames = new ArrayList<String>();
					try {
						File f = new File("names.txt");
						FileReader reader = new FileReader(f.getAbsolutePath());
						BufferedReader bufferedReader = new BufferedReader(reader);

						String line;

						while ((line = bufferedReader.readLine()) != null) {
							//if (kingdomino.hasUsers()== false) {

							//kingdomino.addUser(line);
							User user = new User(line, kingdomino);
							usernames.add(line);
							users.put(line, user);
							//}
						}
						reader.close();

					} catch (IOException exception) {
						System.out.print("wrong");
						exception.printStackTrace();
					}

					SetGameOptions setbonus = new SetGameOptions();
					kingdomino.setCurrentGame(game);
					KingdominoApplication.setKingdomino(kingdomino);
					Gameplay gp = KingdominoApplication.getGameplay();
					/**
					 * load game feature 
					 * @author 
					 */

					gp.setGamestatus(Gamestatus.OnNextTurn);
					gp.setGamestatusInitializing(GamestatusInitializing.Null);
					gp.setGamestatusOnNextTurn(GamestatusOnNextTurn.PreplaceDomino);
					KingdominoApplication.setGameplay(gp);
					//String name = "P1";
					//String name2 = "P2";
					//String name3 = "P3";
					//String name4 = "P4";
					//String [] names = new String [4];
					//names[0]= name;
					//names[1]= name2;
					//names[2]= name3;
					//names[3]= name4;
					for (int i = 0; i < 4; i++) {
					//	if (!users.containsKey(names[i])) {
					//		game.getPlayer(i).setUser(new User(names[i], kingdomino));
					//	}
						game.getPlayer(i).setColor(PlayerColor.values()[i]);
						//game.getPlayer(i).getKingdom().addTerritory(new Castle(0, 0, game.getPlayer(i).getKingdom(), game.getPlayer(i)));
					}
					game.setCurrentDraft(curr);
					GamePage gamepage = new GamePage(true);
					gamepage.setVisible(true);
				} else showMsg("No saved data.");


			}

		});
		
		JButton btnNewButton_3 = new JButton("Readme");
		btnNewButton_3.addActionListener(new ActionListener() {
/**
 * @author antaki
 */
			@Override
			public void actionPerformed(ActionEvent e) {
				showMsg("The steps and rules to play the game are as follows:\n"
						+ "By clicking on registration, players can be added. If the user\n"
						+ "does not exits, a new user with the same name will be added the users repository.\n"
						+ "After adding the names, and clicking the registers, if the all the users are new then\n"
						+ "a new game can be created. If some of them are not, then loading a game is a possible,\n"
						+ "use any of the names in names.txt file to check loading the game.\n"
						+ "After hitting create a new game, more options can be chosen.\n"
						+ "To play the game, in the first round, by clicking on the top square of the dominos\n"
						+ "at the top, the player chooses that domino. After all 4 players choose theirs, P1\n"
						+ "can move the current domino down, so it enters the kingdom. After that the player\n"
						+ "can move it around, rotate. When the player is happy with the placement, they can\n"
						+ "click on place, which is the validate button. If the placement is valid, the player's\n"
						+ "score is show and the player should choose the next domino from the next draft.\n"
						+ "This goes on till the gae is over.");
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton_2)
								.addComponent(btnNewButton_3)
								))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_1)))
					.addGap(301))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel_1)
					.addGap(30)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_3)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addGap(40))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	private void showMsg(String s){
		JOptionPane.showMessageDialog(this.frame, s);
	}

	private boolean saveGame(){
		return SaveGame.saveGame("recent_game.mov");
	}

	
	private String browseForload() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(frame);
		String path = "";

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			path = selectedFile.getAbsolutePath();
		}
		return path;
	}
}
