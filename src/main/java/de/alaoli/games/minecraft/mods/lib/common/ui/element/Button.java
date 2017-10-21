package de.alaoli.games.minecraft.mods.lib.common.ui.element;

import de.alaoli.games.minecraft.mods.lib.common.ui.drawable.Drawable;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.StateableStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.event.MouseEvent;
import de.alaoli.games.minecraft.mods.lib.common.ui.event.MouseListener;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.Optional;
import java.util.function.Consumer;

public class Button extends Element<Button> implements Hoverable<Button>, Disableable<Button>, MouseListener
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/

	public static final FontRenderer FONTRENDERER = Minecraft.getMinecraft().fontRenderer;

	private boolean hovered = false;
	private boolean disabled = false;

	private StateableStyle<BoxStyle> boxStyle;
	private StateableStyle<TextStyle> textStyle;

	private String text;

	private Consumer<? super Button> onEntered;
	private Consumer<? super Button> onExited;
	private Consumer<? super Button> onClicked;

	/******************************************************************************************
	 * Method
	 ******************************************************************************************/

	public Optional<StateableStyle<BoxStyle>> getBoxStyle()
	{
		return Optional.ofNullable( this.boxStyle );
	}

	public Button setBoxStyle( StateableStyle<BoxStyle> boxStyle )
	{
		this.boxStyle = boxStyle;

		return this;
	}

	public Optional<StateableStyle<TextStyle>> getTextStyle()
	{
		return Optional.ofNullable( this.textStyle );
	}

	public Button setTextStyle( StateableStyle<TextStyle> textStyle )
	{
		this.textStyle = textStyle;

		return this;
	}

	public Button setText( String text )
	{
		this.text = text;

		return this;
	}

	public Optional<String> getText()
	{
		return Optional.ofNullable( this.text );
	}

	public State getState()
	{
		if( this.disabled ) {return State.DISABLED; }
		if( this.hovered ) {return State.HOVERED; }

		return State.NONE;
	}

	public Button onMouseEntered( Consumer<? super Button> consumer )
	{
		this.onEntered = consumer;

		return this;
	}

	public Button onMouseExited( Consumer<? super Button> consumer )
	{
		this.onExited = consumer;

		return this;
	}

	public Button onMouseClicked( Consumer<? super Button> consumer )
	{
		this.onClicked = consumer;

		return this;
	}

	/******************************************************************************************
	 * Method - Implement Element
	 ******************************************************************************************/
	
	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		State state = this.getState();

		if( this.boxStyle != null ) { this.boxStyle.get(state).ifPresent( style -> style.drawOn( this ) ); }

			if( ( this.textStyle != null ) &&
					( this.text != null ) ) {
				this.textStyle.get(state).ifPresent(style -> {
					int lineHeight = style.getLineHeight();
					int width = this.box.getWidth();
					int height = this.box.getHeight();
					int x = this.box.getX();
					int y = this.box.getY();
					int centerY = Math.round(y + (0.5f * height) - (0.5f * lineHeight));
					int color = style.getColor().map(Color::getValue).orElse(Color.DEFAULT);
					Align align = style.getAlign().orElse(Align.LEFT);
					String text = FONTRENDERER.trimStringToWidth(this.text, this.box.getWidth() - 4);

					switch (align) {
						case RIGHT:
							x = width - FONTRENDERER.getStringWidth(this.text) - 2;
							break;
						case CENTER:
							x = Math.round((0.5f * width) - (0.5f * FONTRENDERER.getStringWidth(this.text)));
							break;
						case LEFT:
							x += 2;
						default:
							//Nothing to do
							break;
					}

					if (style.hasShadow()) {
						FONTRENDERER.drawStringWithShadow(text, x, centerY, color);
					} else {
						FONTRENDERER.drawString(text, x, centerY, color);
					}
				});
			}

	}

	/******************************************************************************************
	 * Method - Implement Hoverable
	 ******************************************************************************************/

	@Override
	public Button setHover(boolean hover)
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
	public Button setDisable(boolean disable)
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
		if( this.onEntered != null ) { this.onEntered.accept( this );}
	}

	@Override
	public void mouseExited( MouseEvent event )
	{
		if( this.onExited != null ) { this.onExited.accept( this );}
	}

	@Override
	public void mouseClicked( MouseEvent event )
	{
		if( this.onClicked != null ) { this.onClicked.accept( this );}
	}
}
