package boot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;



public class ApplicationConfig {
    private static Properties prop = new Properties();
    static final String filename = "default.properties";

    public static void init() throws IOException {
        prop.load(ApplicationConfig.class.getClassLoader().getResourceAsStream(filename));
        File file = new File(filename);
        
        if(!file.exists()) {
        	file.createNewFile();
        }
        
		prop.load(new FileInputStream(file));
    }

    public static void load(String fileName) throws IOException, FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists() && !file.createNewFile()) {
            throw new RuntimeException("Failed to create file - " + fileName);
        }
        FileInputStream config = new FileInputStream(file);
        //prop.clear();
        prop.load(config);
    }
    
    public static double getAsDouble(String key, String ... defaultValue){
    	return Double.valueOf(get(key, defaultValue));
    }
    
    public static short getAsShort(String key, String ... defaultValue){
    	return Short.valueOf(get(key, defaultValue));
    }
    
    public static int getAsInteger(String key, String ... defaultValue){
    	return Integer.valueOf(get(key, defaultValue));
    }
    
    public static long getAsLong(String key, String ... defaultValue){
    	return Long.valueOf(get(key, defaultValue));
    }
    
    public static boolean getAsBoolean(String key, String ... defaultValue){
    	return Boolean.valueOf(get(key, defaultValue));
    }
    
    public static String get(String key, String ... defaultValue) {
        String value = prop.getProperty(key);
        if (defaultValue.length == 0) {
            return value;
        }
        return value == null || value.trim().isEmpty()? defaultValue[0] : value;
    }

    public static void set(String key, String value) {
        prop.setProperty(key, value);
    }

    public static void setAndSave(String key, String value) throws FileNotFoundException, IOException, URISyntaxException {
        prop.setProperty(key, value);
        File file = new File("default.properties");
        prop.store(new FileOutputStream(file), null);
    }

    public static void setAndSaveAll(Map<String, String> map) throws FileNotFoundException, IOException, URISyntaxException {
        for (String keys : map.keySet()) {
            String value = String.valueOf(map.get(keys));
            prop.setProperty(keys, value);
        }
        String name = map.get("name");
        String fileName = name == null ? "default.properties" : name + ".properties";
        File file = new File(fileName);
        if (file.exists()) {
            prop.store(new FileOutputStream(file), null);
        } else {
            ApplicationConfig.load(fileName);
        }
    }

    public static Properties me(String name) throws FileNotFoundException, IOException {
        String fileName = name == null ? "default.properties" : name + ".properties";
        File file = new File(fileName);
        Properties prop = new Properties();
        if (file.exists()) {
            prop.load(new FileInputStream(file));
        }
        return prop;
    }
}

