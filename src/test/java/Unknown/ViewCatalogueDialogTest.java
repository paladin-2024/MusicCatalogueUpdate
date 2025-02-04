package Unknown;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javax.swing.*;
import java.awt.*;

import static org.testng.Assert.*;

public class ViewCatalogueDialogTest {
    private MusicCatalogue catalogue;
    private ViewCatalogueDialog dialog;

    @BeforeMethod
    public void setUp() {
        catalogue = new MusicCatalogue();
        catalogue.addEntry(new MusicEntry("John Doe", "Studio A", "Pop", true));
        catalogue.addEntry(new MusicEntry("Jane Smith", "Studio B", "Rock", false));

        SwingUtilities.invokeLater(() -> {
            dialog = new ViewCatalogueDialog();
            dialog.setVisible(true);
        });
    }

    @Test
    public void testCatalogueDisplay() {
        SwingUtilities.invokeLater(() -> {
            JTextArea textArea = findTextArea(dialog);
            assertNotNull(textArea, "TextArea should be present in dialog");
            String textContent = textArea.getText();

            assertTrue(textContent.contains("John Doe"), "Text should contain John Doe");
            assertTrue(textContent.contains("Studio A"), "Text should contain Studio A");
            assertTrue(textContent.contains("Rock"), "Text should contain Rock category");
        });
    }

    // Helper method to find JTextArea in the dialog
    private JTextArea findTextArea(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JTextArea) {
                return (JTextArea) component;
            } else if (component instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) component;
                JViewport viewport = scrollPane.getViewport();
                Component view = viewport.getView();
                if (view instanceof JTextArea) {
                    return (JTextArea) view;
                }
            } else if (component instanceof Container) {
                JTextArea result = findTextArea((Container) component);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}
