package de.alaoli.games.minecraft.mods.lib.common.ui.event;

public interface MouseEvent extends InputEvent 
{
	public void mouseClicked( int mouseX, int mouseY, int mouseButton ); 
	public void mouseReleased( int mouseX, int mouseY, int state ); 
	public void mouseClickMove( int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick );
}
