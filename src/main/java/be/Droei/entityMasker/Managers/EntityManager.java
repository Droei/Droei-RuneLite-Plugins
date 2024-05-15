package be.droei.entityMasker.managers;

import be.droei.entityMasker.config.EntityMaskerConfig;
import be.droei.entityMasker.enums.NpcStringEnum;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.client.util.Text;
import net.runelite.client.util.WildcardMatcher;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityManager {
    @Getter
    private final List<NPC> entities = new ArrayList<>();
    EntityMaskerConfig config;
    private Client client;

    @Inject
    public EntityManager(Client client, EntityMaskerConfig config){
        this.client = client;
        this.config = config; //quiiiiiiiiiiiiiiiiiiiii
    }

    public List<NPC> updateConfigEntities(){
        entities.clear();
        String configNpcs = config.getNpcToHighlight();

        if (configNpcs.isEmpty()) return Collections.emptyList();

        if(config.epilepsy()) configNpcs += NpcStringEnum.EPILEPSY.getNpcString();
        if(config.arachnophobia()) configNpcs += NpcStringEnum.ARACHNOPHOBIA.getNpcString();
        if(config.ophidiophobia()) configNpcs += NpcStringEnum.OPHIDIOPHOBIA.getNpcString();
        if(config.cynophobia()) configNpcs += NpcStringEnum.CYNOPHOBIA.getNpcString();

        List<String> output = Text.fromCSV(configNpcs);

        for (NPC npc : client.getNpcs()) {
            for(String name : output){
                if(WildcardMatcher.matches(npc.getName(), name))
                    entities.add(npc);
            }

        }
        return entities;
    }
}
