package yusuf.cato.util;

import java.awt.Color;

public class ColourUtil {

		
	public static int getRainbow(float secounds, float saturation, float brightness) {
		float hue = (System.currentTimeMillis() % (int)(1000 * secounds)) / (float)(1000f * secounds);
		int rainbowColour = Color.HSBtoRGB(hue, 0.8f, 1);
		return rainbowColour;
	}
	
	
	
}
