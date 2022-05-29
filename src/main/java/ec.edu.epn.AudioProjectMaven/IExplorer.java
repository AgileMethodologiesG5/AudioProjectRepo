package ec.edu.epn.AudioProjectMaven;

import java.io.File;

public interface IExplorer {

    public boolean deleteFileByName(String fileName);

    public File setAudioFileByName(String fileName);

}
