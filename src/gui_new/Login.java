package gui_new;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import com.cloudgarden.layout.AnchorConstraint;

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
public class Login extends javax.swing.JFrame implements ActionListener {
	private JLabel lblUsername;
	private JTextField txtUser;
	private JLabel lblInfo;
	private JButton btnLogin;
	private JPasswordField ptxtPassword;
	private JLabel lblPassword;

	private MySQL database;
	
	public Login() {
		super();
		database = MySQL.getInstance();
		initGUI();
	}
	
	private void initGUI() {
		try {
			getContentPane().setLayout(null);
			this.setVisible(true);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				lblUsername = new JLabel();
				getContentPane().add(lblUsername, new AnchorConstraint(1, 1001, 1001, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				lblUsername.setText("Benutzer");
				lblUsername.setBounds(20, 31, 63, 24);
			}
			{
				lblPassword = new JLabel();
				getContentPane().add(lblPassword);
				lblPassword.setText("Passwort");
				lblPassword.setBounds(20, 72, 58, 21);
			}
			{
				txtUser = new JTextField();
				getContentPane().add(txtUser);
				txtUser.setBounds(122, 30, 133, 28);
			}
			{
				ptxtPassword = new JPasswordField();
				getContentPane().add(ptxtPassword);
				ptxtPassword.setBounds(122, 69, 133, 28);
			}
			{
				lblInfo = new JLabel();
				getContentPane().add(lblInfo);
				lblInfo.setBounds(12, 223, 445, 20);
				lblInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			}
			{
				btnLogin = new JButton();
				getContentPane().add(btnLogin);
				btnLogin.setText("Login");
				btnLogin.setBounds(20, 143, 79, 28);
				btnLogin.addActionListener(this);
			}
			pack();
			this.setSize(487, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnLogin) {
			this.LoginMethod();
		}
	}

	private void LoginMethod() {
		try {
			User loginUser = database.login(this.txtUser.getText(), new String(this.ptxtPassword.getPassword()));
			if(loginUser == null)
				throw new Exception();
			this.lblInfo.setText("Logged In");
			new MainFrame(loginUser);
			this.dispose();
		} catch (SQLException e) {
			this.lblInfo.setText("Error, could not login.");
		}
		catch (Exception e) {
			this.lblInfo.setText("Error, could not login");
		}
	}

}
