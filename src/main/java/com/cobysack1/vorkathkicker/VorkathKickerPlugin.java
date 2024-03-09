package com.cobysack1.vorkathkicker;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.Player;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.util.Objects;

@Slf4j
@PluginDescriptor(
	name = "Vorkath Kicker"
)
public class VorkathKickerPlugin extends Plugin
{
	@Inject
	private Client client;

	private Boolean pokedVorkath = false;

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked e)
	{
		if (e.getMenuOption().equals("<col=FF0000>Kick") && e.getMenuTarget().endsWith("Vorkath"))
		{
			e.getMenuEntry().setOption("Kick Vorkath");
			pokedVorkath = true;
		}
		else
		{
			pokedVorkath = false;
		}
	}

	public void setKickMessage(MenuEntry[] menuEntries){
		for(MenuEntry entry : menuEntries){
			if(Objects.equals(entry.getOption(), "Poke")){
				entry.setOption("<col=FF0000>Kick");
			}
		}
	}

	@Subscribe
	public void onClientTick(ClientTick e)
	{
		MenuEntry[] menuEntry = client.getMenuEntries();
		setKickMessage(menuEntry);
		if (pokedVorkath)
		{
			Player local = client.getLocalPlayer();
			if (local.getAnimation() == 827)
			{
				local.setAnimation(423);
				local.setAnimationFrame(0);

				pokedVorkath = false;
			}
		}
	}

}
