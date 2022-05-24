import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Converter {

    // Method that checks if a conversion is possible
    public boolean checkConversion(File audioFile, String format) {
        String fileName = audioFile.getName().substring(0, audioFile.getName().lastIndexOf(".") + 1) + format;
        return Files.exists(Path.of(fileName));
    }

    // Method that converts the given audio file format to another
    public void convertAudioFile(File audioFile, String format) throws UnsupportedAudioFileException, IOException {
        String outFileName = audioFile.getName().substring(0, audioFile.getName().lastIndexOf(".") + 1);
        File outFile;
        AudioInputStream inAudioFile = AudioSystem.getAudioInputStream(audioFile);
        inAudioFile.reset();    // Resetting the mark positions to get all content of the audio
        boolean checkOutFile = false;

        // Selecting the correct type conversion
        switch (format) {
            case "AIFF" -> {
                outFileName = outFileName + "aiff";
                outFile = new File(System.getProperty("user.dir") + "\\audioLibrary\\converterOut\\AIFF\\" + outFileName);
                AudioSystem.write(inAudioFile, AudioFileFormat.Type.AIFF, outFile);
                inAudioFile.close();
                checkOutFile = checkConversion(outFile, format);
            }
            case "AU" -> {
                outFileName = outFileName + "au";
                outFile = new File(System.getProperty("user.dir") + "\\audioLibrary\\converterOut\\AU\\" + outFileName);
                AudioSystem.write(inAudioFile, AudioFileFormat.Type.AU, outFile);
                inAudioFile.close();
                checkOutFile = checkConversion(outFile, format);
            }
            case "WAV" -> {
                outFileName = outFileName + "wav";
                outFile = new File(System.getProperty("user.dir") + "\\audioLibrary\\converterOut\\WAV\\" + outFileName);
                AudioSystem.write(inAudioFile, AudioFileFormat.Type.WAVE, outFile);
                inAudioFile.close();
                checkOutFile = checkConversion(outFile, format);
            }
            default -> System.out.println("Invalid format.");
        }

        if (!checkOutFile) {
            System.out.println("File not created");
        }
    }

}
