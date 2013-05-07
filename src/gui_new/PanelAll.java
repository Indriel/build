package gui_new;
import java.awt.BorderLayout;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

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

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public PanelAll() {
		super();
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
						//scrpaneErfassungTab = new JScrollPane();
						//panelErfassungTab.add(scrpaneErfassungTab, BorderLayout.CENTER);
						this.panelErfassung = new PnlErfassung();
						this.panelErfassung.setVisible(true);
						this.panelErfassungTab.add(panelErfassung);
					}

					this.panelAuswertugnTab = new JPanel();
					BorderLayout panelAuswertugnTabLayout = new BorderLayout();
					panelAuswertugnTab.setLayout(panelAuswertugnTabLayout);
					this.tpaneAllTabs.addTab("Auswertung", panelAuswertugnTab);
					{
						//scrpaneAuswertungTab = new JScrollPane();
						//panelAuswertugnTab.add(scrpaneAuswertungTab, BorderLayout.CENTER);
						this.panelAuswertung = new pnlAuswertung();
						this.panelAuswertung.setVisible(true);
						//this.scrpaneAuswertungTab.add(this.panelAuswertung);
						this.panelAuswertugnTab.add(panelAuswertung);
					}

					this.panelUserTab = new JPanel();
					BorderLayout panelUserTabLayout = new BorderLayout();
					panelUserTab.setLayout(panelUserTabLayout);
					this.tpaneAllTabs.addTab("Mitarbeiter", panelUserTab);
					{
						//scrpaneMitarbeiterTab = new JScrollPane();
						//panelUserTab.add(scrpaneMitarbeiterTab, BorderLayout.CENTER);
						this.panelMitarbeiter = new UserPanel();
						this.panelMitarbeiter.setVisible(true);
						//this.scrpaneMitarbeiterTab.add(this.panelMitarbeiter);
						this.panelUserTab.add(panelMitarbeiter);
					}

					this.panelAdminTab = new JPanel();
					BorderLayout panelAdminTabLayout = new BorderLayout();
					panelAdminTab.setLayout(panelAdminTabLayout);
					this.tpaneAllTabs.addTab("Administration", panelAdminTab);
					{
						//scrpaneAdministration = new JScrollPane();
						//scrpaneAdministration.setVisible(true);
						//panelAdminTab.add(scrpaneAdministration, BorderLayout.CENTER);
						{
							this.panelAdmin = new AdminPanel();
							this.panelAdmin.setVisible(true);
							//scrpaneAdministration.add(this.panelAdmin);
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
