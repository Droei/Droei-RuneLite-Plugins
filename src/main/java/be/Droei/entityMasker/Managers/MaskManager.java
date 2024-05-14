package be.droei.entityMasker.Managers;

import lombok.AccessLevel;
import lombok.Getter;
import net.runelite.api.NPC;
import net.runelite.client.callback.Hooks;
import net.runelite.client.game.npcoverlay.HighlightedNpc;
import net.runelite.client.game.npcoverlay.NpcOverlayService;

import javax.inject.Inject;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MaskManager {
    @Getter(AccessLevel.PACKAGE)
    private final Map<NPC, HighlightedNpc> highlightedNpcs = new HashMap<>();
    private final Function<NPC, HighlightedNpc> isHighlighted = highlightedNpcs::get;
    private final NpcOverlayService npcOverlayService;
    Hooks hooks;

    @Inject
    public MaskManager(Hooks hooks, NpcOverlayService npcOverlayService){
        this.hooks = hooks;
        this.npcOverlayService = npcOverlayService;
    }

    public void maskEntities(List<NPC> npcs){
        for(NPC npc : npcs){
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
}
