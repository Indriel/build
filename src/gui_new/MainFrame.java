package gui_new;

import gui.LoginFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.SwingUtilities;

import data.User;
import database.MySQL;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MainFrame extends javax.swing.JFrame implements ActionListener{
	
	private PanelAll ap = null;
	private JButton btnLogout;
	private JLabel lblUserName;
	private JPanel userData = null;
	
	private User loggedInUser;
	private MySQL database;
	
	public static void main(String[] args) {
		new Login();
	}
	
	public MainFrame(User loginUser) {
		super();
		this.loggedInUser = loginUser;
		this.database = MySQL.getInstance();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setVisible(true);
			this.setTitle("Stundenerfassung");
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			this.setSize(900, 721);
			{
				ap = new PanelAll();
				ap.setVisible(true);
				this.getContentPane().add(ap,BorderLayout.CENTER);
			}
			
			{
				this.userData = new JPanel();
				this.userData.setVisible(true);
				this.getContentPane().add(this.userData, BorderLayout.NORTH);
				userData.setLayout(null);
				userData.setPreferredSize(new java.awt.Dimension(882, 38));
				userData.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				{
					lblUserName = new JLabel();
					lblUserName.setLayout(null);
					userData.add(lblUserName);
					lblUserName.setText("User: " + this.loggedInUser.getName());
					lblUserName.setBounds(14, 9, 91, 21);
				}
				{
					btnLogout = new JButton();
					userData.add(btnLogout);
					btnLogout.setText("Logout");
					btnLogout.setBounds(123, 6, 85, 28);
				}
			}
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnLogout) {
			
		}
	}

}
