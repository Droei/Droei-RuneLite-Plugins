package be.droei.RuneTube;

import be.droei.RuneTube.Api.ApiProcessor;
import be.droei.RuneTube.Api.RuneTubeApi;
import be.droei.RuneTube.Config.RuneTubeConfig;
import be.droei.RuneTube.Panel.RuneTubePanel;
import be.droei.RuneTube.classes.VideoData;
import be.droei.RuneTube.enums.PathEnum;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.util.List;

@Slf4j
@PluginDescriptor(
	name = "RuneTube"
)
public class RuneTubePlugin extends Plugin
{
	@Inject
	private ClientToolbar clientToolbar;

	//	private MyAwesomePluginPanel tabTestPanel;
//	private RuneTubePanel runeTubePanel;
	private NavigationButton navButton;
	private RuneTubePanel runeTubePanel;
	private List<VideoData> videoData;

	@Override
	protected void startUp() throws Exception
	{

		runeTubePanel = injector.getInstance(RuneTubePanel.class);
		runeTubePanel.init();
		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), PathEnum.PANELICON.getPath());

		navButton = NavigationButton.builder()
				.tooltip("RuneTube")
				.icon(icon)
				.priority(5)
				.panel(runeTubePanel)
				.build();

		clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown()
	{
		runeTubePanel.deInit();
		clientToolbar.removeNavigation(navButton);
		runeTubePanel = null;
		navButton = null;
	}


	public List<VideoData> getVideoData(){
		return videoData;
	}

	@Provides
	RuneTubeConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RuneTubeConfig.class);
	}
}
