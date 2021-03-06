package edu.utdallas.mavs.divas.gui.mvp.view.swing.dialog;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveSimulationDialog
{
    private final static Logger logger          = LoggerFactory.getLogger(SaveSimulationDialog.class);

    private static final String simSnapshotPath = System.getProperty("user.home") + System.getProperty("file.separator") + "Divas" + System.getProperty("file.separator") + "simsnapshot"
                                                        + System.getProperty("file.separator");

    public static File getFile(JFrame parent)
    {
        File file = new File(simSnapshotPath);

        if(!file.exists())
        {
            logger.info("Simsnapshot directory not found. Creating it in user home.");
            file.mkdirs();
        }

        // save environment
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(file);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if(fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();

            if(file.exists())
            {
                if(JOptionPane.showConfirmDialog(parent, "File exists, would you like to overwrite it?", "Warning", JOptionPane.WARNING_MESSAGE) != JFileChooser.APPROVE_OPTION)
                    return null;
            }
        }
        return file;
    }
}
