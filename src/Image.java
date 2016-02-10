import java.lang.Math;

public class Image {

	Pixel[][] pixArr;
	int width = 0;
	int height = 0;
	
	Image(int w, int h) {
		width = w;
		height = h;
		pixArr = new Pixel[h][w];
	}
	
	void setPixel(int x, int y, int redVal, int greenVal, int blueVal){
		pixArr[x][y]= new Pixel(redVal, greenVal, blueVal);
	}
	
	void invert() {
		for(int x=0; x<height; x++){
			for(int y=0; y<width; y++){
				pixArr[x][y].setR(Math.abs(pixArr[x][y].getR()-255));
				pixArr[x][y].setG(Math.abs(pixArr[x][y].getG()-255));
				pixArr[x][y].setB(Math.abs(pixArr[x][y].getB()-255));
			}
		}
	}
	
	void grayScale() {
		for(int x=0; x<height; x++){
			for(int y=0; y<width; y++){
				int val = (pixArr[x][y].getR() + pixArr[x][y].getG() + pixArr[x][y].getB())/3;
				pixArr[x][y].setR(val);
				pixArr[x][y].setG(val);
				pixArr[x][y].setB(val);
			}
		}
	}
	
	void motionBlur(int strength) {
		if(strength!=1){
			for(int x=0; x<width; x++){
				for(int y=0; y<height; y++){
					int valR=0;
					int valG=0;
					int valB=0;
					
					if(strength < width-x){
						for(int z=0; z<strength; z++){
							valR += pixArr[y][x+z].getR();
							valG += pixArr[y][x+z].getG();
							valB += pixArr[y][x+z].getB();
						}
						valR = valR/strength;
						valG = valG/strength;
						valB = valB/strength;
					}
					else {
						for(int z=0; z<(width-x); z++){
							valR += pixArr[y][x+z].getR();
							valG += pixArr[y][x+z].getG();
							valB += pixArr[y][x+z].getB();
						}
						valR = valR/(width-x);
						valG = valG/(width-x);
						valB = valB/(width-x);
					}
					pixArr[y][x].setR(valR);
					pixArr[y][x].setG(valG);
					pixArr[y][x].setB(valB);
				}
			}
		}
	}
	
	void emboss() {
		for(int x=height-1; x>0; x--){
			for(int y=width-1; y>0; y--){
				int redDiff = pixArr[x][y].getR() - pixArr[x-1][y-1].getR();
				int greenDiff = pixArr[x][y].getG() - pixArr[x-1][y-1].getG();
				int blueDiff = pixArr[x][y].getB() - pixArr[x-1][y-1].getB();
				
				int maxDifference;
				if(Math.abs(redDiff)>=Math.abs(greenDiff))
					maxDifference = redDiff;
				else
					maxDifference = greenDiff;
				
				if(Math.abs(maxDifference)<Math.abs(blueDiff))
					maxDifference = blueDiff;
				
				int v = 128 + maxDifference;
				
				if(v<0)
					v = 0;
				if(v > 255)
					v = 255;
				
				pixArr[x][y].setR(v);
				pixArr[x][y].setG(v);
				pixArr[x][y].setB(v);
			}
		}
		
		for(int x = 0; x < width; x++){
			pixArr[0][x].setR(128);
			pixArr[0][x].setG(128);
			pixArr[0][x].setB(128);
		}
		
		for(int x = 0; x < height; x++){
			pixArr[x][0].setR(128);
			pixArr[x][0].setG(128);
			pixArr[x][0].setB(128);
		}
		
			
	}
	
	String imgToString(){
		StringBuilder sb = new StringBuilder();
		
		for(int x=0; x<height; x++){
			for(int y=0; y<width; y++){
				sb.append(" ");
				sb.append(pixArr[x][y].getR());
				sb.append(" ");
				sb.append(pixArr[x][y].getG());
				sb.append(" ");
				sb.append(pixArr[x][y].getB());
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
}

