package de.alaoli.games.minecraft.mods.lib.common.ui.element;

import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.common.ui.drawable.Drawable;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.StateableStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.event.KeyboardEvent;
import de.alaoli.games.minecraft.mods.lib.common.ui.event.KeyboardListener;
import de.alaoli.games.minecraft.mods.lib.common.ui.event.MouseEvent;
import de.alaoli.games.minecraft.mods.lib.common.ui.event.MouseListener;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;

public class TextField extends Element<TextField>
		implements Focusable<TextField>, Hoverable<TextField>, Disableable<TextField>, MouseListener, KeyboardListener
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/

	public static final FontRenderer FONTRENDERER = Minecraft.getMinecraft().fontRenderer;

	private boolean focused = false;
	private boolean hovered = false;
	private boolean disabled = false;

	private StateableStyle<BoxStyle> boxStyle;
	private StateableStyle<TextStyle> textStyle;

	private String placeholder;
	private String text = "";
	private int maxLength = 32;
	private int cursorPos = 0;

	/******************************************************************************************
	 * Method
	 ******************************************************************************************/

	public Optional<StateableStyle<BoxStyle>> getBoxStyle()
	{
		return Optional.ofNullable( this.boxStyle );
	}

	public TextField setBoxStyle( StateableStyle<BoxStyle> boxStyle )
	{
		this.boxStyle = boxStyle;

		return this;
	}

	public Optional<StateableStyle<TextStyle>> getTextStyle()
	{
		return Optional.ofNullable( this.textStyle );
	}

	public TextField setTextStyle( StateableStyle<TextStyle> textStyle )
	{
		this.textStyle = textStyle;

		return this;
	}

	public Optional<String> getPlaceholder()
	{
		return Optional.ofNullable( this.placeholder );
	}

	public TextField setPlaceholder( String placeholder)
	{
		this.placeholder = placeholder;

		return this;
	}

	public Optional<String> getText()
	{
		return Optional.ofNullable( this.text );
	}

	public TextField setText( String text )
	{
		this.text = text;
		this.cursorPos = this.text.length();

		return this;
	}

	public State getState()
	{
		if( this.disabled ) {return State.DISABLED; }
		if( this.focused ) {return State.FOCUSED; }
		if( this.hovered ) {return State.HOVERED; }

		return State.NONE;
	}

	public int getMaxLength()
	{
		return this.maxLength;
	}

	public TextField setMaxLength( int maxLength )
	{
		this.maxLength = maxLength;

		return this;
	}

	public boolean writeCharAt( int pos, char c )
	{
		if( ( !this.disabled ) &&
			( ChatAllowedCharacters.isAllowedCharacter( c ) ) &&
			( pos >= 0 ) &&
			( pos <= this.text.length()))
		{
			this.text = this.text.substring( 0, pos ) + c + this.text.substring( pos );

			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean removeCharAt( int pos )
	{
		if( ( !this.disabled ) &&
			( !this.text.isEmpty() ) &&
			( pos >= 0 ) &&
			( pos <= this.text.length() ) )
		{
			this.text = this.text.substring( 0, pos ) + this.text.substring( pos+1 );

			return true;
		}
		else
		{
			return false;
		}
	}

	/******************************************************************************************
	 * Method - Implement Element 
	 ******************************************************************************************/

	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		State state = this.getState();

		if( this.boxStyle != null ) { this.boxStyle.get(state).ifPresent( style -> style.drawOn( this ) ); }

		if( this.textStyle != null )
		{
			this.textStyle.get( state ).ifPresent( style -> {
				int lineHeight = style.getLineHeight();
				int width = this.box.getWidth();
				int height = this.box.getHeight();
				int x = this.box.getX();
				int y = this.box.getY();
				int centerY = Math.round(y + (0.5f * height) - (0.5f * lineHeight));
				Align align = style.getAlign().orElse(Align.LEFT);
				String text = "";
				int color = Color.DEFAULT;

				if( ( this.text != null ) &&
					( !this.text.isEmpty() ) )
				{
					text = FONTRENDERER.trimStringToWidth(this.text, this.box.getWidth() - 4);
					color =  style.getColor().map(Color::getValue).orElse(Color.DEFAULT);
				}
				else if(( this.placeholder != null ) &&
						( !this.placeholder.isEmpty()) &&
						( !this.focused ) &&
						( !this.disabled ) )
				{
					text = FONTRENDERER.trimStringToWidth(this.placeholder, this.box.getWidth() - 4);
					color = this.textStyle.get( State.DISABLED )
						.map( TextStyle::getColor )
						.orElse( Optional.of( new Color() ) )
							.map( Color::getValue )
							.orElse( Color.DEFAULT );
				}

				switch (align) {
					case RIGHT:
						x = width - FONTRENDERER.getStringWidth(this.text) - 2;
						break;
					case CENTER:
						x = Math.round((0.5f * width) - (0.5f * FONTRENDERER.getStringWidth(this.text)));
						break;
					case LEFT:
						x += 1;
					default:
						//Nothing to do
						break;
				}

				if (style.hasShadow()) {
					FONTRENDERER.drawStringWithShadow(text, x, centerY, color);
				} else {
					FONTRENDERER.drawString(text, x, centerY, color);
				}

				if( (!this.disabled) &&
					(this.focused))
				{
					this.drawVerticalLine(
							x + FONTRENDERER.getStringWidth(text.substring(0, this.cursorPos)),
							y + 1,
							y + height - 1,
							color
					);
				}
			});
		}
	}
	
	/******************************************************************************************
	 * Method - Implement Focusable
	 ******************************************************************************************/

	@Override
	public TextField setFocus( boolean focus )
	{
		this.focused = focus;

		return this;
	}

	@Override
	public boolean isFocused()
	{
		return this.focused;
	}

	/******************************************************************************************
	 * Method - Implement Hoverable
	 ******************************************************************************************/

	@Override
	public TextField setHover(boolean hover)
	{
		this.hovered = hover;

		return this;
	}

	@Override
	public boolean isHovered()
	{
		return this.hovered;
	}

	/******************************************************************************************
	 * Method - Implement Disableable
	 ******************************************************************************************/

	@Override
	public TextField setDisable(boolean disable)
	{
		this.disabled = disable;

		return this;
	}

	public boolean isDisabled()
	{
		return this.disabled;
	}

	/******************************************************************************************
	 * Method - Implements MouseListener
	 ******************************************************************************************/

	@Override
	public void mouseEntered( MouseEvent event )
	{

	}

	@Override
	public void mouseExited( MouseEvent event )
	{

	}

	@Override
	public void mouseClicked( MouseEvent event )
	{

	}

	/******************************************************************************************
	 * Method - Implements KeyboardListener
	 ******************************************************************************************/
	
	@Override
	public void keyPressed( KeyboardEvent event )
	{
		if( !this.focused ) { return; }

		switch( event.eventKey ) {
			case Keyboard.KEY_LEFT:
				if (this.cursorPos > 0) { this.cursorPos--; }
				break;

			case Keyboard.KEY_RIGHT:
				if (this.cursorPos < this.text.length()) { this.cursorPos++; }
				break;

			case Keyboard.KEY_HOME:
				this.cursorPos = 0;
				break;

			case Keyboard.KEY_END:
				this.cursorPos = this.text.length();
				break;

			case Keyboard.KEY_TAB:
				/**
				 * @TODO FOCUSABLE
				 */
				break;
			case Keyboard.KEY_BACK:
				if( this.cursorPos > 0 ) { this.removeCharAt( --this.cursorPos ); }
				break;

			case Keyboard.KEY_DELETE:
				if( this.cursorPos < this.text.length() ) { this.removeCharAt( this.cursorPos ); }
				break;

			default:
				if( this.writeCharAt(this.cursorPos, event.eventChar) ) { this.cursorPos++; }
				break;
		}
	}
}
