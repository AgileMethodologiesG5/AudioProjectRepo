package ec.edu.epn.AudioProjectMaven;

import org.junit.Before;
import org.junit.Test;


import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;

import static org.junit.Assert.*;

public class ExplorerTest {
    Explorer e;

    @Before
    public void starUp() {
        e = new Explorer();
    }

    @Test
    public void given_a_file_name_string_and_two_paths_strings_when_moving_file_then_ok() {

        boolean expected = true;
        boolean current = e.moveFile(
                "movingFile.txt",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioLibrary",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioOutput"
        );

        assertEquals(expected, current);
    }

    @Test
    public void given_the_name_of_a_file_when_deleting_then_ok() {
        boolean current = e.deleteFileByName("file.txt");

        assertTrue(current);
    }

    @Test
    public void given_a_path_string_when_changing_directory_then_ok() {
        String expected = "C:\\Users\\Confectus\\Downloads\\AudioProjectTDD\\audioLibrary";
        String current = e.changeCurrentPath("C:\\Users\\Confectus\\Downloads\\AudioProjectTDD\\audioOutput");

        assertNotSame(expected, current);
    }

    @Test
    public void given_a_directory_when_returning_the_files_list_then_ok() {
        String[] expected = {"arc1.aiff", "arc1.au", "arc1.wav", "cheer.wav", "converterOut", "nature.wav" };
        String[] current = e.getFilesList();

        assertArrayEquals(expected, current);
    }

    @Test
    public void given_a_file_name_string_when_selecting_file_then_ok() {
        File current = e.setAudioFileByName("sound.wav");

        assertNotNull(current);
    }

    @Test
    public void given_an_audio_file_when_playing_then_not_supported() {
        File currentFile = e.setAudioFileByName("cheer.mp3");
        boolean current = e.playAudioFile();

        assertFalse(current);
    }

    @Test
    public void given_the_file_when_converting_then_ok() {
        File currentFile = e.setAudioFileByName("house_lo.mp3");
        Format current = Format.WAV;
        assertThrows(UnsupportedAudioFileException.class, () -> e.convertAudioFile(current));

    }

    @Test
    public void given_the_execution_of_program_when_finish_there_are_not_exceptions_then_ok() {
        File currentFile = e.setAudioFileByName("house_lo.mp3");
        e.playAudioFile();
        assertNull(e.getRecentExceptions());
    }

}