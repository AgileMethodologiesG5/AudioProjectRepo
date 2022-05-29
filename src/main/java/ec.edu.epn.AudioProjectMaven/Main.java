package ec.edu.epn.AudioProjectMaven;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String OUT_PATH = System.getProperty("user.dir") + "\\out\\production";
        ProcessBuilder pb = new ProcessBuilder("cmd","/c start cmd.exe /K","cd","src","&&","javac","-d",OUT_PATH,"Cli.java","&&","java","-cp",OUT_PATH,"Cli.java");
        pb.start();

    }
}

