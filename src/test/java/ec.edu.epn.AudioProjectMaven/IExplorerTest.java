package ec.edu.epn.AudioProjectMaven;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class IExplorerTest {

    @Test
    public void given_the_name_of_a_file_when_deleting_then_ok() {
        IExplorer e = Mockito.mock(IExplorer.class);
        Mockito.when(e.deleteFileByName("cheer.wav")).thenReturn(true);

        assertEquals(true, e.deleteFileByName("cheer.wav"));
    }

}
