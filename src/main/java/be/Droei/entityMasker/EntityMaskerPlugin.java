package be.droei.entityMasker;

import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.Point;
import net.runelite.client.callback.Hooks;
import net.runelite.client.game.npcoverlay.HighlightedNpc;
import net.runelite.client.game.npcoverlay.NpcOverlayService;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
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

    @Getter(AccessLevel.PACKAGE)
    private final Map<NPC, HighlightedNpc> highlightedNpcs = new HashMap<>();
    private final Function<NPC, HighlightedNpc> isHighlighted = highlightedNpcs::get;

    private final Hooks.RenderableDrawListener drawListener = this::shouldDraw;

    @Getter(AccessLevel.PACKAGE)
    private final List<NPC> entities = new ArrayList<>();

    @Inject
    private Hooks hooks;
    @Inject
    private NpcOverlayService npcOverlayService;
    @Inject
    private Client client;
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

        for(NPC npc : client.getNpcs()){
            System.out.println(npc.getName());
            entities.add(npc);
            highlightedNpcs.put(npc, HighlightedNpc
                .builder()
                .highlightColor(Color.white)
                .npc(npc)
                .hull(true)
//                    .fillColor(new Color(232, 149, 218, 100))
                    .fillColor(Color.pink)
                .name(true)
//                .render(this::render)
                .build());

        }

        npcOverlayService.registerHighlighter(isHighlighted);
//        hooks.registerRenderableDrawListener(drawListener);

    }

    @Override
    protected void shutDown()
    {
        log.info("Entity Masker stopped!");
        npcOverlayService.unregisterHighlighter(isHighlighted);

    }

    boolean shouldDraw(Renderable renderable, boolean drawingUI)
    {
        if(renderable instanceof NPC){
            return false;
        }
        return true;
    }
}
