package io;

public class ExportData {
	//private static ObjectOutputStream oos;

	/**
	public void writeCharacterisitics(Fenetre f) {
		URL url =  ExportData.class.getResource("ExportData.class");
		URL urlText;
		try {
			urlText = new URL(url,"../info.txt");
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(urlText.getPath())));
			out.writeBytes(Integer.toString(f.app.getPanelAstrolab().getImageResolution())+",");
			out.writeBytes(Integer.toString(ObjetCeleste.getMenuAstrolabe().getTympan().getPhiD())+",");
			out.writeBytes(Integer.toString(ObjetCeleste.getMenuAstrolabe().getTympan().getPhiM())+",");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}*/
	
	
	/*public static void writeLocation (int[][] loc){
		try {
			URL url = ExportData.class.getResource("ExportData.class");
			URL urlText = new URL(url, "../location.txt");
			oos=new ObjectOutputStream(new FileOutputStream(new File(urlText.getPath())));
			oos.writeObject(loc);
			oos.close();
		}
		catch (FileNotFoundException e) {
			try {
				URL url2 = ExportData.class.getResource("ExportData.class");
				URL urlText2 = new URL(url2, "../../location.txt");
				oos=new ObjectOutputStream(new FileOutputStream(new File(urlText2.getPath())));
				oos.writeObject(loc);
				oos.close();
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
