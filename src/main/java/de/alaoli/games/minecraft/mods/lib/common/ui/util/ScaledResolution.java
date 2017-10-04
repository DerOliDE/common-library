package de.alaoli.games.minecraft.mods.lib.common.ui.util;

import net.minecraft.client.Minecraft;

public class ScaledResolution 
{
	public static int getScaleFactor()
	{
		Minecraft mc = Minecraft.getMinecraft();
		
		int scaleFactor = 1;
		int i = mc.gameSettings.guiScale;
        int scaledWidth = mc.displayWidth;
        int scaledHeight = mc.displayHeight;
        
        boolean flag = mc.isUnicode();

        if (i == 0)
        {
            i = 1000;
        }

        while (scaleFactor < i && scaledWidth / (scaleFactor + 1) >= 320 && scaledHeight / (scaleFactor + 1) >= 240)
        {
            ++scaleFactor;
        }

        if (flag && scaleFactor % 2 != 0 && scaleFactor != 1)
        {
            --scaleFactor;
        }
		return scaleFactor;
	}
	
	public static int getScaledHeight()
	{
		return Minecraft.getMinecraft().displayHeight;
	}
	
	public static int getScaledWidth()
	{
		return Minecraft.getMinecraft().displayWidth;
	}
}
