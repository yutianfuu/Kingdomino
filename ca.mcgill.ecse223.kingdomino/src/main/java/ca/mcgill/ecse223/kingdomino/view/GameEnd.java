package ca.mcgill.ecse223.kingdomino.view;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Game;
import ca.mcgill.ecse223.kingdomino.model.Player;
import ca.mcgill.ecse223.kingdomino.view.KingdominoView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GameEnd {

	private JFrame frame;
	private JTextField Player1S;
	private JTextField Player4;
	private JTextField Player3S;
	private JTextField txtPlayerScroe;
	Game game = KingdominoApplication.getKingdomino().getCurrentGame();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameEnd window = new GameEnd();
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
						frame.setVisible(true);
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
	public GameEnd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * calculate ranking, score, resolve tiebreak feature 
	 * @author everyone except david and majd 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		Game game = KingdominoApplication.getKingdomino().getCurrentGame();
		for(Player player: game.getPlayers()) {
			if (player.getCurrentRanking()== 1) {
				player.getUser().setWonGames(player.getUser().getWonGames()+1);
			}
			player.getUser().setPlayedGames(player.getUser().getPlayedGames()+1);
		}
		ArrayList<String> usernames = new ArrayList<String>();
		try{

			File f = new File("profiles.txt");
			FileWriter writer = new FileWriter(f.getAbsolutePath(), true);
			
			for (Player player : game.getPlayers()) {
			
					//if (tokens.length>1) {
						String[]result = new String[5];
						result[0]= player.getUser().getName();
						result[1] = " ";
						result[2]= player.getUser().getWonGames()+"";
						result[3]= " ";
						result[4]= player.getUser().getPlayedGames()+"";
						//writer.write("\n"+result);
						writer.write("\n"+player.getUser().getName()+" "+player.getUser().getWonGames()+" "+player.getUser().getPlayedGames());
					//}
				
			}
			
			//writer.write("\n" + names[i]);
			writer.close();
		} catch (Exception exp) {
			exp.getMessage();
		}

		JButton RestartGame = new JButton("Restart a Game");
		RestartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==RestartGame) {

					KingdominoView main=new KingdominoView();
					main.setVisible(true);
				}
			}
		});
		frame.getContentPane().add(RestartGame);

		JButton ExitGame = new JButton("Exit the Game");
		springLayout.putConstraint(SpringLayout.NORTH, ExitGame, 239, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, RestartGame, -6, SpringLayout.NORTH, ExitGame);
		springLayout.putConstraint(SpringLayout.WEST, ExitGame, 165, SpringLayout.WEST, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.SOUTH, ExitGame, -10, SpringLayout.SOUTH, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.EAST, ExitGame, -147, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, RestartGame, 0, SpringLayout.EAST, ExitGame);
		ExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==ExitGame) {
					System.exit(0);
				}
			}
		});
		frame.getContentPane().add(ExitGame);

		JButton ScoreButton1 = new JButton("");
		springLayout.putConstraint(SpringLayout.NORTH, ScoreButton1, 9, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ScoreButton1, -95, SpringLayout.EAST, frame.getContentPane());
		ScoreButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(ScoreButton1);
		ScoreButton1.setText( KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getTotalScore()+"Ranking: "+KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getCurrentRanking());


		Player1S = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, Player1S, -264, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ScoreButton1, 19, SpringLayout.EAST, Player1S);
		Player1S.setEditable(false);
		//YourScores.setBackground(UIManager.getColor("window"));
		Player1S.setFont(new Font("Times", Font.PLAIN, 16));
		Player1S.setHorizontalAlignment(SwingConstants.LEFT);
		Player1S.setText("Player1 score:");
		frame.getContentPane().add(Player1S);
		Player1S.setColumns(10);
		Player1S.setBorder(null);
		Player1S.setHorizontalAlignment(JTextField.CENTER);
		Player1S.setBackground(null);

		JButton ScoreButton2 = new JButton("");
		springLayout.putConstraint(SpringLayout.SOUTH, ScoreButton1, -9, SpringLayout.NORTH, ScoreButton2);
		springLayout.putConstraint(SpringLayout.NORTH, ScoreButton2, 54, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ScoreButton2, -95, SpringLayout.EAST, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.SOUTH, ScoreButton2, -188, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(ScoreButton2);
		ScoreButton2.setText(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getTotalScore()+"Ranking: "+KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getCurrentRanking());

		Player4 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, ScoreButton2, 6, SpringLayout.EAST, Player4);
		springLayout.putConstraint(SpringLayout.SOUTH, Player1S, -25, SpringLayout.NORTH, Player4);
		Player4.setText("Player2 score:");
		Player4.setHorizontalAlignment(SwingConstants.LEFT);
		Player4.setFont(new Font("Times", Font.PLAIN, 16));
		Player4.setEditable(false);
		Player4.setColumns(10);
		Player4.setBorder(null);
		Player4.setBackground((Color) null);
		frame.getContentPane().add(Player4);

		JButton ScoreButton3 = new JButton("");
		springLayout.putConstraint(SpringLayout.SOUTH, ScoreButton2, -9, SpringLayout.NORTH, ScoreButton3);
		springLayout.putConstraint(SpringLayout.NORTH, ScoreButton3, 98, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ScoreButton3, -95, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, ScoreButton1, 9, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ScoreButton1, -95, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(ScoreButton3);
		ScoreButton3.setText(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getTotalScore()+"Ranking: "+KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getCurrentRanking());

		JButton ScoreButton4 = new JButton("");
		springLayout.putConstraint(SpringLayout.NORTH, ScoreButton4, 6, SpringLayout.SOUTH, ScoreButton3);
		springLayout.putConstraint(SpringLayout.SOUTH, ScoreButton4, -28, SpringLayout.NORTH, RestartGame);
		springLayout.putConstraint(SpringLayout.EAST, ScoreButton4, -95, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(ScoreButton4);
		ScoreButton4.setText(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getTotalScore()+"Ranking "+KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getCurrentRanking());

		Player3S = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, Player3S, -251, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ScoreButton3, 6, SpringLayout.EAST, Player3S);
		springLayout.putConstraint(SpringLayout.WEST, Player4, 0, SpringLayout.WEST, Player3S);
		springLayout.putConstraint(SpringLayout.SOUTH, Player4, -20, SpringLayout.NORTH, Player3S);
		springLayout.putConstraint(SpringLayout.NORTH, Player3S, 105, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ScoreButton3, 29, SpringLayout.NORTH, Player3S);
		Player3S.setText("Player3 score:");
		Player3S.setHorizontalAlignment(SwingConstants.LEFT);
		Player3S.setFont(new Font("Times", Font.PLAIN, 16));
		Player3S.setEditable(false);
		Player3S.setColumns(10);
		Player3S.setBorder(null);
		Player3S.setBackground((Color) null);
		frame.getContentPane().add(Player3S);

		txtPlayerScroe = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, txtPlayerScroe, -251, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ScoreButton4, 6, SpringLayout.EAST, txtPlayerScroe);
		springLayout.putConstraint(SpringLayout.NORTH, txtPlayerScroe, 25, SpringLayout.SOUTH, Player3S);
		springLayout.putConstraint(SpringLayout.SOUTH, txtPlayerScroe, 41, SpringLayout.SOUTH, Player3S);
		txtPlayerScroe.setText("Player4 Score:");
		txtPlayerScroe.setHorizontalAlignment(SwingConstants.LEFT);
		txtPlayerScroe.setFont(new Font("Times", Font.PLAIN, 16));
		txtPlayerScroe.setEditable(false);
		txtPlayerScroe.setColumns(10);
		txtPlayerScroe.setBorder(null);
		txtPlayerScroe.setBackground((Color) null);
		frame.getContentPane().add(txtPlayerScroe);


	}
}
