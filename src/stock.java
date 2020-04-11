import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class stock {
		
		private String fn;
		ArrayList<String> list =  new ArrayList<>();
		
		public stock (String fn) {
			this.fn = fn;
			fileOpener();
		}
		
		public void fileOpener() {
			try {	
			BufferedReader br = new BufferedReader(new FileReader(fn));
			String line;
				while ((line = br.readLine()) != null) {
					//attributes = line.split(",");
					list.add(line);
				}
			br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void Menu() {
			System.out.println("\nMenu");
			int count = 1;
			for (String line: list) {
				String[] attributes = line.split(",");
				System.out.println(count + ") " + attributes[0] + ":\t" + "R" + attributes[1]);
				count++;			
			}
		}
		
		public int availability(int num, int amount) {
			if (num == 0)
				return -2;
			else if (num > list.size() || num < 0){
				return -3;
			}
			
			int count = 1;
			String[] attributes = null;
			for (String line: list) {
				attributes = line.split(",");
				if (count == num) {
					if (Integer.parseInt(attributes[1]) > amount) {
						return -1;
					}
					else if (Integer.parseInt(attributes[2]) == 0) {
						return 0;
					}
					else {
						System.out.println("\nPlease collect: " + attributes[0]);
						//return Integer.parseInt(attributes[1]);
					}
				}
				count++;
			}
			return Integer.parseInt(attributes[1]);
	
		}
		
		public void stockChange(int num) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("temp.csv")); 
				String putData;
				int count = 1;
				for (String line: list) {
					if (count == num) {
						int i = line.lastIndexOf(",");
						int stockVal = Integer.parseInt(line.substring(i+1)) -1;
						putData = line.substring(0,i+1) + stockVal;
						list.set(count-1, putData);
						bw.write(putData+"\n");
						count++;
					}
					else {
						bw.write(line+"\n");
						count++;
					}
				}
				bw.flush();
				bw.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			// delete product file..
	        File oldFile = new File(fn);
	        oldFile.delete();

	        // renaming tmp file name to product name
	        File newFile = new File("temp.csv");
	        newFile.renameTo(oldFile);
			
		}
		
		
		
}
