package Unknown;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javax.swing.*;

import java.awt.*;

import static org.testng.Assert.*;

public class MusicCatalogueFrameTest {
    private MusicCatalogueFrame frame;

    @BeforeMethod
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
            frame = new MusicCatalogueFrame();
            frame.setVisible(true);
        });
    }

    @Test
    public void testFrameInitialization() {
        assertNotNull(frame, "Frame should be initialized");
        assertEquals(frame.getTitle(), "Music Catalogue", "Frame title should be correct");
    }

    @Test
    public void testAddEntry() {
        SwingUtilities.invokeLater(() -> {
            frame.artistField.setText("John Doe");
            frame.studioField.setText("Studio A");
            frame.categoryComboBox.setSelectedItem("Rap");
            frame.availableCheckBox.setSelected(true);
            frame.submitButton.doClick(); // Simulate button click

            assertEquals(frame.catalogue.getEntries().size(), 1, "One entry should be added");
            assertEquals(frame.catalogue.getEntries().get(0).getArtistName(), "John Doe", "Artist name should match");
        });
    }

    @Test
    public void testViewCatalogueButton() {
        SwingUtilities.invokeLater(() -> {
            frame.catalogue.addEntry(new MusicEntry("Jane Smith", "Studio B", "Reggae", false));
            frame.viewCatalogueButton.doClick(); // Simulate button click

            JDialog[] dialogs = findOpenDialogs();
            assertTrue(dialogs.length > 0, "View Catalogue dialog should be opened");
        });
    }

    // Helper method to find open JDialogs
    private JDialog[] findOpenDialogs() {
        Window[] windows = Window.getWindows();
        return (JDialog[]) java.util.Arrays.stream(windows)
                .filter(w -> w instanceof JDialog)
                .toArray(JDialog[]::new);
    }
}
