import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class stockGUI {
	
	private stock st;
	private currency cur;
	private cartGUI cart;
	private JFrame frame;
	private GridBagConstraints c;
	JPanel panel;
	
	public stockGUI(stock st, currency cur, cartGUI cart, JFrame frame) {
		this.st = st;
		this.cur = cur;
		this.cart = cart;
		this.frame = frame;
		panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Products Menu"));
		c = new GridBagConstraints();
	}
	
	public JPanel stockMenu() {
		c.insets = new Insets(5, 5, 5, 5);
	
		int k = 0;
		int l = 0;
		for (int i = 1; i < st.stockSize()+1; i++) {
			int div[] = stockDivider(i, k, l);
			 l = div[1];
			 k = div[0];
			
			mainFrame.setMyConstraints(c, k, l*2-2, 1, 1, GridBagConstraints.WEST);
			panel.add(new JLabel(st.stockItem(i) + "  R" + st.stockPrice(i) + ": "), c);

			mainFrame.setMyConstraints(c, k, l*2-1, 1, 1, GridBagConstraints.WEST);
			JLabel quantLabel;
			if (st.stockQty(i) == 0) quantLabel = new JLabel("Out of stock");
			else quantLabel = new JLabel("Qty  ("+st.stockQty(i)+"):");
			panel.add(quantLabel, c);

			// setting up images icons
			ImageIcon icon = new ImageIcon(st.stockImage(i-1));
			icon = imageTransform(icon);
			mainFrame.setMyConstraints(c, k+1, l*2-2, 1, 2, GridBagConstraints.WEST);
			JButton btn = new JButton(icon);
			panel.add(btn, c);

			Integer j =  Integer.valueOf(i);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (st.stockPrice(j) > cur.getTotal()) {
						currencyGUI.setTotal("Insufficient Funds. You still owe R" + (st.stockPrice(j) - cur.getTotal()));
					} 
					else if (st.stockAvail(j)) {
						cur.changeCal(st.stockPrice(j));
						currencyGUI.setTotal("Total: R" + cur.getTotal());
						quantLabel.setText("Qty ("+st.stockQty(j)+"):");
						//exitLabel.setText("");
						cart.addToCart();
						frame.pack();
					}
					else quantLabel.setText("Out of Stock");
				}
			});

		}
		
		c.insets = new Insets(10, 5, 5, 5);
		
		mainFrame.setMyConstraints(c, 0, st.stockSize()*2, 1, 1, GridBagConstraints.WEST);
		panel.add(checkOutButton(), c);
		
		mainFrame.setMyConstraints(c, 1, st.stockSize()*2, 1, 1, GridBagConstraints.WEST);
		panel.add(resetButton(), c);
		
		return panel;
	}

	public JButton checkOutButton() {
		JButton btn = new JButton("Checkout");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cur.fileChange();
				st.fileChange();
				currencyGUI.setTotal("Total: R0");
				cart.checkout(cur.customerChange(), cur.sufficientChange());
				frame.pack();
			}
		});
		return btn;
	}
	
	public JButton resetButton() {
		JButton btn = new JButton("Reset");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cart.clear();
				frame.pack();
			}
		});
		return btn;
	}
	
	private int[] stockDivider(int i, int k, int l) {
		if (i-1 == Math.floor(st.stockSize()/2)) {
				k = 2;
				l = 0;
		}
		l++;
		int varibales[] = {k, l};
		return varibales;
	}
	
	private ImageIcon imageTransform(ImageIcon imageIcon) {
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back
		return imageIcon;
	}
	
	


}
