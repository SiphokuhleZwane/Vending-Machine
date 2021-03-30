import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class currencyGUI {
	
	private currency cur;
	private static JLabel total;

	public currencyGUI(currency cur) {
		this.cur = cur;
	}
	
	public static void setTotal(String text) {
		total.setText(text);
	}

	public JPanel currencyMenu() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		mainFrame.setMyConstraints(c, 0, 0, 1, 1, GridBagConstraints.CENTER);

		mainFrame.setMyConstraints(c, 0, 1, 1, 1, GridBagConstraints.WEST);
		JLabel input = new JLabel("Select amounts (Rands): ");
		panel.add(input, c);

		mainFrame.setMyConstraints(c, 0, 2, 1, 1, GridBagConstraints.WEST);
		panel.add(currencyPad(), c);

		total = new JLabel("Total: R" + cur.getTotal());
		mainFrame.setMyConstraints(c, 0, 3, 1, 1, GridBagConstraints.WEST);
		panel.add(total, c);
		
		return panel;
	}

	public JPanel currencyPad() {
		JPanel panel = new JPanel(new GridLayout(2, 3));
		for (int i = 1; i < cur.size(); i++) {
			// setting up product names
			JButton btn = new JButton("R" + cur.currencyUnit(i));
			panel.add(btn);
			Integer j = Integer.valueOf(i);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cur.sum(j);
					total.setText("Total: R" + cur.getTotal());
				}
			});

		}
		return panel;
	}

}
