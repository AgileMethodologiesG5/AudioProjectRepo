package ec.edu.epn.AudioProjectMaven;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class ExplorerParametersMovingTest {

    private String fileName, sourcePath, destinationPath;
    private boolean expected;

    @Parameterized.Parameters
    public static Iterable<Object> parameters() {
        List<Object> objects = new ArrayList<Object>();
        objects.add(new Object[]{"file.txt",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioLibrary",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioOutput",
                true
        });
        objects.add(new Object[]{
                "image.png",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioLibrary",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioOutput",
                true
        });
        objects.add(new Object[]{"cheer.wav",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioLibrary",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioOutput",
                true
        });
        objects.add(new Object[]{"clip.mp3",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioLibrary",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioOutput",
                true
        });
        objects.add(new Object[]{"audio.aiff",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioLibrary",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioOutput",
                true
        });
        objects.add(new Object[]{"listen.au",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioLibrary",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioOutput",
                true
        });
        objects.add(new Object[]{"hd.jpg",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioLibrary",
                "C:\\Users\\Confectus\\Downloads\\AudioProjectRepo\\audioOutput",
                true
        });
        return objects;
    }

    public ExplorerParametersMovingTest(String fileName, String sourcePath, String destinationPath, boolean expected) {
        this.fileName = fileName;
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
        this.expected = expected;
    }

    @Test
    public void given_parameters_when_moving_file_then_ok() {
        Explorer e = new Explorer();
        boolean current = e.moveFile(fileName, sourcePath, destinationPath);

        assertEquals(expected, current);
    }

}