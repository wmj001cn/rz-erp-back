/*
 * Decompiled with CFR 0_123.
 
package utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioUtils {
    public static void main(String[] args) {
        String home = System.getProperty("user.dir");
        AudioUtils.playAudio(home + "/sound/alert_duplicate.wav");
    }

    
     * WARNING - Removed try catching itself - possible behaviour change.
     
    public static void playAudio(String audioFilePath) {
        String home = System.getProperty("user.dir");
        String fullPath = home + audioFilePath;
        DataLine soundLine = null;
        int BUFFER_SIZE = 65536;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(fullPath));
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(fileInputStream));
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            soundLine = (SourceDataLine)AudioSystem.getLine(info);
            soundLine.open(audioFormat);
            soundLine.start();
            int nBytesRead = 0;
            byte[] sampledData = new byte[BUFFER_SIZE];
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
                if (nBytesRead < 0) continue;
                soundLine.write(sampledData, 0, nBytesRead);
            }
        }
        catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
        finally {
            soundLine.drain();
            soundLine.close();
        }
    }
}

*/