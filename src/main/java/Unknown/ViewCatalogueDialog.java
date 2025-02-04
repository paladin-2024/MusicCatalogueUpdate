package Unknown;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewCatalogueDialog {
     JDialog catalogueView;
     MusicCatalogue catalogue;

    public ViewCatalogueDialog(MusicCatalogue catalogue) {
        this.catalogue = catalogue;
        this.prepareCatalogueView();
    }

    private void prepareCatalogueView() {
        catalogueView = new JDialog();
        catalogueView.setTitle("Music Catalogue Entries");
        catalogueView.setSize(500, 400);
        catalogueView.setLocationRelativeTo(null);
        catalogueView.setModal(true);

        ViewEntries();

        catalogueView.pack();
    }

    public void showDialog() {
        catalogueView.setVisible(true);
    }

    private void ViewEntries() {
        List<MusicEntry> entries = catalogue.getEntries();
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        for (MusicEntry entry : entries) {
            textArea.append(entry.toString() + "\n");
        }

        catalogueView.setLayout(new BorderLayout());
        catalogueView.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> catalogueView.dispose());
        catalogueView.add(closeButton, BorderLayout.SOUTH);
    }
}