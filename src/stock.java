import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class stock {
		
		private String fn;
		private static BufferedReader br = null;
		
		public stock (String fn) {
			this.fn = fn;
			
		}
		
		public void fileOpener() {
			try {	
			br = new BufferedReader(new FileReader(fn));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void Menu() {
			fileOpener();
			System.out.println("\nMenu");
			String line, attributes[];
			int count = 1;
			try {
				while ((line = br.readLine()) != null) {
					attributes = line.split(",");
					System.out.println(count + ") " + attributes[0] + ":\t" + "R" + attributes[1]);
					count++;			
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public int availability(int num, int amount) {
			fileOpener();
			String line, attributes[];
			int count = 1;
			try {
				while ((line = br.readLine()) != null) {
					if (count == num) {
						attributes = line.split(",");
						if (Integer.parseInt(attributes[1]) > amount) {
							return -1;
						}
						else if (Integer.parseInt(attributes[2]) == 0) {
							return 0;
						}
						else {
							System.out.println("\nPlease collect your: " + attributes[0]);
						/*
						 * BufferedWriter bw = new BufferedWriter(new FileWriter(fn)); String putData;
						 * int i = line.lastIndexOf(","); int stockVal =
						 * Integer.parseInt(line.substring(i+1)) -1; putData = line.substring(0,i+1) +
						 * stockVal; bw.write(putData); bw.close();
						 */
							
							return Integer.parseInt(attributes[1]);
						}
					}
					count++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return -2;
		}
		
		public void stockChange(int num) {
			try{
				BufferedWriter bw = new BufferedWriter(new FileWriter(fn)); 
				String line, putData;
				int count = 1;
				while ((line = br.readLine()) != null) {
					if (count == num) {
						int i = line.lastIndexOf(",");
						int stockVal = Integer.parseInt(line.substring(i+1)) -1;
						putData = line.substring(0,i+1) + stockVal;
						bw.write(putData);
						bw.close();
					}
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             

		}
		
		
		
}
