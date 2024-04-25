package net.runelite.client.plugins.PetTheCapybara.src.main.java.com.PetTheCapybara;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.MenuOpened;
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

	private final ImmutableList<String> set = ImmutableList.of(
			"Trade with", "Attack", "Talk-to", "Examine"
	);

	@Subscribe
	public void onMenuOpened(MenuOpened event){
		MenuEntry[] menuEntries = event.getMenuEntries();

		for (int i = 0; i < set.size(); i++)
		{
			boolean addMenuEntry = false;
			MenuEntry target = null;

			for (MenuEntry menuEntry : menuEntries)
			{
				if (menuEntry.getOption().toLowerCase().equals("drop")
						|| menuEntry.getOption().toLowerCase().equals("destroy"))
				{
					return;
				}
				else if (menuEntry.getOption().equals(set.get(i)))
				{
					addMenuEntry = true;
					target = menuEntry;
				}

				if(addMenuEntry){
					String entityName = target.getTarget();
					Player local = client.getLocalPlayer();


					int c = target.getIdentifier();
					if(c == 1057 || c == 1060 || c == 24786 || c == 24784 || c == 24776 || c == 24773 ||
							c == 24770 || c == 24769 || c == 24783)
					{

						int capyX = target.getNpc().getLocalLocation().getX();
						int capyY = target.getNpc().getLocalLocation().getY();

						int localX = local.getLocalLocation().getX();
						int localY = local.getLocalLocation().getY();

						int distanceX = Math.abs(capyX - localX);
						int distanceY = Math.abs(capyY - localY);

						if (distanceX <= 200 && distanceY <= 200) {
							client.createMenuEntry(0)
									.setOption(PET)
									.setTarget(entityName)
									.setIdentifier(target.getIdentifier())
									.onClick(this::PetTheCapy);
							target.setForceLeftClick(true);
						} else {
							local.setOverheadText("Omg a capy I need to get closer to pet it!!!!! ");
							local.setOverheadCycle(75);
						}
					}
					break;
				}
			}
		}
	}

	private void PetTheCapy(MenuEntry entry){
		Player local = client.getLocalPlayer();

		local.setOverheadText(rollRandomLine());
		local.setOverheadCycle(50);
		local.setAnimation(827);
		local.setActionFrame(0);
	}

	private String rollRandomLine(){

		CapyLines capyLines = new CapyLines();

		final String[] capyList = capyLines.GetCapyLines();
		Random random = new Random();
		int index = random.nextInt(capyList.length);

		return capyList[index];
	}
}
