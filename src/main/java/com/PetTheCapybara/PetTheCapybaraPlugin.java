package net.runelite.client.plugins.PetTheCapybara.src.main.java.com.PetTheCapybara;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import javax.inject.Inject;
import java.util.Random;

@Slf4j
@PluginDescriptor(
	name = "Pet The Capybara"
)
public class PetTheCapybaraPlugin extends Plugin
{
	@Inject
	private Client client;
	private static final String PET = "Pet";

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event)
	{
		Player local = client.getLocalPlayer();
		MenuEntry target = event.getMenuEntry();

		int c = event.getIdentifier();
		if(c == 1057 || c == 1060 || c == 24786 || c == 24784 || c == 24776 || c == 24773 ||
				c == 24770 || c == 24769 || c == 24783)
		{
			if (checkDistance(local, target)) {
				client.createMenuEntry(0)
						.setOption(PET)
						.setTarget(target.getTarget())
						.setIdentifier(target.getIdentifier())
						.onClick(this::PetTheCapy);
			} else {
				local.setOverheadText("Omg a capy I want to get closer to pet it!!!!! ");
				local.setOverheadCycle(75);
			}
		}
	}

	private void PetTheCapy(MenuEntry entry){
		Player local = client.getLocalPlayer();

		local.setOverheadText(rollRandomLine());
		local.setOverheadCycle(75);
		local.setAnimation(827);
		local.setActionFrame(0);
	}

	private String rollRandomLine(){
		CapyLines capyLines = new CapyLines();
		Random random = new Random();

		final String[] capyList = capyLines.GetCapyLines();

		return capyList[random.nextInt(capyList.length)];
	}

	private Boolean checkDistance(Player local, MenuEntry target){
		int capyX = target.getNpc().getLocalLocation().getX();
		int capyY = target.getNpc().getLocalLocation().getY();

		int localX = local.getLocalLocation().getX();
		int localY = local.getLocalLocation().getY();

        return Math.abs(capyX - localX) <= 200 && Math.abs(capyY - localY) <= 200;
	}
}
