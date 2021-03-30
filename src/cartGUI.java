import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class cartGUI {
		
		private stock st;
		private GridBagConstraints c;
		private JPanel panel;
		
		public cartGUI(stock st) {
			this.st = st;
			panel = new JPanel(new GridBagLayout());
			panel.setBorder(BorderFactory.createTitledBorder("Checkout Cart"));
			c = new GridBagConstraints();
			c.insets = new Insets(5, 5, 5, 5);
			panel.setVisible(false);
		}
		
		public void addToCart() {
			panel.removeAll();
			panel.setVisible(true);
			
			mainFrame.setMyConstraints(c, 0, 0, 1, 1, GridBagConstraints.WEST); 
			JLabel cartItem = new JLabel(st.labels()[0]); 
			panel.add(cartItem, c);
			
			mainFrame.setMyConstraints(c, 1, 0, 1, 1, GridBagConstraints.WEST); 
			JLabel cartPrice = new JLabel("Cost"); 
			panel.add(cartPrice, c);
			
			mainFrame.setMyConstraints(c, 2, 0, 1, 1, GridBagConstraints.WEST); 
			JLabel cartQuantity = new JLabel(st.labels()[2]); 
			panel.add(cartQuantity, c);
			
			for (int i = 0; i < st.purchaseCart.size(); i++) {
				panel.getComponent(i);
				mainFrame.setMyConstraints(c, 0, i+1, 1, 1, GridBagConstraints.WEST);
				panel.add(new JLabel(st.purchaseCart.get(i)[0]), c);

				mainFrame.setMyConstraints(c, 1, i+1, 1, 1, GridBagConstraints.EAST);
				panel.add(new JLabel("R"  + st.purchaseCart.get(i)[1]), c);

				mainFrame.setMyConstraints(c, 2, i+1, 1, 1, GridBagConstraints.EAST);
				panel.add(new JLabel("x"  + st.purchaseCart.get(i)[2]), c);
			}
		}
		
		public void checkout(String change, boolean suffChange) {
			panel.removeAll();
			panel.setVisible(true);
			mainFrame.setMyConstraints(c, 0, 0, 1, 1, GridBagConstraints.WEST);
			JLabel changeLabel = new JLabel("Change: " + change);
			panel.add(changeLabel, c);
			
			int i = 0;
			if (!suffChange) {
				i = 1;
				mainFrame.setMyConstraints(c, 0, 1, 1, 1, GridBagConstraints.WEST);
				JLabel sufficientChangeLabel = new JLabel("Insufficient change. Please email 'sipho@mail.com' for assistance");
				panel.add(sufficientChangeLabel, c);
			}
			
			mainFrame.setMyConstraints(c, 0, 1+i, 1, 1, GridBagConstraints.WEST);
			JLabel exitLabel = new JLabel("Thank you for shopping at Sipho's Vending Machine");
			panel.add(exitLabel, c);
		}
		
		public void clear() {
			st.purchaseCart.clear();
			panel.setVisible(false);
			panel.removeAll();
		}
		
		public JPanel getPanel() {
			return panel;
		}
}
