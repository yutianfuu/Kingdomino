package ca.mcgill.ecse223.kingdomino.view;

import java.awt.EventQueue;
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

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.model.Kingdomino;

import java.awt.Color;

public class UserProfile {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void setVisible(boolean b) {
		if (b == true) {
	//public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserProfile window = new UserProfile();
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
	public UserProfile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Profile Information");
		frame.getContentPane().setBackground(new Color(107, 142, 35));
		frame.setBounds(225, 200, 450, 250);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
			}
		});
		/**
		 * provide user profile feature 
		 * @author everyone except david 
		 */
		JLabel lblNewLabel = new JLabel("UserName");
		ArrayList<String> usernames = new ArrayList<String>();
		try {
			File f = new File("names.txt");
            FileReader reader = new FileReader(f.getAbsolutePath());
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) {
                usernames.add(line);
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		textField = new JTextField();
		textField.setColumns(10);
		textField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
                textField.setForeground(Color.BLACK);
			}
		});
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		
		JLabel lblNewLabel_2 = new JLabel("");
		
		JLabel lblNewLabel_3 = new JLabel("");
		JButton btnNewButton_1 = new JButton("Enter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setVisible(false);
				lblNewLabel_3.setVisible(false);
				Kingdomino q = KingdominoApplication.getKingdomino();
				String name = textField.getText();
				
				if (!usernames.contains(name)) {
					lblNewLabel_1.setText("The user: " + name + "does not exist");
					lblNewLabel_1.setVisible(true);
					
					
				}else {
					String msg = "";
					Integer numplayed= 0;
		            Integer numwon = 0;
		 
					try{
						//q.addUser(name);
						File f = new File("profiles.txt");
						FileReader reader = new FileReader(f.getAbsolutePath());
			            BufferedReader bufferedReader = new BufferedReader(reader);
			            String line;
			            ArrayList<Integer> woned = new ArrayList<Integer>();
			            ArrayList<Integer> played = new ArrayList<Integer>();
			            
			            while ((line = bufferedReader.readLine()) != null) {
			                String [] tokens = line.split(" ");
			                if (tokens.length>=3) {
			                	//for (String name1 : usernames) {
			                		if (name.equalsIgnoreCase(tokens[0])) {
			                			numplayed = Integer.decode(tokens[1]);
			                			played.add(numplayed);
			                			numwon = Integer.decode(tokens[2]);
			                			woned.add(numwon);
			                		}
			                	//}
			                }
			            }
			            Collections.sort(woned);
			            Collections.sort(played);
			            numplayed= played.get(played.size()-1);
			            numwon = woned.get(woned.size()-1);
			            reader.close();
			 
					} catch (Exception exp) {
						msg = exp.getMessage();
					}
					lblNewLabel_1.setText(msg);
					lblNewLabel_1.setVisible(true);
					lblNewLabel_2.setText("Games played: \t"+ numplayed);
					lblNewLabel_2.setVisible(true);
					lblNewLabel_3.setText("Games won: \t"+ numwon);
					lblNewLabel_3.setVisible(true);
				}
				
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_1)
							.addComponent(lblNewLabel)
							.addComponent(lblNewLabel_2)
							.addComponent(lblNewLabel_3)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addContainerGap(108, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(btnNewButton)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addGap(36)
					.addComponent(lblNewLabel_2)
					.addGap(27)
					.addComponent(lblNewLabel_3)
					.addContainerGap(111, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

}
