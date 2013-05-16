package gui_new;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

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
public class AdminPanel extends javax.swing.JPanel {
	private JTabbedPane tpaneAdmin;
	private JPanel tpaneAdmin_panelUser;
	private JPanel tpaneAdmin_panelBereiche;
	private JPanel tpaneAdmin_panelZeiten;
	private JPanel tpaneAdmin_panelTaetigkeiten;
	private AdminUserPanel tpaneAdmin_panelUser_UserSettings;
	private AdminBereichePanel tpaneAdmin_panelBereiche_BereichSettings;
	private AdminZeitenPanel tpaneAdmin_panelZeiten_ZeitenSettings;
	private AdminTaetigkeitenPanel tpaneAdmin_panelTaetigkeiten_TSettings;

	private MySQL database;
	private User loggedInUser;
		
	
	public AdminPanel(User loggedInUser) {
		super();
		this.loggedInUser = loggedInUser;
		this.database = MySQL.getInstance();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(673, 481));
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setVisible(true);
			{
				tpaneAdmin = new JTabbedPane();
				this.add(tpaneAdmin, BorderLayout.CENTER);
				tpaneAdmin.setBackground(Color.red);
				{
					
					tpaneAdmin_panelUser = new JPanel();
					BorderLayout tpaneAdmin_panelUserLayout = new BorderLayout();
					tpaneAdmin_panelUser.setLayout(tpaneAdmin_panelUserLayout);
					tpaneAdmin.addTab("Benutzereinstellungen", tpaneAdmin_panelUser);
					{
						tpaneAdmin_panelUser_UserSettings = new AdminUserPanel(this.loggedInUser);
						tpaneAdmin_panelUser_UserSettings.setVisible(true);
						tpaneAdmin_panelUser.add(tpaneAdmin_panelUser_UserSettings, BorderLayout.CENTER);
					}
					
					tpaneAdmin_panelBereiche = new JPanel();
					BorderLayout tpaneAdmin_panelBereicheLayout = new BorderLayout();
					tpaneAdmin_panelBereiche.setLayout(tpaneAdmin_panelBereicheLayout);
					tpaneAdmin.addTab("Bereiche", tpaneAdmin_panelBereiche);
					{
						tpaneAdmin_panelBereiche_BereichSettings = new AdminBereichePanel(this.loggedInUser);
						tpaneAdmin_panelBereiche_BereichSettings.setVisible(true);
						tpaneAdmin_panelBereiche.add(tpaneAdmin_panelBereiche_BereichSettings, BorderLayout.CENTER);
					}
					
					tpaneAdmin_panelZeiten = new JPanel();
					BorderLayout tpaneAdmin_panelZeitenLayout = new BorderLayout();
					tpaneAdmin_panelZeiten.setLayout(tpaneAdmin_panelZeitenLayout);
					tpaneAdmin.addTab("Zeiten", tpaneAdmin_panelZeiten);
					{
						tpaneAdmin_panelZeiten_ZeitenSettings = new AdminZeitenPanel(this.loggedInUser);
						tpaneAdmin_panelZeiten_ZeitenSettings.setVisible(true);
						tpaneAdmin_panelZeiten.add(tpaneAdmin_panelZeiten_ZeitenSettings);
					}
					
					tpaneAdmin_panelTaetigkeiten = new JPanel();
					BorderLayout tpaneAdmin_panelTaetigkeitenLayout = new BorderLayout();
					tpaneAdmin_panelTaetigkeiten.setLayout(tpaneAdmin_panelTaetigkeitenLayout);
					tpaneAdmin.addTab("Taetigkeiten", tpaneAdmin_panelTaetigkeiten);
					{
						this.tpaneAdmin_panelTaetigkeiten_TSettings = new AdminTaetigkeitenPanel(this.loggedInUser);
						this.tpaneAdmin_panelTaetigkeiten_TSettings.setVisible(true);
						tpaneAdmin_panelTaetigkeiten.add(this.tpaneAdmin_panelTaetigkeiten_TSettings);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
