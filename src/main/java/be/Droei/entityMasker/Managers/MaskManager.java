package be.droei.entityMasker.managers;

import be.droei.entityMasker.config.EntityMaskerConfig;
import lombok.AccessLevel;
import lombok.Getter;
import net.runelite.api.NPC;
import net.runelite.client.callback.Hooks;
import net.runelite.client.game.npcoverlay.HighlightedNpc;
import net.runelite.client.game.npcoverlay.NpcOverlayService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MaskManager {

   public Map<NPC, HighlightedNpc> highlightedNpcs = new HashMap<>();
   final Function<NPC, HighlightedNpc> isHighlighted = highlightedNpcs::get;
   final NpcOverlayService npcOverlayService;
   final Hooks hooks;
   final EntityMaskerConfig config;
    @Inject
    public MaskManager(Hooks hooks, NpcOverlayService npcOverlayService, EntityMaskerConfig config){
        this.hooks = hooks;
        this.npcOverlayService = npcOverlayService;
        this.config = config;
    }
    public void maskEntities(List<NPC> npcs){
        highLightNpcs(npcs);
        npcOverlayService.registerHighlighter(isHighlighted);
    }

    public void clearAllMasksList(List<NPC> npcs){
        highLightNpcs(npcs);
        npcOverlayService.unregisterHighlighter(isHighlighted);
    }

    public void highLightNpcs(List<NPC> npcs){
        highlightedNpcs.clear();
        for(NPC npc : npcs){
            highlightedNpcs.put(npc, HighlightedNpc
                    .builder()
                    .highlightColor(config.getBorderAndTextColor())
                    .npc(npc)
                    .hull(true)
                    .fillColor(config.getMaskColor())
                    .name(config.showEntityName())
                    .build());
        }
    }
}
