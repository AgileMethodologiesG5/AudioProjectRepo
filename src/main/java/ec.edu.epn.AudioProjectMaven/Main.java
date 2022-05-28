package ec.edu.epn.AudioProjectMaven;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        Explorer explorer = new Explorer();

        // Creating an explorer and listing all the files in the targeted directory
        String filesList = explorer.getFilesList();
        System.out.println(filesList);

        // Selecting and playing a specific audio file
        explorer.setAudioFileByName("arc1.wav");
        explorer.playAudioFile();

        // Converting the targeted audio file to other audio formats
        {
            explorer.convertAudioFile(Format.WAV);
            explorer.convertAudioFile(Format.AU);
            explorer.convertAudioFile(Format.AIFF);

            explorer.setAudioFileByName("arc1.aiff");
            explorer.convertAudioFile(Format.WAV);
            explorer.convertAudioFile(Format.AU);
            explorer.convertAudioFile(Format.AIFF);

            explorer.setAudioFileByName("arc1.au");
            explorer.convertAudioFile(Format.WAV);
            explorer.convertAudioFile(Format.AU);
            explorer.convertAudioFile(Format.AIFF);
        }

        //Testing move files between directories
        String initialPath = System.getProperty("user.dir") + "\\audioLibrary\\converterOut\\WAV";
        String finalPath = System.getProperty("user.dir") + "\\audioLibrary\\converterOut";
        explorer.moveFile("arc1.wav", initialPath, finalPath);
    }
}

