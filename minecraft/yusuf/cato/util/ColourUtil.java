package yusuf.cato.util;

import java.awt.Color;

public class ColourUtil {

		
	public static int getRainbow(float secounds, float saturation, float brightness) {
		float seconds = 3;
		float hue = (System.currentTimeMillis() % (int)(1000 * seconds)) / (float)(1000f * seconds);
		int rainbowColour = Color.HSBtoRGB(hue, 0.8f, 1);
		return rainbowColour;
	}
	
	
	
}
