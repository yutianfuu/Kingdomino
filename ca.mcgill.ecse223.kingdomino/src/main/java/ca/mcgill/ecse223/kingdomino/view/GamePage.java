package ca.mcgill.ecse223.kingdomino.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.*;
import ca.mcgill.ecse223.kingdomino.model.*;
import ca.mcgill.ecse223.kingdomino.model.Domino.DominoStatus;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom.DirectionKind;
import ca.mcgill.ecse223.kingdomino.model.Player.PlayerColor;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.Gamestatus;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusInitializing;
import ca.mcgill.ecse223.kingdomino.model.Gameplay.GamestatusOnNextTurn;

import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Insets;

public class GamePage extends JFrame {

	private boolean loadedFrom;
	private JPanel contentPane;
	private static JButton[][] kingdom1 = new JButton[9][9];
	private JButton[][] kingdom2 = new JButton[9][9];
	private JButton[][] kingdom3 = new JButton[9][9];
	private JButton[][] kingdom4 = new JButton[9][9];
	private String datFile = "/Users/amelia/git/ecse223-group-project-19i41/ca.mcgill.ecse223.kingdomino/src/main/resources/alldominoes.dat";

	/**
	 * Launch the application.
	 */
//public void setVisible () {
//	//public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GamePage frame = new GamePage();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GamePage(boolean loadedFrom) {
		Game game111 = KingdominoApplication.getKingdomino().getCurrentGame();
		this.loadedFrom = loadedFrom;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1350, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton("back");
		contentPane.add(btnNewButton);
		btnNewButton.setBounds(30, 20, 120, 30);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
		});
		JButton savegame = new JButton("save");
		contentPane.add(savegame);
		savegame.setBounds(180, 20, 120, 30);
		savegame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * save game feature 
				 * @author 
				 */
				// TODO Auto-generated method stub
				if(saveGame())
					showMsg("Game Saved!");
				else
					showMsg("Cannot save game due to error.");
			}
			
		});
		/**
		 * uniform look for the kingdom 
		 * @author 
		 */
		JButton Kingdom1_0_0 = new JButton("C1");
		Kingdom1_0_0.setFont(new Font("Lucida Grande", Font.PLAIN, 5));
		
		Kingdom1_0_0.setVerticalAlignment(SwingConstants.BOTTOM);
		Kingdom1_0_0.setToolTipText("C1");
		Kingdom1_0_0.setForeground(Color.BLUE);
		Kingdom1_0_0.setBounds(160, 600, 30, 30);
		
		contentPane.add(Kingdom1_0_0);
		Kingdom1_0_0.setBackground(SystemColor.textHighlight);
		Kingdom1_0_0.setBorderPainted(false);
		Kingdom1_0_0.setOpaque(true);
		JButton Kingdom2_0_0 = new JButton("C2");
		Kingdom2_0_0.setBackground(Color.YELLOW);
		Kingdom2_0_0.setBounds(490, 600, 30, 30);
		Kingdom2_0_0.setBorderPainted(false);
		Kingdom2_0_0.setOpaque(true);
		contentPane.add(Kingdom2_0_0);
		//Kingdom2_0_0.setBackground(Color.BLACK);
		JButton Kingdom3_0_0 = new JButton("C3");
		Kingdom3_0_0.setBounds(820, 600, 30, 30);
		Kingdom3_0_0.setBorderPainted(false);
		Kingdom3_0_0.setOpaque(true);
		contentPane.add(Kingdom3_0_0);
		Kingdom3_0_0.setBackground(Color.GREEN);
		JButton Kingdom4_0_0 = new JButton("C4");
		Kingdom4_0_0.setBounds(1150, 600, 30, 30);
		contentPane.add(Kingdom4_0_0);
		Kingdom4_0_0.setBackground(Color.PINK);
		Kingdom4_0_0.setBorderPainted(false);
		Kingdom4_0_0.setOpaque(true);
		kingdom1[4][4]= null;
		JButton Kingdom1_2_0 = new JButton("");
		Kingdom1_2_0.setBounds(220, 600, 30, 30);
		contentPane.add(Kingdom1_2_0);
		kingdom1[6][4] = Kingdom1_2_0;
		
		JButton Kingdom1_1_0 = new JButton("");
		Kingdom1_1_0.setBounds(190, 600, 30, 30);
		contentPane.add(Kingdom1_1_0);
		kingdom1[5][4] = Kingdom1_1_0;
		
		JButton Kingdom1_3_0 = new JButton("");
		Kingdom1_3_0.setBounds(250, 600, 30, 30);
		contentPane.add(Kingdom1_3_0);
		kingdom1[7][4] = Kingdom1_3_0;
		
		JButton Kingdom1_N4_0 = new JButton("");
		Kingdom1_N4_0.setBounds(40, 600, 30, 30);
		contentPane.add(Kingdom1_N4_0);
		kingdom1[0][4] = Kingdom1_N4_0;
		
		JButton Kingdom1_N3_0 = new JButton("");
		Kingdom1_N3_0.setBounds(70, 600, 30, 30);
		contentPane.add(Kingdom1_N3_0);
		kingdom1[1][4] = Kingdom1_N3_0;
		
		JButton Kingdom1_N2_0 = new JButton("");
		Kingdom1_N2_0.setBounds(100, 600, 30, 30);
		contentPane.add(Kingdom1_N2_0);
		kingdom1[2][4] = Kingdom1_N2_0;
		
		JButton Kingdom1_N1_0 = new JButton("");
		Kingdom1_N1_0.setBounds(130, 600, 30, 30);
		contentPane.add(Kingdom1_N1_0);
		kingdom1[3][4] = Kingdom1_N1_0;
		
		JButton Kingdom1_4_0 = new JButton("");
		Kingdom1_4_0.setBounds(280, 600, 30, 30);
		contentPane.add(Kingdom1_4_0);
		kingdom1[8][4] = Kingdom1_4_0;
		
		JButton Kingdom1_0_N1 = new JButton("");
		
		Kingdom1_0_N1.setBounds(160, 630, 30, 30);
		contentPane.add(Kingdom1_0_N1);
		kingdom1[4][3] = Kingdom1_0_N1;
		
		JButton Kingdom1_0_N2 = new JButton("");
		Kingdom1_0_N2.setBounds(160, 660, 30, 30);
		contentPane.add(Kingdom1_0_N2);
		kingdom1[4][2] = Kingdom1_0_N2;
		
		JButton Kingdom1_0_N3 = new JButton("");
		Kingdom1_0_N3.setBounds(160, 690, 30, 30);
		contentPane.add(Kingdom1_0_N3);
		kingdom1[4][1] = Kingdom1_0_N3;
		
		JButton Kingdom1_0_N4 = new JButton("");
		Kingdom1_0_N4.setBounds(160, 720, 30, 30);
		contentPane.add(Kingdom1_0_N4);
		kingdom1[4][0] = Kingdom1_0_N4;
		
		JButton Kingdom1_0_4 = new JButton("");
		Kingdom1_0_4.setBounds(160, 480, 30, 30);
		contentPane.add(Kingdom1_0_4);
		kingdom1[4][8]=Kingdom1_0_4;
		
		JButton Kingdom1_0_3 = new JButton("");
		Kingdom1_0_3.setBounds(160, 510, 30, 30);
		contentPane.add(Kingdom1_0_3);
		kingdom1[4][7] = Kingdom1_0_3;
		
		JButton Kingdom1_0_2 = new JButton("");
		Kingdom1_0_2.setBounds(160, 540, 30, 30);
		contentPane.add(Kingdom1_0_2);
		kingdom1[4][6] = Kingdom1_0_2;
		
		JButton Kingdom1_0_1 = new JButton("");
		Kingdom1_0_1.setBounds(160, 570, 30, 30);
		contentPane.add(Kingdom1_0_1);
		kingdom1[4][5]=Kingdom1_0_1;
		
		JButton Kingdom1_1_4 = new JButton("");
		Kingdom1_1_4.setBounds(190, 480, 30, 30);
		contentPane.add(Kingdom1_1_4);
		kingdom1[5][8] = Kingdom1_1_4;
		
		JButton Kingdom1_1_3 = new JButton("");
		Kingdom1_1_3.setBounds(190, 510, 30, 30);
		contentPane.add(Kingdom1_1_3);
		kingdom1[5][7] = Kingdom1_1_3;
		
		JButton Kingdom1_1_2 = new JButton("");
		Kingdom1_1_2.setBounds(190, 540, 30, 30);
		contentPane.add(Kingdom1_1_2);
		kingdom1[5][6] = Kingdom1_1_2;
		
		JButton Kingdom1_1_1 = new JButton("");
		Kingdom1_1_1.setBounds(190, 570, 30, 30);
		contentPane.add(Kingdom1_1_1);
		kingdom1[5][5] = Kingdom1_1_1;
		
		JButton Kingdom1_2_4 = new JButton("");
		Kingdom1_2_4.setBounds(220, 480, 30, 30);
		contentPane.add(Kingdom1_2_4);
		kingdom1[6][8] = Kingdom1_2_4;
		
		JButton Kingdom1_2_3 = new JButton("");
		Kingdom1_2_3.setBounds(220, 510, 30, 30);
		contentPane.add(Kingdom1_2_3);
		kingdom1[6][7] = Kingdom1_2_3;
		
		JButton Kingdom1_2_2 = new JButton("");
		Kingdom1_2_2.setBounds(220, 540, 30, 30);
		contentPane.add(Kingdom1_2_2);
		kingdom1[6][6] = Kingdom1_2_2;
		
		JButton Kingdom1_2_1 = new JButton("");
		Kingdom1_2_1.setBounds(220, 570, 30, 30);
		contentPane.add(Kingdom1_2_1);
		kingdom1[6][5] = Kingdom1_2_1;
		
		JButton Kingdom1_3_4 = new JButton("");
		Kingdom1_3_4.setBounds(250, 480, 30, 30);
		contentPane.add(Kingdom1_3_4);
		kingdom1[7][8] = Kingdom1_3_4;
		
		JButton Kingdom1_3_3 = new JButton("");
		Kingdom1_3_3.setBounds(250, 510, 30, 30);
		contentPane.add(Kingdom1_3_3);
		kingdom1[7][7] = Kingdom1_3_3;
		
		JButton Kingdom1_3_2 = new JButton("");
		Kingdom1_3_2.setBounds(250, 540, 30, 30);
		contentPane.add(Kingdom1_3_2);
		kingdom1[7][6] = Kingdom1_3_2;
		
		JButton Kingdom1_3_1 = new JButton("");
		Kingdom1_3_1.setBounds(250, 570, 30, 30);
		contentPane.add(Kingdom1_3_1);
		kingdom1[7][5] = Kingdom1_3_1;
		
		JButton Kingdom1_4_4 = new JButton("");
		Kingdom1_4_4.setBounds(280, 480, 30, 30);
		contentPane.add(Kingdom1_4_4);
		kingdom1[8][8] = Kingdom1_4_4;
		
		JButton Kingdom1_4_3 = new JButton("");
		Kingdom1_4_3.setBounds(280, 510, 30, 30);
		contentPane.add(Kingdom1_4_3);
		kingdom1[8][7] = Kingdom1_4_3;
		
		JButton Kingdom1_4_2 = new JButton("");
		Kingdom1_4_2.setBounds(280, 540, 30, 30);
		contentPane.add(Kingdom1_4_2);
		kingdom1[8][6] = Kingdom1_4_2;
		
		JButton Kingdom1_4_1 = new JButton("");
		Kingdom1_4_1.setBounds(280, 570, 30, 30);
		contentPane.add(Kingdom1_4_1);
		kingdom1[8][5] = Kingdom1_4_1;
		
		JButton Kingdom1_N4_4 = new JButton("");
		Kingdom1_N4_4.setBounds(40, 480, 30, 30);
		contentPane.add(Kingdom1_N4_4);
		kingdom1[0][8] = Kingdom1_N4_4;
		
		JButton Kingdom1_N3_4 = new JButton("");
		Kingdom1_N3_4.setBounds(70, 480, 30, 30);
		contentPane.add(Kingdom1_N3_4);
		kingdom1[1][8] = Kingdom1_N3_4;
		
		JButton Kingdom1_N2_4 = new JButton("");
		Kingdom1_N2_4.setBounds(100, 480, 30, 30);
		contentPane.add(Kingdom1_N2_4);
		kingdom1[2][8] = Kingdom1_N2_4;
		
		JButton Kingdom1_N1_4 = new JButton("");
		Kingdom1_N1_4.setBounds(130, 480, 30, 30);
		contentPane.add(Kingdom1_N1_4);
		kingdom1[3][8] = Kingdom1_N1_4;
		
		JButton Kingdom1_N4_3 = new JButton("");
		Kingdom1_N4_3.setBounds(40, 510, 30, 30);
		contentPane.add(Kingdom1_N4_3);
		kingdom1[0][7] = Kingdom1_N4_3;
		
		JButton Kingdom1_N3_3 = new JButton("");
		Kingdom1_N3_3.setBounds(70, 510, 30, 30);
		contentPane.add(Kingdom1_N3_3);
		kingdom1[1][7] = Kingdom1_N3_3;
		
		JButton Kingdom1_N2_3 = new JButton("");
		Kingdom1_N2_3.setBounds(100, 510, 30, 30);
		contentPane.add(Kingdom1_N2_3);
		kingdom1[2][7] = Kingdom1_N2_3;
		
		JButton Kingdom1_N1_3 = new JButton("");
		Kingdom1_N1_3.setBounds(130, 510, 30, 30);
		contentPane.add(Kingdom1_N1_3);
		kingdom1[3][7] = Kingdom1_N1_3;
		
		JButton Kingdom1_N1_2 = new JButton("");
		Kingdom1_N1_2.setBounds(130, 540, 30, 30);
		contentPane.add(Kingdom1_N1_2);
		kingdom1[3][6] = Kingdom1_N1_2;
		
		JButton Kingdom1_N2_2 = new JButton("");
		Kingdom1_N2_2.setBounds(100, 540, 30, 30);
		contentPane.add(Kingdom1_N2_2);
		kingdom1[2][6] = Kingdom1_N2_2;
		
		JButton Kingdom1_N3_2 = new JButton("");
		Kingdom1_N3_2.setBounds(70, 540, 30, 30);
		contentPane.add(Kingdom1_N3_2);
		kingdom1[1][6] = Kingdom1_N3_2;
		
		JButton Kingdom1_N4_2 = new JButton("");
		Kingdom1_N4_2.setBounds(40, 540, 30, 30);
		contentPane.add(Kingdom1_N4_2);
		kingdom1[0][6]=Kingdom1_N4_2;
		
		JButton Kingdom1_N4_1 = new JButton("");
		Kingdom1_N4_1.setBounds(40, 570, 30, 30);
		contentPane.add(Kingdom1_N4_1);
		kingdom1[0][5]=Kingdom1_N4_1;
		
		JButton Kingdom1_N3_1 = new JButton("");
		Kingdom1_N3_1.setBounds(70, 570, 30, 30);
		contentPane.add(Kingdom1_N3_1);
		kingdom1[1][5]=Kingdom1_N3_1;
		
		JButton Kingdom1_N2_1 = new JButton("");
		Kingdom1_N2_1.setBounds(100, 570, 30, 30);
		contentPane.add(Kingdom1_N2_1);
		kingdom1[2][5] = Kingdom1_N2_1;
		
		JButton Kingdom1_N1_1 = new JButton("");
		Kingdom1_N1_1.setBounds(130, 570, 30, 30);
		contentPane.add(Kingdom1_N1_1);
		kingdom1[3][5] = Kingdom1_N1_1;
		
		JButton Kingdom1_N4_N1 = new JButton("");
		Kingdom1_N4_N1.setBounds(40, 630, 30, 30);
		contentPane.add(Kingdom1_N4_N1);
		kingdom1[0][3] =  Kingdom1_N4_N1;
		
		JButton Kingdom1_N3_N1 = new JButton("");
		Kingdom1_N3_N1.setBounds(70, 630, 30, 30);
		contentPane.add(Kingdom1_N3_N1);
		kingdom1[1][3]=Kingdom1_N3_N1;
		
		JButton Kingdom1_N2_N1 = new JButton("");
		Kingdom1_N2_N1.setBounds(100, 630, 30, 30);
		contentPane.add(Kingdom1_N2_N1);
		kingdom1[2][3] = Kingdom1_N2_N1;
		
		JButton Kingdom1_N1_N1 = new JButton("");
		Kingdom1_N1_N1.setBounds(130, 630, 30, 30);
		contentPane.add(Kingdom1_N1_N1);
		kingdom1[3][3] = Kingdom1_N1_N1;
		
		JButton Kingdom1_N4_N2 = new JButton("");
		Kingdom1_N4_N2.setBounds(40, 660, 30, 30);
		contentPane.add(Kingdom1_N4_N2);
		kingdom1[0][2] = Kingdom1_N4_N2;
		
		JButton Kingdom1_N3_N2 = new JButton("");
		Kingdom1_N3_N2.setBounds(70, 660, 30, 30);
		contentPane.add(Kingdom1_N3_N2);
		kingdom1[1][2] = Kingdom1_N3_N2;
		
		JButton Kingdom1_N2_N2 = new JButton("");
		Kingdom1_N2_N2.setBounds(100, 660, 30, 30);
		contentPane.add(Kingdom1_N2_N2);
		kingdom1[2][2]= Kingdom1_N2_N2;
		
		JButton Kingdom1_N1_N2 = new JButton("");
		Kingdom1_N1_N2.setBounds(130, 660, 30, 30);
		contentPane.add(Kingdom1_N1_N2);
		kingdom1[3][2]=Kingdom1_N1_N2;
		
		JButton Kingdom1_N1_N3 = new JButton("");
		Kingdom1_N1_N3.setBounds(130, 690, 30, 30);
		contentPane.add(Kingdom1_N1_N3);
		kingdom1[3][1] = Kingdom1_N1_N3;
		
		JButton Kingdom1_N2_N3 = new JButton("");
		Kingdom1_N2_N3.setBounds(100, 690, 30, 30);
		contentPane.add(Kingdom1_N2_N3);
		kingdom1[2][1] = Kingdom1_N2_N3;
		
		JButton Kingdom1_N3_N3 = new JButton("");
		Kingdom1_N3_N3.setBounds(70, 690, 30, 30);
		contentPane.add(Kingdom1_N3_N3);
		kingdom1[1][1] = Kingdom1_N3_N3;
		
		JButton Kingdom1_N4_N3 = new JButton("");
		Kingdom1_N4_N3.setBounds(40, 690, 30, 30);
		contentPane.add(Kingdom1_N4_N3);
		kingdom1[0][1] = Kingdom1_N4_N3;
		
		JButton Kingdom1_N4_N4 = new JButton("");
		Kingdom1_N4_N4.setBounds(40, 720, 30, 30);
		contentPane.add(Kingdom1_N4_N4);
		kingdom1[0][0] = Kingdom1_N4_N4;
		
		JButton Kingdom1_N3_N4 = new JButton("");
		Kingdom1_N3_N4.setBounds(70, 720, 30, 30);
		contentPane.add(Kingdom1_N3_N4);
		kingdom1[1][0] = Kingdom1_N3_N4;
		
		JButton Kingdom1_N2_N4 = new JButton("");
		Kingdom1_N2_N4.setBounds(100, 720, 30, 30);
		contentPane.add(Kingdom1_N2_N4);
		kingdom1[2][0] = Kingdom1_N2_N4;
		
		JButton Kingdom1_N1_N4 = new JButton("");
		Kingdom1_N1_N4.setBounds(130, 720, 30, 30);
		contentPane.add(Kingdom1_N1_N4);
		kingdom1[3][0] = Kingdom1_N1_N4;
		
		JButton Kingdom1_1_N1 = new JButton("");
		Kingdom1_1_N1.setBounds(190, 630, 30, 30);
		contentPane.add(Kingdom1_1_N1);
		kingdom1[5][3]=Kingdom1_1_N1;
		
		JButton Kingdom1_2_N1 = new JButton("");
		Kingdom1_2_N1.setBounds(220, 630, 30, 30);
		contentPane.add(Kingdom1_2_N1);
		kingdom1[6][3] = Kingdom1_2_N1;
		
		JButton Kingdom1_3_N1 = new JButton("");
		Kingdom1_3_N1.setBounds(250, 630, 30, 30);
		contentPane.add(Kingdom1_3_N1);
		kingdom1[7][3]=Kingdom1_3_N1;
		
		JButton Kingdom1_4_N1 = new JButton("");
		Kingdom1_4_N1.setBounds(280, 630, 30, 30);
		contentPane.add(Kingdom1_4_N1);
		kingdom1[8][3] = Kingdom1_4_N1;
		
		JButton Kingdom1_1_N2 = new JButton("");
		Kingdom1_1_N2.setBounds(190, 660, 30, 30);
		contentPane.add(Kingdom1_1_N2);
		kingdom1[5][2] = Kingdom1_1_N2;
		
		JButton Kingdom1_2_N2 = new JButton("");
		Kingdom1_2_N2.setBounds(220, 660, 30, 30);
		contentPane.add(Kingdom1_2_N2);
		kingdom1[6][2] = Kingdom1_2_N2;
		
		JButton Kingdom1_3_N2 = new JButton("");
		Kingdom1_3_N2.setBounds(250, 660, 30, 30);
		contentPane.add(Kingdom1_3_N2);
		kingdom1[7][2]= Kingdom1_3_N2;
		
		JButton Kingdom1_4_N2 = new JButton("");
		Kingdom1_4_N2.setBounds(280, 660, 30, 30);
		contentPane.add(Kingdom1_4_N2);
		kingdom1[8][2]=Kingdom1_4_N2;
		
		JButton Kingdom1_4_N3 = new JButton("");
		Kingdom1_4_N3.setBounds(280, 690, 30, 30);
		contentPane.add(Kingdom1_4_N3);
		kingdom1[8][1]= Kingdom1_4_N3;
		
		JButton Kingdom1_3_N3 = new JButton("");
		Kingdom1_3_N3.setBounds(250, 690, 30, 30);
		contentPane.add(Kingdom1_3_N3);
		kingdom1[7][1]=Kingdom1_3_N3;
		
		JButton Kingdom1_2_N3 = new JButton("");
		Kingdom1_2_N3.setBounds(220, 690, 30, 30);
		contentPane.add(Kingdom1_2_N3);
		kingdom1[6][1]=Kingdom1_2_N3;
		
		JButton Kingdom1_1_N3 = new JButton("");
		Kingdom1_1_N3.setBounds(190, 690, 30, 30);
		contentPane.add(Kingdom1_1_N3);
		kingdom1[5][1]= Kingdom1_1_N3;
		
		JButton Kingdom1_1_N4 = new JButton("");
		Kingdom1_1_N4.setBounds(190, 720, 30, 30);
		contentPane.add(Kingdom1_1_N4);
		kingdom1[5][0]=Kingdom1_1_N4;
		
		JButton Kingdom1_2_N4 = new JButton("");
		Kingdom1_2_N4.setBounds(220, 720, 30, 30);
		contentPane.add(Kingdom1_2_N4);
		kingdom1[6][0]=Kingdom1_2_N4;
		
		JButton Kingdom1_3_N4 = new JButton("");
		Kingdom1_3_N4.setBounds(250, 720, 30, 30);
		contentPane.add(Kingdom1_3_N4);
		kingdom1[7][0]=Kingdom1_3_N4;
		
		JButton Kingdom1_4_N4 = new JButton("");
		Kingdom1_4_N4.setBounds(280, 720, 30, 30);
		contentPane.add(Kingdom1_4_N4);
		kingdom1[8][0]=Kingdom1_4_N4;











		
		// second player kingdom 
		JButton Kingdom2_N4_4 = new JButton("");
		Kingdom2_N4_4.setBounds(370, 480, 30, 30);
		contentPane.add(Kingdom2_N4_4);
		
		JButton Kingdom2_N3_4 = new JButton("");
		Kingdom2_N3_4.setBounds(400, 480, 30, 30);
		contentPane.add(Kingdom2_N3_4);
		
		JButton Kingdom2_N2_4 = new JButton("");
		Kingdom2_N2_4.setBounds(430, 480, 30, 30);
		contentPane.add(Kingdom2_N2_4);
		
		JButton Kingdom2_N1_4 = new JButton("");
		Kingdom2_N1_4.setBounds(460, 480, 30, 30);
		contentPane.add(Kingdom2_N1_4);
		
		JButton Kingdom2_0_4 = new JButton("");
		Kingdom2_0_4.setBounds(490, 480, 30, 30);
		contentPane.add(Kingdom2_0_4);
		
		JButton Kingdom2_1_4 = new JButton("");
		Kingdom2_1_4.setBounds(520, 480, 30, 30);
		contentPane.add(Kingdom2_1_4);
		
		JButton Kingdom2_2_4 = new JButton("");
		Kingdom2_2_4.setBounds(550, 480, 30, 30);
		contentPane.add(Kingdom2_2_4);
		
		JButton Kingdom2_3_4 = new JButton("");
		Kingdom2_3_4.setBounds(580, 480, 30, 30);
		contentPane.add(Kingdom2_3_4);
		
		JButton Kingdom2_4_4 = new JButton("");
		Kingdom2_4_4.setBounds(610, 480, 30, 30);
		contentPane.add(Kingdom2_4_4);
		
		JButton Kingdom2_N3_3 = new JButton("");
		Kingdom2_N3_3.setBounds(400, 510, 30, 30);
		contentPane.add(Kingdom2_N3_3);
		
		JButton Kingdom2_N4_3 = new JButton("");
		Kingdom2_N4_3.setBounds(370, 510, 30, 30);
		contentPane.add(Kingdom2_N4_3);
		
		JButton Kingdom2_N2_3 = new JButton("");
		Kingdom2_N2_3.setBounds(430, 510, 30, 30);
		contentPane.add(Kingdom2_N2_3);
		
		JButton Kingdom2_N1_3 = new JButton("");
		Kingdom2_N1_3.setBounds(460, 510, 30, 30);
		contentPane.add(Kingdom2_N1_3);
		
		JButton Kingdom2_0_3 = new JButton("");
		Kingdom2_0_3.setBounds(490, 510, 30, 30);
		contentPane.add(Kingdom2_0_3);
		
		JButton Kingdom2_1_3 = new JButton("");
		Kingdom2_1_3.setBounds(520, 510, 30, 30);
		contentPane.add(Kingdom2_1_3);
		
		JButton Kingdom2_2_3 = new JButton("");
		Kingdom2_2_3.setBounds(550, 510, 30, 30);
		contentPane.add(Kingdom2_2_3);
		
		JButton Kingdom2_3_3 = new JButton("");
		Kingdom2_3_3.setBounds(580, 510, 30, 30);
		contentPane.add(Kingdom2_3_3);
		
		JButton Kingdom2_4_3 = new JButton("");
		Kingdom2_4_3.setBounds(610, 510, 30, 30);
		contentPane.add(Kingdom2_4_3);
		
		JButton Kingdom2_N4_2 = new JButton("");
		Kingdom2_N4_2.setBounds(370, 540, 30, 30);
		contentPane.add(Kingdom2_N4_2);
		
		JButton Kingdom2_N3_2 = new JButton("");
		Kingdom2_N3_2.setBounds(400, 540, 30, 30);
		contentPane.add(Kingdom2_N3_2);
		
		JButton Kingdom2_N2_2 = new JButton("");
		Kingdom2_N2_2.setBounds(430, 540, 30, 30);
		contentPane.add(Kingdom2_N2_2);
		
		JButton Kingdom2_N1_2 = new JButton("");
		Kingdom2_N1_2.setBounds(460, 540, 30, 30);
		contentPane.add(Kingdom2_N1_2);
		
		JButton Kingdom2_0_2 = new JButton("");
		Kingdom2_0_2.setBounds(490, 540, 30, 30);
		contentPane.add(Kingdom2_0_2);
		
		JButton Kingdom2_1_2 = new JButton("");
		Kingdom2_1_2.setBounds(520, 540, 30, 30);
		contentPane.add(Kingdom2_1_2);
		
		JButton Kingdom2_2_2 = new JButton("");
		Kingdom2_2_2.setBounds(550, 540, 30, 30);
		contentPane.add(Kingdom2_2_2);
		
		JButton Kingdom2_3_2 = new JButton("");
		Kingdom2_3_2.setBounds(580, 540, 30, 30);
		contentPane.add(Kingdom2_3_2);
		
		JButton Kingdom2_4_2 = new JButton("");
		Kingdom2_4_2.setBounds(610, 540, 30, 30);
		contentPane.add(Kingdom2_4_2);
		
		JButton Kingdom2_N4_1 = new JButton("");
		Kingdom2_N4_1.setBounds(370, 570, 30, 30);
		contentPane.add(Kingdom2_N4_1);
		
		JButton Kingdom2_N3_1 = new JButton("");
		Kingdom2_N3_1.setBounds(400, 570, 30, 30);
		contentPane.add(Kingdom2_N3_1);
		
		JButton Kingdom2_N2_1 = new JButton("");
		Kingdom2_N2_1.setBounds(430, 570, 30, 30);
		contentPane.add(Kingdom2_N2_1);
		
		JButton Kingdom2_N1_1 = new JButton("");
		Kingdom2_N1_1.setBounds(460, 570, 30, 30);
		contentPane.add(Kingdom2_N1_1);
		
		JButton Kingdom2_0_1 = new JButton("");
		Kingdom2_0_1.setBounds(490, 570, 30, 30);
		contentPane.add(Kingdom2_0_1);
		
		JButton Kingdom2_1_1 = new JButton("");
		Kingdom2_1_1.setBounds(520, 570, 30, 30);
		contentPane.add(Kingdom2_1_1);
		
		JButton Kingdom2_2_1 = new JButton("");
		Kingdom2_2_1.setBounds(550, 570, 30, 30);
		contentPane.add(Kingdom2_2_1);
		
		JButton Kingdom2_3_1 = new JButton("");
		Kingdom2_3_1.setBounds(580, 570, 30, 30);
		contentPane.add(Kingdom2_3_1);
		
		JButton Kingdom2_4_1 = new JButton("");
		Kingdom2_4_1.setBounds(610, 570, 30, 30);
		contentPane.add(Kingdom2_4_1);
		
		JButton Kingdom2_N4_0 = new JButton("");
		Kingdom2_N4_0.setBounds(370, 600, 30, 30);
		contentPane.add(Kingdom2_N4_0);
		
		JButton Kingdom2_N3_0 = new JButton("");
		Kingdom2_N3_0.setBounds(400, 600, 30, 30);
		contentPane.add(Kingdom2_N3_0);
		
		JButton Kingdom2_N2_0 = new JButton("");
		Kingdom2_N2_0.setBounds(430, 600, 30, 30);
		contentPane.add(Kingdom2_N2_0);
		
		JButton Kingdom2_N1_0 = new JButton("");
		Kingdom2_N1_0.setBounds(460, 600, 30, 30);
		contentPane.add(Kingdom2_N1_0);
		
		JButton Kingdom2_1_0 = new JButton("");
		Kingdom2_1_0.setBounds(520, 600, 30, 30);
		contentPane.add(Kingdom2_1_0);
		
		JButton Kingdom2_2_0 = new JButton("");
		Kingdom2_2_0.setBounds(550, 600, 30, 30);
		contentPane.add(Kingdom2_2_0);
		
		JButton Kingdom2_3_0 = new JButton("");
		Kingdom2_3_0.setBounds(580, 600, 30, 30);
		contentPane.add(Kingdom2_3_0);
		
		JButton Kingdom2_4_0 = new JButton("");
		Kingdom2_4_0.setBounds(610, 600, 30, 30);
		contentPane.add(Kingdom2_4_0);
		
		JButton Kingdom2_N4_N1 = new JButton("");
		Kingdom2_N4_N1.setBounds(370, 630, 30, 30);
		contentPane.add(Kingdom2_N4_N1);
		
		JButton Kingdom2_N3_N1 = new JButton("");
		Kingdom2_N3_N1.setBounds(400, 630, 30, 30);
		contentPane.add(Kingdom2_N3_N1);
		
		JButton Kingdom2_N2_N1 = new JButton("");
		Kingdom2_N2_N1.setBounds(430, 630, 30, 30);
		contentPane.add(Kingdom2_N2_N1);
		
		JButton Kingdom2_N1_N1 = new JButton("");
		Kingdom2_N1_N1.setBounds(460, 630, 30, 30);
		contentPane.add(Kingdom2_N1_N1);
		
		JButton Kingdom2_0_N1 = new JButton("");
		Kingdom2_0_N1.setBounds(490, 630, 30, 30);
		contentPane.add(Kingdom2_0_N1);
		
		JButton Kingdom2_1_N1 = new JButton("");
		Kingdom2_1_N1.setBounds(520, 630, 30, 30);
		contentPane.add(Kingdom2_1_N1);
		
		JButton Kingdom2_2_N1 = new JButton("");
		Kingdom2_2_N1.setBounds(550, 630, 30, 30);
		contentPane.add(Kingdom2_2_N1);
		
		JButton Kingdom2_3_N1 = new JButton("");
		Kingdom2_3_N1.setBounds(580, 630, 30, 30);
		contentPane.add(Kingdom2_3_N1);
		
		JButton Kingdom2_4_N1 = new JButton("");
		Kingdom2_4_N1.setBounds(610, 630, 30, 30);
		contentPane.add(Kingdom2_4_N1);
		
		JButton Kingdom2_N4_N2 = new JButton("");
		Kingdom2_N4_N2.setBounds(370, 660, 30, 30);
		contentPane.add(Kingdom2_N4_N2);
		
		JButton Kingdom2_N3_N2 = new JButton("");
		Kingdom2_N3_N2.setBounds(400, 660, 30, 30);
		contentPane.add(Kingdom2_N3_N2);
		
		JButton Kingdom2_N2_N2 = new JButton("");
		Kingdom2_N2_N2.setBounds(430, 660, 30, 30);
		contentPane.add(Kingdom2_N2_N2);
		
		JButton Kingdom2_N1_N2 = new JButton("");
		Kingdom2_N1_N2.setBounds(460, 660, 30, 30);
		contentPane.add(Kingdom2_N1_N2);
		
		JButton Kingdom2_0_N2 = new JButton("");
		Kingdom2_0_N2.setBounds(490, 660, 30, 30);
		contentPane.add(Kingdom2_0_N2);
		
		JButton Kingdom2_1_N2 = new JButton("");
		Kingdom2_1_N2.setBounds(520, 660, 30, 30);
		contentPane.add(Kingdom2_1_N2);
		
		JButton Kingdom2_2_N2 = new JButton("");
		Kingdom2_2_N2.setBounds(550, 660, 30, 30);
		contentPane.add(Kingdom2_2_N2);
		
		JButton Kingdom2_3_N2 = new JButton("");
		Kingdom2_3_N2.setBounds(580, 660, 30, 30);
		contentPane.add(Kingdom2_3_N2);
		
		JButton Kingdom2_4_N2 = new JButton("");
		Kingdom2_4_N2.setBounds(610, 660, 30, 30);
		contentPane.add(Kingdom2_4_N2);
		
		JButton Kingdom2_N4_N3 = new JButton("");
		Kingdom2_N4_N3.setBounds(370, 690, 30, 30);
		contentPane.add(Kingdom2_N4_N3);
		
		JButton Kingdom2_N3_N3 = new JButton("");
		Kingdom2_N3_N3.setBounds(400, 690, 30, 30);
		contentPane.add(Kingdom2_N3_N3);
		
		JButton Kingdom2_N2_N3 = new JButton("");
		Kingdom2_N2_N3.setBounds(430, 690, 30, 30);
		contentPane.add(Kingdom2_N2_N3);
		
		JButton Kingdom2_N1_N3 = new JButton("");
		Kingdom2_N1_N3.setBounds(460, 690, 30, 30);
		contentPane.add(Kingdom2_N1_N3);
		
		JButton Kingdom2_0_N3 = new JButton("");
		Kingdom2_0_N3.setBounds(490, 690, 30, 30);
		contentPane.add(Kingdom2_0_N3);
		
		JButton Kingdom2_1_N3 = new JButton("");
		Kingdom2_1_N3.setBounds(520, 690, 30, 30);
		contentPane.add(Kingdom2_1_N3);
		
		JButton Kingdom2_2_N3 = new JButton("");
		Kingdom2_2_N3.setBounds(550, 690, 30, 30);
		contentPane.add(Kingdom2_2_N3);
		
		JButton Kingdom2_3_N3 = new JButton("");
		Kingdom2_3_N3.setBounds(580, 690, 30, 30);
		contentPane.add(Kingdom2_3_N3);
		
		JButton Kingdom2_4_N3 = new JButton("");
		Kingdom2_4_N3.setBounds(610, 690, 30, 30);
		contentPane.add(Kingdom2_4_N3);
		
		JButton Kingdom2_N4_N4 = new JButton("");
		Kingdom2_N4_N4.setBounds(370, 720, 30, 30);
		contentPane.add(Kingdom2_N4_N4);
		
		JButton Kingdom2_N3_N4 = new JButton("");
		Kingdom2_N3_N4.setBounds(400, 720, 30, 30);
		contentPane.add(Kingdom2_N3_N4);
		
		JButton Kingdom2_N2_N4 = new JButton("");
		Kingdom2_N2_N4.setBounds(430, 720, 30, 30);
		contentPane.add(Kingdom2_N2_N4);
		
		JButton Kingdom2_N1_N4 = new JButton("");
		Kingdom2_N1_N4.setBounds(460, 720, 30, 30);
		contentPane.add(Kingdom2_N1_N4);
		
		JButton Kingdom2_0_N4 = new JButton("");
		Kingdom2_0_N4.setBounds(490, 720, 30, 30);
		contentPane.add(Kingdom2_0_N4);
		
		JButton Kingdom2_1_N4 = new JButton("");
		Kingdom2_1_N4.setBounds(520, 720, 30, 30);
		contentPane.add(Kingdom2_1_N4);
		
		JButton Kingdom2_2_N4 = new JButton("");
		Kingdom2_2_N4.setBounds(550, 720, 30, 30);
		contentPane.add(Kingdom2_2_N4);
		
		JButton Kingdom2_3_N4 = new JButton("");
		Kingdom2_3_N4.setBounds(580, 720, 30, 30);
		contentPane.add(Kingdom2_3_N4);
		
		JButton Kingdom2_4_N4 = new JButton("");
		Kingdom2_4_N4.setBounds(610, 720, 30, 30);
		contentPane.add(Kingdom2_4_N4);
		
		kingdom2[0][0] = Kingdom2_N4_N4 ;
		kingdom2[0][1] = Kingdom2_N4_N3;
		kingdom2[0][2] = Kingdom2_N4_N2;
		kingdom2[0][3] = Kingdom2_N4_N1;
		kingdom2[0][4] = Kingdom2_N4_0;
		kingdom2[0][5] = Kingdom2_N4_1;
		kingdom2[0][6] = Kingdom2_N4_2;
		kingdom2[0][7] = Kingdom2_N4_3;
		kingdom2[0][8] = Kingdom2_N4_4;
		
		kingdom2[1][0] = Kingdom2_N3_N4;
		kingdom2[1][1] = Kingdom2_N3_N3;
		kingdom2[1][2] = Kingdom2_N3_N2;
		kingdom2[1][3] = Kingdom2_N3_N1;
		kingdom2[1][4] = Kingdom2_N3_0;
		kingdom2[1][5] = Kingdom2_N3_1;
		kingdom2[1][6] = Kingdom2_N3_2;
		kingdom2[1][7] = Kingdom2_N3_3;
		kingdom2[1][8] = Kingdom2_N3_4; 
		
		kingdom2[2][0] = Kingdom2_N2_N4;
		kingdom2[2][1] = Kingdom2_N2_N3;
		kingdom2[2][2] = Kingdom2_N2_N2;
		kingdom2[2][3] = Kingdom2_N2_N1;
		kingdom2[2][4] = Kingdom2_N2_0;
		kingdom2[2][5] = Kingdom2_N2_1;
		kingdom2[2][6] = Kingdom2_N2_2;
		kingdom2[2][7] = Kingdom2_N2_3;
		kingdom2[2][8] = Kingdom2_N2_4;
		
		kingdom2[3][0] = Kingdom2_N1_N4;
		kingdom2[3][1] = Kingdom2_N1_N3;
		kingdom2[3][2] = Kingdom2_N1_N2;
		kingdom2[3][3] = Kingdom2_N1_N1;
		kingdom2[3][4] = Kingdom2_N1_0;
		kingdom2[3][5] = Kingdom2_N1_1;
		kingdom2[3][6] = Kingdom2_N1_2;
		kingdom2[3][7] = Kingdom2_N1_3;
		kingdom2[3][8] = Kingdom2_N1_4;
		
		kingdom2[4][0] = Kingdom2_0_N4;
		kingdom2[4][1] = Kingdom2_0_N3;
		kingdom2[4][2] = Kingdom2_0_N2;
		kingdom2[4][3] = Kingdom2_0_N1;
		kingdom2[4][4] = null;
		kingdom2[4][5] = Kingdom2_0_1;
		kingdom2[4][6] = Kingdom2_0_2;
		kingdom2[4][7] = Kingdom2_0_3;
		kingdom2[4][8] = Kingdom2_0_4;
		
		kingdom2[5][0] = Kingdom2_1_N4;
		kingdom2[5][1] = Kingdom2_1_N3;
		kingdom2[5][2] = Kingdom2_1_N2;
		kingdom2[5][3] = Kingdom2_1_N1;
		kingdom2[5][4] = Kingdom2_1_0;
		kingdom2[5][5] = Kingdom2_1_1;
		kingdom2[5][6] = Kingdom2_1_2;
		kingdom2[5][7] = Kingdom2_1_3;
		kingdom2[5][8] = Kingdom2_1_4;
		
		kingdom2[6][0] = Kingdom2_2_N4;
		kingdom2[6][1] = Kingdom2_2_N3;
		kingdom2[6][2] = Kingdom2_2_N2;
		kingdom2[6][3] = Kingdom2_2_N1;
		kingdom2[6][4] = Kingdom2_2_0;
		kingdom2[6][5] = Kingdom2_2_1;
		kingdom2[6][6] = Kingdom2_2_2;
		kingdom2[6][7] = Kingdom2_2_3;
		kingdom2[6][8] = Kingdom2_2_4;
		
		kingdom2[7][0] = Kingdom2_3_N4;
		kingdom2[7][1] = Kingdom2_3_N3;
		kingdom2[7][2] = Kingdom2_3_N2;
		kingdom2[7][3] = Kingdom2_3_N1;
		kingdom2[7][4] = Kingdom2_3_0;
		kingdom2[7][5] = Kingdom2_3_1;
		kingdom2[7][6] = Kingdom2_3_2;
		kingdom2[7][7] = Kingdom2_3_3;
		kingdom2[7][8] = Kingdom2_3_4;
		
		kingdom2[8][0] = Kingdom2_4_N4;
		kingdom2[8][1] = Kingdom2_4_N3;
		kingdom2[8][2] = Kingdom2_4_N2;
		kingdom2[8][3] = Kingdom2_4_N1;
		kingdom2[8][4] = Kingdom2_4_0;
		kingdom2[8][5] = Kingdom2_4_1;
		kingdom2[8][6] = Kingdom2_4_2;
		kingdom2[8][7] = Kingdom2_4_3;
		kingdom2[8][8] = Kingdom2_4_4;
		
	// third kingdom 
		JButton Kingdom3_N4_4 = new JButton("");
		Kingdom3_N4_4.setBounds(700, 480, 30, 30);
		contentPane.add(Kingdom3_N4_4);
		
		JButton Kingdom3_N3_4 = new JButton("");
		Kingdom3_N3_4.setBounds(730, 480, 30, 30);
		contentPane.add(Kingdom3_N3_4);
		
		JButton Kingdom3_N2_4 = new JButton("");
		Kingdom3_N2_4.setBounds(760, 480, 30, 30);
		contentPane.add(Kingdom3_N2_4);
		
		JButton Kingdom3_N1_4 = new JButton("");
		Kingdom3_N1_4.setBounds(790, 480, 30, 30);
		contentPane.add(Kingdom3_N1_4);
		
		JButton Kingdom3_0_4 = new JButton("");
		Kingdom3_0_4.setBounds(820, 480, 30, 30);
		contentPane.add(Kingdom3_0_4);
		
		JButton Kingdom3_1_4 = new JButton("");
		Kingdom3_1_4.setBounds(850, 480, 30, 30);
		contentPane.add(Kingdom3_1_4);
		
		JButton Kingdom3_2_4 = new JButton("");
		Kingdom3_2_4.setBounds(880, 480, 30, 30);
		contentPane.add(Kingdom3_2_4);
		
		JButton Kingdom3_3_4 = new JButton("");
		Kingdom3_3_4.setBounds(910, 480, 30, 30);
		contentPane.add(Kingdom3_3_4);
		
		JButton Kingdom3_4_4 = new JButton("");
		Kingdom3_4_4.setBounds(940, 480, 30, 30);
		contentPane.add(Kingdom3_4_4);
		
		JButton Kingdom4_N4_4 = new JButton("");
		Kingdom4_N4_4.setBounds(1030, 480, 30, 30);
		contentPane.add(Kingdom4_N4_4);
		
		JButton Kingdom4_N3_4 = new JButton("");
		Kingdom4_N3_4.setBounds(1060, 480, 30, 30);
		contentPane.add(Kingdom4_N3_4);
		
		JButton Kingdom4_N2_4 = new JButton("");
		Kingdom4_N2_4.setBounds(1090, 480, 30, 30);
		contentPane.add(Kingdom4_N2_4);
		
		JButton Kingdom4_N1_4 = new JButton("");
		Kingdom4_N1_4.setBounds(1120, 480, 30, 30);
		contentPane.add(Kingdom4_N1_4);
		
		JButton Kingdom4_0_4 = new JButton("");
		Kingdom4_0_4.setBounds(1150, 480, 30, 30);
		contentPane.add(Kingdom4_0_4);
		
		JButton Kingdom4_1_4 = new JButton("");
		Kingdom4_1_4.setBounds(1180, 480, 30, 30);
		contentPane.add(Kingdom4_1_4);
		
		JButton Kingdom4_2_4 = new JButton("");
		Kingdom4_2_4.setBounds(1210, 480, 30, 30);
		contentPane.add(Kingdom4_2_4);
		
		JButton Kingdom4_3_4 = new JButton("");
		Kingdom4_3_4.setBounds(1240, 480, 30, 30);
		contentPane.add(Kingdom4_3_4);
		
		JButton Kingdom4_4_4 = new JButton("");
		Kingdom4_4_4.setBounds(1270, 480, 30, 30);
		contentPane.add(Kingdom4_4_4);
		
		JButton Kingdom3_N4_3 = new JButton("");
		Kingdom3_N4_3.setBounds(700, 510, 30, 30);
		contentPane.add(Kingdom3_N4_3);
		
		JButton Kingdom3_N3_3 = new JButton("");
		Kingdom3_N3_3.setBounds(730, 510, 30, 30);
		contentPane.add(Kingdom3_N3_3);
		
		JButton Kingdom3_N2_3 = new JButton("");
		Kingdom3_N2_3.setBounds(760, 510, 30, 30);
		contentPane.add(Kingdom3_N2_3);
		
		JButton Kingdom3_N1_3 = new JButton("");
		Kingdom3_N1_3.setBounds(790, 510, 30, 30);
		contentPane.add(Kingdom3_N1_3);
		
		JButton Kingdom3_0_3 = new JButton("");
		Kingdom3_0_3.setBounds(820, 510, 30, 30);
		contentPane.add(Kingdom3_0_3);
		
		JButton Kingdom3_1_3 = new JButton("");
		Kingdom3_1_3.setBounds(850, 510, 30, 30);
		contentPane.add(Kingdom3_1_3);
		
		JButton Kingdom3_2_3 = new JButton("");
		Kingdom3_2_3.setBounds(880, 510, 30, 30);
		contentPane.add(Kingdom3_2_3);
		
		JButton Kingdom3_3_3 = new JButton("");
		Kingdom3_3_3.setBounds(910, 510, 30, 30);
		contentPane.add(Kingdom3_3_3);
		
		JButton Kingdom3_4_3 = new JButton("");
		Kingdom3_4_3.setBounds(940, 510, 30, 30);
		contentPane.add(Kingdom3_4_3);
		
		
		
		
		
		
		/////////////////
		//forth kingdom//
		/////////////////
		
		
		
		JButton Kingdom3_N4_2 = new JButton("");
		Kingdom3_N4_2.setBounds(700, 540, 30, 30);
		contentPane.add(Kingdom3_N4_2);
		
		JButton Kingdom3_N3_2 = new JButton("");
		Kingdom3_N3_2.setBounds(730, 540, 30, 30);
		contentPane.add(Kingdom3_N3_2);
		
		JButton Kingdom3_N2_2 = new JButton("");
		Kingdom3_N2_2.setBounds(760, 540, 30, 30);
		contentPane.add(Kingdom3_N2_2);
		
		JButton Kingdom3_N1_2 = new JButton("");
		Kingdom3_N1_2.setBounds(790, 540, 30, 30);
		contentPane.add(Kingdom3_N1_2);
		
		JButton Kingdom3_0_2 = new JButton("");
		Kingdom3_0_2.setBounds(820, 540, 30, 30);
		contentPane.add(Kingdom3_0_2);
		
		JButton Kingdom3_1_2 = new JButton("");
		Kingdom3_1_2.setBounds(850, 540, 30, 30);
		contentPane.add(Kingdom3_1_2);
		
		JButton Kingdom3_2_2 = new JButton("");
		Kingdom3_2_2.setBounds(880, 540, 30, 30);
		contentPane.add(Kingdom3_2_2);
		
		JButton Kingdom3_3_2 = new JButton("");
		Kingdom3_3_2.setBounds(910, 540, 30, 30);
		contentPane.add(Kingdom3_3_2);
		
		JButton Kingdom3_4_2 = new JButton("");
		Kingdom3_4_2.setBounds(940, 540, 30, 30);
		contentPane.add(Kingdom3_4_2);
		
		
		JButton Kingdom3_N4_1 = new JButton("");
		Kingdom3_N4_1.setBounds(700, 570, 30, 30);
		contentPane.add(Kingdom3_N4_1);
		
		JButton Kingdom3_N3_1 = new JButton("");
		Kingdom3_N3_1.setBounds(730, 570, 30, 30);
		contentPane.add(Kingdom3_N3_1);
		
		JButton Kingdom3_N2_1 = new JButton("");
		Kingdom3_N2_1.setBounds(760, 570, 30, 30);
		contentPane.add(Kingdom3_N2_1);
		
		JButton Kingdom3_N1_1 = new JButton("");
		Kingdom3_N1_1.setBounds(790, 570, 30, 30);
		contentPane.add(Kingdom3_N1_1);
		
		JButton Kingdom3_0_1 = new JButton("");
		Kingdom3_0_1.setBounds(820, 570, 30, 30);
		contentPane.add(Kingdom3_0_1);
		
		JButton Kingdom3_1_1 = new JButton("");
		Kingdom3_1_1.setBounds(850, 570, 30, 30);
		contentPane.add(Kingdom3_1_1);
		
		JButton Kingdom3_2_1 = new JButton("");
		Kingdom3_2_1.setBounds(880, 570, 30, 30);
		contentPane.add(Kingdom3_2_1);
		
		JButton Kingdom3_3_1 = new JButton("");
		Kingdom3_3_1.setBounds(910, 570, 30, 30);
		contentPane.add(Kingdom3_3_1);
		
		JButton Kingdom3_4_1 = new JButton("");
		Kingdom3_4_1.setBounds(940, 570, 30, 30);
		contentPane.add(Kingdom3_4_1);
		
		
		
		JButton Kingdom3_N3_0 = new JButton("");
		Kingdom3_N3_0.setBounds(730, 600, 30, 30);
		contentPane.add(Kingdom3_N3_0);
		
		JButton Kingdom3_N2_0 = new JButton("");
		Kingdom3_N2_0.setBounds(760, 600, 30, 30);
		contentPane.add(Kingdom3_N2_0);
		
		JButton Kingdom3_N4_0 = new JButton("");
		Kingdom3_N4_0.setBounds(700, 600, 30, 30);
		contentPane.add(Kingdom3_N4_0);
		
		JButton Kingdom3_N1_0 = new JButton("");
		Kingdom3_N1_0.setBounds(790, 600, 30, 30);
		contentPane.add(Kingdom3_N1_0);
		
		JButton Kingdom3_1_0 = new JButton("");
		Kingdom3_1_0.setBounds(850, 600, 30, 30);
		contentPane.add(Kingdom3_1_0);
		
		JButton Kingdom3_2_0 = new JButton("");
		Kingdom3_2_0.setBounds(880, 600, 30, 30);
		contentPane.add(Kingdom3_2_0);
		
		JButton Kingdom3_3_0 = new JButton("");
		Kingdom3_3_0.setBounds(910, 600, 30, 30);
		contentPane.add(Kingdom3_3_0);
		
		JButton Kingdom3_4_0 = new JButton("");
		Kingdom3_4_0.setBounds(940, 600, 30, 30);
		contentPane.add(Kingdom3_4_0);
		
		
		JButton Kingdom3_N4_N1 = new JButton("");
		Kingdom3_N4_N1.setBounds(700, 630, 30, 30);
		contentPane.add(Kingdom3_N4_N1);
		
		JButton Kingdom3_N3_N1 = new JButton("");
		Kingdom3_N3_N1.setBounds(730, 630, 30, 30);
		contentPane.add(Kingdom3_N3_N1);
		
		JButton Kingdom3_N2_N1 = new JButton("");
		Kingdom3_N2_N1.setBounds(760, 630, 30, 30);
		contentPane.add(Kingdom3_N2_N1);
		
		JButton Kingdom3_N1_N1 = new JButton("");
		Kingdom3_N1_N1.setBounds(790, 630, 30, 30);
		contentPane.add(Kingdom3_N1_N1);
		
		JButton Kingdom3_0_N1 = new JButton("");
		Kingdom3_0_N1.setBounds(820, 630, 30, 30);
		contentPane.add(Kingdom3_0_N1);
		
		JButton Kingdom3_1_N1 = new JButton("");
		Kingdom3_1_N1.setBounds(850, 630, 30, 30);
		contentPane.add(Kingdom3_1_N1);
		
		JButton Kingdom3_2_N1 = new JButton("");
		Kingdom3_2_N1.setBounds(880, 630, 30, 30);
		contentPane.add(Kingdom3_2_N1);
		
		JButton Kingdom3_3_N1 = new JButton("");
		Kingdom3_3_N1.setBounds(910, 630, 30, 30);
		contentPane.add(Kingdom3_3_N1);
		
		JButton Kingdom3_4_N1 = new JButton("");
		Kingdom3_4_N1.setBounds(940, 630, 30, 30);
		contentPane.add(Kingdom3_4_N1);
		
		
		JButton Kingdom3_N4_N2 = new JButton("");
		Kingdom3_N4_N2.setBounds(700, 660, 30, 30);
		contentPane.add(Kingdom3_N4_N2);
		
		JButton Kingdom3_N3_N2 = new JButton("");
		Kingdom3_N3_N2.setBounds(730, 660, 30, 30);
		contentPane.add(Kingdom3_N3_N2);
		
		JButton Kingdom3_N2_N2 = new JButton("");
		Kingdom3_N2_N2.setBounds(760, 660, 30, 30);
		contentPane.add(Kingdom3_N2_N2);
		
		JButton Kingdom3_N1_N2 = new JButton("");
		Kingdom3_N1_N2.setBounds(790, 660, 30, 30);
		contentPane.add(Kingdom3_N1_N2);
		
		JButton Kingdom3_0_N2 = new JButton("");
		Kingdom3_0_N2.setBounds(820, 660, 30, 30);
		contentPane.add(Kingdom3_0_N2);
		
		JButton Kingdom3_1_N2 = new JButton("");
		Kingdom3_1_N2.setBounds(850, 660, 30, 30);
		contentPane.add(Kingdom3_1_N2);
		
		JButton Kingdom3_2_N2 = new JButton("");
		Kingdom3_2_N2.setBounds(880, 660, 30, 30);
		contentPane.add(Kingdom3_2_N2);
		
		JButton Kingdom3_3_N2 = new JButton("");
		Kingdom3_3_N2.setBounds(910, 660, 30, 30);
		contentPane.add(Kingdom3_3_N2);
		
		JButton Kingdom3_4_N2 = new JButton("");
		Kingdom3_4_N2.setBounds(940, 660, 30, 30);
		contentPane.add(Kingdom3_4_N2);
		
		
		JButton Kingdom3_N4_N3 = new JButton("");
		Kingdom3_N4_N3.setBounds(700, 690, 30, 30);
		contentPane.add(Kingdom3_N4_N3);
		
		JButton Kingdom3_N3_N3 = new JButton("");
		Kingdom3_N3_N3.setBounds(730, 690, 30, 30);
		contentPane.add(Kingdom3_N3_N3);
		
		JButton Kingdom3_N2_N3 = new JButton("");
		Kingdom3_N2_N3.setBounds(760, 690, 30, 30);
		contentPane.add(Kingdom3_N2_N3);
		
		JButton Kingdom3_N1_N3 = new JButton("");
		Kingdom3_N1_N3.setBounds(790, 690, 30, 30);
		contentPane.add(Kingdom3_N1_N3);
		
		JButton Kingdom3_0_N3 = new JButton("");
		Kingdom3_0_N3.setBounds(820, 690, 30, 30);
		contentPane.add(Kingdom3_0_N3);
		
		JButton Kingdom3_1_N3 = new JButton("");
		Kingdom3_1_N3.setBounds(850, 690, 30, 30);
		contentPane.add(Kingdom3_1_N3);
		
		JButton Kingdom3_2_N3 = new JButton("");
		Kingdom3_2_N3.setBounds(880, 690, 30, 30);
		contentPane.add(Kingdom3_2_N3);
		
		JButton Kingdom3_3_N3 = new JButton("");
		Kingdom3_3_N3.setBounds(910, 690, 30, 30);
		contentPane.add(Kingdom3_3_N3);
		
		JButton Kingdom3_4_N3 = new JButton("");
		Kingdom3_4_N3.setBounds(940, 690, 30, 30);
		contentPane.add(Kingdom3_4_N3);
		
		
		
		JButton Kingdom3_N4_N4 = new JButton("");
		Kingdom3_N4_N4.setBounds(700, 720, 30, 30);
		contentPane.add(Kingdom3_N4_N4);
		
		JButton Kingdom3_N3_N4 = new JButton("");
		Kingdom3_N3_N4.setBounds(730, 720, 30, 30);
		contentPane.add(Kingdom3_N3_N4);
		
		JButton Kingdom3_N2_N4 = new JButton("");
		Kingdom3_N2_N4.setBounds(760, 720, 30, 30);
		contentPane.add(Kingdom3_N2_N4);
		
		JButton Kingdom3_N1_N4 = new JButton("");
		Kingdom3_N1_N4.setBounds(790, 720, 30, 30);
		contentPane.add(Kingdom3_N1_N4);
		
		JButton Kingdom3_0_N4 = new JButton("");
		Kingdom3_0_N4.setBounds(820, 720, 30, 30);
		contentPane.add(Kingdom3_0_N4);
		
		JButton Kingdom3_1_N4 = new JButton("");
		Kingdom3_1_N4.setBounds(850, 720, 30, 30);
		contentPane.add(Kingdom3_1_N4);
		
		JButton Kingdom3_2_N4 = new JButton("");
		Kingdom3_2_N4.setBounds(880, 720, 30, 30);
		contentPane.add(Kingdom3_2_N4);
		
		JButton Kingdom3_3_N4 = new JButton("");
		Kingdom3_3_N4.setBounds(910, 720, 30, 30);
		contentPane.add(Kingdom3_3_N4);
		
		JButton Kingdom3_4_N4 = new JButton("");
		Kingdom3_4_N4.setBounds(940, 720, 30, 30);
		contentPane.add(Kingdom3_4_N4);
		
		
		
		//Add to array
		kingdom3[0][0] = Kingdom3_N4_N4;
		kingdom3[0][1] = Kingdom3_N4_N3;
		kingdom3[0][2] = Kingdom3_N4_N2;
		kingdom3[0][3] = Kingdom3_N4_N1;
		kingdom3[0][4] = Kingdom3_N4_0;
		kingdom3[0][5] = Kingdom3_N4_1;
		kingdom3[0][6] = Kingdom3_N4_2;
		kingdom3[0][7] = Kingdom3_N4_3;
		kingdom3[0][8] = Kingdom3_N4_4;
		
		kingdom3[1][0] = Kingdom3_N3_N4;
		kingdom3[1][1] = Kingdom3_N3_N3;
		kingdom3[1][2] = Kingdom3_N3_N2;
		kingdom3[1][3] = Kingdom3_N3_N1;
		kingdom3[1][4] = Kingdom3_N3_0;
		kingdom3[1][5] = Kingdom3_N3_1;
		kingdom3[1][6] = Kingdom3_N3_2;
		kingdom3[1][7] = Kingdom3_N3_3;
		kingdom3[1][8] = Kingdom3_N3_4; 
		
		kingdom3[2][0] = Kingdom3_N2_N4;
		kingdom3[2][1] = Kingdom3_N2_N3;
		kingdom3[2][2] = Kingdom3_N2_N2;
		kingdom3[2][3] = Kingdom3_N2_N1;
		kingdom3[2][4] = Kingdom3_N2_0;
		kingdom3[2][5] = Kingdom3_N2_1;
		kingdom3[2][6] = Kingdom3_N2_2;
		kingdom3[2][7] = Kingdom3_N2_3;
		kingdom3[2][8] = Kingdom3_N2_4;
		
		kingdom3[3][0] = Kingdom3_N1_N4;
		kingdom3[3][1] = Kingdom3_N1_N3;
		kingdom3[3][2] = Kingdom3_N1_N2;
		kingdom3[3][3] = Kingdom3_N1_N1;
		kingdom3[3][4] = Kingdom3_N1_0;
		kingdom3[3][5] = Kingdom3_N1_1;
		kingdom3[3][6] = Kingdom3_N1_2;
		kingdom3[3][7] = Kingdom3_N1_3;
		kingdom3[3][8] = Kingdom3_N1_4;
		
		kingdom3[4][0] = Kingdom3_0_N4;
		kingdom3[4][1] = Kingdom3_0_N3;
		kingdom3[4][2] = Kingdom3_0_N2;
		kingdom3[4][3] = Kingdom3_0_N1;
		kingdom3[4][4] = null;
		kingdom3[4][5] = Kingdom3_0_1;
		kingdom3[4][6] = Kingdom3_0_2;
		kingdom3[4][7] = Kingdom3_0_3;
		kingdom3[4][8] = Kingdom3_0_4;
		
		kingdom3[5][0] = Kingdom3_1_N4;
		kingdom3[5][1] = Kingdom3_1_N3;
		kingdom3[5][2] = Kingdom3_1_N2;
		kingdom3[5][3] = Kingdom3_1_N1;
		kingdom3[5][4] = Kingdom3_1_0;
		kingdom3[5][5] = Kingdom3_1_1;
		kingdom3[5][6] = Kingdom3_1_2;
		kingdom3[5][7] = Kingdom3_1_3;
		kingdom3[5][8] = Kingdom3_1_4;
		
		kingdom3[6][0] = Kingdom3_2_N4;
		kingdom3[6][1] = Kingdom3_2_N3;
		kingdom3[6][2] = Kingdom3_2_N2;
		kingdom3[6][3] = Kingdom3_2_N1;
		kingdom3[6][4] = Kingdom3_2_0;
		kingdom3[6][5] = Kingdom3_2_1;
		kingdom3[6][6] = Kingdom3_2_2;
		kingdom3[6][7] = Kingdom3_2_3;
		kingdom3[6][8] = Kingdom3_2_4;
		
		kingdom3[7][0] = Kingdom3_3_N4;
		kingdom3[7][1] = Kingdom3_3_N3;
		kingdom3[7][2] = Kingdom3_3_N2;
		kingdom3[7][3] = Kingdom3_3_N1;
		kingdom3[7][4] = Kingdom3_3_0;
		kingdom3[7][5] = Kingdom3_3_1;
		kingdom3[7][6] = Kingdom3_3_2;
		kingdom3[7][7] = Kingdom3_3_3;
		kingdom3[7][8] = Kingdom3_3_4;
		
		kingdom3[8][0] = Kingdom3_4_N4;
		kingdom3[8][1] = Kingdom3_4_N3;
		kingdom3[8][2] = Kingdom3_4_N2;
		kingdom3[8][3] = Kingdom3_4_N1;
		kingdom3[8][4] = Kingdom3_4_0;
		kingdom3[8][5] = Kingdom3_4_1;
		kingdom3[8][6] = Kingdom3_4_2;
		kingdom3[8][7] = Kingdom3_4_3;
		kingdom3[8][8] = Kingdom3_4_4;
		// FORTH PLAYER KINGDOM 
		
		JButton Kingdom4_N4_3 = new JButton("");
		Kingdom4_N4_3.setBounds(1030, 510, 30, 30);
		contentPane.add(Kingdom4_N4_3);
		
		JButton Kingdom4_N3_3 = new JButton("");
		Kingdom4_N3_3.setBounds(1060, 510, 30, 30);
		contentPane.add(Kingdom4_N3_3);
		
		JButton Kingdom4_N2_3 = new JButton("");
		Kingdom4_N2_3.setBounds(1090, 510, 30, 30);
		contentPane.add(Kingdom4_N2_3);
		
		JButton Kingdom4_N1_3 = new JButton("");
		Kingdom4_N1_3.setBounds(1120, 510, 30, 30);
		contentPane.add(Kingdom4_N1_3);
		
		JButton Kingdom4_0_3 = new JButton("");
		Kingdom4_0_3.setBounds(1150, 510, 30, 30);
		contentPane.add(Kingdom4_0_3);
		
		JButton Kingdom4_1_3 = new JButton("");
		Kingdom4_1_3.setBounds(1180, 510, 30, 30);
		contentPane.add(Kingdom4_1_3);
		
		JButton Kingdom4_2_3 = new JButton("");
		Kingdom4_2_3.setBounds(1210, 510, 30, 30);
		contentPane.add(Kingdom4_2_3);
		
		JButton Kingdom4_3_3 = new JButton("");
		Kingdom4_3_3.setBounds(1240, 510, 30, 30);
		contentPane.add(Kingdom4_3_3);
		
		JButton Kingdom4_4_3 = new JButton("");
		Kingdom4_4_3.setBounds(1270, 510, 30, 30);
		contentPane.add(Kingdom4_4_3);
		JButton Kingdom4_N4_2 = new JButton("");
		Kingdom4_N4_2.setBounds(1030, 540, 30, 30);
		contentPane.add(Kingdom4_N4_2);
		
		JButton Kingdom4_N3_2 = new JButton("");
		Kingdom4_N3_2.setBounds(1060, 540, 30, 30);
		contentPane.add(Kingdom4_N3_2);
		
		JButton Kingdom4_N1_2 = new JButton("");
		Kingdom4_N1_2.setBounds(1120, 540, 30, 30);
		contentPane.add(Kingdom4_N1_2);
		
		JButton Kingdom4_N2_2 = new JButton("");
		Kingdom4_N2_2.setBounds(1090, 540, 30, 30);
		contentPane.add(Kingdom4_N2_2);
		
		JButton Kingdom4_0_2 = new JButton("");
		Kingdom4_0_2.setBounds(1150, 540, 30, 30);
		contentPane.add(Kingdom4_0_2);
		
		JButton Kingdom4_1_2 = new JButton("");
		Kingdom4_1_2.setBounds(1180, 540, 30, 30);
		contentPane.add(Kingdom4_1_2);
		
		JButton Kingdom4_2_2 = new JButton("");
		Kingdom4_2_2.setBounds(1210, 540, 30, 30);
		contentPane.add(Kingdom4_2_2);
		
		JButton Kingdom4_3_2 = new JButton("");
		Kingdom4_3_2.setBounds(1240, 540, 30, 30);
		contentPane.add(Kingdom4_3_2);
		
		JButton Kingdom4_4_2 = new JButton("");
		Kingdom4_4_2.setBounds(1270, 540, 30, 30);
		contentPane.add(Kingdom4_4_2);
		JButton Kingdom4_N4_1 = new JButton("");
		Kingdom4_N4_1.setBounds(1030, 570, 30, 30);
		contentPane.add(Kingdom4_N4_1);
		
		JButton Kingdom4_N3_1 = new JButton("");
		Kingdom4_N3_1.setBounds(1060, 570, 30, 30);
		contentPane.add(Kingdom4_N3_1);
		
		JButton Kingdom4_N2_1 = new JButton("");
		Kingdom4_N2_1.setBounds(1090, 570, 30, 30);
		contentPane.add(Kingdom4_N2_1);
		
		JButton Kingdom4_N1_1 = new JButton("");
		Kingdom4_N1_1.setBounds(1120, 570, 30, 30);
		contentPane.add(Kingdom4_N1_1);
		
		JButton Kingdom4_0_1 = new JButton("");
		Kingdom4_0_1.setBounds(1150, 570, 30, 30);
		contentPane.add(Kingdom4_0_1);
		
		JButton Kingdom4_1_1 = new JButton("");
		Kingdom4_1_1.setBounds(1180, 570, 30, 30);
		contentPane.add(Kingdom4_1_1);
		
		JButton Kingdom4_2_1 = new JButton("");
		Kingdom4_2_1.setBounds(1210, 570, 30, 30);
		contentPane.add(Kingdom4_2_1);
		
		JButton Kingdom4_3_1 = new JButton("");
		Kingdom4_3_1.setBounds(1240, 570, 30, 30);
		contentPane.add(Kingdom4_3_1);
		
		JButton Kingdom4_4_1 = new JButton("");
		Kingdom4_4_1.setBounds(1270, 570, 30, 30);
		contentPane.add(Kingdom4_4_1);
		JButton Kingdom4_N4_0 = new JButton("");
		Kingdom4_N4_0.setBounds(1030, 600, 30, 30);
		contentPane.add(Kingdom4_N4_0);
		
		JButton Kingdom4_N3_0 = new JButton("");
		Kingdom4_N3_0.setBounds(1060, 600, 30, 30);
		contentPane.add(Kingdom4_N3_0);
		
		JButton Kingdom4_N2_0 = new JButton("");
		Kingdom4_N2_0.setBounds(1090, 600, 30, 30);
		contentPane.add(Kingdom4_N2_0);
		
		JButton Kingdom4_N1_0 = new JButton("");
		Kingdom4_N1_0.setBounds(1120, 600, 30, 30);
		contentPane.add(Kingdom4_N1_0);
		
		JButton Kingdom4_1_0 = new JButton("");
		Kingdom4_1_0.setBounds(1180, 600, 30, 30);
		contentPane.add(Kingdom4_1_0);
		
		JButton Kingdom4_2_0 = new JButton("");
		Kingdom4_2_0.setBounds(1210, 600, 30, 30);
		contentPane.add(Kingdom4_2_0);
		
		JButton Kingdom4_3_0 = new JButton("");
		Kingdom4_3_0.setBounds(1240, 600, 30, 30);
		contentPane.add(Kingdom4_3_0);
		
		JButton Kingdom4_4_0 = new JButton("");
		Kingdom4_4_0.setBounds(1270, 600, 30, 30);
		contentPane.add(Kingdom4_4_0);
		
		JButton Kingdom4_N4_N1 = new JButton("");
		Kingdom4_N4_N1.setBounds(1030, 630, 30, 30);
		contentPane.add(Kingdom4_N4_N1);
		
		JButton Kingdom4_N3_N1 = new JButton("");
		Kingdom4_N3_N1.setBounds(1060, 630, 30, 30);
		contentPane.add(Kingdom4_N3_N1);
		
		JButton Kingdom4_N2_N1 = new JButton("");
		Kingdom4_N2_N1.setBounds(1090, 630, 30, 30);
		contentPane.add(Kingdom4_N2_N1);
		
		JButton Kingdom4_N1_N1 = new JButton("");
		Kingdom4_N1_N1.setBounds(1120, 630, 30, 30);
		contentPane.add(Kingdom4_N1_N1);
		
		JButton Kingdom4_0_N1 = new JButton("");
		Kingdom4_0_N1.setBounds(1150, 630, 30, 30);
		contentPane.add(Kingdom4_0_N1);
		
		JButton Kingdom4_1_N1 = new JButton("");
		Kingdom4_1_N1.setBounds(1180, 630, 30, 30);
		contentPane.add(Kingdom4_1_N1);
		
		JButton Kingdom4_2_N1 = new JButton("");
		Kingdom4_2_N1.setBounds(1210, 630, 30, 30);
		contentPane.add(Kingdom4_2_N1);
		
		JButton Kingdom4_3_N1 = new JButton("");
		Kingdom4_3_N1.setBounds(1240, 630, 30, 30);
		contentPane.add(Kingdom4_3_N1);
		
		JButton Kingdom4_4_N1 = new JButton("");
		Kingdom4_4_N1.setBounds(1270, 630, 30, 30);
		contentPane.add(Kingdom4_4_N1);
		JButton Kingdom4_N4_N2 = new JButton("");
		Kingdom4_N4_N2.setBounds(1030, 660, 30, 30);
		contentPane.add(Kingdom4_N4_N2);
		
		JButton Kingdom4_N3_N2 = new JButton("");
		Kingdom4_N3_N2.setBounds(1060, 660, 30, 30);
		contentPane.add(Kingdom4_N3_N2);
		
		JButton Kingdom4_N2_N2 = new JButton("");
		Kingdom4_N2_N2.setBounds(1090, 660, 30, 30);
		contentPane.add(Kingdom4_N2_N2);
		
		JButton Kingdom4_N1_N2 = new JButton("");
		Kingdom4_N1_N2.setBounds(1120, 660, 30, 30);
		contentPane.add(Kingdom4_N1_N2);
		
		JButton Kingdom4_0_N2 = new JButton("");
		Kingdom4_0_N2.setBounds(1150, 660, 30, 30);
		contentPane.add(Kingdom4_0_N2);
		
		JButton Kingdom4_1_N2 = new JButton("");
		Kingdom4_1_N2.setBounds(1180, 660, 30, 30);
		contentPane.add(Kingdom4_1_N2);
		
		JButton Kingdom4_2_N2 = new JButton("");
		Kingdom4_2_N2.setBounds(1210, 660, 30, 30);
		contentPane.add(Kingdom4_2_N2);
		
		JButton Kingdom4_3_N2 = new JButton("");
		Kingdom4_3_N2.setBounds(1240, 660, 30, 30);
		contentPane.add(Kingdom4_3_N2);
		
		JButton Kingdom4_4_N2 = new JButton("");
		Kingdom4_4_N2.setBounds(1270, 660, 30, 30);
		contentPane.add(Kingdom4_4_N2);
		JButton Kingdom4_N4_N3 = new JButton("");
		Kingdom4_N4_N3.setBounds(1030, 690, 30, 30);
		contentPane.add(Kingdom4_N4_N3);
		
		JButton Kingdom4_N3_N3 = new JButton("");
		Kingdom4_N3_N3.setBounds(1060, 690, 30, 30);
		contentPane.add(Kingdom4_N3_N3);
		
		JButton Kingdom4_N2_N3 = new JButton("");
		Kingdom4_N2_N3.setBounds(1090, 690, 30, 30);
		contentPane.add(Kingdom4_N2_N3);
		
		JButton Kingdom4_N1_N3 = new JButton("");
		Kingdom4_N1_N3.setBounds(1120, 690, 30, 30);
		contentPane.add(Kingdom4_N1_N3);
		
		JButton Kingdom4_0_N3 = new JButton("");
		Kingdom4_0_N3.setBounds(1150, 690, 30, 30);
		contentPane.add(Kingdom4_0_N3);
		
		JButton Kingdom4_1_N3 = new JButton("");
		Kingdom4_1_N3.setBounds(1180, 690, 30, 30);
		contentPane.add(Kingdom4_1_N3);
		
		JButton Kingdom4_2_N3 = new JButton("");
		Kingdom4_2_N3.setBounds(1210, 690, 30, 30);
		contentPane.add(Kingdom4_2_N3);
		
		JButton Kingdom4_3_N3 = new JButton("");
		Kingdom4_3_N3.setBounds(1240, 690, 30, 30);
		contentPane.add(Kingdom4_3_N3);
		
		JButton Kingdom4_4_N3 = new JButton("");
		Kingdom4_4_N3.setBounds(1270, 690, 30, 30);
		contentPane.add(Kingdom4_4_N3);
		JButton Kingdom4_N4_N4 = new JButton("");
		Kingdom4_N4_N4.setBounds(1030, 720, 30, 30);
		contentPane.add(Kingdom4_N4_N4);
		
		JButton Kingdom4_N3_N4 = new JButton("");
		Kingdom4_N3_N4.setBounds(1060, 720, 30, 30);
		contentPane.add(Kingdom4_N3_N4);
		
		JButton Kingdom4_N2_N4 = new JButton("");
		Kingdom4_N2_N4.setBounds(1090, 720, 30, 30);
		contentPane.add(Kingdom4_N2_N4);
		
		JButton Kingdom4_N1_N4 = new JButton("");
		Kingdom4_N1_N4.setBounds(1120, 720, 30, 30);
		contentPane.add(Kingdom4_N1_N4);
		
		JButton Kingdom4_0_N4 = new JButton("");
		Kingdom4_0_N4.setBounds(1150, 720, 30, 30);
		contentPane.add(Kingdom4_0_N4);
		
		JButton Kingdom4_2_N4 = new JButton("");
		Kingdom4_2_N4.setBounds(1210, 720, 30, 30);
		contentPane.add(Kingdom4_2_N4);
		
		JButton Kingdom4_1_N4 = new JButton("");
		Kingdom4_1_N4.setBounds(1180, 720, 30, 30);
		contentPane.add(Kingdom4_1_N4);
		
		JButton Kingdom4_3_N4 = new JButton("");
		Kingdom4_3_N4.setBounds(1240, 720, 30, 30);
		contentPane.add(Kingdom4_3_N4);
		
		JButton Kingdom4_4_N4 = new JButton("");
		Kingdom4_4_N4.setBounds(1270, 720, 30, 30);
		contentPane.add(Kingdom4_4_N4);
		kingdom4[0][0] = Kingdom4_N4_N4;
		kingdom4[0][1] = Kingdom4_N4_N3;
		kingdom4[0][2] = Kingdom4_N4_N2;
		kingdom4[0][3] = Kingdom4_N4_N1;
		kingdom4[0][4] = Kingdom4_N4_0;
		kingdom4[0][5] = Kingdom4_N4_1;
		kingdom4[0][6] = Kingdom4_N4_2;
		kingdom4[0][7] = Kingdom4_N4_3;
		kingdom4[0][8] = Kingdom4_N4_4;
		
		kingdom4[1][0] = Kingdom4_N3_N4;
		kingdom4[1][1] = Kingdom4_N3_N3;
		kingdom4[1][2] = Kingdom4_N3_N2;
		kingdom4[1][3] = Kingdom4_N3_N1;
		kingdom4[1][4] = Kingdom4_N3_0;
		kingdom4[1][5] = Kingdom4_N3_1;
		kingdom4[1][6] = Kingdom4_N3_2;
		kingdom4[1][7] = Kingdom4_N3_3;
		kingdom4[1][8] = Kingdom4_N3_4; 
		
		kingdom4[2][0] = Kingdom4_N2_N4;
		kingdom4[2][1] = Kingdom4_N2_N3;
		kingdom4[2][2] = Kingdom4_N2_N2;
		kingdom4[2][3] = Kingdom4_N2_N1;
		kingdom4[2][4] = Kingdom4_N2_0;
		kingdom4[2][5] = Kingdom4_N2_1;
		kingdom4[2][6] = Kingdom4_N2_2;
		kingdom4[2][7] = Kingdom4_N2_3;
		kingdom4[2][8] = Kingdom4_N2_4;
		
		kingdom4[3][0] = Kingdom4_N1_N4;
		kingdom4[3][1] = Kingdom4_N1_N3;
		kingdom4[3][2] = Kingdom4_N1_N2;
		kingdom4[3][3] = Kingdom4_N1_N1;
		kingdom4[3][4] = Kingdom4_N1_0;
		kingdom4[3][5] = Kingdom4_N1_1;
		kingdom4[3][6] = Kingdom4_N1_2;
		kingdom4[3][7] = Kingdom4_N1_3;
		kingdom4[3][8] = Kingdom4_N1_4;
		
		kingdom4[4][0] = Kingdom4_0_N4;
		kingdom4[4][1] = Kingdom4_0_N3;
		kingdom4[4][2] = Kingdom4_0_N2;
		kingdom4[4][3] = Kingdom4_0_N1;
		kingdom4[4][4] = null;
		kingdom4[4][5] = Kingdom4_0_1;
		kingdom4[4][6] = Kingdom4_0_2;
		kingdom4[4][7] = Kingdom4_0_3;
		kingdom4[4][8] = Kingdom4_0_4;
		
		kingdom4[5][0] = Kingdom4_1_N4;
		kingdom4[5][1] = Kingdom4_1_N3;
		kingdom4[5][2] = Kingdom4_1_N2;
		kingdom4[5][3] = Kingdom4_1_N1;
		kingdom4[5][4] = Kingdom4_1_0;
		kingdom4[5][5] = Kingdom4_1_1;
		kingdom4[5][6] = Kingdom4_1_2;
		kingdom4[5][7] = Kingdom4_1_3;
		kingdom4[5][8] = Kingdom4_1_4;
		
		kingdom4[6][0] = Kingdom4_2_N4;
		kingdom4[6][1] = Kingdom4_2_N3;
		kingdom4[6][2] = Kingdom4_2_N2;
		kingdom4[6][3] = Kingdom4_2_N1;
		kingdom4[6][4] = Kingdom4_2_0;
		kingdom4[6][5] = Kingdom4_2_1;
		kingdom4[6][6] = Kingdom4_2_2;
		kingdom4[6][7] = Kingdom4_2_3;
		kingdom4[6][8] = Kingdom4_2_4;
		
		kingdom4[7][0] = Kingdom4_3_N4;
		kingdom4[7][1] = Kingdom4_3_N3;
		kingdom4[7][2] = Kingdom4_3_N2;
		kingdom4[7][3] = Kingdom4_3_N1;
		kingdom4[7][4] = Kingdom4_3_0;
		kingdom4[7][5] = Kingdom4_3_1;
		kingdom4[7][6] = Kingdom4_3_2;
		kingdom4[7][7] = Kingdom4_3_3;
		kingdom4[7][8] = Kingdom4_3_4;
		
		kingdom4[8][0] = Kingdom4_4_N4;
		kingdom4[8][1] = Kingdom4_4_N3;
		kingdom4[8][2] = Kingdom4_4_N2;
		kingdom4[8][3] = Kingdom4_4_N1;
		kingdom4[8][4] = Kingdom4_4_0;
		kingdom4[8][5] = Kingdom4_4_1;
		kingdom4[8][6] = Kingdom4_4_2;
		kingdom4[8][7] = Kingdom4_4_3;
		kingdom4[8][8] = Kingdom4_4_4;
		
		
		
		JButton Kingdom1_1 = new JButton("");
		Kingdom1_1.setBounds(100, 450, 30, 30);
		contentPane.add(Kingdom1_1);
		
		JButton Kingdom1_2 = new JButton("");
		Kingdom1_2.setBounds(130, 450, 30, 30);
		contentPane.add(Kingdom1_2);
		
		JButton Kingdom2_1 = new JButton("");
		Kingdom2_1.setBounds(450, 450, 30, 30);
		contentPane.add(Kingdom2_1);
		
		JButton Kingdom2_2 = new JButton("");
		Kingdom2_2.setBounds(480, 450, 30, 30);
		contentPane.add(Kingdom2_2);
		
		JButton Kingdom3_1 = new JButton("");
		Kingdom3_1.setBounds(780, 450, 30, 30);
		contentPane.add(Kingdom3_1);
		
		JButton Kingdom3_2 = new JButton("");
		Kingdom3_2.setBounds(810, 450, 30, 30);
		contentPane.add(Kingdom3_2);
		
		JButton Kingdom4_1 = new JButton("");
		Kingdom4_1.setBounds(1100, 450, 30, 30);
		contentPane.add(Kingdom4_1);
		
		JButton Kingdom4_2 = new JButton("");
		Kingdom4_2.setBounds(1130, 450, 30, 30);
		contentPane.add(Kingdom4_2);
		
		Kingdomino kingdomino = KingdominoApplication.getKingdomino();
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        
       // KingdominoApplication.setKingdomino(kingdomino);
        //Draft nextDraft = new Draft(Draft.DraftStatus.FaceUp, game);
        //Domino domino = getdominoByID(1);
        //DominoSelection selection = nextDraft.addSelection(game.getNextPlayer(), domino);
        //game.getNextPlayer().setDominoSelection(selection);
		
		Gameplay gp = KingdominoApplication.getGameplay();
		
        
        JLabel Player1 = new JLabel(game.getPlayer(0).getUser().getName());
		Player1.setForeground(new Color(0, 0, 0));
		Player1.setBounds(40, 450, 270, 20);
		contentPane.add(Player1);
		
		JLabel Player2 = new JLabel(game.getPlayer(1).getUser().getName());
		Player2.setForeground(Color.BLACK);
		Player2.setBounds(370, 450, 270, 20);
		contentPane.add(Player2);
		
		JLabel Player3 = new JLabel(game.getPlayer(2).getUser().getName());
		Player3.setForeground(Color.BLACK);
		Player3.setBounds(700, 450, 270, 20);
		contentPane.add(Player3);
		
		JLabel Player4 = new JLabel(game.getPlayer(3).getUser().getName());
		Player4.setForeground(Color.BLACK);
		Player4.setBounds(1030, 450, 270, 20);
		contentPane.add(Player4);
        OnNextTurn controller = new OnNextTurn();
        
        JButton Draft1_1 = new JButton("");
		contentPane.add(Draft1_1);
		JButton Draft2_1 = new JButton("");
		contentPane.add(Draft2_1);
		Draft2_1.setBounds(360, 60, 30, 30);
		JButton Draft2_2 = new JButton("");
		contentPane.add(Draft2_2);
		Draft1_1.setBounds(280, 60, 30, 30);
		JButton Draft1_2 = new JButton("");
		contentPane.add(Draft1_2);
		Draft1_2.setBounds(280, 90, 30, 30);
		Draft2_2.setBounds(360, 90, 30, 30);
		JButton Draft3_1 = new JButton("");
		contentPane.add(Draft3_1);
		Draft3_1.setBounds(440, 60, 30, 30);
		JButton Draft3_2 = new JButton("");
		contentPane.add(Draft3_2);
		Draft3_2.setBounds(440, 90, 30, 30);
		JButton Draft4_1 = new JButton("");
		contentPane.add(Draft4_1);
		Draft4_1.setBounds(520, 60, 30, 30);
		JButton Draft4_2 = new JButton("");
		contentPane.add(Draft4_2);
		Draft4_2.setBounds(520, 90, 30, 30);
		
		
		ImageIcon c1 = new ImageIcon("1cr.jpg");
		ImageIcon c3 = new ImageIcon("3cr.png");
		ImageIcon c4 = new ImageIcon("4cr.png");
		ImageIcon blk =new ImageIcon("blk.png");
		
		JButton Draft1_3 = new JButton("");
		contentPane.add(Draft1_3);
		Draft1_3.setBounds(280, 130, 30, 30);
		//Draft1_3.setText("·");
		ImageIcon c2 = new ImageIcon("2cr.png");
		
		
		JButton Draft2_3 = new JButton("");
		contentPane.add(Draft2_3);
		Draft2_3.setBounds(360, 130, 30, 30);
		
		JButton Draft3_3 = new JButton("");
		contentPane.add(Draft3_3);
		Draft3_3.setBounds(440, 130, 30, 30);
		
		JButton Draft4_3 = new JButton("");
		contentPane.add(Draft4_3);
		Draft4_3.setBounds(520, 130, 30, 30);
		Draft4_3.setIcon(c1);
		
		
        if (gp.getGamestatusInitializing().equals(GamestatusInitializing.CreatingFirstDraft) && !this.loadedFrom) {
    		//if (game.getCurrentDraft()!= null) {
        	/**
        	 * creating first draft 
        	 * @author everyone except david and majd 
        	 */
    			Draft1_1.setBorderPainted(false);
    			Draft1_1.setOpaque(true);
    			Draft1_1.setBackground(getColorByTile(game.getNextDraft().getIdSortedDomino(0).getLeftTile()));
    			//Draft1_1.setMargin(new Insets(0, 0, 0, 0));
    			//Draft1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 5));
    			//Draft1_1.setText(game.getCurrentDraft().getIdSortedDomino(0).getLeftCrown()+"");
    			Draft1_2.setBorderPainted(false);
    			Draft1_2.setOpaque(true);
    			Draft1_2.setBackground(getColorByTile(game.getNextDraft().getIdSortedDomino(0).getRightTile()));
    			//Draft1_2.setText(game.getCurrentDraft().getIdSortedDomino(0).getRightCrown()+"");
    			int cn1=game.getNextDraft().getIdSortedDomino(0).getLeftCrown()+game.getNextDraft().getIdSortedDomino(0).getRightCrown();
    			int cn2=game.getNextDraft().getIdSortedDomino(1).getLeftCrown()+game.getNextDraft().getIdSortedDomino(1).getRightCrown();
    			int cn3=game.getNextDraft().getIdSortedDomino(2).getLeftCrown()+game.getNextDraft().getIdSortedDomino(2).getRightCrown();
    			int cn4=game.getNextDraft().getIdSortedDomino(3).getLeftCrown()+game.getNextDraft().getIdSortedDomino(3).getRightCrown();
    			switch(cn1) {
    			case 0:
    				Draft1_3.setIcon(blk);
    				break;
    			case 1:
    				Draft1_3.setIcon(c1);
    				break;
    			case 2:
    				Draft1_3.setIcon(c2);
    				break;
    			case 3:
    				Draft1_3.setIcon(c3);
    				break;
    			case 4:
    				Draft1_3.setIcon(c4);	break;
    			
    				
    			}
    			Draft2_1.setBorderPainted(false);
    			Draft2_1.setOpaque(true);
    			Draft2_1.setBackground(getColorByTile(game.getNextDraft().getIdSortedDomino(1).getLeftTile()));
    			//Draft2_1.setText(game.getCurrentDraft().getIdSortedDomino(1).getLeftCrown()+"");
    			Draft2_2.setBorderPainted(false);
    			Draft2_2.setOpaque(true);
    			Draft2_2.setBackground(getColorByTile(game.getNextDraft().getIdSortedDomino(1).getRightTile()));
    			System.out.println(cn1);
    			System.out.println(cn2);
    			System.out.println(cn3);
    			System.out.println(cn4);
    			//System.out.println(game.getNextDraft().getIdSortedDomino(1).getLeftCrown()+game.getNextDraft().getIdSortedDomino(1).getRightCrown());
    			//System.out.println(game.getNextDraft().getIdSortedDomino(2).getLeftCrown()+game.getNextDraft().getIdSortedDomino(2).getRightCrown());
    			//System.out.println(game.getNextDraft().getIdSortedDomino(3).getLeftCrown()+game.getNextDraft().getIdSortedDomino(3).getRightCrown());
    			//Draft2_2.setText(game.getCurrentDraft().getIdSortedDomino(1).getRightCrown()+"");
    			switch(cn2) {
    			case 0:
    				Draft2_3.setIcon(blk);
    				break;
    			case 1:
    				Draft2_3.setIcon(c1);
    				break;
    			case 2:
    				Draft2_3.setIcon(c2);
    				break;
    			case 3:
    				Draft2_3.setIcon(c3);
    				break;
    			case 4:
    				Draft2_3.setIcon(c4);
    				break;
    			
    				
    			}
    			Draft3_1.setBorderPainted(false);
    			Draft3_1.setOpaque(true);
    			Draft3_1.setBackground(getColorByTile(game.getNextDraft().getIdSortedDomino(2).getLeftTile()));
    			//Draft3_1.setText(game.getCurrentDraft().getIdSortedDomino(2).getLeftCrown()+"");
    			Draft3_2.setBorderPainted(false);
    			Draft3_2.setOpaque(true);
    			Draft3_2.setBackground(getColorByTile(game.getNextDraft().getIdSortedDomino(2).getRightTile()));
    			//Draft3_2.setText(game.getCurrentDraft().getIdSortedDomino(2).getRightCrown()+"");
    			switch(cn3) {
    			case 0:
    				Draft3_3.setIcon(blk);
    				break;
    			case 1:
    				Draft3_3.setIcon(c1);
    				break;
    			case 2:
    				Draft3_3.setIcon(c2);
    				break;
    			case 3:
    				Draft3_3.setIcon(c3);
    				break;
    			case 4:
    				Draft3_3.setIcon(c4);
    				break;
    			
    				
    			}
    			Draft4_1.setBorderPainted(false);
    			Draft4_1.setOpaque(true);
    			Draft4_1.setBackground(getColorByTile(game.getNextDraft().getIdSortedDomino(3).getLeftTile()));
    			//Draft4_1.setText(game.getCurrentDraft().getIdSortedDomino(3).getLeftCrown()+"");
    			Draft4_2.setBorderPainted(false);
    			Draft4_2.setOpaque(true);
    			Draft4_2.setBackground(getColorByTile(game.getNextDraft().getIdSortedDomino(3).getRightTile()));
    			switch(cn4) {
    			case 0:
    				Draft4_3.setIcon(blk);	break;
    			case 1:
    				Draft4_3.setIcon(c1);	break;
    			case 2:
    				Draft4_3.setIcon(c2);	break;
    			case 3:
    				Draft4_3.setIcon(c3);	break;
    			case 4:
    				Draft4_3.setIcon(c4);	break;
    			}
    			//Draft4_2.setText(game.getCurrentDraft().getIdSortedDomino(3).getRightCrown()+"");
    			
    			
    			
    		//}
        }else{
        	/**
        	 * load game feature 
        	 * @author everyone except david 
        	 */
        	System.out.println("here");
			Draft1_1.setBorderPainted(false);
			Draft1_1.setOpaque(true);
			Draft1_2.setBorderPainted(false);
			Draft1_2.setOpaque(true);
			Draft2_1.setBorderPainted(false);
			Draft2_1.setOpaque(true);

			Draft2_2.setBorderPainted(false);
			Draft2_2.setOpaque(true);

			Draft3_1.setBorderPainted(false);
			Draft3_1.setOpaque(true);

			Draft3_2.setBorderPainted(false);
			Draft3_2.setOpaque(true);

			Draft4_1.setBorderPainted(false);
			Draft4_1.setOpaque(true);

			Draft4_2.setBorderPainted(false);
			Draft4_2.setOpaque(true);
			
			/*Draft1_3.setIcon(blk);
			Draft2_3.setIcon(blk);
			Draft3_3.setIcon(blk);
			Draft4_3.setIcon(blk);*/
			
			ArrayList<Integer> sels = new ArrayList<Integer>();
			for (DominoSelection sel: game.getCurrentDraft().getSelections()) {
				sels.add(sel.getDomino().getId());
			}
			int selected =0;
			

			for(int i = 0; i < game.getCurrentDraft().getIdSortedDominos().size(); i++){
				//if (sels.contains(game.getCurrentDraft().getIdSortedDomino(i).getId())) {
					//selected++;
				switch(i){
					case 0:
						Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
						Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
						System.out.println(game.getCurrentDraft().getIdSortedDomino(0).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(0).getRightCrown());
						switch(game.getCurrentDraft().getIdSortedDomino(0).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(0).getRightCrown()) {
		    			case 0:
		    				Draft1_3.setIcon(blk);	break;
		    			case 1:
		    				Draft1_3.setIcon(c1);	break;
		    			case 2:
		    				Draft1_3.setIcon(c2);	break;
		    			case 3:
		    				Draft1_3.setIcon(c3);	break;
		    			
		    			}
						
						break;
					case 1:
						Draft2_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(1).getLeftTile()));
						Draft2_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(1).getRightTile()));
						System.out.println(game.getCurrentDraft().getIdSortedDomino(1).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(1).getRightCrown());
						switch(game.getCurrentDraft().getIdSortedDomino(1).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(1).getRightCrown()) {
		    			case 0:
		    				Draft2_3.setIcon(blk);	break;
		    			case 1:
		    				Draft2_3.setIcon(c1);	break;
		    			case 2:
		    				Draft2_3.setIcon(c2);	break;
		    			case 3:
		    				Draft2_3.setIcon(c3);	break;
		    			
		    			}
						break;
					case 2:
						Draft3_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(2).getLeftTile()));
						Draft3_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(2).getRightTile()));
						System.out.println(game.getCurrentDraft().getIdSortedDomino(2).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(2).getRightCrown());
						switch(game.getCurrentDraft().getIdSortedDomino(2).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(2).getRightCrown()) {
		    			case 0:
		    				Draft3_3.setIcon(blk);	break;
		    			case 1:
		    				Draft3_3.setIcon(c1);	break;
		    			case 2:
		    				Draft3_3.setIcon(c2);	break;
		    			case 3:
		    				Draft3_3.setIcon(c3);	break;
		    			
		    			}
						break;
					case 3:
						Draft4_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(3).getLeftTile()));
						Draft4_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(3).getRightTile()));
						System.out.println(game.getCurrentDraft().getIdSortedDomino(3).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(3).getRightCrown());
						switch(game.getCurrentDraft().getIdSortedDomino(3).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(3).getRightCrown()) {
		    			/*case 0:
		    				Draft4_3.setIcon(blk);	break;*/
		    			case 1:
		    				Draft4_3.setIcon(c1);	break;
		    			case 2:
		    				Draft4_3.setIcon(c2);	break;
		    			case 3:
		    				Draft4_3.setIcon(c3);	break;
		    			
		    			}
						break;
				//}
				
				}
			}
			
		}
        /**
         * browse domino pile and shuffle domino pile 
         * @author everyone except david and majd 
         */
        controller.triggerEventA("draftReady");
        //KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().setDominoSelection(null);
        ArrayList<Domino> dominoinpile = new ArrayList<Domino>();
        for (Domino domino : game.getAllDominos()) {
        	if (domino.getStatus().equals(DominoStatus.InPile)) {
        		dominoinpile.add(domino);
        	}
        }
        int numofdominos;
        if (KingdominoApplication.getGameplay().getGamestatus().equals(Gamestatus.Initializing)) {
        	numofdominos = 11;
        }else {
        	numofdominos = 11- (48-dominoinpile.size())/4;
        }
        JTextField newlabel_1= new JTextField("");
		contentPane.add(newlabel_1);
		newlabel_1.setBounds(710, 90, 140, 40);
		newlabel_1.setText(numofdominos+"");
		JLabel score1 = new JLabel("Score: ");
		contentPane.add(score1);
		score1.setBounds(50, 750, 100, 30);
		JLabel score2 = new JLabel("Score: ");
		contentPane.add(score2);
		score2.setBounds(370, 750, 100, 30);
		JLabel score3 = new JLabel("Score: ");
		contentPane.add(score3);
		score3.setBounds(700, 750, 100, 30);
		JLabel score4 = new JLabel("Score: ");
		contentPane.add(score4);
		score4.setBounds(1030, 750, 100, 30);
		JLabel score1_1 = new JLabel("");
		contentPane.add(score1_1);
		score1_1.setBounds(150, 750, 100, 30);
		JLabel score1_2 = new JLabel("");
		contentPane.add(score1_2);
		score1_2.setBounds(470, 750, 100, 30);
		JLabel score1_3 = new JLabel("");
		contentPane.add(score1_3);
		score1_3.setBounds(800, 750, 100, 30);
		JLabel score1_4 = new JLabel("");
		contentPane.add(score1_4);
		score1_4.setBounds(1130, 750, 100, 30);
		/**
		 * load game feature 
		 * @author 
		 */

		paintLoadGameKingdom(kingdom1, 0);
		paintLoadGameKingdom(kingdom2, 1);
		paintLoadGameKingdom(kingdom3, 2);
		paintLoadGameKingdom(kingdom4, 3);
		for (int i = 0;i<4;i++) {
		if (game.getPlayer(i).getDominoSelection()!= null) {
			if (i ==0) {
				Kingdom1_1.setBorderPainted(false);
				Kingdom1_1.setOpaque(true);
				Kingdom1_2.setBorderPainted(false);
				Kingdom1_2.setOpaque(true);
				Color left = getColorByTile(game.getPlayer(i).getDominoSelection().getDomino().getLeftTile());
				Color right = getColorByTile(game.getPlayer(i).getDominoSelection().getDomino().getRightTile());
				Kingdom1_1.setBackground(left);
				Kingdom1_2.setBackground(right);
			}else if (i ==1) {
				Kingdom2_1.setBorderPainted(false);
				Kingdom2_1.setOpaque(true);
				Kingdom2_2.setBorderPainted(false);
				Kingdom2_2.setOpaque(true);
				Color left = getColorByTile(game.getPlayer(i).getDominoSelection().getDomino().getLeftTile());
				Color right = getColorByTile(game.getPlayer(i).getDominoSelection().getDomino().getRightTile());
				Kingdom2_1.setBackground(left);
				Kingdom2_2.setBackground(right);
			}else if (i == 2) {
				Kingdom3_1.setBorderPainted(false);
				Kingdom3_1.setOpaque(true);
				Kingdom3_2.setBorderPainted(false);
				Kingdom3_2.setOpaque(true);
				Color left = getColorByTile(game.getPlayer(i).getDominoSelection().getDomino().getLeftTile());
				Color right = getColorByTile(game.getPlayer(i).getDominoSelection().getDomino().getRightTile());
				Kingdom3_1.setBackground(left);
				Kingdom3_2.setBackground(right);
			}else if (i ==3) {
				Kingdom4_1.setBorderPainted(false);
				Kingdom4_1.setOpaque(true);
				Kingdom4_2.setBorderPainted(false);
				Kingdom4_2.setOpaque(true);
				Color left = getColorByTile(game.getPlayer(i).getDominoSelection().getDomino().getLeftTile());
				Color right = getColorByTile(game.getPlayer(i).getDominoSelection().getDomino().getRightTile());
				Kingdom4_1.setBackground(left);
				Kingdom4_2.setBackground(right);
			}
		}
		}
        
        
		DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
		//game.setNextPlayer(game.getPlayer(1));
		
		//Draft1_1.setEnabled(true);
		/**
		 * choose next domino feature 
		 * @author everyone except david and majd 
		 */
		Draft1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if ((gp.getGamestatus().equals(Gamestatus.Initializing)&&gp.getGamestatusInitializing().equals(GamestatusInitializing.SelectingFirstDomino))||(gp.getGamestatus().equals(Gamestatus.OnNextTurn)&&gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.SelectDomino) )) {
					Draft1_3.setIcon(blk);
				
				boolean flag = true;
				Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				OnNextTurn controller = new OnNextTurn();
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(0).getColor().toString())) {
					int id = game.getCurrentDraft().getIdSortedDomino(0).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
						
					game.getPlayer(0).setDominoSelection(null);
					
					controller.triggerEventB("dominoSelected", id);
					
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					   sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					
					Kingdom1_1.setBorderPainted(false);
					Kingdom1_1.setOpaque(true);
					Kingdom1_2.setBorderPainted(false);
					Kingdom1_2.setOpaque(true);
					Color left = getColorByTile(getdominoByID(id).getLeftTile());
					Color right = getColorByTile(getdominoByID(id).getRightTile());
					Kingdom1_1.setBackground(left);
					Kingdom1_2.setBackground(right);
					Draft1_1.setBorderPainted(true);
					Draft1_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft1_2.setBorderPainted(true);
					Draft1_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(1).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(0).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
						
					game.getPlayer(1).setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					   sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					
					Kingdom2_1.setBorderPainted(false);
					Kingdom2_1.setOpaque(true);
					Kingdom2_2.setBorderPainted(false);
					Kingdom2_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom2_1.setBackground(left);
					Kingdom2_2.setBackground(right);
					Draft1_1.setBorderPainted(true);
					Draft1_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft1_2.setBorderPainted(true);
					Draft1_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(2).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(0).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
						
					game.getPlayer(0).setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					   sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					
					Kingdom3_1.setBorderPainted(false);
					Kingdom3_1.setOpaque(true);
					Kingdom3_2.setBorderPainted(false);
					Kingdom3_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom3_1.setBackground(left);
					Kingdom3_2.setBackground(right);
					Draft1_1.setBorderPainted(true);
					Draft1_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft1_2.setBorderPainted(true);
					Draft1_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(3).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(0).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
						
					game.getPlayer(0).setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					   sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					
					Kingdom4_1.setBorderPainted(false);
					Kingdom4_1.setOpaque(true);
					Kingdom4_2.setBorderPainted(false);
					Kingdom4_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom4_1.setBackground(left);
					Kingdom4_2.setBackground(right);
					Draft1_1.setBorderPainted(true);
					Draft1_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft1_2.setBorderPainted(true);
					Draft1_2.setOpaque(false);
					flag = false;
					
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
					if (KingdominoApplication.getGameplay().getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CreateNextDraft)) {
						/**
						 * create, order, reveal next draft feature
						 * @author everyone except david and majd 
						 */
						
						controller.triggerEventA("draftReady");
						controller.triggerEventA("orderReady");
						if (game.getCurrentDraft().getIdSortedDominos().size()!= 0) {
							//numofdominos =4;
							Draft1_1.setBorderPainted(false);
							Draft1_1.setOpaque(true);
							Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
							Draft1_2.setBorderPainted(false);
							Draft1_2.setOpaque(true);
							Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(0).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(0).getRightCrown()) {
			    			case 0:
			    				Draft1_3.setIcon(blk);	break;
			    			case 1:
			    				Draft1_3.setIcon(c1);	break;
			    			case 2:
			    				Draft1_3.setIcon(c2);	break;
			    			case 3:
			    				Draft1_3.setIcon(c3);	break;}
							Draft2_1.setBorderPainted(false);
							Draft2_1.setOpaque(true);
							Draft2_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(1).getLeftTile()));
							Draft2_2.setBorderPainted(false);
							Draft2_2.setOpaque(true);
							Draft2_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(1).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(1).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(1).getRightCrown()) {
			    			case 0:
			    				Draft2_3.setIcon(blk);	break;
			    			case 1:
			    				Draft2_3.setIcon(c1);	break;
			    			case 2:
			    				Draft2_3.setIcon(c2);	break;
			    			case 3:
			    				Draft2_3.setIcon(c3);	break;}
							Draft3_1.setBorderPainted(false);
							Draft3_1.setOpaque(true);
							Draft3_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(2).getLeftTile()));
							Draft3_2.setBorderPainted(false);
							Draft3_2.setOpaque(true);
							Draft3_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(2).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(2).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(2).getRightCrown()) {
			    			case 0:
			    				Draft3_3.setIcon(blk);	break;
			    			case 1:
			    				Draft3_3.setIcon(c1);	break;
			    			case 2:
			    				Draft3_3.setIcon(c2);	break;
			    			case 3:
			    				Draft3_3.setIcon(c3);	break;}
							Draft4_1.setBorderPainted(false);
							Draft4_1.setOpaque(true);
							Draft4_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(3).getLeftTile()));
							Draft4_2.setBorderPainted(false);
							Draft4_2.setOpaque(true);
							Draft4_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(3).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(3).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(3).getRightCrown()) {
			    			case 0:
			    				Draft4_3.setIcon(blk);	break;
			    			case 1:
			    				Draft4_3.setIcon(c1);	break;
			    			case 2:
			    				Draft4_3.setIcon(c2);	break;
			    			case 3:
			    				Draft4_3.setIcon(c3);	break;}
							newlabel_1.setText((25-game.getAllDrafts().size())/2+"");
							//for (Player player: KingdominoApplication.getKingdomino().getCurrentGame().getPlayers()) {
							//	System.out.println("f");
							//	player.setDominoSelection(null);
							//	if (sel!= null) {
							//		player.getDominoSelection().delete();
							//	}
							//}
						}
					}
				}
			}else {
				Error error = new Error();
				error.setVisible(true);
			}
			}
			
		});
		
		
		
		Draft2_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/**
				 * choose next domino feature 
				 * @author everyone except david and majd 
				 */
				
				if ((gp.getGamestatus().equals(Gamestatus.Initializing)&&gp.getGamestatusInitializing().equals(GamestatusInitializing.SelectingFirstDomino))||(gp.getGamestatus().equals(Gamestatus.OnNextTurn)&&gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.SelectDomino) )) {
					Draft2_3.setIcon(blk);
				boolean flag = true;
				Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				OnNextTurn controller = new OnNextTurn();
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(1).getColor().toString())) {
					int id = game.getCurrentDraft().getIdSortedDomino(1).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom2_1.setBorderPainted(false);
					Kingdom2_1.setOpaque(true);
					Kingdom2_2.setBorderPainted(false);
					Kingdom2_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom2_1.setBackground(left);
					Kingdom2_2.setBackground(right);
					Draft2_1.setBorderPainted(true);
					Draft2_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft2_2.setBorderPainted(true);
					Draft2_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(2).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(1).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom3_1.setBorderPainted(false);
					Kingdom3_1.setOpaque(true);
					Kingdom3_2.setBorderPainted(false);
					Kingdom3_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom3_1.setBackground(left);
					Kingdom3_2.setBackground(right);
					Draft2_1.setBorderPainted(true);
					Draft2_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft2_2.setBorderPainted(true);
					Draft2_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(3).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(1).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom4_1.setBorderPainted(false);
					Kingdom4_1.setOpaque(true);
					Kingdom4_2.setBorderPainted(false);
					Kingdom4_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom4_1.setBackground(left);
					Kingdom4_2.setBackground(right);
					Draft2_1.setBorderPainted(true);
					Draft2_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft2_2.setBorderPainted(true);
					Draft2_2.setOpaque(false);
					flag = false;
					if (KingdominoApplication.getGameplay().getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CreateNextDraft)) {
						/**
						 * create, order, reveal next draft 
						 * @author everyone except david and majd 
						 */
						controller.triggerEventA("draftReady");
						controller.triggerEventA("orderReady");
						if (game.getCurrentDraft().getIdSortedDominos().size()!= 0 ) {
							//numofdominos =4;
							Draft1_1.setBorderPainted(false);
							Draft1_1.setOpaque(true);
							Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
							Draft1_2.setBorderPainted(false);
							Draft1_2.setOpaque(true);
							Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(0).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(0).getRightCrown()) {
			    			case 0:
			    				Draft1_3.setIcon(blk);	break;
			    			case 1:
			    				Draft1_3.setIcon(c1);	break;
			    			case 2:
			    				Draft1_3.setIcon(c2);	break;
			    			case 3:
			    				Draft1_3.setIcon(c3);	break;}
							Draft2_1.setBorderPainted(false);
							Draft2_1.setOpaque(true);
							Draft2_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(1).getLeftTile()));
							Draft2_2.setBorderPainted(false);
							Draft2_2.setOpaque(true);
							Draft2_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(1).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(1).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(1).getRightCrown()) {
			    			case 0:
			    				Draft2_3.setIcon(blk);	break;
			    			case 1:
			    				Draft2_3.setIcon(c1);	break;
			    			case 2:
			    				Draft2_3.setIcon(c2);	break;
			    			case 3:
			    				Draft2_3.setIcon(c3);	break;}
							Draft3_1.setBorderPainted(false);
							Draft3_1.setOpaque(true);
							Draft3_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(2).getLeftTile()));
							Draft3_2.setBorderPainted(false);
							Draft3_2.setOpaque(true);
							Draft3_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(2).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(2).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(2).getRightCrown()) {
			    			case 0:
			    				Draft3_3.setIcon(blk);	break;
			    			case 1:
			    				Draft3_3.setIcon(c1);	break;
			    			case 2:
			    				Draft3_3.setIcon(c2);	break;
			    			case 3:
			    				Draft3_3.setIcon(c3);	break;}
							Draft4_1.setBorderPainted(false);
							Draft4_1.setOpaque(true);
							Draft4_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(3).getLeftTile()));
							Draft4_2.setBorderPainted(false);
							Draft4_2.setOpaque(true);
							Draft4_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(3).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(3).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(3).getRightCrown()) {
			    			case 0:
			    				Draft4_3.setIcon(blk);	break;
			    			case 1:
			    				Draft4_3.setIcon(c1);	break;
			    			case 2:
			    				Draft4_3.setIcon(c2);	break;
			    			case 3:
			    				Draft4_3.setIcon(c3);	break;}
							newlabel_1.setText((25-game.getAllDrafts().size())/2+"");
							//for (Player player: KingdominoApplication.getKingdomino().getCurrentGame().getPlayers()) {
							//	System.out.println("f");
							//	player.setDominoSelection(null);
							//	if (sel!= null) {
							//		player.getDominoSelection().delete();
							//	}
							//}
						}
					}
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(0).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(1).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom1_1.setBorderPainted(false);
					Kingdom1_1.setOpaque(true);
					Kingdom1_2.setBorderPainted(false);
					Kingdom1_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom1_1.setBackground(left);
					Kingdom1_2.setBackground(right);
					Draft2_1.setBorderPainted(true);
					Draft2_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft2_2.setBorderPainted(true);
					Draft2_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
			}else {
				Error error = new Error();
				error.setVisible(true);
			}
			}
			
		});
		
		Draft3_1.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/**
				 * choose next domino feature 
				 * @author everyone except david and majd 
				 */
				
				if ((gp.getGamestatus().equals(Gamestatus.Initializing)&&gp.getGamestatusInitializing().equals(GamestatusInitializing.SelectingFirstDomino))||(gp.getGamestatus().equals(Gamestatus.OnNextTurn)&&gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.SelectDomino) )) {
					Draft3_3.setIcon(blk);
				boolean flag = true;
				Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				OnNextTurn controller = new OnNextTurn();
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(2).getColor().toString())) {
					int id = game.getCurrentDraft().getIdSortedDomino(2).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom3_1.setBorderPainted(false);
					Kingdom3_1.setOpaque(true);
					Kingdom3_2.setBorderPainted(false);
					Kingdom3_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom3_1.setBackground(left);
					Kingdom3_2.setBackground(right);
					Draft3_1.setBorderPainted(true);
					Draft3_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft3_2.setBorderPainted(true);
					Draft3_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(3).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(2).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom4_1.setBorderPainted(false);
					Kingdom4_1.setOpaque(true);
					Kingdom4_2.setBorderPainted(false);
					Kingdom4_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom4_1.setBackground(left);
					Kingdom4_2.setBackground(right);
					Draft3_1.setBorderPainted(true);
					Draft3_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft3_2.setBorderPainted(true);
					Draft3_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
					if (KingdominoApplication.getGameplay().getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CreateNextDraft)) {
						/**
						 * create, order, reveal next draft feature 
						 * @author everyone except david and majd 
						 */
						controller.triggerEventA("draftReady");
						controller.triggerEventA("orderReady");
						if (game.getCurrentDraft().getIdSortedDominos().size()!= 0) {
							//numofdominos =4;
							Draft1_1.setBorderPainted(false);
							Draft1_1.setOpaque(true);
							Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
							Draft1_2.setBorderPainted(false);
							Draft1_2.setOpaque(true);
							Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(0).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(0).getRightCrown()) {
			    			case 0:
			    				Draft1_3.setIcon(blk);	break;
			    			case 1:
			    				Draft1_3.setIcon(c1);	break;
			    			case 2:
			    				Draft1_3.setIcon(c2);	break;
			    			case 3:
			    				Draft1_3.setIcon(c3);	break;}
							Draft2_1.setBorderPainted(false);
							Draft2_1.setOpaque(true);
							Draft2_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(1).getLeftTile()));
							Draft2_2.setBorderPainted(false);
							Draft2_2.setOpaque(true);
							Draft2_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(1).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(1).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(1).getRightCrown()) {
			    			case 0:
			    				Draft2_3.setIcon(blk);	break;
			    			case 1:
			    				Draft2_3.setIcon(c1);	break;
			    			case 2:
			    				Draft2_3.setIcon(c2);	break;
			    			case 3:
			    				Draft2_3.setIcon(c3);	break;}
							Draft3_1.setBorderPainted(false);
							Draft3_1.setOpaque(true);
							Draft3_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(2).getLeftTile()));
							Draft3_2.setBorderPainted(false);
							Draft3_2.setOpaque(true);
							Draft3_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(2).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(2).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(2).getRightCrown()) {
			    			case 0:
			    				Draft3_3.setIcon(blk);	break;
			    			case 1:
			    				Draft3_3.setIcon(c1);	break;
			    			case 2:
			    				Draft3_3.setIcon(c2);	break;
			    			case 3:
			    				Draft3_3.setIcon(c3);	break;}
							Draft4_1.setBorderPainted(false);
							Draft4_1.setOpaque(true);
							Draft4_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(3).getLeftTile()));
							Draft4_2.setBorderPainted(false);
							Draft4_2.setOpaque(true);
							Draft4_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(3).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(3).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(3).getRightCrown()) {
			    			case 0:
			    				Draft4_3.setIcon(blk);	break;
			    			case 1:
			    				Draft4_3.setIcon(c1);	break;
			    			case 2:
			    				Draft4_3.setIcon(c2);	break;
			    			case 3:
			    				Draft4_3.setIcon(c3);	break;}
							newlabel_1.setText((25-game.getAllDrafts().size())/2+"");
							//for (Player player: KingdominoApplication.getKingdomino().getCurrentGame().getPlayers()) {
							//	System.out.println("f");
							//	player.setDominoSelection(null);
							//	if (sel!= null) {
							//		player.getDominoSelection().delete();
							//	}
							//}
						}
					}
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(0).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(2).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom1_1.setBorderPainted(false);
					Kingdom1_1.setOpaque(true);
					Kingdom1_2.setBorderPainted(false);
					Kingdom1_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom1_1.setBackground(left);
					Kingdom1_2.setBackground(right);
					Draft3_1.setBorderPainted(true);
					Draft3_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft3_2.setBorderPainted(true);
					Draft3_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(1).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(2).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel!= null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom2_1.setBorderPainted(false);
					Kingdom2_1.setOpaque(true);
					Kingdom2_2.setBorderPainted(false);
					Kingdom2_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom2_1.setBackground(left);
					Kingdom2_2.setBackground(right);
					Draft3_1.setBorderPainted(true);
					Draft3_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft3_2.setBorderPainted(true);
					Draft3_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
			}else {
				Error error = new Error();
				error.setVisible(true);
			}
			}
		});
		
		Draft4_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/**
				 * choose next domino feature 
				 * @author everyone except david and majd 
				 */
				
				if ((gp.getGamestatus().equals(Gamestatus.Initializing)&&gp.getGamestatusInitializing().equals(GamestatusInitializing.SelectingFirstDomino))||(gp.getGamestatus().equals(Gamestatus.OnNextTurn)&&gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.SelectDomino) )) {
					Draft4_3.setIcon(blk);
				boolean flag = true;
				Game game = KingdominoApplication.getKingdomino().getCurrentGame();
				OnNextTurn controller = new OnNextTurn();
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(3).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(3).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel != null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom4_1.setBorderPainted(false);
					Kingdom4_1.setOpaque(true);
					Kingdom4_2.setBorderPainted(false);
					Kingdom4_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom4_1.setBackground(left);
					Kingdom4_2.setBackground(right);
					Draft4_1.setBorderPainted(true);
					Draft4_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft4_2.setBorderPainted(true);
					Draft4_2.setOpaque(false);
					flag = false;
					if (KingdominoApplication.getGameplay().getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CreateNextDraft)) {
						/**
						 * create, order, reveal next draft 
						 * @author 
						 */
						controller.triggerEventA("draftReady");
						controller.triggerEventA("orderReady");
						if (game.getCurrentDraft().getIdSortedDominos().size()!= 0) {
							//numofdominos =4;
							Draft1_1.setBorderPainted(false);
							Draft1_1.setOpaque(true);
							Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
							Draft1_2.setBorderPainted(false);
							Draft1_2.setOpaque(true);
							Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(0).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(0).getRightCrown()) {
			    			case 0:
			    				Draft1_3.setIcon(blk);	break;
			    			case 1:
			    				Draft1_3.setIcon(c1);	break;
			    			case 2:
			    				Draft1_3.setIcon(c2);	break;
			    			case 3:
			    				Draft1_3.setIcon(c3);	break;}
							Draft2_1.setBorderPainted(false);
							Draft2_1.setOpaque(true);
							Draft2_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(1).getLeftTile()));
							Draft2_2.setBorderPainted(false);
							Draft2_2.setOpaque(true);
							Draft2_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(1).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(1).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(1).getRightCrown()) {
			    			case 0:
			    				Draft2_3.setIcon(blk);	break;
			    			case 1:
			    				Draft2_3.setIcon(c1);	break;
			    			case 2:
			    				Draft2_3.setIcon(c2);	break;
			    			case 3:
			    				Draft2_3.setIcon(c3);	break;}
							Draft3_1.setBorderPainted(false);
							Draft3_1.setOpaque(true);
							Draft3_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(2).getLeftTile()));
							Draft3_2.setBorderPainted(false);
							Draft3_2.setOpaque(true);
							Draft3_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(2).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(2).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(2).getRightCrown()) {
			    			case 0:
			    				Draft3_3.setIcon(blk);	break;
			    			case 1:
			    				Draft3_3.setIcon(c1);	break;
			    			case 2:
			    				Draft3_3.setIcon(c2);	break;
			    			case 3:
			    				Draft3_3.setIcon(c3);	break;}
							Draft4_1.setBorderPainted(false);
							Draft4_1.setOpaque(true);
							Draft4_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(3).getLeftTile()));
							Draft4_2.setBorderPainted(false);
							Draft4_2.setOpaque(true);
							Draft4_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(3).getRightTile()));
							switch(game.getCurrentDraft().getIdSortedDomino(3).getLeftCrown()+game.getCurrentDraft().getIdSortedDomino(3).getRightCrown()) {
			    			case 0:
			    				Draft4_3.setIcon(blk);	break;
			    			case 1:
			    				Draft4_3.setIcon(c1);	break;
			    			case 2:
			    				Draft4_3.setIcon(c2);	break;
			    			case 3:
			    				Draft4_3.setIcon(c3);	break;}
							newlabel_1.setText((25-game.getAllDrafts().size())/2+"");
							//for (Player player: KingdominoApplication.getKingdomino().getCurrentGame().getPlayers()) {
							//	System.out.println("f");
							//	player.setDominoSelection(null);
							//	if (sel!= null) {
							//		player.getDominoSelection().delete();
							//	}
							//}
						}
					}
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(0).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(3).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel != null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom1_1.setBorderPainted(false);
					Kingdom1_1.setOpaque(true);
					Kingdom1_2.setBorderPainted(false);
					Kingdom1_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom1_1.setBackground(left);
					Kingdom1_2.setBackground(right);
					Draft4_1.setBorderPainted(true);
					Draft4_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft4_2.setBorderPainted(true);
					Draft4_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(1).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(3).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel != null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom2_1.setBorderPainted(false);
					Kingdom2_1.setOpaque(true);
					Kingdom2_2.setBorderPainted(false);
					Kingdom2_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom2_1.setBackground(left);
					Kingdom2_2.setBackground(right);
					Draft4_1.setBorderPainted(true);
					Draft4_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft4_2.setBorderPainted(true);
					Draft4_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(2).getColor().toString())&& flag == true) {
					int id = game.getCurrentDraft().getIdSortedDomino(3).getId();
					//player1 = game.getNextPlayer();
					DominoSelection sel = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection();
					if (sel != null) {
						game.getNextPlayer().getDominoSelection().delete();
					}
					game.getNextPlayer().setDominoSelection(null);
					controller.triggerEventB("dominoSelected", id);
					int x = getPlayerIndex(game.getNextPlayer());
					if (x>=1) {
					sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					} else if (x == 0) {
						sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection();
					}
					Kingdom3_1.setBorderPainted(false);
					Kingdom3_1.setOpaque(true);
					Kingdom3_2.setBorderPainted(false);
					Kingdom3_2.setOpaque(true);
					Color left = getColorByTile(sel.getDomino().getLeftTile());
					Color right = getColorByTile(sel.getDomino().getRightTile());
					Kingdom3_1.setBackground(left);
					Kingdom3_2.setBackground(right);
					Draft4_1.setBorderPainted(true);
					Draft4_1.setOpaque(false);
					//Draft1_1.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getLeftTile()));
					Draft4_2.setBorderPainted(true);
					Draft4_2.setOpaque(false);
					flag = false;
					//Draft1_2.setBackground(getColorByTile(game.getCurrentDraft().getIdSortedDomino(0).getRightTile()));
				}
				
			}else {
				Error error = new Error();
				error.setVisible(true);
			}
			}
			
		});
		JLabel newlabel= new JLabel("");
		contentPane.add(newlabel);
		newlabel.setBounds(580, 90, 150, 30);
		newlabel.setText("num of drafts left: ");
		
		JCheckBox GameRules = new JCheckBox("display game rules");
		GameRules.setBounds(555, 27, 150, 25);
		GameRules.setBackground(UIManager.getColor("Button.background"));
		GameRules.setBorderPainted(true);
		contentPane.add(GameRules);
		JTextArea txt = new JTextArea();
		txt.setBounds(80,130,1000,410);
		txt.setOpaque(false);
		txt.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txt.setText("For each player:\n\nIn the first round:\nChoose a domino from the pile above and your turn is over. When all the players has completed their selection, \nplace/disacrd the domino following the instructions below and choose a domino from the newly generated pile. \n\nIn the rest of the game:\nStep 1:  Please choose a new domino from the dominoes above.\nStep 2:  Move the chosen domino to your kingdom by clicking the “up”, “down”,  “left”, “right” buttons. Once \nyou’ve decided the position of the domino, click “place” to place it. If the domino cannot be placed in your \nkingdom, click “discard” to discard it.\nStep 3:  After you’ve placed or discarded the domino, choose a new domino from the dominoes \nabove and your turn is over.");
		
		txt.setVisible(false);
		txt.setBackground(new Color(0, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane(txt);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		contentPane.add(txt);
		GameRules.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JCheckBox chckbxNewCheckBox = (JCheckBox) event.getSource();
		        if (chckbxNewCheckBox.isSelected()) {
		        	txt.setVisible(true);// do something if check box is selected
		        	
		        } else {
		        	txt.setVisible(false);// check box is unselected, do something else
		        }
		    }});


		
		DiscardDomino discard = new DiscardDomino();
		int down= 0x2B07;
		/**
		 * move current domino feature 
		 * @author everyone except david and majd 
		 */
		JButton btnNewButton_1 = new JButton(getEmojiByUnicode(down));
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setBounds(30, 60, 30, 30);
		btnNewButton_1.setEnabled(true);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Player player = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer();
				if ( KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
					
						if (!Kingdom1_1.isBorderPainted()) {
							Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
							DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, kingdom, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection().getDomino());
							dominoinkingdom.setDirection(DirectionKind.Right);
							boolean forceToDiscard = discard.forceToDiscard(dominoinkingdom);
							if (forceToDiscard == false) {
							if ((kingdom1[2][8].isBorderPainted()||(kingdom1[3][8].isBorderPainted()))){
							kingdom1[2][8].setBorderPainted(false);
							Color color1 = Kingdom1_1.getBackground();
							Color color2 = Kingdom1_2.getBackground();
							kingdom1[2][8].setOpaque(true);
							kingdom1[3][8].setBorderPainted(false);
							kingdom1[3][8].setOpaque(true);
							kingdom1[2][8].setBackground(color1);
							kingdom1[3][8].setBackground(color2);
							//Domino domino = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino();
							//int x = getPlayerIndex(game.getNextPlayer());
							
							//if (x>=1) {
							//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getKingdom();
							//sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
							//} else  {
							//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getKingdom();
							//}
							
							
							}else {
								boolean flag = true;
								for (int i =-4;i<5 && flag;i++) {
									for (int j = -4;j<5&& flag;j++) {
										
										Domino domino = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino();
										//int x = getPlayerIndex(game.getNextPlayer());
										//Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
										//if (x>=1) {
										//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getKingdom();
										//sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
										//} else  {
										//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getKingdom();
										//}
										PlaceDomino a = new PlaceDomino();
										//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, kingdom, domino);
										//Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
										//DominoInKingdom dominoInK = new DominoInKingdom (i, j, kingdom, domino);
										//kingdom.addTerritory(dominoInK);
										dominoinkingdom.setX(i);
										dominoinkingdom.setY(j);
										dominoinkingdom.setDirection(DirectionKind.Down);
										if (a.isValid(dominoinkingdom)) {
											kingdom1[i+4][j+4].setBorderPainted(false);
											Color color1 = Kingdom1_1.getBackground();
											Color color2 = Kingdom1_2.getBackground();
											int[]adj = adjacentpos(dominoinkingdom);
											kingdom1[i+4][j+4].setOpaque(true);
											kingdom1[adj[0]+4][adj[1]+4].setBorderPainted(false);
											kingdom1[adj[0]+4][adj[1]+4].setOpaque(true);
											kingdom1[i+4][j+4].setBackground(color1);
											kingdom1[adj[0]+4][adj[1]+4].setBackground(color2);
										    
											Kingdom1_1.setBackground(null);
											Kingdom1_1.setBorderPainted(true);
											Kingdom1_2.setBackground(null);
											Kingdom1_2.setBorderPainted(true);
											flag = false;
											//kingdom.removeTerritory(dominoInK);
							
									}if (flag) {
										dominoinkingdom.setDirection(DirectionKind.Up);
										if (a.isValid(dominoinkingdom)) {
											kingdom1[i+4][j+4].setBorderPainted(false);
											Color color1 = Kingdom1_1.getBackground();
											Color color2 = Kingdom1_2.getBackground();
											int[]adj = adjacentpos(dominoinkingdom);
											kingdom1[i+4][j+4].setOpaque(true);
											kingdom1[adj[0]+4][adj[1]+4].setBorderPainted(false);
											kingdom1[adj[0]+4][adj[1]+4].setOpaque(true);
											kingdom1[i+4][j+4].setBackground(color1);
											kingdom1[adj[0]+4][adj[1]+4].setBackground(color2);
										    
											Kingdom1_1.setBackground(null);
											Kingdom1_1.setBorderPainted(true);
											Kingdom1_2.setBackground(null);
											Kingdom1_2.setBorderPainted(true);
											flag = false;
										}
									}if (flag) {
										dominoinkingdom.setDirection(DirectionKind.Left);
										if (a.isValid(dominoinkingdom)) {
											kingdom1[i+4][j+4].setBorderPainted(false);
											Color color1 = Kingdom1_1.getBackground();
											Color color2 = Kingdom1_2.getBackground();
											int[]adj = adjacentpos(dominoinkingdom);
											kingdom1[i+4][j+4].setOpaque(true);
											kingdom1[adj[0]+4][adj[1]+4].setBorderPainted(false);
											kingdom1[adj[0]+4][adj[1]+4].setOpaque(true);
											kingdom1[i+4][j+4].setBackground(color1);
											kingdom1[adj[0]+4][adj[1]+4].setBackground(color2);
										    
											Kingdom1_1.setBackground(null);
											Kingdom1_1.setBorderPainted(true);
											Kingdom1_2.setBackground(null);
											Kingdom1_2.setBorderPainted(true);
											flag = false;
										}
									}
									if (flag) {
										dominoinkingdom.setDirection(DirectionKind.Right);
										if (a.isValid(dominoinkingdom)) {
											kingdom1[i+4][j+4].setBorderPainted(false);
											Color color1 = Kingdom1_1.getBackground();
											Color color2 = Kingdom1_2.getBackground();
											int[]adj = adjacentpos(dominoinkingdom);
											kingdom1[i+4][j+4].setOpaque(true);
											kingdom1[adj[0]+4][adj[1]+4].setBorderPainted(false);
											kingdom1[adj[0]+4][adj[1]+4].setOpaque(true);
											kingdom1[i+4][j+4].setBackground(color1);
											kingdom1[adj[0]+4][adj[1]+4].setBackground(color2);
										    
											Kingdom1_1.setBackground(null);
											Kingdom1_1.setBorderPainted(true);
											Kingdom1_2.setBackground(null);
											Kingdom1_2.setBorderPainted(true);
											flag = false;
										}
									}
							}
								}
							}
							}
							//dominoinkingdom.delete();
							//kingdom.removeTerritory(dominoinkingdom);
							//boolean forceToDiscard = discard.forceToDiscard(dominoinkingdom);
							if (forceToDiscard == false) {
								//kingdom.addTerritory(dominoinkingdom);
							
							kingdom.addTerritory(dominoinkingdom);
							Kingdom1_1.setBackground(null);
							Kingdom1_1.setBorderPainted(true);
							Kingdom1_2.setBackground(null);
							Kingdom1_2.setBorderPainted(true);
							}else {
								
								/**
								 * discard domino feature 
								 * @author everyone except david and majd 
								 */
								
								JButton discard = new JButton("discard");
								contentPane.add(discard);
								discard.setBounds(550, 300, 100, 30);
								discard.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										Kingdom1_1.setBackground(null);
										Kingdom1_1.setBorderPainted(true);
										Kingdom1_2.setBackground(null);
										Kingdom1_2.setBorderPainted(true);
										kingdom1[2][8].setBorderPainted(true);
										
										kingdom1[2][8].setOpaque(false);
										kingdom1[3][8].setBorderPainted(true);
										kingdom1[3][8].setOpaque(false);
										controller.triggerEventD("discardRequest", dominoinkingdom);
										dominoinkingdom.delete();
										if (gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CalculateResult)) {
											GameEnd end = new GameEnd();
											end.setVisible(true);
										}
										discard.setVisible(false);
										
									}
									
								});
								if (gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CalculateResult)) {
									GameEnd end = new GameEnd();
									end.setVisible(true);
								}
								//System.out.println("wrong");
								//Discard window = new Discard();
								//window.setVisible(true);
								
							}
							
								
						}else {
							//if (sel!= null) {
								Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
								DominoInKingdom dominoinkingdom = (DominoInKingdom) kingdom.getTerritory(kingdom.getTerritories().size()-1);
								int xbe = dominoinkingdom.getX();
								int ybe = dominoinkingdom.getY();
								int []adjbe = adjacentpos(dominoinkingdom);
								if (!((xbe== 0&& ybe==1)||(adjbe[0]== 0 && adjbe[1]== 1))) {
								kingdom1[xbe+4][ybe+4].setBackground(null);
								kingdom1[xbe+4][ybe+4].setBorderPainted(true);
								
								kingdom1[adjbe[0]+4][adjbe[1]+4].setBackground(null);
								kingdom1[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom(), sel.getDomino());
								KingdominoController.Move_Current_Domino(DirectionKind.Down, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getRightTile());
								kingdom.addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(kingdom)) {
									KingdominoController.Move_Current_Domino(DirectionKind.Up, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								//if (kingdom1[x+4][y+4].isBorderPainted()) {
			
								kingdom1[x+4][y+4].setBorderPainted(false);
								kingdom1[x+4][y+4].setOpaque(true);
								kingdom1[x+4][y+4].setBackground(color1);
								int []adj = adjacentpos(dominoinkingdom);
								kingdom1[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom1[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom1[adj[0]+4][adj[1]+4].setBackground(color2);
							   kingdom.removeTerritory(dominoinkingdom);
								//}
								//}
							}
							//}
						}
					
				
			
			}else if ( KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
				if (!Kingdom2_1.isBorderPainted()) {
					Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
					DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, kingdom, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection().getDomino());
					dominoinkingdom.setDirection(DirectionKind.Right);
					boolean forceToDiscard = discard.forceToDiscard(dominoinkingdom);
					if (forceToDiscard == false) {
					if ((kingdom2[2][8].isBorderPainted()||(kingdom2[3][8].isBorderPainted()))){
					kingdom2[2][8].setBorderPainted(false);
					Color color1 = Kingdom2_1.getBackground();
					Color color2 = Kingdom2_2.getBackground();
					kingdom2[2][8].setOpaque(true);
					kingdom2[3][8].setBorderPainted(false);
					kingdom2[3][8].setOpaque(true);
					kingdom2[2][8].setBackground(color1);
					kingdom2[3][8].setBackground(color2);
					//Domino domino = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino();
					//int x = getPlayerIndex(game.getNextPlayer());
					
					//if (x>=1) {
					//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getKingdom();
					//sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					//} else  {
					//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getKingdom();
					//}
					
					
					}else {
						boolean flag = true;
						for (int i =-4;i<5 && flag;i++) {
							for (int j = -4;j<5&& flag;j++) {
								
								Domino domino = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino();
								//int x = getPlayerIndex(game.getNextPlayer());
								//Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
								//if (x>=1) {
								//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getKingdom();
								//sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
								//} else  {
								//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getKingdom();
								//}
								PlaceDomino a = new PlaceDomino();
								//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, kingdom, domino);
								//Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
								//DominoInKingdom dominoInK = new DominoInKingdom (i, j, kingdom, domino);
								//kingdom.addTerritory(dominoInK);
								dominoinkingdom.setX(i);
								dominoinkingdom.setY(j);
								dominoinkingdom.setDirection(DirectionKind.Down);
								if (a.isValid(dominoinkingdom)) {
									kingdom2[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom2_1.getBackground();
									Color color2 = Kingdom2_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom2[i+4][j+4].setOpaque(true);
									kingdom2[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom2[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom2[i+4][j+4].setBackground(color1);
									kingdom2[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom2_1.setBackground(null);
									Kingdom2_1.setBorderPainted(true);
									Kingdom2_2.setBackground(null);
									Kingdom2_2.setBorderPainted(true);
									flag = false;
									//kingdom.removeTerritory(dominoInK);
					
							}if (flag) {
								dominoinkingdom.setDirection(DirectionKind.Up);
								if (a.isValid(dominoinkingdom)) {
									kingdom2[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom2_1.getBackground();
									Color color2 = Kingdom2_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom2[i+4][j+4].setOpaque(true);
									kingdom2[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom2[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom2[i+4][j+4].setBackground(color1);
									kingdom2[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom2_1.setBackground(null);
									Kingdom2_1.setBorderPainted(true);
									Kingdom2_2.setBackground(null);
									Kingdom2_2.setBorderPainted(true);
									flag = false;
								}
							}if (flag) {
								dominoinkingdom.setDirection(DirectionKind.Left);
								if (a.isValid(dominoinkingdom)) {
									kingdom2[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom2_1.getBackground();
									Color color2 = Kingdom2_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom2[i+4][j+4].setOpaque(true);
									kingdom2[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom2[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom2[i+4][j+4].setBackground(color1);
									kingdom2[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom2_1.setBackground(null);
									Kingdom2_1.setBorderPainted(true);
									Kingdom2_2.setBackground(null);
									Kingdom2_2.setBorderPainted(true);
									flag = false;
								}
							}
							if (flag) {
								dominoinkingdom.setDirection(DirectionKind.Right);
								if (a.isValid(dominoinkingdom)) {
									kingdom2[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom2_1.getBackground();
									Color color2 = Kingdom2_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom2[i+4][j+4].setOpaque(true);
									kingdom2[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom2[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom2[i+4][j+4].setBackground(color1);
									kingdom2[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom2_1.setBackground(null);
									Kingdom2_1.setBorderPainted(true);
									Kingdom2_2.setBackground(null);
									Kingdom2_2.setBorderPainted(true);
									flag = false;
								}
							}
					}
						}
					}
					}
					//dominoinkingdom.delete();
					//kingdom.removeTerritory(dominoinkingdom);
					//boolean forceToDiscard = discard.forceToDiscard(dominoinkingdom);
					if (forceToDiscard == false) {
						//kingdom.addTerritory(dominoinkingdom);
					
					kingdom.addTerritory(dominoinkingdom);
					Kingdom2_1.setBackground(null);
					Kingdom2_1.setBorderPainted(true);
					Kingdom2_2.setBackground(null);
					Kingdom2_2.setBorderPainted(true);
					}else {
						//Discarddomino discard = new Discarddomino();
						//boolean alreayDiscarded = false;
						//for (DominoInKingdom dominoInK : kingdom.getTerritories()) {
							
						//}
						/**
						 * discard domino feature 
						 * @author everyone except david and majd 
						 */
						JButton discard = new JButton("discard");
						contentPane.add(discard);
						discard.setBounds(550, 300, 100, 30);
						discard.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								Kingdom2_1.setBackground(null);
								Kingdom2_1.setBorderPainted(true);
								Kingdom2_2.setBackground(null);
								Kingdom2_2.setBorderPainted(true);
								kingdom2[2][8].setBorderPainted(true);
								
								kingdom2[2][8].setOpaque(false);
								kingdom2[3][8].setBorderPainted(true);
								kingdom2[3][8].setOpaque(false);
								controller.triggerEventD("discardRequest", dominoinkingdom);
								dominoinkingdom.delete();
								if (gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CalculateResult)) {
									GameEnd end = new GameEnd();
									end.setVisible(true);
								}
								discard.setVisible(false);
								
							}
							
						});
						if (gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CalculateResult)) {
							GameEnd end = new GameEnd();
							end.setVisible(true);
						}
						//System.out.println("wrong");
						//Discard window = new Discard();
						//window.setVisible(true);
						
					}
			
					
				}else {
					//if (sel!= null) {
						Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
						
						DominoInKingdom dominoinkingdom = (DominoInKingdom) kingdom.getTerritory(kingdom.getTerritories().size()-1);
						int xbe = dominoinkingdom.getX();
						int ybe = dominoinkingdom.getY();
						int []adjbe = adjacentpos(dominoinkingdom);
						if (!((xbe== 0&& ybe==1)||(adjbe[0]== 0 && adjbe[1]== 1))) {
						kingdom2[xbe+4][ybe+4].setBackground(null);
						kingdom2[xbe+4][ybe+4].setBorderPainted(true);
						
						kingdom2[adjbe[0]+4][adjbe[1]+4].setBackground(null);
						kingdom2[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
						//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom(), sel.getDomino());
						KingdominoController.Move_Current_Domino(DirectionKind.Down, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
						kingdom.addTerritory(dominoinkingdom);
						ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
						if (!con.verifyNoOverlapping(kingdom)) {
							KingdominoController.Move_Current_Domino(DirectionKind.Up, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
							//kingdom.removeTerritory(dominoinkingdom);
						}
						Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getLeftTile());
						Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getRightTile());
						int x = dominoinkingdom.getX();
						int y = dominoinkingdom.getY();
	
						kingdom2[x+4][y+4].setBorderPainted(false);
						kingdom2[x+4][y+4].setOpaque(true);
						kingdom2[x+4][y+4].setBackground(color1);
						int []adj = adjacentpos(dominoinkingdom);
						kingdom2[adj[0]+4][adj[1]+4].setBorderPainted(false);
						kingdom2[adj[0]+4][adj[1]+4].setOpaque(true);
					   kingdom2[adj[0]+4][adj[1]+4].setBackground(color2);
					   kingdom.removeTerritory(dominoinkingdom);
					//}
					}
				}
			}else if ( KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
				if (!Kingdom3_1.isBorderPainted()) {
					Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
					DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, kingdom, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection().getDomino());
					dominoinkingdom.setDirection(DirectionKind.Right);
					boolean forceToDiscard = discard.forceToDiscard(dominoinkingdom);
					if (forceToDiscard == false) {
					if ((kingdom3[2][8].isBorderPainted()||(kingdom3[3][8].isBorderPainted()))){
					kingdom3[2][8].setBorderPainted(false);
					Color color1 = Kingdom3_1.getBackground();
					Color color2 = Kingdom3_2.getBackground();
					kingdom3[2][8].setOpaque(true);
					kingdom3[3][8].setBorderPainted(false);
					kingdom3[3][8].setOpaque(true);
					kingdom3[2][8].setBackground(color1);
					kingdom3[3][8].setBackground(color2);
					//Domino domino = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino();
					//int x = getPlayerIndex(game.getNextPlayer());
					
					//if (x>=1) {
					//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getKingdom();
					//sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					//} else  {
					//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getKingdom();
					//}
					
					
					}else {
						boolean flag = true;
						for (int i =-4;i<5 && flag;i++) {
							for (int j = -4;j<5&& flag;j++) {
								
								Domino domino = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino();
								//int x = getPlayerIndex(game.getNextPlayer());
								//Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
								//if (x>=1) {
								//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getKingdom();
								//sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
								//} else  {
								//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getKingdom();
								//}
								PlaceDomino a = new PlaceDomino();
								//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, kingdom, domino);
								//Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
								//DominoInKingdom dominoInK = new DominoInKingdom (i, j, kingdom, domino);
								//kingdom.addTerritory(dominoInK);
								dominoinkingdom.setX(i);
								dominoinkingdom.setY(j);
								dominoinkingdom.setDirection(DirectionKind.Down);
								if (a.isValid(dominoinkingdom)) {
									kingdom3[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom3_1.getBackground();
									Color color2 = Kingdom3_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom3[i+4][j+4].setOpaque(true);
									kingdom3[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom3[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom3[i+4][j+4].setBackground(color1);
									kingdom3[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom3_1.setBackground(null);
									Kingdom3_1.setBorderPainted(true);
									Kingdom3_2.setBackground(null);
									Kingdom3_2.setBorderPainted(true);
									flag = false;
									//kingdom.removeTerritory(dominoInK);
					
							}if (flag) {
								dominoinkingdom.setDirection(DirectionKind.Up);
								if (a.isValid(dominoinkingdom)) {
									kingdom3[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom3_1.getBackground();
									Color color2 = Kingdom3_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom3[i+4][j+4].setOpaque(true);
									kingdom3[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom3[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom3[i+4][j+4].setBackground(color1);
									kingdom3[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom3_1.setBackground(null);
									Kingdom3_1.setBorderPainted(true);
									Kingdom3_2.setBackground(null);
									Kingdom3_2.setBorderPainted(true);
									flag = false;
								}
							}if (flag) {
								dominoinkingdom.setDirection(DirectionKind.Left);
								if (a.isValid(dominoinkingdom)) {
									kingdom3[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom3_1.getBackground();
									Color color2 = Kingdom3_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom3[i+4][j+4].setOpaque(true);
									kingdom3[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom3[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom3[i+4][j+4].setBackground(color1);
									kingdom3[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom3_1.setBackground(null);
									Kingdom3_1.setBorderPainted(true);
									Kingdom3_2.setBackground(null);
									Kingdom3_2.setBorderPainted(true);
									flag = false;
								}
							}
							if (flag) {
								dominoinkingdom.setDirection(DirectionKind.Right);
								if (a.isValid(dominoinkingdom)) {
									kingdom3[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom3_1.getBackground();
									Color color2 = Kingdom3_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom3[i+4][j+4].setOpaque(true);
									kingdom3[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom3[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom3[i+4][j+4].setBackground(color1);
									kingdom3[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom3_1.setBackground(null);
									Kingdom3_1.setBorderPainted(true);
									Kingdom3_2.setBackground(null);
									Kingdom3_2.setBorderPainted(true);
									flag = false;
								}
							}
					}
						}
					}
					}
					//dominoinkingdom.delete();
					//kingdom.removeTerritory(dominoinkingdom);
					//boolean forceToDiscard = discard.forceToDiscard(dominoinkingdom);
					if (forceToDiscard == false) {
						//kingdom.addTerritory(dominoinkingdom);
					
					kingdom.addTerritory(dominoinkingdom);
					Kingdom3_1.setBackground(null);
					Kingdom3_1.setBorderPainted(true);
					Kingdom3_2.setBackground(null);
					Kingdom3_2.setBorderPainted(true);
					}else {
						/**
						 * discard domino feature 
						 * @author everyone except david and majd 
						 */
						JButton discard = new JButton("discard");
						contentPane.add(discard);
						discard.setBounds(550, 300, 100, 30);
						discard.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								Kingdom3_1.setBackground(null);
								Kingdom3_1.setBorderPainted(true);
								Kingdom3_2.setBackground(null);
								Kingdom3_2.setBorderPainted(true);
								kingdom3[2][8].setBorderPainted(true);
								
								kingdom3[2][8].setOpaque(false);
								kingdom3[3][8].setBorderPainted(true);
								kingdom3[3][8].setOpaque(false);
								controller.triggerEventD("discardRequest", dominoinkingdom);
								dominoinkingdom.delete();
								if (gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CalculateResult)) {
									GameEnd end = new GameEnd();
									end.setVisible(true);
								}
								discard.setVisible(false);
								
							}
							
						});
						if (gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CalculateResult)) {
							GameEnd end = new GameEnd();
							end.setVisible(true);
						}
						//System.out.println("wrong");
						//Discard window = new Discard();
						//window.setVisible(true);
						
					}
					
				}else {
					if (KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection()!= null) {
						Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
						
						DominoInKingdom dominoinkingdom = (DominoInKingdom) kingdom.getTerritory(kingdom.getTerritories().size()-1);
						int xbe = dominoinkingdom.getX();
						int ybe = dominoinkingdom.getY();
						int []adjbe = adjacentpos(dominoinkingdom);
						if (!((xbe== 0&& ybe==1)||(adjbe[0]== 0 && adjbe[1]== 1))) {
						kingdom3[xbe+4][ybe+4].setBackground(null);
						kingdom3[xbe+4][ybe+4].setBorderPainted(true);
						
						kingdom3[adjbe[0]+4][adjbe[1]+4].setBackground(null);
						kingdom3[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
						//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom(), sel.getDomino());
						KingdominoController.Move_Current_Domino(DirectionKind.Down, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
						kingdom.addTerritory(dominoinkingdom);
						ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
						if (!con.verifyNoOverlapping(kingdom)) {
							KingdominoController.Move_Current_Domino(DirectionKind.Up, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
							//kingdom.removeTerritory(dominoinkingdom);
						}
						Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getLeftTile());
						Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getRightTile());
						int x = dominoinkingdom.getX();
						int y = dominoinkingdom.getY();
	
						kingdom3[x+4][y+4].setBorderPainted(false);
						kingdom3[x+4][y+4].setOpaque(true);
						kingdom3[x+4][y+4].setBackground(color1);
						int []adj = adjacentpos(dominoinkingdom);
						kingdom3[adj[0]+4][adj[1]+4].setBorderPainted(false);
						kingdom3[adj[0]+4][adj[1]+4].setOpaque(true);
					   kingdom3[adj[0]+4][adj[1]+4].setBackground(color2);
					   kingdom.removeTerritory(dominoinkingdom);
					}
					}
				}
			}else if ( KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())) {
				if (!Kingdom4_1.isBorderPainted()) {
					Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
					DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, kingdom, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getDominoSelection().getDomino());
					dominoinkingdom.setDirection(DirectionKind.Right);
					boolean forceToDiscard = discard.forceToDiscard(dominoinkingdom);
					if (forceToDiscard == false) {
					if ((kingdom4[2][8].isBorderPainted()||(kingdom4[3][8].isBorderPainted()))){
					kingdom4[2][8].setBorderPainted(false);
					Color color1 = Kingdom4_1.getBackground();
					Color color2 = Kingdom4_2.getBackground();
					kingdom4[2][8].setOpaque(true);
					kingdom4[3][8].setBorderPainted(false);
					kingdom4[3][8].setOpaque(true);
					kingdom4[2][8].setBackground(color1);
					kingdom4[3][8].setBackground(color2);
					Domino domino = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino();
					//int x = getPlayerIndex(game.getNextPlayer());
					
					//if (x>=1) {
					//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getKingdom();
					//sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
					//} else  {
					//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getKingdom();
					//}
					
					
					}else {
						boolean flag = true;
						for (int i =-4;i<5 && flag;i++) {
							for (int j = -4;j<5&& flag;j++) {
								
								Domino domino = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino();
								//int x = getPlayerIndex(game.getNextPlayer());
								//Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
								//if (x>=1) {
								//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getKingdom();
								//sel = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(x-1).getDominoSelection();
								//} else  {
								//	kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getKingdom();
								//}
								PlaceDomino a = new PlaceDomino();
								//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, kingdom, domino);
								//Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
								//DominoInKingdom dominoInK = new DominoInKingdom (i, j, kingdom, domino);
								//kingdom.addTerritory(dominoInK);
								dominoinkingdom.setX(i);
								dominoinkingdom.setY(j);
								dominoinkingdom.setDirection(DirectionKind.Down);
								if (a.isValid(dominoinkingdom)) {
									kingdom4[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom4_1.getBackground();
									Color color2 = Kingdom4_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom4[i+4][j+4].setOpaque(true);
									kingdom4[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom4[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom4[i+4][j+4].setBackground(color1);
									kingdom4[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom4_1.setBackground(null);
									Kingdom4_1.setBorderPainted(true);
									Kingdom4_2.setBackground(null);
									Kingdom4_2.setBorderPainted(true);
									flag = false;
									//kingdom.removeTerritory(dominoInK);
					
							}if (flag) {
								dominoinkingdom.setDirection(DirectionKind.Up);
								if (a.isValid(dominoinkingdom)) {
									kingdom4[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom4_1.getBackground();
									Color color2 = Kingdom4_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom4[i+4][j+4].setOpaque(true);
									kingdom4[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom4[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom4[i+4][j+4].setBackground(color1);
									kingdom4[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom4_1.setBackground(null);
									Kingdom4_1.setBorderPainted(true);
									Kingdom4_2.setBackground(null);
									Kingdom4_2.setBorderPainted(true);
									flag = false;
								}
							}if (flag) {
								dominoinkingdom.setDirection(DirectionKind.Left);
								if (a.isValid(dominoinkingdom)) {
									kingdom4[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom4_1.getBackground();
									Color color2 = Kingdom4_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom4[i+4][j+4].setOpaque(true);
									kingdom4[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom4[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom4[i+4][j+4].setBackground(color1);
									kingdom4[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom4_1.setBackground(null);
									Kingdom4_1.setBorderPainted(true);
									Kingdom4_2.setBackground(null);
									Kingdom4_2.setBorderPainted(true);
									flag = false;
								}
							}
							if (flag) {
								dominoinkingdom.setDirection(DirectionKind.Right);
								if (a.isValid(dominoinkingdom)) {
									kingdom4[i+4][j+4].setBorderPainted(false);
									Color color1 = Kingdom4_1.getBackground();
									Color color2 = Kingdom4_2.getBackground();
									int[]adj = adjacentpos(dominoinkingdom);
									kingdom4[i+4][j+4].setOpaque(true);
									kingdom4[adj[0]+4][adj[1]+4].setBorderPainted(false);
									kingdom4[adj[0]+4][adj[1]+4].setOpaque(true);
									kingdom4[i+4][j+4].setBackground(color1);
									kingdom4[adj[0]+4][adj[1]+4].setBackground(color2);
								    
									Kingdom4_1.setBackground(null);
									Kingdom4_1.setBorderPainted(true);
									Kingdom4_2.setBackground(null);
									Kingdom4_2.setBorderPainted(true);
									flag = false;
								}
							}
					}
						}
					}
					}
					//dominoinkingdom.delete();
					//kingdom.removeTerritory(dominoinkingdom);
					//boolean forceToDiscard = discard.forceToDiscard(dominoinkingdom);
					if (forceToDiscard == false) {
						//kingdom.addTerritory(dominoinkingdom);
					
					kingdom.addTerritory(dominoinkingdom);
					Kingdom4_1.setBackground(null);
					Kingdom4_1.setBorderPainted(true);
					Kingdom4_2.setBackground(null);
					Kingdom4_2.setBorderPainted(true);
					}else {
						/**
						 * discard domino feature 
						 * @author everyone except david and majd 
						 */
						JButton discard = new JButton("discard");
						contentPane.add(discard);
						discard.setBounds(550, 300, 100, 30);
						discard.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								Kingdom4_1.setBackground(null);
								Kingdom4_1.setBorderPainted(true);
								Kingdom4_2.setBackground(null);
								Kingdom4_2.setBorderPainted(true);
								kingdom4[2][8].setBorderPainted(true);
								
								kingdom4[2][8].setOpaque(false);
								kingdom4[3][8].setBorderPainted(true);
								kingdom4[3][8].setOpaque(false);
								controller.triggerEventD("discardRequest", dominoinkingdom);
								dominoinkingdom.delete();
								if (gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CalculateResult)) {
									GameEnd end = new GameEnd();
									end.setVisible(true);
								}
								discard.setVisible(false);
								
							}
							
						});
						if (gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CalculateResult)) {
							GameEnd end = new GameEnd();
							end.setVisible(true);
						}
						//System.out.println("wrong");
						//Discard window = new Discard();
						//window.setVisible(true);
						
					}
					
				}else {
					if (KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection()!= null) {
						Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
						
						DominoInKingdom dominoinkingdom = (DominoInKingdom) kingdom.getTerritory(kingdom.getTerritories().size()-1);
						int xbe = dominoinkingdom.getX();
						int ybe = dominoinkingdom.getY();
						int []adjbe = adjacentpos(dominoinkingdom);
						if (!((xbe== 0&& ybe==1)||(adjbe[0]== 0 && adjbe[1]== 1))) {
						kingdom4[xbe+4][ybe+4].setBackground(null);
						kingdom4[xbe+4][ybe+4].setBorderPainted(true);
						
						kingdom4[adjbe[0]+4][adjbe[1]+4].setBackground(null);
						kingdom4[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
						//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom(), sel.getDomino());
						KingdominoController.Move_Current_Domino(DirectionKind.Down, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
						kingdom.addTerritory(dominoinkingdom);
						ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
						if (!con.verifyNoOverlapping(kingdom)) {
							KingdominoController.Move_Current_Domino(DirectionKind.Up, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
							//kingdom.removeTerritory(dominoinkingdom);
						}
						Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getLeftTile());
						Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getRightTile());
						int x = dominoinkingdom.getX();
						int y = dominoinkingdom.getY();
	
						kingdom4[x+4][y+4].setBorderPainted(false);
						kingdom4[x+4][y+4].setOpaque(true);
						kingdom4[x+4][y+4].setBackground(color1);
						int []adj = adjacentpos(dominoinkingdom);
						kingdom4[adj[0]+4][adj[1]+4].setBorderPainted(false);
						kingdom4[adj[0]+4][adj[1]+4].setOpaque(true);
					   kingdom4[adj[0]+4][adj[1]+4].setBackground(color2);
					   kingdom.removeTerritory(dominoinkingdom);
					}
					}
				}
			}
			}
			
		});

		int up=0x2B06;
		/**
		 * move current domino feature 
		 * @author everyone except david and majd 
		 */
	JButton btnNewButton_2 = new JButton(getEmojiByUnicode(up));
	contentPane.add(btnNewButton_2);
	btnNewButton_2.setBounds(60, 60, 30, 30);
	btnNewButton_2.setEnabled(true);
	btnNewButton_2.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
					
						//if (sel!= null) {
							Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
							Player player = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer();
							DominoInKingdom dominoinkingdom = (DominoInKingdom) kingdom.getTerritory(kingdom.getTerritories().size()-1);
							int xbe = dominoinkingdom.getX();
							int ybe = dominoinkingdom.getY();
							int []adjbe = adjacentpos(dominoinkingdom);
							if (!((xbe== 0&& ybe==-1)||(adjbe[0]== 0 && adjbe[1]== -1))) {
								if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
									
							kingdom1[xbe+4][ybe+4].setBackground(null);
							kingdom1[xbe+4][ybe+4].setBorderPainted(true);
							
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBackground(null);
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
									
									kingdom2[xbe+4][ybe+4].setBackground(null);
									kingdom2[xbe+4][ybe+4].setBorderPainted(true);
									
									kingdom2[adjbe[0]+4][adjbe[1]+4].setBackground(null);
									kingdom2[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}else if(player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
									
									kingdom3[xbe+4][ybe+4].setBackground(null);
									kingdom3[xbe+4][ybe+4].setBorderPainted(true);
									
									kingdom3[adjbe[0]+4][adjbe[1]+4].setBackground(null);
									kingdom3[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())) {
									
									kingdom4[xbe+4][ybe+4].setBackground(null);
									kingdom4[xbe+4][ybe+4].setBorderPainted(true);
									
									kingdom4[adjbe[0]+4][adjbe[1]+4].setBackground(null);
									kingdom4[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}
							//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom(), sel.getDomino());
							KingdominoController.Move_Current_Domino(DirectionKind.Up, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
							//Color color1 = getColorByTile(sel.getDomino().getLeftTile());
							//Color color2 = getColorByTile(sel.getDomino().getRightTile());
							
							
							if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Down, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getRightTile());
		
							kingdom1[x+4][y+4].setBorderPainted(false);
							kingdom1[x+4][y+4].setOpaque(true);
							kingdom1[x+4][y+4].setBackground(color1);
							int []adj = adjacentpos(dominoinkingdom);
							kingdom1[adj[0]+4][adj[1]+4].setBorderPainted(false);
							kingdom1[adj[0]+4][adj[1]+4].setOpaque(true);
						   kingdom1[adj[0]+4][adj[1]+4].setBackground(color2);
						   player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Down, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getRightTile());
								kingdom2[x+4][y+4].setBorderPainted(false);
								kingdom2[x+4][y+4].setOpaque(true);
								kingdom2[x+4][y+4].setBackground(color1);
								int []adj = adjacentpos(dominoinkingdom);
								kingdom2[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom2[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom2[adj[0]+4][adj[1]+4].setBackground(color2);
							   player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Down, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getRightTile());
								kingdom3[x+4][y+4].setBorderPainted(false);
								kingdom3[x+4][y+4].setOpaque(true);
								kingdom3[x+4][y+4].setBackground(color1);
								int []adj = adjacentpos(dominoinkingdom);
								kingdom3[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom3[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom3[adj[0]+4][adj[1]+4].setBackground(color2);
							   player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Down, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getRightTile());
								kingdom4[x+4][y+4].setBorderPainted(false);
								kingdom4[x+4][y+4].setOpaque(true);
								kingdom4[x+4][y+4].setBackground(color1);
								int []adj = adjacentpos(dominoinkingdom);
								kingdom4[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom4[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom4[adj[0]+4][adj[1]+4].setBackground(color2);
							   player.getKingdom().removeTerritory(dominoinkingdom);
							}
						//}
						}
					}
				
			
		
		
	});
	int  left=0x2B05;
	/**
	 * move current domino feature 
	 * @author everyone except david and majd 
	 */
	JButton btnNewButton_3 = new JButton(getEmojiByUnicode(left));
	contentPane.add(btnNewButton_3);
	btnNewButton_3.setBounds(90, 60, 30, 30);
	btnNewButton_3.setEnabled(true);
	btnNewButton_3.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
					
						//if (sel!= null) {
							Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
							Player player = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer();
							DominoInKingdom dominoinkingdom = (DominoInKingdom) kingdom.getTerritory(kingdom.getTerritories().size()-1);
							int xbe = dominoinkingdom.getX();
							int ybe = dominoinkingdom.getY();
							int []adjbe = adjacentpos(dominoinkingdom);
							if (!((xbe== 1&& ybe==0)||(adjbe[0]== 1 && adjbe[1]== 0))) {
								if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
							kingdom1[xbe+4][ybe+4].setBackground(null);
							kingdom1[xbe+4][ybe+4].setBorderPainted(true);
							
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBackground(null);
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
									kingdom2[xbe+4][ybe+4].setBackground(null);
									kingdom2[xbe+4][ybe+4].setBorderPainted(true);
									
									kingdom2[adjbe[0]+4][adjbe[1]+4].setBackground(null);
									kingdom2[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
									kingdom3[xbe+4][ybe+4].setBackground(null);
									kingdom3[xbe+4][ybe+4].setBorderPainted(true);
									
									kingdom3[adjbe[0]+4][adjbe[1]+4].setBackground(null);
									kingdom3[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())){
									kingdom4[xbe+4][ybe+4].setBackground(null);
									kingdom4[xbe+4][ybe+4].setBorderPainted(true);
									
									kingdom4[adjbe[0]+4][adjbe[1]+4].setBackground(null);
									kingdom4[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}
							//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom(), sel.getDomino());
							KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
							//Color color1 = getColorByTile(sel.getDomino().getLeftTile());
							//Color color2 = getColorByTile(sel.getDomino().getRightTile());
							//int x = dominoinkingdom.getX();
							//int y = dominoinkingdom.getY();
							if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Right, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getRightTile());
		
							kingdom1[x+4][y+4].setBorderPainted(false);
							kingdom1[x+4][y+4].setOpaque(true);
							kingdom1[x+4][y+4].setBackground(color1);
							int []adj = adjacentpos(dominoinkingdom);
							kingdom1[adj[0]+4][adj[1]+4].setBorderPainted(false);
							kingdom1[adj[0]+4][adj[1]+4].setOpaque(true);
						   kingdom1[adj[0]+4][adj[1]+4].setBackground(color2);
						   player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Right, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getRightTile());
								kingdom2[x+4][y+4].setBorderPainted(false);
								kingdom2[x+4][y+4].setOpaque(true);
								kingdom2[x+4][y+4].setBackground(color1);
								int []adj = adjacentpos(dominoinkingdom);
								kingdom2[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom2[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom2[adj[0]+4][adj[1]+4].setBackground(color2);
							   player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Right, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getRightTile());
								kingdom3[x+4][y+4].setBorderPainted(false);
								kingdom3[x+4][y+4].setOpaque(true);
								kingdom3[x+4][y+4].setBackground(color1);
								int []adj = adjacentpos(dominoinkingdom);
								kingdom3[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom3[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom3[adj[0]+4][adj[1]+4].setBackground(color2);
							   player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())){
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Right, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getRightTile());
								kingdom4[x+4][y+4].setBorderPainted(false);
								kingdom4[x+4][y+4].setOpaque(true);
								kingdom4[x+4][y+4].setBackground(color1);
								int []adj = adjacentpos(dominoinkingdom);
								kingdom4[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom4[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom4[adj[0]+4][adj[1]+4].setBackground(color2);
							   player.getKingdom().removeTerritory(dominoinkingdom);
							}
						}
						//}
					}
				
			
		
		
	});
	int right=0x27A1;
	/**
	 * move current domino feature 
	 * @author everyone except david and majd 
	 */
	JButton btnNewButton_4 = new JButton(getEmojiByUnicode(right));
	contentPane.add(btnNewButton_4);
	btnNewButton_4.setBounds(120, 60, 30, 30);
	btnNewButton_4.setEnabled(true);
	btnNewButton_4.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
					
						//if (sel!= null) {
							Player player = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer();
							Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
							
							DominoInKingdom dominoinkingdom = (DominoInKingdom) kingdom.getTerritory(kingdom.getTerritories().size()-1);
							int xbe = dominoinkingdom.getX();
							int ybe = dominoinkingdom.getY();
							int []adjbe = adjacentpos(dominoinkingdom);
							if (!((xbe== -1&& ybe==0)||(adjbe[0]== -1 && adjbe[1]== 0))) {
								if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
							kingdom1[xbe+4][ybe+4].setBackground(null);
							kingdom1[xbe+4][ybe+4].setBorderPainted(true);
							
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBackground(null);
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
									kingdom2[xbe+4][ybe+4].setBackground(null);
									kingdom2[xbe+4][ybe+4].setBorderPainted(true);
									
									kingdom2[adjbe[0]+4][adjbe[1]+4].setBackground(null);
									kingdom2[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
									kingdom3[xbe+4][ybe+4].setBackground(null);
									kingdom3[xbe+4][ybe+4].setBorderPainted(true);
									
									kingdom3[adjbe[0]+4][adjbe[1]+4].setBackground(null);
									kingdom3[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())){
									kingdom4[xbe+4][ybe+4].setBackground(null);
									kingdom4[xbe+4][ybe+4].setBorderPainted(true);
									
									kingdom4[adjbe[0]+4][adjbe[1]+4].setBackground(null);
									kingdom4[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
								}
							//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom(), sel.getDomino());
							KingdominoController.Move_Current_Domino(DirectionKind.Right, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
							//Color color1 = getColorByTile(sel.getDomino().getLeftTile());
							//Color color2 = getColorByTile(sel.getDomino().getRightTile());
							//int x = dominoinkingdom.getX();
							//int y = dominoinkingdom.getY();
							if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getRightTile());
		
							kingdom1[x+4][y+4].setBorderPainted(false);
							kingdom1[x+4][y+4].setOpaque(true);
							kingdom1[x+4][y+4].setBackground(color1);
							int []adj = adjacentpos(dominoinkingdom);
							kingdom1[adj[0]+4][adj[1]+4].setBorderPainted(false);
							kingdom1[adj[0]+4][adj[1]+4].setOpaque(true);
						   kingdom1[adj[0]+4][adj[1]+4].setBackground(color2);
						   player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getRightTile());
		
								kingdom2[x+4][y+4].setBorderPainted(false);
								kingdom2[x+4][y+4].setOpaque(true);
								kingdom2[x+4][y+4].setBackground(color1);
								int []adj = adjacentpos(dominoinkingdom);
								kingdom2[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom2[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom2[adj[0]+4][adj[1]+4].setBackground(color2);
							   player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getRightTile());
		
								kingdom3[x+4][y+4].setBorderPainted(false);
								kingdom3[x+4][y+4].setOpaque(true);
								kingdom3[x+4][y+4].setBackground(color1);
								int []adj = adjacentpos(dominoinkingdom);
								kingdom3[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom3[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom3[adj[0]+4][adj[1]+4].setBackground(color2);
							   player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())){
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getRightTile());
		
								kingdom4[x+4][y+4].setBorderPainted(false);
								kingdom4[x+4][y+4].setOpaque(true);
								kingdom4[x+4][y+4].setBackground(color1);
								int []adj = adjacentpos(dominoinkingdom);
								kingdom4[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom4[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom4[adj[0]+4][adj[1]+4].setBackground(color2);
							   player.getKingdom().removeTerritory(dominoinkingdom);
							}
						}
						//}
					}
				
			
		
		
	});
	int clk =0x2935;
	/**
	 * rotate current domino feature 
	 * @author everyone except david 
	 */
	JButton btnNewButton_5 = new JButton(getEmojiByUnicode(clk));
	contentPane.add(btnNewButton_5);
	btnNewButton_5.setBounds(150, 60, 30, 30);
	btnNewButton_5.setEnabled(true);
	btnNewButton_5.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
					
						//if (sel!= null) {
							Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
							Player player = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer();
							DominoInKingdom dominoinkingdom = (DominoInKingdom) kingdom.getTerritory(kingdom.getTerritories().size()-1);
							int xbe = dominoinkingdom.getX();
							int ybe = dominoinkingdom.getY();
							int []adjbe = adjacentpos(dominoinkingdom);
							if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
							kingdom1[xbe+4][ybe+4].setBackground(null);
							kingdom1[xbe+4][ybe+4].setBorderPainted(true);
							//int []adjbe = adjacentpos(dominoinkingdom);
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBackground(null);
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
							}else if (KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
								kingdom2[xbe+4][ybe+4].setBackground(null);
								kingdom2[xbe+4][ybe+4].setBorderPainted(true);
								//int []adjbe = adjacentpos(dominoinkingdom);
								kingdom2[adjbe[0]+4][adjbe[1]+4].setBackground(null);
								kingdom2[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
							}else if (KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
								kingdom3[xbe+4][ybe+4].setBackground(null);
								kingdom3[xbe+4][ybe+4].setBorderPainted(true);
								//int []adjbe = adjacentpos(dominoinkingdom);
								kingdom3[adjbe[0]+4][adjbe[1]+4].setBackground(null);
								kingdom3[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
							}else if (KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())){
								kingdom4[xbe+4][ybe+4].setBackground(null);
								kingdom4[xbe+4][ybe+4].setBorderPainted(true);
								//int []adjbe = adjacentpos(dominoinkingdom);
								kingdom4[adjbe[0]+4][adjbe[1]+4].setBackground(null);
								kingdom4[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
							}
							//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom(), sel.getDomino());
							KingdominoController.rotateCurrentDomino(getrotation("clockwise", dominoinkingdom), KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
							//Color color1 = getColorByTile(sel.getDomino().getLeftTile());
							//Color color2 = getColorByTile(sel.getDomino().getRightTile());
							//int x = dominoinkingdom.getX();
							//int y = dominoinkingdom.getY();
		
							
							if (KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.rotateCurrentDomino(getrotation("counterclockwise", dominoinkingdom), KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								int []adj = adjacentpos(dominoinkingdom);
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getRightTile());
							if (!((x== 0&& y==0)||(adj[0]== 0 && adj[1]== 0))) {
								//if (!(adj[0]== 0 && adj[1]== 0)) {
								//Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getLeftTile());
								//Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getRightTile());
		
							kingdom1[x+4][y+4].setBorderPainted(false);
							kingdom1[x+4][y+4].setOpaque(true);
							kingdom1[x+4][y+4].setBackground(color1);
							
							kingdom1[adj[0]+4][adj[1]+4].setBorderPainted(false);
							kingdom1[adj[0]+4][adj[1]+4].setOpaque(true);
						   kingdom1[adj[0]+4][adj[1]+4].setBackground(color2);
							//}
						}else {
				
							kingdom1[xbe+4][ybe+4].setBackground(color1);
							kingdom1[xbe+4][ybe+4].setBorderPainted(false);
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBackground(color2);
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBorderPainted(false);
						}
							player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.rotateCurrentDomino(getrotation("counterclockwise", dominoinkingdom), KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								int []adj = adjacentpos(dominoinkingdom);
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getRightTile());
								if (!((x== 0&& y==0)||(adj[0]== 0 && adj[1]== 0))) {
									//if (!(adj[0]== 0 && adj[1]== 0)) {
								
			
								kingdom2[x+4][y+4].setBorderPainted(false);
								kingdom2[x+4][y+4].setOpaque(true);
								kingdom2[x+4][y+4].setBackground(color1);
								
								kingdom2[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom2[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom2[adj[0]+4][adj[1]+4].setBackground(color2);
								//}
							}else {
								kingdom2[xbe+4][ybe+4].setBackground(color1);
								kingdom2[xbe+4][ybe+4].setBorderPainted(false);
								kingdom2[adjbe[0]+4][adjbe[1]+4].setBackground(color2);
								kingdom2[adjbe[0]+4][adjbe[1]+4].setBorderPainted(false);
							}
								player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.rotateCurrentDomino(getrotation("counterclockwise", dominoinkingdom), KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								int []adj = adjacentpos(dominoinkingdom);
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getRightTile());
								if (!((x== 0&& y==0)||(adj[0]== 0 && adj[1]== 0))) {
									//if (!(adj[0]== 0 && adj[1]== 0)) {
								
			
								kingdom3[x+4][y+4].setBorderPainted(false);
								kingdom3[x+4][y+4].setOpaque(true);
								kingdom3[x+4][y+4].setBackground(color1);
								
								kingdom3[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom3[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom3[adj[0]+4][adj[1]+4].setBackground(color2);
								//}
							}else {
								kingdom3[xbe+4][ybe+4].setBackground(color1);
								kingdom3[xbe+4][ybe+4].setBorderPainted(false);
								kingdom3[adjbe[0]+4][adjbe[1]+4].setBackground(color2);
								kingdom3[adjbe[0]+4][adjbe[1]+4].setBorderPainted(false);
							}
								player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())){
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.rotateCurrentDomino(getrotation("counterclockwise", dominoinkingdom), KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								int []adj = adjacentpos(dominoinkingdom);
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getRightTile());
								if (!((x== 0&& y==0)||(adj[0]== 0 && adj[1]== 0))) {
									//if (!(adj[0]== 0 && adj[1]== 0)) {
								
			
								kingdom4[x+4][y+4].setBorderPainted(false);
								kingdom4[x+4][y+4].setOpaque(true);
								kingdom4[x+4][y+4].setBackground(color1);
								
								kingdom4[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom4[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom4[adj[0]+4][adj[1]+4].setBackground(color2);
								//}
							}else {
								kingdom4[xbe+4][ybe+4].setBackground(color1);
								kingdom4[xbe+4][ybe+4].setBorderPainted(false);
								kingdom4[adjbe[0]+4][adjbe[1]+4].setBackground(color2);
								kingdom4[adjbe[0]+4][adjbe[1]+4].setBorderPainted(false);
							}
								player.getKingdom().removeTerritory(dominoinkingdom);
							}
							
						}
					//}
				
			
		
		
	});
	int counterclk=0x2934;
	/**
	 * rotate current domino feature 
	 * @author everyone except david
	 */
	JButton btnNewButton_6 = new JButton(getEmojiByUnicode(counterclk));
	contentPane.add(btnNewButton_6);
	btnNewButton_6.setBounds(180, 60, 30, 30);
	//btnNewButton_6.setEnabled(true);
	btnNewButton_6.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
					//if (gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.PreplaceDomino)) {
						//if (sel!= null) {
							Kingdom kingdom = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom();
							Player player = KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer();
							DominoInKingdom dominoinkingdom = (DominoInKingdom) kingdom.getTerritory(kingdom.getTerritories().size()-1);
							int xbe = dominoinkingdom.getX();
							int ybe = dominoinkingdom.getY();
							int []adjbe = adjacentpos(dominoinkingdom);
							if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
							kingdom1[xbe+4][ybe+4].setBackground(null);
							kingdom1[xbe+4][ybe+4].setBorderPainted(true);
							
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBackground(null);
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
								kingdom2[xbe+4][ybe+4].setBackground(null);
								kingdom2[xbe+4][ybe+4].setBorderPainted(true);
								
								kingdom2[adjbe[0]+4][adjbe[1]+4].setBackground(null);
								kingdom2[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
								kingdom3[xbe+4][ybe+4].setBackground(null);
								kingdom3[xbe+4][ybe+4].setBorderPainted(true);
								
								kingdom3[adjbe[0]+4][adjbe[1]+4].setBackground(null);
								kingdom3[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())){
								kingdom4[xbe+4][ybe+4].setBackground(null);
								kingdom4[xbe+4][ybe+4].setBorderPainted(true);
								
								kingdom4[adjbe[0]+4][adjbe[1]+4].setBackground(null);
								kingdom4[adjbe[0]+4][adjbe[1]+4].setBorderPainted(true);
							}
		
							//DominoInKingdom dominoinkingdom = new DominoInKingdom(-2, 4, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom(), sel.getDomino());
							KingdominoController.rotateCurrentDomino(getrotation("counterclockwise", dominoinkingdom), KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
							//Color color1 = getColorByTile(sel.getDomino().getLeftTile());
							//Color color2 = getColorByTile(sel.getDomino().getRightTile());
							//int x = dominoinkingdom.getX();
							//int y = dominoinkingdom.getY();
							
							if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.rotateCurrentDomino(getrotation("clockwise", dominoinkingdom), KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								int []adj = adjacentpos(dominoinkingdom);
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getDominoSelection().getDomino().getRightTile());
							if (!((x== 0&& y==0)||(adj[0]== 0 && adj[1]== 0))) {
								//if (!(adj[0]== 0 && adj[1]== 0)) {
		
							kingdom1[x+4][y+4].setBorderPainted(false);
							kingdom1[x+4][y+4].setOpaque(true);
							kingdom1[x+4][y+4].setBackground(color1);
							
							kingdom1[adj[0]+4][adj[1]+4].setBorderPainted(false);
							kingdom1[adj[0]+4][adj[1]+4].setOpaque(true);
						   kingdom1[adj[0]+4][adj[1]+4].setBackground(color2);
							//}
						}else {
							kingdom1[xbe+4][ybe+4].setBackground(color1);
							kingdom1[xbe+4][ybe+4].setBorderPainted(false);
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBackground(color2);
							kingdom1[adjbe[0]+4][adjbe[1]+4].setBorderPainted(false);
						}
							player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.rotateCurrentDomino(getrotation("clockwise", dominoinkingdom), KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								int []adj = adjacentpos(dominoinkingdom);
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getDominoSelection().getDomino().getRightTile());
								if (!((x== 0&& y==0)||(adj[0]== 0 && adj[1]== 0))) {
									//if (!(adj[0]== 0 && adj[1]== 0)) {
			
								kingdom2[x+4][y+4].setBorderPainted(false);
								kingdom2[x+4][y+4].setOpaque(true);
								kingdom2[x+4][y+4].setBackground(color1);
								
								kingdom2[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom2[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom2[adj[0]+4][adj[1]+4].setBackground(color2);
								//}
							}else {
								kingdom2[xbe+4][ybe+4].setBackground(color1);
								kingdom2[xbe+4][ybe+4].setBorderPainted(false);
								kingdom2[adjbe[0]+4][adjbe[1]+4].setBackground(color2);
								kingdom2[adjbe[0]+4][adjbe[1]+4].setBorderPainted(false);
							}
								player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.rotateCurrentDomino(getrotation("clockwise", dominoinkingdom), KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								int []adj = adjacentpos(dominoinkingdom);
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getDominoSelection().getDomino().getRightTile());
								if (!((x== 0&& y==0)||(adj[0]== 0 && adj[1]== 0))) {
									//if (!(adj[0]== 0 && adj[1]== 0)) {
			
								kingdom3[x+4][y+4].setBorderPainted(false);
								kingdom3[x+4][y+4].setOpaque(true);
								kingdom3[x+4][y+4].setBackground(color1);
								
								kingdom3[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom3[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom3[adj[0]+4][adj[1]+4].setBackground(color2);
								//}
							}else {
								kingdom3[xbe+4][ybe+4].setBackground(color1);
								kingdom3[xbe+4][ybe+4].setBorderPainted(false);
								kingdom3[adjbe[0]+4][adjbe[1]+4].setBackground(color2);
								kingdom3[adjbe[0]+4][adjbe[1]+4].setBorderPainted(false);
							}
								player.getKingdom().removeTerritory(dominoinkingdom);
							}else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())){
								player.getKingdom().addTerritory(dominoinkingdom);
								ca.mcgill.ecse223.kingdomino.controller.controller con = new ca.mcgill.ecse223.kingdomino.controller.controller();
								if (!con.verifyNoOverlapping(player.getKingdom())) {
									KingdominoController.rotateCurrentDomino(getrotation("clockwise", dominoinkingdom), KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//KingdominoController.Move_Current_Domino(DirectionKind.Left, KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer(), dominoinkingdom);
									//kingdom.removeTerritory(dominoinkingdom);
								}
								int x = dominoinkingdom.getX();
								int y = dominoinkingdom.getY();
								int []adj = adjacentpos(dominoinkingdom);
								Color color1 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getLeftTile());
								Color color2 = getColorByTile(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getDominoSelection().getDomino().getRightTile());
								if (!((x== 0&& y==0)||(adj[0]== 0 && adj[1]== 0))) {
									//if (!(adj[0]== 0 && adj[1]== 0)) {
			
								kingdom4[x+4][y+4].setBorderPainted(false);
								kingdom4[x+4][y+4].setOpaque(true);
								kingdom4[x+4][y+4].setBackground(color1);
								
								kingdom4[adj[0]+4][adj[1]+4].setBorderPainted(false);
								kingdom4[adj[0]+4][adj[1]+4].setOpaque(true);
							   kingdom4[adj[0]+4][adj[1]+4].setBackground(color2);
								//}
							}else {
								kingdom4[xbe+4][ybe+4].setBackground(color1);
								kingdom4[xbe+4][ybe+4].setBorderPainted(false);
								kingdom4[adjbe[0]+4][adjbe[1]+4].setBackground(color2);
								kingdom4[adjbe[0]+4][adjbe[1]+4].setBorderPainted(false);
							}
								player.getKingdom().removeTerritory(dominoinkingdom);
							}
					//}
					//}
		}
				
			
		
		
	});
	int place =0x2714;
	/**
	 * place domino feature 
	 * @author everyone except david and majd 
	 */
	JButton btnNewButton_7 = new JButton(getEmojiByUnicode(place));
	contentPane.add(btnNewButton_7);
	btnNewButton_7.setBounds(210, 60, 30, 30);
	btnNewButton_7.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Game game = KingdominoApplication.getKingdomino().getCurrentGame();
			Player player = game.getNextPlayer();
			OnNextTurn controller = new OnNextTurn();
			
			//gp.setGamestatus("PlacingDomino");
			//KingdominoApplication.setGameplay(gp);
			//PlaceDomino pd = new PlaceDomino();
			//boolean flag = pd.isValid((DominoInKingdom) player.getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1));
			//pd.finalize((DominoInKingdom) player.getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1));
			
			controller.triggerEventD("placeRequest", (DominoInKingdom) game.getNextPlayer().getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1));
			if(((DominoInKingdom) player.getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1)).getDomino().getStatus().equals(DominoStatus.PlacedInKingdom)) {
				//btnNewButton_6.setEnabled(false);
				//btnNewButton_5.setEnabled(false);
				//btnNewButton_4.setEnabled(false);
				//btnNewButton_3.setEnabled(false);
				//btnNewButton_2.setEnabled(false);
				//btnNewButton_1.setEnabled(false);
				//System.out.println("yes");
				/**
				 * calculate result feature 
				 * @author 
				 */
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(0).getColor().toString())) {
					score1_1.setText(game.getPlayer(0).getTotalScore()+"");
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(1).getColor().toString())) {
					score1_2.setText(game.getPlayer(1).getTotalScore()+"");
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(2).getColor().toString())) {
					score1_3.setText(game.getPlayer(2).getTotalScore()+"");
				}
				if (game.getNextPlayer().getColor().toString().equalsIgnoreCase(game.getPlayer(3).getColor().toString())) {
					score1_4.setText(game.getPlayer(3).getTotalScore()+"");
				}
					
			}else if (((DominoInKingdom) player.getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1)).getDomino().getStatus().equals(DominoStatus.ErroneouslyPreplaced)) {
				Discarddomino discard = new Discarddomino();
				discard.setVisible(true);
			}
			if (gp.getGamestatusOnNextTurn().equals(GamestatusOnNextTurn.CalculateResult)) {
				GameEnd end = new GameEnd();
				end.setVisible(true);
			}
		}
		
	});
	int discardit =0x2716;
	/*JButton btnNewButton_8 = new JButton(getEmojiByUnicode(discardit));
	 //contentPane.add(btnNewButton_8);
	 btnNewButton_8.setBounds(240, 60, 30, 30);
	 btnNewButton_8.addActionListener(new ActionListener() {
	  @Override
	  public void actionPerformed(ActionEvent e) {
	   Game game = KingdominoApplication.getKingdomino().getCurrentGame();
	   Player player = game.getNextPlayer();
	   Kingdom kingdom = player.getKingdom();
	   
	   DominoInKingdom dominoinkingdom = (DominoInKingdom) kingdom.getTerritory(kingdom.getTerritories().size()-1);
	   int xbe = dominoinkingdom.getX();
	   int ybe = dominoinkingdom.getY();
	   int x2=0,y2=0;
	   switch(dominoinkingdom.getDirection()) {
	   case Up :
	    x2=xbe;
	    y2=ybe+1;
	   case Down:
	    x2=xbe;
	    y2=ybe-1;
	   case Left:
	    x2=xbe-1;
	    y2=ybe;
	   case Right:
	    x2=xbe+1;
	    y2=ybe;
	   }
	   if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(0).getColor().toString())) {
	    kingdom1[xbe+4][ybe+4].setBackground(null);
	    kingdom1[xbe+4][ybe+4].setBorderPainted(true);
	    kingdom1[x2+4][y2+4].setBackground(null);
	    kingdom1[x2+4][y2+4].setBorderPainted(true);
	    
	    
	    }else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(1).getColor().toString())) {
	     kingdom2[xbe+4][ybe+4].setBackground(null);
	     kingdom2[xbe+4][ybe+4].setBorderPainted(true);
	     
	     kingdom2[x2+4][y2+4].setBackground(null);
	     kingdom2[x2+4][y2+4].setBorderPainted(true);
	    }else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(2).getColor().toString())) {
	     kingdom3[xbe+4][ybe+4].setBackground(null);
	     kingdom3[xbe+4][ybe+4].setBorderPainted(true);
	     
	     kingdom3[x2+4][y2+4].setBackground(null);
	     kingdom3[x2+4][y2+4].setBorderPainted(true);
	    }else if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(3).getColor().toString())){
	     kingdom4[xbe+4][ybe+4].setBackground(null);
	     kingdom4[xbe+4][ybe+4].setBorderPainted(true);
	     
	     kingdom4[x2+4][y2+4].setBackground(null);
	     kingdom4[x2+4][y2+4].setBorderPainted(true);
	    }
	   OnNextTurn controller = new OnNextTurn();
	   controller.triggerEventD("discardRequest", (DominoInKingdom) game.getNextPlayer().getKingdom().getTerritory(game.getNextPlayer().getKingdom().getTerritories().size()-1));
	   
	  }
	 });*/
	
	}

	

	public Color getColorByTile (TerrainType ter) {
		switch (ter) {
		case WheatField:
			return Color.YELLOW;
		case Grass:
			return Color.GREEN;
		case Lake:
			return Color.BLUE;
		case Forest:
			return Color.RED;
		case Mountain: 
			return Color.black;
		case Swamp: 
			return Color.DARK_GRAY;
		default:
			return null;
		}
			
	}
	public int[] adjacentpos (DominoInKingdom cur) {
    	//public int[] getAdjacentPosition (DominoInKingdom input) 
    	//throws NonValidInputException 
    	//{

    	int x = cur.getX();
    	int y = cur.getY();
    	//if (input instanceof Castle) {
    	//throw new NonValidInputException();
    	//}
    	DirectionKind before = ((DominoInKingdom) cur).getDirection();
    	if (before.equals(DirectionKind.Left)) {
    	x = x-1;
    	}
    	if (before.equals(DirectionKind.Up)) {
    	y = y+1;
    	}
    	if (before.equals(DirectionKind.Right)) {
    	x = x+1;
    	}
    	if (before.equals(DirectionKind.Down)) {
    	y = y-1;
    	}
    		int[] position = new int[] {x, y};
    		return position;


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
    private Domino getdominoByID(int id) {
        Game game = KingdominoApplication.getKingdomino().getCurrentGame();
        for (Domino domino : game.getAllDominos()) {
            if (domino.getId() == id) {
                return domino;
            }
        }
        throw new java.lang.IllegalArgumentException("Domino with ID " + id + " not found.");
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
    public DirectionKind getrotation(String rotation,DominoInKingdom cur) {
    	if (rotation.equals("clockwise")) {
    		if (cur.getDirection() == DirectionKind.Up) {
    			return DirectionKind.Right;
    		}
    		else if (cur.getDirection() == DirectionKind.Right) {
    			return DirectionKind.Down;
    		}
    		else if (cur.getDirection() == DirectionKind.Down) {
    			return DirectionKind.Left;
    		}
    		else {
    			return DirectionKind.Up;
    		}
    	}
    	
    	else if (rotation.equals("counterclockwise")) {
    		if (cur.getDirection() == DirectionKind.Up) {
    			return DirectionKind.Left;
    		}
    		else if (cur.getDirection() == DirectionKind.Right) {
    			return DirectionKind.Up;
    		}
    		else if (cur.getDirection() == DirectionKind.Down) {
    			return DirectionKind.Right;
    		}
    		else {
    			return DirectionKind.Down;
    		}
    	}
    	else {
    		return null;
    	}
    }
    public int getPlayerIndex(Player player ) {
    	for (int i = 0; i<4;i++) {
    		if (player.getColor().toString().equalsIgnoreCase(KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(i).getColor().toString())) {
    			return i;
    		}
    	}
    		return -1;
    	
    }

	private void showMsg(String s){
		JOptionPane.showMessageDialog(this, s);
	}

	private boolean saveGame(){
		return SaveGame.saveGame("recent_game.mov");
	}

	private void paintLoadGameKingdom(JButton[][] buttons, int playerOrder){
		for(KingdomTerritory kt: KingdominoApplication.getKingdomino().getCurrentGame().getPlayer(playerOrder).getKingdom().getTerritories()){
			int XL;
			int YL;
			int XR = 0;
			int YR = 0;
			if(kt instanceof DominoInKingdom){
				XL = kt.getX();
				YL = kt.getY();
				switch(((DominoInKingdom) kt).getDirection()){
					case Up:
						XR = XL;
						YR = YL + 1;
						break;
					case Down:
						XR = XL;
						YR = YL - 1;
						break;
					case Left:
						XR = XL - 1;
						YR = YL;
						break;
					case Right:
						XR = XL + 1;
						YR = YL;
						break;
				}
				buttons[XL + 4][YL + 4].setBorderPainted(false);
				buttons[XL + 4][YL + 4].setOpaque(true);
				Color left = getColorByTile(((DominoInKingdom) kt).getDomino().getLeftTile());
				buttons[XL + 4][YL + 4].setBackground(left);

				buttons[XR + 4][YR + 4].setBorderPainted(false);
				buttons[XR + 4][YR + 4].setOpaque(true);
				Color right = getColorByTile(((DominoInKingdom) kt).getDomino().getRightTile());
				buttons[XR + 4][YR + 4].setBackground(right);
			}
		}
	}
	public String getEmojiByUnicode(int unicode){
		return new String(Character.toChars(unicode));
	}



}
