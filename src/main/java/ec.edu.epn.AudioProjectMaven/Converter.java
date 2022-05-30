package ec.edu.epn.AudioProjectMaven;

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
        int beginIndex = 0;
        int endIndex = audioFile.getName().lastIndexOf(".") + 1;
        String fileName = audioFile.getName().substring(beginIndex, endIndex) + format;
        return Files.exists(Path.of(fileName));
    }

    // Method that converts the given audio file format to another
    public void convertAudioFile(File audioFile, Format format, Path directory) throws UnsupportedAudioFileException, IOException  {
        int beginIndex = 0;
        int endIndex = audioFile.getName().lastIndexOf(".") + 1;
        File outFile;
        String stringFormat = format.name().toLowerCase();
        String outFileName = audioFile.getName().substring(beginIndex, endIndex) + stringFormat;


        AudioInputStream inAudioFile;
        inAudioFile = AudioSystem.getAudioInputStream(audioFile);
        inAudioFile.reset();    // Resetting the mark positions to get all content of the audio

        // Selecting the correct type conversion
        AudioFileFormat.Type type = getFormatType(format);
        String outPath = directory + "\\converterOut\\" + type.toString() + outFileName;
        outFile = new File(outPath);

        // Writing the converted audio file in the given directory
        AudioSystem.write(inAudioFile, type, outFile);
        inAudioFile.close();
        if (checkConversion(outFile, type.toString()))
            System.out.println("The file conversion has been successful");

    }

    // Method that gets the supported audio format by the given string
    private AudioFileFormat.Type getFormatType (Format outFormat) {
        switch (outFormat) {
            case AIFF -> {
                return AudioFileFormat.Type.AIFF;
            }
            case AU -> {
                return AudioFileFormat.Type.AU;
            }
            case WAV -> {
                return AudioFileFormat.Type.WAVE;
            }
        }
        //Return a default format
        return AudioFileFormat.Type.WAVE;
    }

}
