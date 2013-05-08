package gui;

import gui_new.AdminPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.User;
import java.awt.BorderLayout;

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
public class Main {
	
	private static LoginFrame lf = null;
	static private JPanel pnlTest;
	private static MenuFrame mf = null;
	private static AdminPanel ap = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		lf = new LoginFrame();
		lf.setVisible(true);
		lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lf.setPreferredSize(new java.awt.Dimension(872, 574));
		{
			pnlTest = new JPanel();
			lf.getContentPane().add(pnlTest, BorderLayout.CENTER);
			pnlTest.setPreferredSize(new java.awt.Dimension(732, 431));
			{
				//ap = new AdminPanel();
				ap.setVisible(true);
				pnlTest.add(ap);
			}
		}
		lf.setSize(872, 574);
	}
	
	public static void toMenu(User user) {
		lf.dispose();
		mf = new MenuFrame(user);
		mf.setVisible(true);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
