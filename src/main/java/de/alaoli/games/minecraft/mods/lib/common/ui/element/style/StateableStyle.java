package de.alaoli.games.minecraft.mods.lib.common.ui.element.style;

import de.alaoli.games.minecraft.mods.lib.common.ui.util.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StateableStyle<T extends Style> implements Style
{
    /******************************************************************************************
     * Attribute
     ******************************************************************************************/

    private State fallback = State.NONE;
    private Map<State, T> states = new HashMap<>();

    /******************************************************************************************
     * Method
     ******************************************************************************************/

    public Optional<State> getFallback()
    {
        return Optional.ofNullable( this.fallback );
    }

    public StateableStyle<T> setFallback( State state )
    {
        this.fallback = state;

        return this;
    }

    public StateableStyle<T> add( State state, T style )
    {
        this.states.put( state, style );

        return this;
    }

    public StateableStyle<T> remove( State state )
    {
        this.states.remove( state );

        return this;
    }

    public boolean exists( State state )
    {
        return this.states.containsKey( state );
    }

    public Optional<T> get(State state )
    {
        if( this.states.containsKey( state ) )
        {
            return Optional.ofNullable( this.states.get( state ) );
        }
        else
        {
            return Optional.ofNullable( this.states.get( this.fallback ) );
        }

    }
}
