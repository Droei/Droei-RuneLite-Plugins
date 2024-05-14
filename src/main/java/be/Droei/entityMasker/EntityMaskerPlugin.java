package be.droei.entityMasker;

import javax.inject.Inject;

import be.droei.entityMasker.config.EntityMaskerConfig;
import be.droei.entityMasker.overlay.EntityMaskerPluginOverlay;
import com.google.inject.ProvidedBy;
import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.client.callback.Hooks;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.npcoverlay.HighlightedNpc;
import net.runelite.client.game.npcoverlay.NpcOverlayService;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@PluginDescriptor(
        name = "Entity Masker",
        description = "A plugin made to mask out entities that can potentially disturb players due to mental or physical problems",
        tags = {"entity", "medical", "phobia", "epilepsy"}

)
public class EntityMaskerPlugin extends Plugin
{
    @Getter
    private final List<NPC> entities = new ArrayList<>();

    //Mountain troll
    // targets.contains(name)
    @Inject
    private OverlayManager overlayManager;
    @Inject
    EntityMaskerPluginOverlay entityMaskerPluginOverlay;
    @Override
    protected void startUp()
    {
        overlayManager.add(entityMaskerPluginOverlay);

        log.info("Entity Masker started!");
    }

    @Override
    protected void shutDown()
    {
        log.info("Entity Masker stopped!");
//        npcOverlayService.unregisterHighlighter(isHighlighted);

    }



    @Provides
    EntityMaskerConfig entityMaskerConfig(ConfigManager configManager){
        return configManager.getConfig(EntityMaskerConfig.class);
    }
}