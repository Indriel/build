package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import java.util.jar.JarInputStream;

import javax.swing.JTabbedPane;
import javax.swing.plaf.InsetsUIResource;

import data.User;
import database.MySQL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


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
public class MenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanelOuterInput = null;
	private InputPanel jPanelInnerInput = null;
	private JPanel jPanelOuterOutput = null;
	private OutputPanel jPanelInnerOutput = null;
	private JPanel jPanelOuterAdmin = null;
	private AdminPanel jPanelInnerAdmin = null;
	private JPanel jPanelOuterUser = null;
	private UserPropertyPanel jPanelInnerUser = null;
	private MySQL sql = null;
	
	private User user = null;


	public MenuFrame(User user) {
		super();
		setPreferredSize(new Dimension(1000, 600));
		this.user = user;
		sql = MySQL.getInstance();
		initialize();
	}

	
	private void initialize() {
		this.setSize(560, 580);
		this.setContentPane(getJContentPane());
		this.setTitle("Menu");
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds( (dim.width/2) - (this.getWidth()/2), (dim.height/2) - (this.getHeight()/2), this.getWidth(), this.getHeight());
	}

	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setPreferredSize(new Dimension(1000, 600));
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			UIManager.put("TabbedPane.selected", Color.WHITE); 
			UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(1,1,1,1));
			jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
			jTabbedPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					try {
						if(user.getName().compareTo("admin") == 0) {
							sql.updateComboBoxUser(jPanelInnerOutput.getUserComboBoxModel());
							sql.updateComboBoxUser(jPanelInnerInput.getUserComboBoxModel());
							jPanelInnerOutput.getUserComboBoxModel().addElement(user);
						}
						jPanelInnerInput.updateTime();
					} catch (Exception e) {}
				}
			});
			jTabbedPane.setPreferredSize(new Dimension(1000, 600));
			jTabbedPane.addTab("Erfassung", null, getJPanelOuterInput(), null);
			jTabbedPane.addTab("Auswertung", null, getJPanelOuterOutput(), null);
			if(user.getName().compareTo("admin") == 0)
				jTabbedPane.addTab("Administrator", null, getJPanelOuterAdmin(), null);
			jTabbedPane.addTab("Benutzereinstellungen", null, getJPanelOuterUser(), null);
		}
		return jTabbedPane;
	}

	
	private JPanel getJPanelOuterInput() {
		if (jPanelOuterInput == null) {
			jPanelOuterInput = new JPanel();
			jPanelOuterInput.setLayout(new BorderLayout());
			jPanelOuterInput.add(getJPanelInnerInput(), BorderLayout.WEST);
		}
		return jPanelOuterInput;
	}
	
	
	private JPanel getJPanelOuterUser() {
		if (jPanelOuterUser == null) {
			jPanelOuterUser = new JPanel();
			jPanelOuterUser.setLayout(new BorderLayout());
			jPanelOuterUser.add(getJPanelInnerUser(), BorderLayout.WEST);
		}
		return jPanelOuterUser;
	}
	
	private InputPanel getJPanelInnerInput() {
		if (jPanelInnerInput == null) {
			jPanelInnerInput = new InputPanel(this.user);
			jPanelInnerInput.setLayout(new FlowLayout());
			jPanelInnerInput.setPreferredSize(new Dimension(550, 100));
		}
		return jPanelInnerInput;
	}
	
	
	private UserPropertyPanel getJPanelInnerUser() {
		if (jPanelInnerUser == null) {
			jPanelInnerUser = new UserPropertyPanel(this.user);
			jPanelInnerUser.setLayout(new FlowLayout());
			jPanelInnerUser.setPreferredSize(new Dimension(550, 100));
		}
		return jPanelInnerUser;
	}

	
	private JPanel getJPanelOuterOutput() {
		if (jPanelOuterOutput == null) {
			jPanelOuterOutput = new JPanel();
			jPanelOuterOutput.setLayout(new BorderLayout());
			jPanelOuterOutput.add(getJPanelInnerOutput(), BorderLayout.WEST);
		}
		return jPanelOuterOutput;
	}

	
	private OutputPanel getJPanelInnerOutput() {
		if (jPanelInnerOutput == null) {
			jPanelInnerOutput = new OutputPanel(user);
			jPanelInnerOutput.setLayout(new FlowLayout());
			jPanelInnerOutput.setPreferredSize(new Dimension(550, 100));
		}
		return jPanelInnerOutput;
	}

	
	private JPanel getJPanelOuterAdmin() {
		if (jPanelOuterAdmin == null) {
			jPanelOuterAdmin = new JPanel();
			jPanelOuterAdmin.setLayout(new BorderLayout());
			jPanelOuterAdmin.add(getJPanelInnerAdmin(), BorderLayout.WEST);
		}
		return jPanelOuterAdmin;
	}

	
	private AdminPanel getJPanelInnerAdmin() {
		if (jPanelInnerAdmin == null) {
			jPanelInnerAdmin = new AdminPanel();
			jPanelInnerAdmin.setLayout(new FlowLayout());
			jPanelInnerAdmin.setPreferredSize(new Dimension(550, 400));
		}
		return jPanelInnerAdmin;
	}

}
