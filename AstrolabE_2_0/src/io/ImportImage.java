package io;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class ImportImage {
	
	public static String language;

	public static void setLanguage(String s) {
		language = s;
	}
	
	
	public static BufferedImage read(String s) {
		URL url =  ImportImage.class.getResource("ImportImage.class");
		URL urlImage = null;
		try {
			urlImage = new URL(url,"../data/images/"+s);
			//System.out.println(urlImage);
			return (ImageIO.read(urlImage).equals(null) 
					? ImageIO.read(new File(s)) : 
					ImageIO.read(urlImage)
					)
					;
		} catch (IOException e) {
			try {
				urlImage = new URL(url,"../../data/images/"+s);
				return (ImageIO.read(urlImage).equals(null) ? ImageIO.read(new File(s)) : ImageIO.read(urlImage));
			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println("Image import didn't succeed");
			}
		} 
		return null;
	}
	/**
	public static void write(BufferedImage image,String format, String s) {
		URL url =  ImportImage.class.getResource("ImportImage.class");
		URL urlImage;
		FileOutputStream fos;
		try {
			urlImage = new URL(url,"../"+s);
			fos = new FileOutputStream(new File(urlImage.getPath()));
			ImageIO.write(image,format,fos);
		} catch (IOException e) {
			try {
				urlImage = new URL(url,"../../"+s);
				System.out.println(urlImage.getPath());
				fos = new FileOutputStream(new File(urlImage.getPath()));
				ImageIO.write(image,format,fos);
			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println("AAAAAAAAAAAAAAA");
			}
		}
	}*/
}
