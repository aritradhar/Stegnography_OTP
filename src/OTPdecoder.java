//author Aritra Dhar
//MT12004
//M.TECH CSE
//INFORMATION SECURITY
//IIIT-Delhi
//OTP decode to get the image binary format
//------------------>sereceiver side tool<----------------
//Read the comments carefully for the successful deployment

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;


public class OTPdecoder 
{

	public static void main(String[] args) throws Exception 
	{
		// user needs to provide the location of his carrier_bin.txt which was created in the CarrierDecoder.java
		FileInputStream in_bin_carr=new FileInputStream("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\carrier_bin.txt");
		
		//user needs to provide the path for the steganographic key file.
		//the key file is sent by the sender along with the carrier file(c.txt)
		//this file is top secret, exposing this file may lead to secret message leakage
		FileInputStream in_bin_key=new FileInputStream("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\key.txt");
		
		BufferedReader b_carr=new BufferedReader(new InputStreamReader(new DataInputStream(in_bin_carr)));
		BufferedReader b_key=new BufferedReader(new InputStreamReader(new DataInputStream(in_bin_key)));
		
		//User needs to give a location for the temporary file
		//the file name is strictly img_new.txt
		//after program execution, the user needs to convert last two binary string to integers
		//they will give width and height of the secret message
		//after calculation of height and width, the user is to delete last two strings
		File f_img=new File("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\img_new.txt");
		f_img.createNewFile();
		FileWriter img_writer=new FileWriter(f_img);
		
		String key_line,carr_line;
		Integer carr=0x00000000,key=0x00000000,img_f=0x00000000;
		String img;
		
		while(((key_line=b_key.readLine())!=null) && ((carr_line=b_carr.readLine())!=null))
		{
			if(key_line.equalsIgnoreCase("*"))
				img_writer.append("*\n");
			else
			{
				key=0;
				carr=0;
			
				key=Integer.parseInt(key_line,2);
	
				carr=Integer.parseInt(carr_line,2);
		
				img_f=key^carr;
				img=Integer.toBinaryString(img_f);
				img_writer.append(img+"\n");
			}
		}
	    
		in_bin_carr.close();
		in_bin_key.close();
		img_writer.close();
		
		System.out.print("Complete");
	}

}
