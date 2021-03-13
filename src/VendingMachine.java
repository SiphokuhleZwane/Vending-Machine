import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.*;

public class VendingMachine implements ActionListener {

	static String productsFile = "products.csv";
	static String currencyStockFile = "currency_stock.csv";

	static currency cur = new currency(currencyStockFile);
	stock st = new stock(productsFile);

	JLabel total;
	JLabel change;
	JLabel purchasedItem;
	JLabel insuffFunds;

	public VendingMachine() {
		JFrame frame = new JFrame("SIPHO'S VEDNING MACHINE");
		frame.setLayout(new BorderLayout());

		frame.add(menuTop(cur), BorderLayout.NORTH);
		frame.add(menuCenter(cur), BorderLayout.CENTER);
		frame.add(menuBottom(cur), BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static void setMyConstraints(GridBagConstraints c, int gridx, int gridy, int anchor) {
		c.gridx = gridx;
		c.gridy = gridy;
		c.anchor = anchor;
	}

	public JPanel menuBottom(currency cur) {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Cart"));
		GridBagConstraints c = new GridBagConstraints();
		
		Jbutton finishButton 
		setMyConstraints(c, 0, 0, GridBagConstraints.WEST);
		JLabel cartItem = new JLabel("Item :");
		panel.add(cartItem, c);

		setMyConstraints(c, 1, 0, GridBagConstraints.EAST);
		JLabel cartQuantity = new JLabel("Quantity");
		panel.add(cartQuantity, c);
		
		while 
		setMyConstraints(c, 0, 1, GridBagConstraints.WEST);
		JLabel changeLabel = new JLabel("Change :");
		panel.add(changeLabel, c);

		setMyConstraints(c, 1, 1, GridBagConstraints.EAST);
		change = new JLabel();
		panel.add(change, c);
		
		setMyConstraints(c, 0, 2, GridBagConstraints.WEST);
		insuffFunds = new JLabel();
		panel.add(insuffFunds, c);
		
		return panel;
	}

	public JPanel menuCenter(currency cur) {

		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Products Menu"));
		GridBagConstraints c = new GridBagConstraints();
		String[] labels = { "Product \t", "Price     ", "Quantity" };
		String[] prodImages = { "C:\\Users\\sippz\\Desktop\\Vending Machine\\Products\\Simba Fruit Chutney.png",
				"C:\\Users\\sippz\\Desktop\\Vending Machine\\Products\\Doritos Cajun.png",
				"C:\\Users\\sippz\\Desktop\\Vending Machine\\Products\\Snickers.png",
				"C:\\Users\\sippz\\Desktop\\Vending Machine\\Products\\Lunch Bar.png",
				"C:\\Users\\sippz\\Desktop\\Vending Machine\\Products\\Peanuts & Raisins.png",
				"C:\\Users\\sippz\\Desktop\\Vending Machine\\Products\\Powerade.png" };

		ArrayList<String[]> prodInfo = st.list();

		for (int i = 0; i < labels.length; i++) {
			setMyConstraints(c, i, 0, GridBagConstraints.WEST);
			panel.add(new JLabel(labels[i]), c);
		}

		for (int i = 0; i < prodImages.length; i++) {
			// setting up product names
			setMyConstraints(c, 0, i + 1, GridBagConstraints.WEST);
			panel.add(new JLabel(i + 1 + ". " + prodInfo.get(i)[0] + ": "), c);

			// setting up prices
			setMyConstraints(c, 1, i + 1, GridBagConstraints.WEST);
			panel.add(new JLabel("R" + prodInfo.get(i)[1]), c);

			// setting up quantities
			setMyConstraints(c, 2, i + 1, GridBagConstraints.WEST);
			JLabel quantLabel = new JLabel(prodInfo.get(i)[2]);
			panel.add(quantLabel, c);

			// setting up images icons
			ImageIcon icon = new ImageIcon(prodImages[i]);
			icon = imageTransform(icon);
			setMyConstraints(c, 3, i + 1, GridBagConstraints.WEST);
			JButton btn = new JButton(icon);
			panel.add(btn, c);

			final Integer j = new Integer(i);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int price = Integer.parseInt(prodInfo.get(j)[1]);
					if (price > cur.getTotal()) {
						insuffFunds.setText("Insufficient Funds. You still owe R" + (price - cur.getTotal()));
						st.fileChange();
					} else {
						quantLabel.setText(prodInfo.get(j)[2]);
						cur.changeCal(st.prodPrice(j));
						total.setText("Total: R" + cur.getTotal());
						purchasedItem.setText(st.prodAvail(j));
						st.fileChange();
					}
				}
			});

		}
		return panel;
	}
	
	public void sipho() {
		
	}

	public ImageIcon imageTransform(ImageIcon imageIcon) {
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back
		return imageIcon;
	}

	public JPanel menuTop(currency cur) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		setMyConstraints(c, 0, 0, GridBagConstraints.CENTER);
		JLabel menuName = new JLabel("SIPHO'S VENDING MACHINE");
		panel.add(menuName, c);

		setMyConstraints(c, 0, 1, GridBagConstraints.WEST);
		JLabel input = new JLabel("Enter an amount in Rands: ");
		panel.add(input, c);

		setMyConstraints(c, 0, 2, GridBagConstraints.WEST);
		panel.add(currencyPad(), c);

		total = new JLabel("Total: R" + cur.getTotal());
		setMyConstraints(c, 0, 3, GridBagConstraints.WEST);
		panel.add(total, c);

		return panel;
	}

	public JPanel currencyPad() {
		JPanel panel = new JPanel(new GridLayout(2, 3));
		ArrayList<String[]> curInfo = cur.list();
		for (int i = 0; i < curInfo.size(); i++) {
			// setting up product names
			JButton btn = new JButton("R" + curInfo.get(i)[0]);
			panel.add(btn);
			final Integer j = new Integer(i);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cur.fileChange();
					cur.sum(Integer.parseInt(curInfo.get(j)[0]));
					total.setText("Total: R" + cur.getTotal());
				}
			});

		}
		return panel;
	}

	public static void main(String[] args) {
		stock st = new stock(productsFile);

		System.out.println("SIPHO'S VEDNING MACHINE");

		VendingMachine vend = new VendingMachine();

		Scanner scan = new Scanner(System.in);
		/*
		 * while (true){ try {
		 * System.out.print("\nPlease pick number (enter 0 to exit): ");
		 * 
		 * int pick = scan.nextInt();
		 * 
		 * if (pick == 0) { System.out.println("\nExiting Vending Machine"); break; }
		 * else if (st.insufficientFunds(pick-1, cur.getTotal())) { vend.menuTop(cur); }
		 * else if (st.prodAvail(pick-1)) { cur.changeCal(st.prodPrice(pick-1)); }
		 * 
		 * 
		 * } catch (IndexOutOfBoundsException | InputMismatchException e) {
		 * System.out.println("Pick doesn't exist. Please enter number between 1 and 6"
		 * ); continue; } }
		 */
		cur.finalChange();
		cur.fileChange();
		st.fileChange();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			// int amount = Integer.parseInt(amountText.getText());
			/*
			 * if (amount == 0) { System.out.println("\nTotal: R" + cur.getTotal()); } else
			 * if (cur.currencyCheck(amount)) { cur.sum(amount); } else {
			 * System.out.println("Currency entered not accepted"); }
			 */
		} catch (IndexOutOfBoundsException | InputMismatchException error) {
			System.out.println("Invalid entry");
		}
	}
}
