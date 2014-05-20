//author Aritra Dhar
//MT12004
//M.TECH CSE
//INFORMATION SECURITY
//IIIT-Delhi
//Image decoder from image binary format
//---------------->receiver side tool<--------------------
//Read the comments carefully for the successful deployment


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;


public class ImageDecoder 
{
	public static void main(String[] args) throws Exception 
	{
		BufferedReader wd_b=new BufferedReader(new InputStreamReader(System.in));
		BufferedReader hi_b=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter weidth and height of the image retrived from img_new.txt :: ");
		String wd_s=wd_b.readLine();
		String hi_s=hi_b.readLine();
		int hi=Integer.parseInt(hi_s);
		int wd=Integer.parseInt(wd_s);
		//int wd=819,hi=460;               
		
		//user needs to give the location of the retrieved secret image
		//the file name can be choseen by the user
		//the format of the file is to be chosen .jpg strictly
		File img_ret=new File("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\image_ret.jpg");
		img_ret.createNewFile();
		
		BufferedImage img_copy=new BufferedImage(wd, hi, BufferedImage.TYPE_INT_RGB);
		
		//the user need to provide the location of the img_new.txt which is created by OTPdecoder.java
		FileInputStream in_bin_img=new FileInputStream("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\img_new.txt");
		BufferedReader b_img=new BufferedReader(new InputStreamReader(new DataInputStream(in_bin_img)));
		
		int i=0,j=0;
		String img_input;
		int []pix_data=new int[wd*hi];
		
		while((img_input=b_img.readLine()).equalsIgnoreCase("*")!=true)
		{
			pix_data[i]=Integer.parseInt(img_input,2);
			i++;
		}
		int count=0;
		
		for(i=0;i<wd;i++)
		{
			for(j=0;j<hi;j++)
			{
				int temp=0x00000000;  //RGB initiation
				temp=temp | pix_data[count];
				count++;
				
				img_copy.setRGB(i, j, temp);
			}
		}
		
		ImageIO.write(img_copy,"jpg",img_ret);
		
		in_bin_img.close();
		b_img.close();
		
		System.out.print("Secret message Recoverded");
	}

}
