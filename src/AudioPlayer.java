import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AudioPlayer {

    // Method that checks if an audio file is compatible with the Audio Player
    public boolean checkCompatibility(File audioFile) {
        String fileName = audioFile.getName();
        String fileFormat = fileName.substring(fileName.lastIndexOf(".") + 1);

        boolean formatIsAIFF = fileFormat.equalsIgnoreCase("AIFF");
        boolean formatIsAU = fileFormat.equalsIgnoreCase("AU");
        boolean formatIsWAV = fileFormat.equalsIgnoreCase("WAV");

        // Checking if the audio file format is compatible
        return formatIsAIFF || formatIsAU || formatIsWAV;
    }

    // Method that plays the given audio file
    public void playAudioFile(File audioFile) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
        if (checkCompatibility(audioFile)) {
            // Getting the raw data of the audio file
            AudioInputStream stream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = stream.getFormat();
            int bufferSize = ((int) stream.getFrameLength() * format.getFrameSize());

            DataLine.Info info = new DataLine.Info(Clip.class, format, bufferSize);

            Clip clip = (Clip) AudioSystem.getLine(info);

            double durationInSeconds = (stream.getFrameLength() + 0.0) / format.getFrameRate();

            // Playing the sound of the audio file
            clip.open(stream);
            clip.start();

            // Waiting until the playing has finished
            TimeUnit.SECONDS.sleep((long) durationInSeconds);
        }
    }

}
