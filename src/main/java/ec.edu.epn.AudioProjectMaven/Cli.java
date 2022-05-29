package ec.edu.epn.AudioProjectMaven;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Cli {
    // CLI Windows' commands to run them in the program's execution
    private static final ProcessBuilder clearCommand = new ProcessBuilder("cmd", "/c", "cls").inheritIO();
    private static final ProcessBuilder exitCommand = new ProcessBuilder("cmd", "/c", "exit").inheritIO();

    // Scanner object to get the user's input
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        // Some default variables to run the program
        Explorer explorer = new Explorer();
        explorer.setAudioFileByName("arc1.wav");
        String commandOption = "";

        // Loop of CLI execution
        while (!commandOption.equals("exit")) {

            //Code lines to read the user's input
            System.out.print("audioPlayer> ");
            commandOption = read();

            //Switch case to use the commands. More information on individual commands is in the help command section
            switch (commandOption) {
                case "list", "ls", "dir" -> {
                    String[] listFiles = explorer.getFilesList();
                    for (String nameFile : listFiles) {
                        System.out.println(nameFile);
                    }
                }

                case "set" -> {
                    Object file = null;
                    while (file == null) {
                        System.out.println("Write the file's name");
                        file = explorer.setAudioFileByName(read());

                        if (file == null) {
                            System.out.println("The file is null");
                        }
                    }
                }

                case "play", "py" -> {
                    boolean itsCompatible = explorer.playAudioFile();
                    if (!itsCompatible) {
                        System.out.println("The audio's format its incompatible");
                    }
                }

                case "delete", "dlt" -> {
                    System.out.print("Insert the name of the file to delete: ");
                    String fileToDelete = read();

                    boolean successfulDelete = explorer.deleteFileByName(fileToDelete);

                    if (successfulDelete) {
                        System.out.println("The file has been deleted successfully.");
                        break;
                    }
                    System.out.println("An error has occurred during the deleting process.");

                }

                case "move", "mv" -> {
                    System.out.print("Insert the name of the file to move: ");
                    String fileToMove = read();

                    System.out.print("Insert the source path: ");
                    String sourcePath = read();

                    System.out.print("Insert the destination path: ");
                    String destinationPath = read();

                    boolean successfulMove = explorer.moveFile(fileToMove, sourcePath, destinationPath);

                    if (successfulMove) {
                        System.out.println("The file has been moved successfully.");
                        break;
                    }
                    System.out.println("An error has occurred during the moving process.");

                }

                case "change-path", "change", "chg" -> {
                    System.out.print("Insert the new path: ");
                    String newPath = read();
                    String oldPath = explorer.changeCurrentPath(newPath);

                    System.out.println("The path has been changed \nof: " + oldPath + "\nto: " + newPath);

                }

                case "convert", "conv", "cv" -> {
                    System.out.println("Insert the format to convert: ");
                    System.out.println("""
                            1. WAV
                            2. AU
                            3. AIFF""".indent(2));
                    try {
                        explorer.convertAudioFile(Format.values()[Integer.parseInt(read())]);
                    } catch (UnsupportedAudioFileException | IOException e) {
                        throw new RuntimeException(e);
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Insert a number");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("ERROR: Insert a valid option");
                    }

                }

                case "clear", "cls" -> clearCommand.start();

                case "exit" -> exitCommand.start();

                case "show-exceptions", "show-exc", "exc" -> {
                    ArrayList<Exception> exceptions = explorer.getRecentExceptions();
                    System.out.println(exceptions);
                }

                case "get-directory", "get-dir", "gd" -> System.out.println("The current directory is:\n" +
                        explorer.getDirectory());

                case "help", "h" -> {
                    System.out.println("\nSUPPORTED COMMANDS:\n");
                    System.out.println("help, h - shows all the commands accepted by the interface.");
                    System.out.println("list, ls - shows the existing files in the targeted directory.");
                    System.out.println("delete, dlt - delete a main-directory's file with its name");
                    System.out.println("set - targets the audio file with the given name.");
                    System.out.println("play, py - plays the targeted audio file.");
                    System.out.println("move, mv - moves the given file from the source directory to the destination one.");
                    System.out.println("change-path, change, chg - changes the targeted path.");
                    System.out.println("convert, conv, cv - converts the targeted audio file to the given format.");
                    System.out.println("clear, cls - clears the command shell's screen.");
                    System.out.println("exit - finish the application.");
                    System.out.println("show-exceptions, show-exc, exc - show the generated exceptions of the execution.");
                    System.out.println("get-directory, get-dir, gd - prints the current main directory.\n");

                }
            }

        }
    }

    //Method which read entries
    public static String read() {
        return scanner.next();
    }

}
