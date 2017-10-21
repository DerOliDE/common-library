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
		
	private TextStyle textStyle;
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

		int width = this.box.getWidth();
		int height = this.box.getHeight();
		int x = this.box.getX();
		int y = Math.round( this.box.getY() + (0.5f * height ) - ( 0.5f * this.textStyle.getLineHeight() ) );
		int color = this.textStyle.getColor().map( Color::getValue ).orElse( Color.DEFAULT );
		Align align = this.textStyle.getAlign().orElse( Align.LEFT );
		
		switch( align )
		{
			case RIGHT:
				x =	width - FONTRENDERER.getStringWidth( this.text);
				break;
			case CENTER:
				x =	Math.round( ( 0.5f * width ) - ( 0.5f * FONTRENDERER.getStringWidth( this.text) ) );
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
