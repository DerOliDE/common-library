package de.alaoli.games.minecraft.mods.lib.common.ui.wrapped;

import de.alaoli.games.minecraft.mods.lib.common.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.layout.AbstractPane;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ScrollingText extends AbstractPane<ScrollingText>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    public static final FontRenderer FONTRENDERER = Minecraft.getMinecraft().fontRenderer;

    private final List<String> lines = new ArrayList<>();
    private ScrollingList wrapped;
    private TextStyle textStyle;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    public ScrollingText setTextStyle(TextStyle textStyle )
    {
        this.textStyle = textStyle;

        return this;
    }

    public Optional<TextStyle> getTextStyle()
    {
        return Optional.ofNullable( this.textStyle );
    }

    public ScrollingText setLines( List<String> lines )
    {
        this.lines.clear();
        this.lines.addAll( lines );

        return this;
    }

    public List<String> getLines()
    {
        return this.lines;
    }

    public Optional<ScrollingList> getScrollingList()
    {
        return Optional.ofNullable( this.wrapped );
    }

    /* **************************************************************************************************************
     * Method - Implements AbstractPane
     ************************************************************************************************************** */

    @Override
    public Optional<Collection<Element>> getElements() { return Optional.empty(); }

    @Override
    public void doLayout()
    {
        this.wrapped = new ScrollingList( this );
    }

    @Override
    public void drawElement( int mouseX, int mouseY, float partialTicks)
    {
        super.drawElement( mouseX, mouseY, partialTicks );
        this.wrapped.drawScreen( mouseX, mouseY, partialTicks );
    }

    /* **************************************************************************************************************
     * Class - GuiScrollingList
     ************************************************************************************************************** */

    public static class ScrollingList extends GuiScrollingList
    {
        private ScrollingText parent;

        public ScrollingList( ScrollingText parent )
        {
            super(
                Minecraft.getMinecraft(),
                parent.box.getWidth()-2,
                parent.box.getHeight()-2,
                parent.box.getY()+1,
                parent.box.getY() + parent.box.getHeight()-1,
                parent.box.getX()+1,
                parent.getTextStyle().map( TextStyle::getLineHeight ).orElse( 0 )
            );
            this.parent = parent;
        }

        /* ****************************************************************************************************
         * Method - Implement GuiScrollingList
         **************************************************************************************************** */

        @Override
        protected int getSize()
        {
            return this.parent.lines.size();
        }

        @Override
        protected void elementClicked(int index, boolean doubleClick) {}

        @Override
        protected boolean isSelected(int index) { return false; }

        @Override
        protected void drawBackground() {}

        @Override
        protected void drawSlot(int index, int right, int y, int height, Tessellator tess)
        {
            String line = (this.parent.lines != null ) ? this.parent.lines.get( index ) : null;

            if( line == null ) { return; }
            if( this.parent.textStyle == null ) { this.parent.textStyle = new TextStyle(); }

            y += 2;
            int x = this.left+2;
            int color = this.parent.textStyle.getColor().map( Color::getValue ).orElse( Color.DEFAULT );
            Align align = this.parent.textStyle.getAlign().orElse( Align.LEFT );

            switch( align )
            {
                case RIGHT:
                    x =	this.listWidth - FONTRENDERER.getStringWidth( line );
                    break;
                case CENTER:
                    x =	Math.round( (0.5f * this.listWidth) - (0.5f * FONTRENDERER.getStringWidth( line )) );
                    break;
                case LEFT:
                default:
                    //Nothing to do
                    break;
            }
            if( this.parent.textStyle.hasShadow() )
            {
                FONTRENDERER.drawStringWithShadow( line, x, y, color );
            }
            else
            {
                FONTRENDERER.drawString( line, x, y, color );
            }
        }
    }
}
