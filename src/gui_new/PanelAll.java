package gui_new;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import data.User;
import database.MySQL;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class PanelAll extends javax.swing.JPanel {
	private JTabbedPane tpaneAllTabs;
	private JScrollPane scrpaneAdministration;
	private JScrollPane scrpaneMitarbeiterTab;
	private JScrollPane scrpaneAuswertungTab;
	private JScrollPane scrpaneErfassungTab;
	private JPanel panelAdminTab;
	private JPanel panelErfassungTab;
	private JPanel panelAuswertugnTab;
	private JPanel panelUserTab;
	private AdminPanel panelAdmin;
	private PnlErfassung panelErfassung;
	private pnlAuswertung panelAuswertung;
	private UserPanel panelMitarbeiter;

	private MySQL database;
	private User loggedInUser;

	public PanelAll(User loggedInUser) {
		super();
		this.loggedInUser = loggedInUser;
		this.database = MySQL.getInstance();
		initGUI();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(895, 537));
			{
				tpaneAllTabs = new JTabbedPane();
				this.add(tpaneAllTabs, BorderLayout.CENTER);
				{
					this.panelErfassungTab = new JPanel();
					BorderLayout panelErfassungTabLayout = new BorderLayout();
					panelErfassungTab.setLayout(panelErfassungTabLayout);
					this.tpaneAllTabs.addTab("Erfassung", panelErfassungTab);
					{
						this.panelErfassung = new PnlErfassung(this.loggedInUser);
						this.panelErfassung.setVisible(true);
						this.panelErfassungTab.add(panelErfassung);
					}

					this.panelAuswertugnTab = new JPanel();
					BorderLayout panelAuswertugnTabLayout = new BorderLayout();
					panelAuswertugnTab.setLayout(panelAuswertugnTabLayout);
					this.tpaneAllTabs.addTab("Auswertung", panelAuswertugnTab);
					{
						this.panelAuswertung = new pnlAuswertung(this.loggedInUser);
						this.panelAuswertung.setVisible(true);
						this.panelAuswertugnTab.add(panelAuswertung);
					}

					this.panelUserTab = new JPanel();
					BorderLayout panelUserTabLayout = new BorderLayout();
					panelUserTab.setLayout(panelUserTabLayout);
					this.tpaneAllTabs.addTab("Mitarbeiter", panelUserTab);
					{
						this.panelMitarbeiter = new UserPanel(this.loggedInUser);
						this.panelMitarbeiter.setVisible(true);
						this.panelUserTab.add(panelMitarbeiter);
					}

					if (this.loggedInUser.getName().equals("admin")) {
						this.panelAdminTab = new JPanel();
						BorderLayout panelAdminTabLayout = new BorderLayout();
						panelAdminTab.setLayout(panelAdminTabLayout);
						this.tpaneAllTabs.addTab("Administration",
								panelAdminTab);
						{
							this.panelAdmin = new AdminPanel(this.loggedInUser);
							this.panelAdmin.setVisible(true);
							this.panelAdminTab.add(panelAdmin);
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
