package be.Droei.PetTheCapybara;

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