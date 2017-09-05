package de.alaoli.games.minecraft.mods.lib.common.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.alaoli.games.minecraft.mods.lib.common.ui.event.InputEvent;
import de.alaoli.games.minecraft.mods.lib.common.ui.event.KeyboardEvent;
import de.alaoli.games.minecraft.mods.lib.common.ui.event.MouseEvent;
import de.alaoli.games.minecraft.mods.lib.common.ui.layout.Pane;
import de.alaoli.games.minecraft.mods.lib.common.ui.layout.Layout;
import net.minecraft.client.gui.GuiScreen;

public abstract class Screen<T extends Screen> extends GuiScreen implements Layout
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/
	
	private Pane layout;
	private List<InputEvent> listener = new ArrayList<>();
	
	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
	
	public abstract void init();
	
	public T setLayout( Pane layout )
	{
		this.layout = layout;
		
		layout.setElementDimension( this.width, this.height );
		
		return (T)this;
	}
	
	public T addInputListener( InputEvent event )
	{
		this.listener.add( event );
		
		return (T)this;
	}
	
	public void removeInputListener( InputEvent event )
	{
		this.listener.remove( event );
	}
	
	public void clearInputListener()
	{
		this.listener.clear();
	}
	
	/******************************************************************************************
	 * Method - Implement GuiScreen
	 ******************************************************************************************/
	
	@Override
	public void initGui() 
	{
		super.initGui();
	
		this.init();
		this.layout();
		
	}
	
	@Override
	protected void keyTyped( char typedChar, int keyCode ) throws IOException 
	{
		super.keyTyped( typedChar, keyCode );
		
		this.listener
			.stream()
			.filter( listen -> { return listen instanceof KeyboardEvent; } )
			.forEach( listen -> ((KeyboardEvent)listen).keyTyped( typedChar, keyCode ) );
	}

	@Override
	protected void mouseClicked( int mouseX, int mouseY, int mouseButton ) throws IOException 
	{
		super.mouseClicked( mouseX, mouseY, mouseButton );
		
		this.listener
			.stream()
			.filter( listen -> { return listen instanceof MouseEvent; } )
			.forEach( listen -> ((MouseEvent)listen).mouseClicked( mouseX, mouseY, mouseButton ) );
	}

	@Override
	protected void mouseReleased( int mouseX, int mouseY, int state )
	{
		super.mouseReleased( mouseX, mouseY, state );
		
		this.listener
			.stream()
			.filter( listen -> { return listen instanceof MouseEvent; } )
			.forEach( listen -> ((MouseEvent)listen).mouseReleased( mouseX, mouseY, state ) );
	}

	@Override
	protected void mouseClickMove( int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick )
	{
		super.mouseClickMove( mouseX, mouseY, clickedMouseButton, timeSinceLastClick );
		
		this.listener
			.stream()
			.filter( listen -> { return listen instanceof MouseEvent; } )
			.forEach( listen -> ((MouseEvent)listen).mouseClickMove( mouseX, mouseY, clickedMouseButton, timeSinceLastClick ) );
	}


	@Override
	public void drawScreen( int mouseX, int mouseY, float partialTicks )
	{	
		this.layout.drawElement( mouseX, mouseY, partialTicks );
	}
	
	/******************************************************************************************
	 * Method - Implement Layout
	 ******************************************************************************************/
	
	@Override
	public void layout()
	{
		this.layout.layout();
	}
}
