package ec.edu.epn.AudioProjectMaven;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Explorer {

    File audioFile = null;                                                  // Targeted audio file of the explorer
    AudioPlayer audioPlayer = new AudioPlayer();                            // Audio player linked with the explorer
    Converter converter = new Converter();                                  // Audio converter linked with the explorer
    Path directory;                                                         // Targeted directory of the explorer
    ArrayList<Exception> exceptionArrayList = new ArrayList<>();

    public Explorer() {
        String stringDirectory = System.getProperty("user.dir");
        Path mainPath = Path.of(stringDirectory);
        while(!mainPath.getFileName().toString().equals("src")){
            mainPath = mainPath.getParent();
        }
        this.directory = mainPath.getParent().resolve("audioLibrary");
    }

    // Method that checks if the given path exists
    private boolean checkPath(Path path) {
        return Files.exists(path);
    }

    // Method that changes the targeted directory
    public String changeCurrentPath(String path) {
        Path oldPath = directory;
        Path newPath = Path.of(path);

        if (checkPath(newPath)) {
            directory = newPath;
        }
        return String.valueOf(oldPath);
    }

    // Method that gets the current files located in the targeted directory
    public String[] getFilesList() {
        // Local variables
        File directoryFile = new File(String.valueOf(directory));
        File[] filesPathsList = directoryFile.listFiles();
        ArrayList<String> fileNames = new ArrayList<>();

        String[] error = {"There are no files in the pointing directory\n" };

        if (filesPathsList != null) {
            // Sorting files
            Arrays.sort(filesPathsList);

            for (File file : filesPathsList) {
                fileNames.add(file.getName());
            }

            return fileNames.toArray(new String[0]);
        }

        return error;
    }

    // Method that sets the pointed audio file by the given name
    public File setAudioFileByName(String fileName) {
        if (checkPath(directory.resolve(fileName))) {
            List<Path> filesList;

            // Searching the file through layers of filters
            try (Stream<Path> walk = Files.walk(directory)) {
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
        boolean compatibility;

        try {
            compatibility = audioPlayer.playAudioFile(audioFile);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e) {
            exceptionArrayList.add(e);
            return false;
        }

        return compatibility;
    }


    // Method that converts the targeted audio file format

    public void convertAudioFile(Format format) throws UnsupportedAudioFileException, IOException {
        converter.convertAudioFile(audioFile, format, this.directory);
    }

    // Method that deletes a file by the given name
    public boolean deleteFileByName(String name) {
        if (!checkPath(directory.resolve(name))) {
            System.out.println("The file \"" + name +
                    "\" does not exists in the directory \"" + directory + "\"");
            return false;
        }

        File fileToDelete = new File(directory + "\\" + name);

        System.out.print("Are you sure to delete the file \"" + name + "\"? (y/n): ");

        // Checking if the deletion was approved
        try {
            if (System.in.read() == 121) {
                return fileToDelete.delete();
            }
        } catch (IOException e) {
            exceptionArrayList.add(e);
        }
        return false;
    }

    // Method that moves a file to another directory
    public boolean moveFile(String fileName, String strSourcePath, String strDestinationPath) {
        // Checking if the given name exists
        Path sourceFilePath = Path.of(strSourcePath + "\\" + fileName);
        Path destinationPath = Path.of(strDestinationPath);

        // Checking it the given paths exists
        if (checkPath(sourceFilePath) && checkPath(destinationPath)) {
            // Moving the file
            Path destinationFilePath = Paths.get(strDestinationPath + "\\" + fileName);

            try {
                Files.move(sourceFilePath, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
                return true;
            } catch (IOException e) {
                exceptionArrayList.add(e);
                return false;
            }
        }
        return false;
    }

    // Method that saves the last current exceptions
    public ArrayList<Exception> getRecentExceptions() {
        if (exceptionArrayList.isEmpty()) {
            exceptionArrayList = null;
        }
        return exceptionArrayList;
    }

    // Method that gets the targeted directory
    public String getDirectory() {
        return String.valueOf(directory);
    }
}



