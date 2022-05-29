package ec.edu.epn.AudioProjectMaven;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Explorer {

    String directory = System.getProperty("user.dir") + "\\audioLibrary";   // Targeted directory of the explorer
    File audioFile = null;                                                  // Targeted audio file of the explorer
    AudioPlayer audioPlayer = new AudioPlayer();                            // Audio player linked with the explorer
    Converter converter = new Converter();                                  // Audio converter linked with the explorer
    ArrayList<Exception> exceptionArrayList = new ArrayList<>();

    // Method that checks if the given path exists
    private boolean checkPath(String path) {
        return Files.exists(Path.of(path));
    }

    // Method that changes the targeted directory
    public String changeCurrentPath(String path) {
        String oldPath = directory;

        if (checkPath(path)) {
            directory = path;
        }

        return oldPath;
    }

    // Getter method to get the targeted directory
    public String getDirectory() {
        return directory;
    }

    // Method that gets the current files located in the targeted directory
    public String[] getFilesList() {
        /* Local variables */
        File directoryFile = new File(directory);
        File[] filesPathsList = directoryFile.listFiles();
        ArrayList<String> fileNames = new ArrayList<>();

        String[] error = {"There are no files in the pointing directory\n" };

        if (filesPathsList != null) {
            // Sorting files
            Arrays.sort(filesPathsList);

            for (File file : filesPathsList) {
                fileNames.add(file.getName());
            }

            String[] stringArray = fileNames.toArray(new String[0]);
            return stringArray;
        }

        return error;
    }

    // Method that sets the pointed audio file by the given name
    public File setAudioFileByName(String fileName) {
        if (checkPath(directory + "\\" + fileName)) {
            List<Path> filesList;

            // Searching the file through layers of filters
            try (Stream<Path> walk = Files.walk(Path.of(directory))) {
                Stream<Path> firstFilter = walk.filter(Files::isRegularFile);
                Stream<Path> secondFilter = firstFilter.filter(p -> p.getFileName().toString().equalsIgnoreCase(fileName));
                filesList = secondFilter.collect(Collectors.toList());
            } catch (IOException e) {
                exceptionArrayList.add(e);
                return null;
            }

            audioFile = new File(String.valueOf(filesList.get(0)));
        }

        return audioFile;
    }

    // Method that plays the targeted audio file
    public boolean playAudioFile() {
        boolean compatibility = false;

        try {
            compatibility = audioPlayer.playAudioFile(audioFile);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e) {
            exceptionArrayList.add(e);
            return false;
        }

        return compatibility;
    }

    // Method that converts the targeted audio file format
    public void convertAudioFile(Format format) throws IOException, UnsupportedAudioFileException {
        converter.convertAudioFile(audioFile, format);
    }

    // Method that deletes a file by the given name
    public boolean deleteFileByName(String name) {
        if (!checkPath(directory + "\\" + name)) {
            System.out.println("The file \"" + name +
                    "\" does not exists in the directory \"" + directory + "\"");
            return false;
        }

        File fileToDelete = new File(directory + "\\" + name);
        boolean validation = fileToDelete.delete();

        if (validation)
            System.out.println("The file has been deleted.");

        return validation;
    }

    // Method that moves a file to another directory
    public boolean moveFile(String fileName, String sourcePath, String destinationPath) {
        // Checking if the given name exists
        if (checkPath(sourcePath + "\\" + fileName)) {

            // Checking it the given path exists
            if (checkPath(destinationPath)) {
                // Moving the file
                Path sourceFilePath = Paths.get(sourcePath + "\\" + fileName);
                Path destinationFilePath = Paths.get(destinationPath + "\\" + fileName);

                try {
                    Files.move(sourceFilePath, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
                    return true;
                } catch (IOException e) {
                    exceptionArrayList.add(e);
                    return false;
                }
            }

        }
        return false;
    }

    public ArrayList<Exception> getRecentExceptions(){
        if (exceptionArrayList.isEmpty()){
            exceptionArrayList = null;
        }
        return exceptionArrayList;
    }

}
