package de.alaoli.games.minecraft.mods.lib.common.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.alaoli.games.minecraft.mods.lib.common.ui.event.*;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Focusable;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Hoverable;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import de.alaoli.games.minecraft.mods.lib.common.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.common.ui.layout.AbstractPane;
import de.alaoli.games.minecraft.mods.lib.common.ui.layout.Layout;
import net.minecraft.client.gui.GuiScreen;

public abstract class Screen<T extends Screen> extends GuiScreen implements Layout
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/
	
	private AbstractPane layout;
	private final List<Element> listeners = new ArrayList<>();

	/******************************************************************************************
	 * Method
	 ******************************************************************************************/

	public T setLayout(AbstractPane layout )
	{
		this.layout = layout;

		layout.setElementBounds(0, 0, this.width, this.height );

		return (T)this;
	}
	
	public Optional<Layout> getLayout()
	{
		return Optional.ofNullable( this.layout );
	}
	
	public <L extends Element & InputListener> T addListener( L listener )
	{		
		this.listeners.add( listener );
		
		return (T)this;
	}
	
	public <L extends Element & InputListener> T removeListener( L listener )
	{
		this.listeners.remove( listener );
		
		return (T)this;
	}
	
	public <L extends Element & InputListener> boolean hasListener( L listener )
	{
		return this.listeners.contains( listener );
	}
	
	public void clearInputListener()
	{
		this.listeners.clear();
	}

	public void close()
	{

		this.mc.displayGuiScreen(null);

		if (this.mc.currentScreen == null)
		{
			this.mc.setIngameFocus();
		}
	}

	/******************************************************************************************
	 * Method - Implement GuiScreen
	 ******************************************************************************************/
	
	@Override
	public void initGui() 
	{
		super.initGui();

		//MinecraftForge.EVENT_BUS.register( this );
		this.layout();
	}
	
	@Override
	public void onGuiClosed() 
	{
		super.onGuiClosed();

		//MinecraftForge.EVENT_BUS.unregister( this );
		this.listeners.clear();
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
	}

	@Override
	public void handleMouseInput() throws IOException
	{
		super.handleMouseInput();
		
		if( this.listeners.isEmpty() ) { return; }

		MouseEvent event = new MouseEvent(
			Mouse.getEventX() * this.width / this.mc.displayWidth,
			this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1,
			Mouse.getEventButton()
		);
		this.listeners
			.stream()
			.filter( listener -> listener instanceof MouseListener )
			.collect(Collectors.toCollection( ArrayList::new ))
			.forEach( listener -> {
				boolean hovered = listener.box.contains( event.x, event.y );

				//Element entered or exited event
				if( listener instanceof Hoverable)
				{
					if( ( hovered ) &&
						( !((Hoverable)listener).isHovered() ) )
					{
						((Hoverable)listener).setHover( true );
						((MouseListener)listener).mouseEntered( event );
						MinecraftForge.EVENT_BUS.post( new MouseEnteredEvent( (Element&MouseListener)listener, event ) );
					}
					else if(( !hovered ) &&
							( ((Hoverable)listener).isHovered() ) )
					{
						((Hoverable)listener).setHover( false );
						((MouseListener)listener).mouseExited( event );
						MinecraftForge.EVENT_BUS.post( new MouseExitedEvent( (Element&MouseListener)listener, event ) );
					}
				}

				//Element clicked or released event
				if( ( hovered ) &&
					( Mouse.getEventButtonState() ) )
				{
					if( ( listener instanceof Focusable ) &&
						( !((Focusable)listener).isFocused() ))
					{
						this.listeners
							.stream()
							.filter( focus -> focus instanceof Focusable )
							.forEach( focus -> ((Focusable)focus).setFocus( false ) );

						((Focusable)listener).setFocus( true );
					}
					((MouseListener)listener).mouseClicked( event );
					//MinecraftForge.EVENT_BUS.post( new MouseClickedEvent( (Element&MouseListener)listener, event ) );
				}
			});
	}

	@Override
	public void handleKeyboardInput() throws IOException 
	{
		int eventKey = Keyboard.getEventKey();
		char  eventChar = Keyboard.getEventCharacter();

		//Close Screen
		if( eventKey == Keyboard.KEY_ESCAPE ) { this.close(); return; }
		
		//Nothing to do
		if ( this.listeners.isEmpty() ) { return; }
		
		if( ( Keyboard.getEventKeyState() ) ||
			(( eventKey == Keyboard.KEY_NONE && eventChar >= 32 ) ))
		{
			KeyboardEvent event = new KeyboardEvent( eventChar, eventKey );

			this.listeners
				.stream()
				.filter( listener -> listener instanceof KeyboardListener)
				.collect(Collectors.toCollection( ArrayList::new ))
				.forEach( listener -> ((KeyboardListener)listener).keyPressed( event ) );
		}
		this.mc.dispatchKeypresses();
	}

	@Override
	public void drawScreen( int mouseX, int mouseY, float partialTicks )
	{
		if( this.layout == null ) { return; }

		this.layout.drawElement( mouseX, mouseY, partialTicks );
	}
	
	/******************************************************************************************
	 * Method - Implement Layout
	 ******************************************************************************************/

	@Override
	public void layout()
	{
		this.doLayout();
		
		if( this.layout != null ) { this.layout.layout(); }
		
	}
	
	@Override
	public void doLayout() {}	
}
