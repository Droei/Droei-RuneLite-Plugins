package be.Droei.entityMasker;

import javax.inject.Inject;

import be.Droei.entityMasker.config.EntityMaskerConfig;
import be.Droei.entityMasker.overlay.EntityMaskerPluginOverlay;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
        name = "Entity Masker",
        description = "A plugin made to mask out entities that can potentially disturb players",
        tags = {"entity", "medical", "phobia", "epilepsy"}

)
public class EntityMaskerPlugin extends Plugin
{
    @Inject
    OverlayManager overlayManager;

    @Inject
    EntityMaskerPluginOverlay entityMaskerPluginOverlay;

    @Override
    protected void startUp()
    {
        overlayManager.add(entityMaskerPluginOverlay);
    }
    @Override
    protected void shutDown()
    {
        entityMaskerPluginOverlay.clear();
        overlayManager.remove(entityMaskerPluginOverlay);
    }
    @Provides
    EntityMaskerConfig entityMaskerConfig(ConfigManager configManager){
        return configManager.getConfig(EntityMaskerConfig.class);
    }
}
