package ec.edu.epn.AudioProjectMaven;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String OUT_PATH = System.getProperty("user.dir") + "\\target\\classes\\ec\\edu\\epn\\AudioProjectMaven";
        String SRC_PATH = "src\\main\\java\\ec.edu.epn.AudioProjectMaven";
        ProcessBuilder pb = new ProcessBuilder("cmd","/c start cmd.exe /K","cd",SRC_PATH,"&&","javac","-d",OUT_PATH,"@sources.txt","&&","java","-cp",OUT_PATH,"Cli.java");
        pb.start();

    }
}

