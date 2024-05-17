package be.droei.RuneTube.Api;

import be.droei.RuneTube.classes.VideoData;
import be.droei.RuneTube.enums.ChannelTagEnum;
import be.droei.RuneTube.enums.PathEnum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApiProcessor {

    RuneTubeApi runeTubeApi = new RuneTubeApi();

    public List<VideoData> GetAllVideos(){
        // Read JSON data from the file
        String filePath = PathEnum.VIDSJSON.getPath(); // Adjust the path based on your project structure
        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
            return null;
        }


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        VideoData[] videoDataArray = gson.fromJson(jsonData.toString(), VideoData[].class);

        for (VideoData videoData : videoDataArray) {
            System.out.println(videoData);
        }

        return Arrays.asList(videoDataArray);
    }

    public List<VideoData> FilterByTag( List<VideoData> videoData, ChannelTagEnum channelTagEnum){
        return videoData.stream().filter(e -> e.getTag() == channelTagEnum
                .getNumber()).collect(Collectors.toList());
    }
}
