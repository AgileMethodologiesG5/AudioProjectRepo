package ec.edu.epn.AudioProjectMaven;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class ExplorerParametersDeletingTest {

    private String fileName;

    @Parameterized.Parameters
    public static Iterable<Object> parameters() {
        List<Object> objects = new ArrayList<Object>();
        objects.add(new Object[]{"file.txt"});
        objects.add(new Object[]{"image.png"});
        objects.add(new Object[]{"cheer.wav"});
        objects.add(new Object[]{"clip.mp3"});
        objects.add(new Object[]{"audio.aiff"});
        objects.add(new Object[]{"listen.au"});
        objects.add(new Object[]{"hd.jpg"});
        return objects;
    }

    public ExplorerParametersDeletingTest(String fileName) {
        this.fileName = fileName;
    }

    @Test
    public void given_parameters_when_changing_directory_then_ok() {
        Explorer e = new Explorer();
        boolean current = e.deleteFileByName(fileName);

        assertTrue(current);
    }

}