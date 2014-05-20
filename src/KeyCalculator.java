//author Aritra Dhar
//MT12004
//M.TECH CSE
//INFORMATION SECURITY
//IIIT-Delhi
//One-Time Pad (OTP) key generator
//------------->sender side tool<--------------------------
//Read the comments carefully for the successful deployment

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;



public class KeyCalculator 
{
	static int pow(int a,int b)
	{
		int result=1;
		for(int i=1;i<=b;i++)
		{
		 result*=a;	
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException 
	{
		// user needs to provide the location of his bin.txt which was created in the ImageEncoder.java
		FileInputStream in_bin_img=new FileInputStream("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\bin.txt");
		
		// user needs to provide the location of his carrier_bin.txt which was created in the CarrierDecoder.java
		FileInputStream in_bin_carr=new FileInputStream("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\carrier_bin.txt");
		
		//user needs to provide the path for the steganographic key file.
		//file name is strictly key.txt
		//this file is top secret, exposing this file may lead to secret message leakage
		File f_key=new File("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\key.txt");
		
		f_key.createNewFile();
		
		FileWriter key_writer=new FileWriter(f_key);
		@SuppressWarnings("resource")
		
		BufferedReader b_img=new BufferedReader(new InputStreamReader(new DataInputStream(in_bin_img)));
		BufferedReader b_carr=new BufferedReader(new InputStreamReader(new DataInputStream(in_bin_carr)));
		
		String img_line,carr_line,key;
		Integer img=0x00000000,carr=0x00000000,key_f=0x00000000;
		
		while(((img_line=b_img.readLine())!=null) && ((carr_line=b_carr.readLine())!=null))
		{
			if(img_line.equalsIgnoreCase("*"))
				key_writer.append("*\n");
			else
			{
				img=0;
				carr=0;
				img=Integer.parseInt(img_line,2);
				carr=Integer.parseInt(carr_line,2);

				key_f=img^carr;
				key=Integer.toBinaryString(key_f);
				key_writer.append(key+"\n");
			}
		}
		
		b_carr.close();
		b_carr.close();
		key_writer.close();
		
		System.out.print("Write Complete");
	}

}
