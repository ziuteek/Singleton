package edu.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class AppConfig {

    private AppConfig(){
        try {
         Thread.sleep(30);   
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }


    private static AppConfig instance;

    public static AppConfig getInstance(){
        if(instance == null){
            synchronized (AppConfig.class){
                if(instance == null){
                instance = new AppConfig();
                }
            }
        }
        return instance;
    }

    private Map<String, Object> config = new HashMap<>();

    public void set(String name, Object value){
        config.put(name, value);
    }
    public Object get(String name){
        return config.get(name);
    }

    public <T> T get(String name, Class<T> type){
        return type.cast(config.get(name));
    }

    public void load(String filename){
        try {
            var fileReader = new FileReader(filename);
            Yaml yaml = new Yaml();
            config = yaml.load(fileReader);
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        
    }

    public void save(String filename){
        try {
            var fileWriter = new FileWriter(filename);
            Yaml yaml = new Yaml();
            yaml.dump(config, fileWriter);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}

