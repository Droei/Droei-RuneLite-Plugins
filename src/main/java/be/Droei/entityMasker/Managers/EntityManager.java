package be.droei.entityMasker.Managers;

import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.NPC;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    @Getter
    private final List<NPC> entities = new ArrayList<>();

    private Client client;

    @Inject
    public EntityManager(Client client){
        this.client = client;
    }

    public List<NPC> updateAllEntities() {
        entities.clear();
        for (NPC npc : client.getNpcs()) {
            entities.add(npc);
        }
        return entities;
    }

    public List<NPC> putConfigEntities(){
        return client.getNpcs();
    }
}
