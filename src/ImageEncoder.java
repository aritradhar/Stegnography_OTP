//author Aritra Dhar
//MT12004
//M.TECH CSE
//INFORMATION SECURITY
//IIIT-Delhi
//Image Encoder and Decoder
//----------->Sender side tool<---------------------------
//Read the comments carefully for the successful deployment

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;





public class ImageEncoder 
{
	public ImageEncoder() 
	{
	}
	public static void main(String []a) throws Exception
	{
		//change secret image location (secret message.txt)
		File in_f=new File("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\secret message.jpg");
		//change location for the temporary file storage, later to be deleted. file name is strictly bin.txt
		File bin_f=new File("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\bin.txt");
		bin_f.createNewFile();
		FileWriter bwrite=new FileWriter(bin_f);
		
		try 
		{
			BufferedImage img=ImageIO.read(in_f);
			
			int hi=img.getHeight();
			int wd=img.getWidth();
			
			BufferedImage img_copy=new BufferedImage(wd, hi, BufferedImage.TYPE_INT_RGB);
			
			int [][]pixel_data=new int[wd*hi][3];
			
			String []bin_pixel_data=new String[wd*hi];
			System.out.println(hi);
			System.out.println(wd);
			
			int i,j,k=0;
			
			for(i=0;i<wd;i++)
			{
				for(j=0;j<hi;j++)
				{
					int clr=  img.getRGB(i,j);
					int  red   = (clr & 0x00ff0000) >> 16;  
				    int  green = (clr & 0x0000ff00) >> 8;  
				    int  blue  =  clr & 0x000000ff; 
				    int clr_alpha_el=(clr & 0x00ffffff);
				    
				    bin_pixel_data[k]=Integer.toBinaryString(clr_alpha_el);
				    
				    pixel_data[k][0]=red;                           
				    pixel_data[k][1]=green;                        
				    pixel_data[k][2]=blue;				          
				    
				    int temp=0x00000000;  //RGB initiation
				    temp=(temp | (red<<16));
				    temp=(temp | (green<<8));
				    temp=temp | blue;
				    
				    img_copy.setRGB(i, j, temp);
				    bwrite.append(bin_pixel_data[k]+"\n");
				    k++;
				}
			}
			bwrite.append("*\n"+Integer.toBinaryString(wd)+"\n"+Integer.toBinaryString(hi));  // Insert information about the dimention of the image
			
			System.out.println(k);
			bwrite.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.print("Encoding Complete");
	}
}


