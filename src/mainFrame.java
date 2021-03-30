import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class mainFrame {
	
	cartGUI cart;
	stockGUI stock;
	JFrame frame;
	
	JLabel changeLabel;
	JLabel exitLabel;
	
	public mainFrame(stock st, currency cur) {

		frame = new JFrame("SIPHO'S VEDNING MACHINE");
		frame.setLayout(new BorderLayout());
		
		currencyGUI currGui = new currencyGUI(cur);
		frame.add(currGui.currencyMenu(), BorderLayout.NORTH);
		
		cart = new cartGUI(st);
		frame.add(cart.getPanel(), BorderLayout.SOUTH);

		
		stockGUI stGui = new stockGUI(st, cur, cart, frame);
		frame.add(stGui.stockMenu(), BorderLayout.CENTER);

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	
	protected static void setMyConstraints(GridBagConstraints c, int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
		c.gridx = gridx;
		c.gridy = gridy;
		c.anchor = anchor;
		c.gridwidth = gridwidth;
		c.gridheight = gridheight;
	}
	
	


}
