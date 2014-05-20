
//author Aritra Dhar
//MT12004
//M.TECH CSE
//INFORMATION SECURITY
//IIIT-Delhi
//Carrier file converter
//--------------->sender/receiver side tool<---------------
//Read the comments carefully for the successful deployment

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class CarrierEncoder 
{
	public static void main(String[] args) throws IOException 
	{
		
		//user needs to provide the location for the temorary file. File name is strictly carrier_bin.txt
		File carr_bin=new File("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\carrier_bin.txt");
		carr_bin.createNewFile();
		
		//user needs to give the carrier file location (c.txt)
		FileReader carr_stream=new FileReader("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\c.txt");
		FileWriter bwrite=new FileWriter(carr_bin);
		char current_char = 0;
		int current;
		String bin;
		while(current_char!=(char)-1)
		{
			current_char=(char)carr_stream.read();
			//current=0x00000000;
			current=(int)(current_char);
			bin=Integer.toBinaryString(current);
			bwrite.append(bin+"\n");		
		}
		bwrite.close();
		carr_stream.close();
		System.out.print("Write Complete");
	}

}
