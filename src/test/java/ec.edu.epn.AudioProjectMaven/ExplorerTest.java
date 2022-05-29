package ec.edu.epn.AudioProjectMaven;

import org.junit.Test;


import static org.junit.Assert.*;

public class ExplorerTest {

    @Test
    public void given_a_file_name_string_and_two_paths_strings_when_moving_file_then_ok() {
        Explorer e = new Explorer();
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
        Explorer e = new Explorer();
        boolean current = e.deleteFileByName("file.txt");

        assertTrue(current);

    }

    @Test
    public void given_a_path_string_when_changing_directory_then_ok() {
        Explorer e = new Explorer();
        String expected = "C:\\Users\\Confectus\\Downloads\\AudioProjectTDD\\audioLibrary";
        String current = e.changeCurrentPath("C:\\Users\\Confectus\\Downloads\\AudioProjectTDD\\audioOutput");

        assertNotSame(expected, current);
    }

}