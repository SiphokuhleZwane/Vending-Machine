import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperations {
	
	private final String fileName;
	protected ArrayList<String[]> list =  new ArrayList<>();
	
	public FileOperations(String fileName) {
		this.fileName = fileName;
		fileOpener();
	}
	
	public void fileOpener() {
		try {	
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
					String[] attributes = line.split(",");
					list.add(attributes);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Vending Machine has run into an error");
		}
	}
	
	public void stockChange(int count, String change, int index) { 
		int stockVal;
		if (change.equals("subtract"))
			stockVal = Integer.parseInt(list.get(count)[index]) -1;
		else
			stockVal = Integer.parseInt(list.get(count)[index]) +1;
		list.get(count)[index] = ""+stockVal;
	}	
	
	public void fileChange() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("temp.csv")); 
			for (String[] attributes: list) {
				String line = String.join(",", attributes);
				bw.write(line+"\n");
			}
			bw.flush();
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}

		// delete product file..
		File oldFile = new File(fileName);
		oldFile.delete();

		// renaming tmp file name to product name
		File newFile = new File("temp.csv");
		newFile.renameTo(oldFile);
	}
}
