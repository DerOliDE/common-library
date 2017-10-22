package de.alaoli.games.minecraft.mods.lib.common.ui.element;

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

import java.util.Optional;

public class TextArea extends Element<TextArea>
		implements Focusable<TextArea>, Hoverable<TextArea>, Disableable<TextArea>, MouseListener, KeyboardListener
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
    private String[] lines = new String[5];

    private int maxLength = 32;
    private int maxLines = 5;
    private int cursorPos = 0;
    private int cursorLine = 0;

    /******************************************************************************************
     * Method
     ******************************************************************************************/

    public Optional<StateableStyle<BoxStyle>> getBoxStyle()
    {
        return Optional.ofNullable( this.boxStyle );
    }

    public TextArea setBoxStyle( StateableStyle<BoxStyle> boxStyle )
    {
        this.boxStyle = boxStyle;

        return this;
    }

    public Optional<StateableStyle<TextStyle>> getTextStyle()
    {
        return Optional.ofNullable( this.textStyle );
    }

    public TextArea setTextStyle( StateableStyle<TextStyle> textStyle )
    {
        this.textStyle = textStyle;

        return this;
    }

    public Optional<String> getPlaceholder()
    {
        return Optional.ofNullable( this.placeholder );
    }

    public TextArea setPlaceholder( String placeholder)
    {
        this.placeholder = placeholder;

        return this;
    }

    public Optional<String> getText()
    {
        StringBuilder builder = new StringBuilder( " " );

        for( String line : this.lines )
        {
            if( line != null ) { builder.append( line ); }
        }
        return Optional.of( builder.toString() );
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

    public TextArea setMaxLength( int maxLength )
    {
        this.maxLength = maxLength;

        return this;
    }

    public boolean writeChar( char c )
    {
        return this.writeCharAt( this.cursorPos, this.cursorLine, c );
    }

    public boolean writeCharAt( int pos, int line, char c )
    {
        if( ( !this.disabled ) &&
            ( ChatAllowedCharacters.isAllowedCharacter( c ) ) &&
            ( this.validCursorPosAt( pos, line ) ) )
        {
            if( this.lines[line] == null ) { this.lines[line] = ""; }
            this.lines[line] = this.lines[line].substring( 0, pos ) + c + this.lines[line].substring( pos );

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeChar()
    {
        return this.removeCharAt( this.cursorPos, this.cursorLine );
    }

    public boolean removeCharAt( int pos, int line )
    {
        if( ( !this.disabled ) &&
            ( this.validCursorPosAt( pos, line ) ) &&
            ( !this.lines[line].isEmpty() ) )
        {
            this.lines[line] = this.lines[line].substring( 0, pos ) + this.lines[line].substring( pos+1 );

            return true;
        }
        else
        {
            return false;
        }
    }

    protected boolean validCursorPos( )
    {
        return this.validCursorPosAt( this.cursorPos, this.cursorLine );
    }

    protected boolean validCursorPosAt( int pos, int line )
    {

        if( ( line >= 0 ) &&
            ( line < maxLines ) &&
            ( pos >= 0) &&
            ( pos <= ((this.lines[line] ==null) ? 0 : this.lines[line].length() )) )
        {
            return true;
        }
        return false;
    }

    /******************************************************************************************
     * Method - Implement Element
     ******************************************************************************************/

    @Override
    public void drawElement( int mouseX, int mouseY,float partialTicks )
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
                Align align = style.getAlign().orElse(Align.LEFT);
                String text;
                int color = Color.DEFAULT;


                for( int i=0; i < this.maxLines-1; i++ )
                {
                    text = (this.lines[i] == null ) ? "" : this.lines[i];

                    if ((text != null) &&
                        (!text.isEmpty())) {
                        text = FONTRENDERER.trimStringToWidth(text, this.box.getWidth() - 4);
                        color = style.getColor().map(Color::getValue).orElse(Color.DEFAULT);
                    } else if ((this.placeholder != null) &&
                            (!this.placeholder.isEmpty()) &&
                            (!this.focused) &&
                            (!this.disabled)&&
                            ( i == 0)) {
                        text = FONTRENDERER.trimStringToWidth(this.placeholder, this.box.getWidth() - 4);
                        color = this.textStyle.get(State.DISABLED)
                                .map(TextStyle::getColor)
                                .orElse(Optional.of(new Color()))
                                .map(Color::getValue)
                                .orElse(Color.DEFAULT);
                    }

                    switch (align) {
                        case RIGHT:
                            x = width - FONTRENDERER.getStringWidth(text) - 2;
                            break;
                        case CENTER:
                            x = Math.round((0.5f * width) - (0.5f * FONTRENDERER.getStringWidth(text)));
                            break;
                        case LEFT:
                            x += 1;
                        default:
                            //Nothing to do
                            break;
                    }

                    if (style.hasShadow()) {
                        FONTRENDERER.drawStringWithShadow(text, x, y + lineHeight/ 2+ i*lineHeight , color);
                    } else {
                        FONTRENDERER.drawString(text, x, y + lineHeight/2 + i*lineHeight , color);
                    }
                }
                text = (this.lines[this.cursorLine] == null ) ? "" : this.lines[this.cursorLine];
                if ((!this.disabled) &&
                        (this.focused)) {
                    this.drawVerticalLine(
                            x + FONTRENDERER.getStringWidth(text.substring(0, this.cursorPos)),
                            y+ this.cursorLine*lineHeight + 1,
                            y + lineHeight + this.cursorLine*lineHeight+ 2,
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
    public TextArea setFocus( boolean focus )
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
    public TextArea setHover(boolean hover)
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
    public TextArea setDisable(boolean disable)
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

        String text;

        switch( event.eventKey ) {
            case Keyboard.KEY_LEFT:
                if (this.cursorPos > 0) { this.cursorPos--; }
                break;

            case Keyboard.KEY_RIGHT:
                if (this.cursorPos < this.lines[this.cursorLine].length()) { this.cursorPos++; }
                break;

            case Keyboard.KEY_UP:
                if( this.cursorLine > 0 )
                {
                    this.cursorLine--;
                    this.cursorPos = Math.min( this.cursorPos, (this.lines[this.cursorLine] != null) ? this.lines[this.cursorLine].length():0);
                }
                break;

            case Keyboard.KEY_DOWN:
                if( this.cursorLine < this.maxLines-1 )
                {
                    this.cursorLine++;
                    this.cursorPos = Math.min( this.cursorPos, (this.lines[this.cursorLine] != null) ? this.lines[this.cursorLine].length():0);
                }
                break;

            case Keyboard.KEY_HOME:
                this.cursorPos = 0;
                break;

            case Keyboard.KEY_END:

                this.cursorPos = this.lines[this.cursorLine].length();
                break;

            case Keyboard.KEY_TAB:
                /**
                 * @TODO FOCUSABLE
                 */
                break;
            case Keyboard.KEY_RETURN:
                if( this.cursorLine < this.maxLines-1 )
                {
                    this.cursorLine++;
                    this.cursorPos = 0;
                }
                break;

            case Keyboard.KEY_BACK:
                if( this.cursorPos > 0 ) { this.removeCharAt( --this.cursorPos, this.cursorLine ); }
                break;

            case Keyboard.KEY_DELETE:
                if( this.cursorPos < this.lines[this.cursorLine].length() ) { this.removeCharAt( this.cursorPos, this.cursorLine ); }
                break;

            default:
                if( this.writeCharAt(this.cursorPos, this.cursorLine, event.eventChar) ) { this.cursorPos++; }
                break;
        }
    }
}
