package be.droei.entityMasker.enums;

public enum NpcStringEnum {
    EPILEPSY(",Wyrm,Killerwatt"),
    ARACHNOPHOBIA(",Spider,Blessed spider,Crypt spider,Deadly red spider,Fever spider,Giant crypt spider," +
            "Giant spider,Ice spider,Jungle spider,Poison spider,Shadow spider,Sarachnis," +
            "Spawn of Sarachnis,Spindel,Temple Spider,Venenatis"),
    OPHIDIOPHOBIA("Snake,Poison Snake,Giant Snake,Zulrah");
    private final String npcString;
    NpcStringEnum(String path){
        this.npcString = path;
    }

    public String getNpcString(){
        return npcString;
    }
}
