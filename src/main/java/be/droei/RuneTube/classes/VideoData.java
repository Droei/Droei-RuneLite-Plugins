package be.droei.RuneTube.classes;

public class VideoData {
    public String id;
    public String title;
    public String youtuber;
    public String lastUploadDate;
    public int tag;
    @Override
    public String toString() {
        return "VideoData{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", youtuber='" + youtuber + '\'' +
                ", lastUploadDate='" + lastUploadDate + '\'' +
                ", type:'" + tag + '\'' +
                '}';
    }

    public int getTag(){
        return tag;
    }

    String GetVideoUrl(){
        return "https://www.youtube.com/watch?v="+id;
    }

    public String GetVideoImg(){
        return "https://i.ytimg.com/vi/"+id+"/default.jpg";
    }
}