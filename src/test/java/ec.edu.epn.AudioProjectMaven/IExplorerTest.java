package ec.edu.epn.AudioProjectMaven;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.Assert.*;

public class IExplorerTest {

    @Test
    public void given_the_name_of_a_file_when_deleting_then_ok() {
        IExplorer e = Mockito.mock(IExplorer.class);
        Mockito.when(e.deleteFileByName("cheer.wav")).thenReturn(true);

        assertEquals(true, e.deleteFileByName("cheer.wav"));
    }

    @Test
    public void given_a_file_name_string_when_selecting_file_then_ok() {
        IExplorer e = Mockito.mock(IExplorer.class);
        Mockito.when(e.setAudioFileByName("cheer.wav")).thenReturn(
                new File("C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioLibrary\\cheer.wav")
        );

        assertNotNull(e.setAudioFileByName("cheer.wav"));
    }

}
