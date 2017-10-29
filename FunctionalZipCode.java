package lab2b;

//Carlos Bustos
//1001317137
//October 28, 2017
//Lab2b

/*
NOTE TO GRADER: the output text file looks like a huge mess
If you open the file in excel and use the tabs to delineate
the file, you can see that the file is organized nicely
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class FunctionalZipCode {
	
	private static File input = new File("L02b zip_code_database.csv");
	private static File output = new File("Problem_2b_output.txt");
	
	//Method receives an empty ArrayList and initiates it with the data in the input file
	private static ArrayList<zipCodeClass> initTheArray() throws IOException {
		ArrayList<zipCodeClass> zccArray = new ArrayList<zipCodeClass>();
		String [] values;
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(input));
		
		//reads the file until the last line
		while((line = br.readLine()) != null) {
			values = line.split(",");
			//stores objects(lines) not the individual data parts(columns)
			zccArray.add(new zipCodeClass(Integer.parseInt(values[0]), values[1], values[2], values[3], Integer.parseInt(values[4])));
		}
		br.close();
		return zccArray;
	}
	
	//Makes a file and sends data to it
	private static void writeOutputFile(ArrayList<zipCodeClass> zccArray) throws IOException{
		//Create the file
		FileWriter writer = new FileWriter(output);
		writer.write("County Name\tCounty Pop.\tCity Name\tCity Pop.\tNo. Zip Codes\n");
		//System.out.println("County Name\tCounty Pop.\tCity Name\tCity Pop.\tNo. Zip Codes");
		DecimalFormat IntWithComma = new DecimalFormat("###,###,###");
		//stream that obtains all unique county names
		zccArray.stream().map(cnty -> cnty.getCountyName()).distinct().sorted((cnty1, cnty2) -> cnty1.compareTo(cnty2))
				.forEach(p -> {
					int totalPop = (int) zccArray.stream().filter(zcc -> zcc.getCountyName().equals(p)).mapToInt(zcc -> zcc.getPopulation()).sum();
					//stream that gets all unique cities in a county, treat this stream like a for loop and c as your counter
					zccArray.stream().filter(a -> a.getCountyName().equals(p)).map(cty -> cty.getCityName()).distinct().sorted((cty1, cty2) -> cty1.compareTo(cty2))
						.forEach(c -> {
							int city_pop = (int) zccArray.stream().filter(zcc->zcc.getCountyName().equals(p)).filter(zcc -> zcc.getCityName().equals(c)).mapToInt(zcc ->zcc.getPopulation()).sum();
							int count = (int) zccArray.stream().filter(a ->a.getCountyName().equals(p)).filter(b->b.getCityName().equals(c)).count();
							String line = (p.toString() + "\t"+IntWithComma.format(totalPop)+"\t"+c.toString()+ "\t"+IntWithComma.format(city_pop)+"\t"+IntWithComma.format(count));	
							try {
								writer.write(line);
								//System.out.print(line);
							}
							catch(IOException e) {
								e.printStackTrace();
							}
						});
				});
		writer.close();
	}
	
	public  static void main(String[] args) {
		ArrayList<zipCodeClass> zccArray = new ArrayList<zipCodeClass>();
		try {
			zccArray = initTheArray();
			writeOutputFile(zccArray);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
