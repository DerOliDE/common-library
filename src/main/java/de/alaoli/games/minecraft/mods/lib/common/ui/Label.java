package de.alaoli.games.minecraft.mods.lib.common.ui;

import de.alaoli.games.minecraft.mods.lib.common.ui.util.Element;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.ElementNode;
import net.minecraft.client.renderer.GlStateManager;

public class Label extends Element
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/
	
	private final String id;
	private String text = "";
	private int textColor = 0;
	
	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
	
	public Label( String id, ElementNode parent )
	{
		this.id = id;
		this.setParent( parent );
	}
	
	public Label( String id, ElementNode parent, String text )
	{
		this( id, parent );
		this.text = text;
	}
	
	public Label setText( String text ) 
	{
		this.text = text;
		
		return this;
	}	
	
	public String getText() 
	{
		return this.text;
	}

	public Label setTextColor( int textColor ) 
	{
		this.textColor = textColor;
		
		return this;
	}
	
	public int getTextColor() 
	{
		return this.textColor;
	}

	/******************************************************************************************
	 * Method - Implement Node
	 ******************************************************************************************/
	
	@Override
	public String getNodeName() 
	{
		return this.id;
	}
	
	/******************************************************************************************
	 * Method - Implement ElementNode
	 ******************************************************************************************/
	
	@Override
	public void act( int mouseX, int mouseY, float partialTicks ) {}
	
	@Override
	public void draw( int mouseX, int mouseY, float partialTicks )
	{
		GlStateManager.pushMatrix();
        {
        	GlStateManager.translate( this.getPosX(), this.getPosY(), 0 );
        	GlStateManager.scale( this.getScaleX(), this.getScaleY(), 1.0f );
            this.getFontRenderer().drawString( this.text, 0, 0, this.textColor );
        }
        GlStateManager.popMatrix();			
	}
}
