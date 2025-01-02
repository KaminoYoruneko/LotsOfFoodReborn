package com.KaminnoYoruneko.lots_of_food_reborn.notImportant;

import javax.json.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

//废弃
public class addCutOutToJson {
    public static void main(String[] args) {
        // 指定要遍历的目录路径
        String dirPath = "path/to/your/json/files";

        try (Stream<Path> paths = Files.walk(Paths.get(dirPath))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(path -> {
                        try (FileReader fileReader = new FileReader(path.toFile());
                             JsonReader jsonReader = Json.createReader(fileReader);
                             FileWriter fileWriter = new FileWriter(path.toFile(), false)) {

                            // 读取并解析JSON文件
                            JsonObject jsonObject = jsonReader.readObject();

                            // 在第一级对象中添加"render_type": "cutout"
//                            jsonObject.put("render_type", "cutout");

                            // 创建JsonWriter并设置格式化输出
                            JsonWriter jsonWriter = Json.createWriter(fileWriter);
//                            jsonWriter.setPrettyPrinting(true);

                            // 将修改后的JSON对象写回文件
                            jsonWriter.writeObject(jsonObject);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
