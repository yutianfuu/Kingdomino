package ca.mcgill.ecse223.kingdomino.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileSystemView;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.LoadGame;
import ca.mcgill.ecse223.kingdomino.controller.SetGameOptions;
import ca.mcgill.ecse223.kingdomino.model.*;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.Gamestatus;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusInitializing;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusOnNextTurn;
import ca.mcgill.ecse223.kingdomino.model.Player.PlayerColor;
import  java.awt.image.BufferedImage;


public class Registration {

	private static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private String datFile = "src/main/resources/alldominoes.dat";

	/**
	 * Launch the application.
	 */

	public static void setVisible(boolean b) {
		//public static void main(String[] args) {
		if (frame == null)
			new Registration();
		frame.setVisible(b);
		
	}

	/**
	 * Create the application.
	 */
	public Registration() {

		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Registration");
		//frame.getContentPane().setBackground(new Color(107, 142, 35));
		frame.setBounds(200, 100, 500, 420);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		textField = new JTextField();
		textField.setBounds(165, 29, 143, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				textField.setForeground(Color.BLACK);
			}
		});

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(165, 75, 143, 32);
		frame.getContentPane().add(textField_1);
		textField_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_1.setText("");
				textField_1.setForeground(Color.BLACK);
			}
		});


		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(165, 132, 143, 32);
		frame.getContentPane().add(textField_2);
		textField_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_2.setText("");
				textField_2.setForeground(Color.BLACK);
			}
		});

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(165, 181, 143, 32);
		frame.getContentPane().add(textField_3);
		textField_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_3.setText("");
				textField_3.setForeground(Color.BLACK);
			}
		});
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(242, 348, 137, 27);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("BonusOption");
		comboBox.setVisible(false);
		comboBox.addItem("MiddleKingdom");
		comboBox.addItem("Harmony");
		comboBox.addItem("Both");
		comboBox.addItem("none");
		JButton btnNewButton = new JButton("Register");
		JButton btnNewButton_2 = new JButton("Load Game");
		JButton btnNewButton_3 = new JButton("Start a New Game");

		JLabel lblNewLabel = new JLabel("Player1");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times", Font.PLAIN, 15));
		lblNewLabel.setBounds(46, 37, 81, 24);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblPlayer = new JLabel("Player2");
		lblPlayer.setForeground(Color.WHITE);
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setFont(new Font("Times", Font.PLAIN, 15));
		lblPlayer.setBounds(46, 83, 81, 24);
		frame.getContentPane().add(lblPlayer);

		JLabel lblPlayer_1 = new JLabel("Player3");
		lblPlayer_1.setForeground(Color.WHITE);
		lblPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_1.setFont(new Font("Times", Font.PLAIN, 15));
		lblPlayer_1.setBounds(46, 137, 81, 24);
		frame.getContentPane().add(lblPlayer_1);

		JLabel lblPlayer_2 = new JLabel("Player4");
		lblPlayer_2.setForeground(Color.WHITE);
		lblPlayer_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_2.setFont(new Font("Times", Font.PLAIN, 15));
		lblPlayer_2.setBounds(46, 186, 81, 24);
		frame.getContentPane().add(lblPlayer_2);

		/*JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("src/main/resources/BackgroundR.png"));
		lblNewLabel_1.setBounds(0, -14, 558, 422);
		frame.getContentPane().add(lblNewLabel_1);
		*/



		Kingdomino kingdomino = new Kingdomino();
		KingdominoApplication.setKingdomino(kingdomino);
		HashMap<String, User> users = new HashMap<String, User>();
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/**
				 * uniform look 
				 * @author antaki
				 */
				String path = browseForload();
				if (path == "") return;
				Game game = LoadGame.loadGame(path);
				Draft curr = game.getCurrentDraft();
				if(game != null){
					SetGameOptions setbonus = new SetGameOptions();
					kingdomino.setCurrentGame(game);
					KingdominoApplication.setKingdomino(kingdomino);
					Gameplay gp = KingdominoApplication.getGameplay();

					gp.setGamestatus(Gamestatus.OnNextTurn);
					gp.setGamestatusInitializing(GamestatusInitializing.Null);
					gp.setGamestatusOnNextTurn(GamestatusOnNextTurn.PreplaceDomino);
					KingdominoApplication.setGameplay(gp);
					String name = textField.getText();
					String name2 = textField_1.getText();
					String name3 = textField_2.getText();
					String name4 = textField_3.getText();
					String [] names = new String [4];
					names[0]= name;
					names[1]= name2;
					names[2]= name3;
					names[3]= name4;
					if (comboBox.getSelectedItem().toString().equalsIgnoreCase("MiddleKingdom")){
						setbonus.selectBonusM("is");
					}
					if (comboBox.getSelectedItem().toString().equalsIgnoreCase("Harmony")) {
						setbonus.selectBonus("is");
					}
					if (comboBox.getSelectedItem().toString().equalsIgnoreCase("both")) {
						setbonus.selectBonus("is");
						setbonus.selectBonusM("is");
					}
					for (int i = 0; i < names.length; i++) {
						game.getPlayer(i).setUser(users.get(names[i]));
						game.getPlayer(i).setColor(PlayerColor.values()[i]);
						//game.getPlayer(i).getKingdom().addTerritory(new Castle(0, 0, game.getPlayer(i).getKingdom(), game.getPlayer(i)));
					}
					game.setCurrentDraft(curr);
					GamePage gamepage = new GamePage(true);
					gamepage.setVisible(true);
				} else showMsg("No saved data.");

			}

		});
		btnNewButton_2.setVisible(false);
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

		} catch (IOException e) {
			System.out.print("wrong");
			e.printStackTrace();
		}

		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * start a new game feature 
			 * @author 
			 */
			public void actionPerformed(ActionEvent e) {
				//Kingdomino kingdomino = new Kingdomino();
				//Kingdomino kingdomino = new Kingdomino();
				//KingdominoApplication.setKingdomino(kingdomino);
				Kingdomino q = KingdominoApplication.getKingdomino();

				// Game game = new Game(48, kingdomino);
				//game.setNumberOfPlayers(4);
				//kingdomino.setCurrentGame(game);
				String name = textField.getText();
				String name2 = textField_1.getText();
				String name3 = textField_2.getText();
				String name4 = textField_3.getText();
				String [] names = new String [4];
				names[0]= name;
				names[1]= name2;
				names[2]= name3;
				names[3]= name4;

				boolean hasAllUser = true;
				for (int i = 0;i<4;i++) {
					if (names[i]!= null) {
						if (users.containsKey(names[i])) {
							//lblNewLabel_2.setText("The new user: " + name + " has NOT been updated");
							//lblNewLabel_2.setVisible(true);

							btnNewButton_3.setVisible(true);


						}else {
							hasAllUser = false;
							User user = new User(names[i], q);
							q.addUser(user);
							users.put(names[i], user);
							String msg = "The new user: " + names[i] + " has been updated";
							lblNewLabel.setText(msg);
							lblNewLabel.setVisible(true);
							try{

								File f = new File("names.txt");
								FileWriter writer = new FileWriter(f.getAbsolutePath(), true);
								writer.write("\n" + names[i]);
								writer.close();
							} catch (Exception exp) {
								msg = exp.getMessage();
							}
						}
					}

				}
				if(hasAllUser ==true) {
					//String msg = "The new user: " + name + " has been updated";

					btnNewButton_2.setVisible(true);


				}
				btnNewButton_3.setVisible(true);

			}
		});

		btnNewButton.setBounds(66, 259, 117, 29);
		frame.getContentPane().add(btnNewButton);


		//JButton btnNewButton_1 = new JButton("Cancel");
		//btnNewButton_1.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {
		//		setVisible(false);
		//		KingdominoView view = new KingdominoView();
		//		view.setVisible(true);
		//	}
		//});
		//btnNewButton_1.setBounds(242, 259, 117, 29);
		//frame.getContentPane().add(btnNewButton_1);

		//JButton btnNewButton_1 = new JButton("Cancel");
		//btnNewButton_1.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {
		//		frame.setVisible(false);
		//	}
		//});
		//btnNewButton_1.setBounds(242, 259, 117, 29);
		//frame.getContentPane().add(btnNewButton_1);




		btnNewButton_2.setBounds(66, 312, 127, 29);
		frame.getContentPane().add(btnNewButton_2);

		btnNewButton_3.setVisible(false);
		btnNewButton_3.setBounds(242, 309, 137, 32);
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Kingdomino kingdomino = new Kingdomino();

				comboBox.setVisible(true);
			}

		});
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * start a new game feature and select bonus 
				 * @author everyone except majd and david
				 */
				// TODO Auto-generated method stub
				Kingdomino kingdomino = KingdominoApplication.getKingdomino();
				Game game = new Game(48, kingdomino);
				SetGameOptions setbonus = new SetGameOptions();
				game.setNumberOfPlayers(4);
				kingdomino.setCurrentGame(game);
				//game.setNumberOfPlayers(4);
				// kingdomino.setCurrentGame(game);
				// Populate game
				// addDefaultUsersAndPlayers(game);
				createAllDominoes(game);


				KingdominoApplication.setKingdomino(kingdomino);
				Gameplay gp = new Gameplay();
				gp.setGamestatus(Gamestatus.Initializing);
				KingdominoApplication.setGameplay(gp);
				String name = textField.getText();
				String name2 = textField_1.getText();
				String name3 = textField_2.getText();
				String name4 = textField_3.getText();
				ArrayList<String> names = new ArrayList<String>();
				names.add(name);
				names.add(name2);
				names.add(name3);
				names.add(name4);
				Collections.shuffle(names);
				//String [] names = new String [4];
				//names[0]= name;
				//names[1]= name2;
				//names[2]= name3;
				//names[3]= name4;
				
				if (comboBox.getSelectedItem().toString().equalsIgnoreCase("MiddleKingdom")){
					setbonus.selectBonusM("is");
				}
				if (comboBox.getSelectedItem().toString().equalsIgnoreCase("Harmony")) {
					setbonus.selectBonus("is");
				}
				if (comboBox.getSelectedItem().toString().equalsIgnoreCase("both")) {
					setbonus.selectBonus("is");
					setbonus.selectBonusM("is");
				}
				for (int i = 0; i < names.size(); i++) {
					//User user = KingdominoApplication.getKingdomino().addUser(names[i]);
					Player player = new Player(KingdominoApplication.getKingdomino().getCurrentGame());
					player.setUser(users.get(names.get(i)));
					player.setColor(PlayerColor.values()[i]);
					game.addPlayer(player);
					Kingdom kingdom = new Kingdom(player);
					new Castle(0, 0, kingdom, player);
				}
				//game.setNextPlayer(game.getPlayer(0));
				setVisible(false);
				GamePage gamepage = new GamePage(false);
				gamepage.setVisible(true);


				//btnNewButton_3.setVisible(true);
			}

		});
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("src/main/resources/BackgroundR.png"));
		lblNewLabel_1.setBounds(0, -14, 558, 422);
		frame.getContentPane().add(lblNewLabel_1);

	}
	private void addDefaultUsersAndPlayers(Game game) {
		String[] userNames = {"User1", "User2", "User3", "User4"};
		for (int i = 0; i < userNames.length; i++) {
			User user = game.getKingdomino().addUser(userNames[i]);
			Player player = new Player(game);
			player.setUser(user);
			player.setColor(PlayerColor.values()[i]);
			Kingdom kingdom = new Kingdom(player);
			new Castle(0, 0, kingdom, player);
		}
	}
	private void createAllDominoes(Game game) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.datFile));
			String line = "";
			String delimiters = "[:\\+()]";
			while ((line = br.readLine()) != null) {
				String[] dominoString = line.split(delimiters); // {id, leftTerrain, rightTerrain, crowns}
				int dominoId = Integer.decode(dominoString[0]);
				TerrainType leftTerrain = getTerrainType(dominoString[1]);
				TerrainType rightTerrain = getTerrainType(dominoString[2]);
				int numCrown = 0;
				if (dominoString.length > 3) {
					numCrown = Integer.decode(dominoString[3]);
				}
				new Domino(dominoId, leftTerrain, rightTerrain, numCrown, game);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new java.lang.IllegalArgumentException(
					"Error occured while trying to read alldominoes.dat: " + e.getMessage());
		}
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
	
	
	private TerrainType getTerrainType(String terrain) {
		switch (terrain) {
			case "W":
				return TerrainType.WheatField;
			case "F":
				return TerrainType.Forest;
			case "M":
				return TerrainType.Mountain;
			case "G":
				return TerrainType.Grass;
			case "S":
				return TerrainType.Swamp;
			case "L":
				return TerrainType.Lake;
			default:
				throw new java.lang.IllegalArgumentException("Invalid terrain type: " + terrain);
		}

	}

	private void showMsg(String s){
		JOptionPane.showMessageDialog(this.frame, s);
	}
}


