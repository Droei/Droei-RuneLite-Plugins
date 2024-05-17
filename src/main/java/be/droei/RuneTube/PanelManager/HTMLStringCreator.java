package be.droei.RuneTube.PanelManager;

import be.droei.RuneTube.classes.VideoData;

import java.util.Objects;

public class HTMLStringCreator {
    public static String htmlLabel(String text)
    {
        return "<html><body style = 'color:#cccccc; font-size: 10px;'><span style=\"background-color: rgb(59, 65, 65); text-align: center; font-size: 15px;\">" + text + "</span></body></html>";
    }

    public static String htmlImage(VideoData vid)
    {
        StringBuilder html = new StringBuilder("<html><body><div>");
        String str = vid.title;

        if(str.length() > 55)
            str = str.substring(0,54) + "...";
        if(str.length() < 20)
            str = str + "\r\n";

        html.append(
            "<div style=\"background-color: rgb(59, 65, 65); display: flex; align-items: center; justify-content: center; flex-direction: column;\">\n" +
                    "<h2 style=\"background-color: rgb(59, 65, 65); text-align: center; font-size: 15px;\">")
                .append(str)
                .append("</h2>\n")
                .append("<img src='")
                .append(vid.GetVideoImg())
                .append("' alt='NOOOOOO YOU NEED TO LOAD IMAGE.... HAHA GOOGLE API GO BRRRRRRRRRR' width='218' height='150'>\n")
                .append("<p>Creator: ")
                .append(vid.youtuber)
                .append("</p>\n")
                .append("</div");

        html.append("</div></body></html>");

        return html.toString();
    }

    public static String htmlTitle(){
        return "<html>\n" +
                "<body>\n" +
                "    <div style=\"width: 175px; background-color: rgb(59, 65, 65);\">\n" +
                "<h1 style='font-size: 30px; margin: 0; padding: 0; width: 100%; text-align: center;'>Your latest RuneVids</h1>" +
                "        </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
