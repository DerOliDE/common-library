package de.alaoli.games.minecraft.mods.lib.common.ui.util;

import de.alaoli.games.minecraft.mods.lib.common.util.Node;
import net.minecraft.client.gui.FontRenderer;

public interface ElementNode extends Node
{
	public boolean hasParent();
	public ElementNode setParent( ElementNode parent );
	public ElementNode getParent();
	
	public ElementNode setAlign( Align align );
	public Align getAlign();
	
	public ElementNode setOffsetX( float offsetX );
	public ElementNode setOffsetY( float offsetY );
	public float getOffsetX();
	public float getOffsetY();

	public ElementNode setPosX( float posX );
	public ElementNode setPosY( float posY );
	public float getPosX();
	public float getPosY();
	
	public ElementNode setScale( float scale );	
	public ElementNode setScaleX( float scaleX );
	public ElementNode setScaleY( float scaleY );
	public float getScaleX();
	public float getScaleY();
	
	public ElementNode setWidth( float width );
	public ElementNode setHeight( float height );
	public float getWidth();
	public float getHeight();

	public FontRenderer getFontRenderer();
	
	public void layout();
	public void draw( int mouseX, int mouseY, float partialTicks );
}
