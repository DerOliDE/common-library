package de.alaoli.games.minecraft.mods.lib.common.ui.element;

import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Label extends Element<Label>
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/
	
	public static final FontRenderer FONTRENDERER = Minecraft.getMinecraft().fontRenderer;
		
	private TextStyle textStyle;;
	private String text;
	
	/******************************************************************************************
	 * Method
	 ******************************************************************************************/

	public Label setText( String text ) 
	{
		this.text = text;
		
		return this;
	}	
	
	public Optional<String> getText() 
	{
		return Optional.ofNullable( this.text );
	}
	
	public Label setTextStyle( TextStyle textStyle )
	{
		this.textStyle = textStyle;
		
		return this;
	}
	
	public Optional<TextStyle> getTextStyle()
	{
		return Optional.ofNullable( this.textStyle );
	}
	
	/******************************************************************************************
	 * Method - Implement Element
	 ******************************************************************************************/

	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		if( this.text == null ) { return; }
		if( this.textStyle == null ) { this.textStyle = new TextStyle(); }

		int x = this.getElementPosX(); 
		int y = this.getElementPosY() + this.getElementHeight() / 2 - FONTRENDERER.FONT_HEIGHT / 2;		
		int color = this.textStyle.getColor().map( Color::getValue ).orElse( Color.DEFAULT );
		Align align = this.textStyle.getAlign().orElse( Align.LEFT );
		
		switch( align )
		{
			case RIGHT:
				x =	this.getElementWidth() - FONTRENDERER.getStringWidth( this.text);
				break;
			case CENTER:
				x =	this.getElementWidth() / 2 - FONTRENDERER.getStringWidth( this.text) / 2;
				break;
			case LEFT:
			default:
				//Nothing to do
				break;
		}
		
		if( this.textStyle.hasShadow() )
		{
			FONTRENDERER.drawStringWithShadow( this.text, x, y, color );
		}
		else
		{
			FONTRENDERER.drawString( this.text, x, y, color );
		}
	}
}
