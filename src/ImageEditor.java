import java.io.*;
import java.util.*;

public class ImageEditor {

	public static void main(String[] args) {
		
		try{
			
			//read in input and create Image
			FileReader fr = new FileReader(args[0]);
			Scanner s = new Scanner(fr);
			
			s.useDelimiter("(\\s+)(#[^\\n]*\\n)?(\\s*)|(#[^\\n]*\\n)(\\s*)");
			
			s.next(); //P3
			int width = Integer.valueOf(s.next());
			int height = Integer.valueOf(s.next());
			s.next(); //max value
			
			Image img = new Image(width, height);
			
			
			for(int x=0; x<height; x++){
				for(int y=0; y<width; y++){
					int redVal = Integer.valueOf(s.next());
					int greenVal = Integer.valueOf(s.next());
					int blueVal = Integer.valueOf(s.next());
					img.setPixel(x, y, redVal, greenVal, blueVal);
				}
			}
			s.close();
			fr.close();
			
			//transform image based on command-line arguments
			if(args[2].equals("invert"))
					img.invert();
			else if(args[2].equals("grayscale"))
				img.grayScale();
			else if(args[2].equals("motionblur"))
				img.motionBlur(Integer.valueOf(args[3]));
			else if(args[2].equals("emboss"))
				img.emboss();
			
			
				
			//write out output
			StringBuilder sb = new StringBuilder();
			sb.append("P3 ");
			sb.append(width);
			sb.append(" ");
			sb.append(height);
			sb.append(" 255");
			sb.append(img.imgToString());
			
			FileWriter fw = new FileWriter(args[1]);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			
			pw.print(sb.toString());
			
			pw.close();
			bw.close();
			fw.close();
			
			
		}
		catch(IOException e){
			System.out.println("Error");
		}
		
	}

}

