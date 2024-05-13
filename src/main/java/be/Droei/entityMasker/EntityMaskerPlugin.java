package be.droei.entityMasker;

import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.NPCComposition;
import net.runelite.client.game.npcoverlay.HighlightedNpc;
import net.runelite.client.game.npcoverlay.NpcOverlayService;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.awt.*;
import java.util.HashMap;
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

    @Inject
    private NpcOverlayService npcOverlayService;
    @Inject
    private Client client;
    //Mountain troll
    // targets.contains(name)
    @Override
    protected void startUp()
    {
        log.info("Entity Masker started!");

        for(NPC npc : client.getNpcs()){
            System.out.println(npc.getName());

            highlightedNpcs.put(npc, HighlightedNpc
                .builder()
                .highlightColor(Color.white)
                .npc(npc)
                            .hull(true)
                            .fillColor(Color.pink)

                .name(true)

//                .render(this::render)
                .build());

        }
        npcOverlayService.registerHighlighter(isHighlighted);

    }

    private boolean render(NPC n)
    {
        final NPCComposition c = n.getTransformedComposition();
        if (c != null && c.isFollower())
        {
            return false;
        }

        return true;
    }

    @Override
    protected void shutDown()
    {
        log.info("Entity Masker stopped!");
        npcOverlayService.unregisterHighlighter(isHighlighted);

    }
}
