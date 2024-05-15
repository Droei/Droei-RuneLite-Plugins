package be.droei.entityMasker;

import javax.inject.Inject;

import be.droei.entityMasker.config.EntityMaskerConfig;
import be.droei.entityMasker.managers.EntityManager;
import be.droei.entityMasker.managers.MaskManager;
import be.droei.entityMasker.overlay.EntityMaskerPluginOverlay;
import com.google.inject.ProvidedBy;
import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ClientTick;
import net.runelite.api.worldmap.WorldMap;
import net.runelite.api.worldmap.WorldMapData;
import net.runelite.api.worldmap.WorldMapRenderer;
import net.runelite.client.callback.Hooks;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.npcoverlay.HighlightedNpc;
import net.runelite.client.game.npcoverlay.NpcOverlayService;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayUtil;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;

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
