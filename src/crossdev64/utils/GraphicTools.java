package crossdev64.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class GraphicTools
{
	static public ImageIcon loadIcon(String oResource, int width, int height)
	{
		ImageIcon icon = loadIcon(oResource);
	    BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(icon.getImage(), 0, 0, width, height, null);
	    g2.dispose();

	    return new ImageIcon(resizedImg);
	}

	static public ImageIcon loadIcon(String oResource)
	{
		return new ImageIcon(Class.class.getResource(oResource));
	}
}
