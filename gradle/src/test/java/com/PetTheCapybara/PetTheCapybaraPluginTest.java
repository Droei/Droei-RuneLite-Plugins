package net.runelite.client.plugins.PetTheCapybara.src.test.java.com.PetTheCapybara;

import net.runelite.client.plugins.PetTheCapybara.src.main.java.com.PetTheCapybara.PetTheCapybaraPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PetTheCapybaraPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PetTheCapybaraPlugin.class);
		RuneLite.main(args);
	}
}