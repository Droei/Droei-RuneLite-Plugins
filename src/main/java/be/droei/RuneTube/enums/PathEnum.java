package be.droei.RuneTube.enums;

public enum  PathEnum{
    //    VIDSJSON("src/main/resources/latestVids.json"),
    VIDSJSON("src/main/java/be/droei/runetube/latestVids.json"),
    PANELICON("/youtube.png");

    private final String path;
    PathEnum(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}