package ca.mcgill.ecse223.kingdomino.view;

import ca.mcgill.ecse223.kingdomino.KingdominoApplication;
import ca.mcgill.ecse223.kingdomino.controller.OnNextTurn;
import ca.mcgill.ecse223.kingdomino.model.DominoInKingdom;
import ca.mcgill.ecse223.kingdomino.model.Gameplay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Discarddomino {

    JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Discarddomino window = new Discarddomino();
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
                        Discarddomino window = new Discarddomino();
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
     * discard domino feature 
     * @author everyone except david and majd 
     */
    public Discarddomino() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblNewLabel = new JLabel("Sorry, you cannot place it");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton DiscardButton = new JButton("Discard it");
        DiscardButton.setVisible(false);
        DiscardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==DiscardButton) {
                    Gameplay gp = KingdominoApplication.getGameplay();
                    OnNextTurn controller = new OnNextTurn();
                    DominoInKingdom dominoinkingdom = (DominoInKingdom) KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom().getTerritory(KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom().getTerritories().size()-1);
                    controller.triggerEventD("discardRequest", dominoinkingdom);
                    KingdominoApplication.getKingdomino().getCurrentGame().getNextPlayer().getKingdom().getTerritories().remove(dominoinkingdom);
                    dominoinkingdom.delete();

                }
            }
        });

        JButton TryButton = new JButton("Try again");
        TryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==TryButton) {
                    frame.dispose();
                }
            }
        });
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(79)
                                                .addComponent(DiscardButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(TryButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(54)
                                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(78, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(40)
                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(DiscardButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TryButton))
                                .addContainerGap(54, Short.MAX_VALUE))
        );
        frame.getContentPane().setLayout(groupLayout);
        frame.dispose();
    }
}
