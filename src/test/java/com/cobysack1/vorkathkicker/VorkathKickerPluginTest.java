package com.cobysack1.vorkathkicker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class VorkathKickerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VorkathKickerPlugin.class);
		RuneLite.main(args);
	}
}