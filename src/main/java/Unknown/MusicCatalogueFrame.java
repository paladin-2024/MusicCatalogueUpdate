package Unknown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicCatalogueFrame {
    JFrame BorderFrame; // Declare the JFrame variable
    public JTextField artistField;
    public JTextField studioField;
    public JComboBox<String> categoryComboBox;
    public JCheckBox availableCheckBox;
    public JButton submitButton;
    public JButton viewCatalogueButton;
    public JButton exitButton;
    public MusicCatalogue catalogue;

    public MusicCatalogueFrame() {
        this.settingBorderFrame();
    }

    public JFrame settingBorderFrame() {
        this.BorderFrame = new JFrame();
        catalogue = new MusicCatalogue();

        BorderFrame.setTitle("Music Catalogue");
        BorderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderFrame.setSize(500, 350);
        BorderFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("My Music Catalogue", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Agency FB", Font.BOLD, 24));

        BorderFrame.add(titleLabel, BorderLayout.NORTH);
        BorderFrame.add(this.SouthPart(), BorderLayout.SOUTH);
        BorderFrame.add(this.WestPart(), BorderLayout.WEST);
        BorderFrame.add(this.CenterPart(), BorderLayout.CENTER);

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String artist = artistField.getText();
                String studio = studioField.getText();
                String category = (String) categoryComboBox.getSelectedItem();
                boolean available = availableCheckBox.isSelected();

                MusicEntry entry = new MusicEntry(artist, studio, category, available);
                catalogue.addEntry(entry);
                artistField.setText("");
                studioField.setText("");
                availableCheckBox.setSelected(false);
                System.out.println("Entry added: " + entry);
            }
        });

        // Action listener for the view catalogue button
        viewCatalogueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCatalogueDialog dialog = new ViewCatalogueDialog(catalogue);
                dialog.showDialog(); // Call the new method to show the dialog
            }
        });

        // Action listener for the exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return BorderFrame;
    }

    public JPanel SouthPart() {
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
        viewCatalogueButton = new JButton("View Catalogue");
        exitButton = new JButton("Exit");

        buttonPanel.add(submitButton);
        buttonPanel.add(viewCatalogueButton);
        buttonPanel.add(exitButton);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        buttonPanel.add(separator);

        return buttonPanel;
    }

    public JPanel WestPart() {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));

        JButton rapButton = new JButton("Rap");
        JButton reggaeButton = new JButton("Reggae");
        JButton balladsButton = new JButton("Ballads");

        rapButton.addActionListener(e -> categoryComboBox.setSelectedItem("Rap"));
        reggaeButton.addActionListener(e -> categoryComboBox.setSelectedItem("Reggae"));
        balladsButton.addActionListener(e -> categoryComboBox.setSelectedItem("Ballads"));

        categoryPanel.add(rapButton);
        categoryPanel.add(reggaeButton);
        categoryPanel.add(balladsButton);

        return categoryPanel; // Return the categoryPanel
    }

    public JPanel CenterPart() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Artist Name:"));
        artistField = new JTextField();
        inputPanel.add(artistField);

        inputPanel.add(new JLabel("Recording Studio:"));
        studioField = new JTextField();
        inputPanel.add(studioField);

        inputPanel.add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>(new String[]{"Rap", "Reggae", "Ballads"});
        inputPanel.add(categoryComboBox);

        inputPanel.add(new JLabel("Available:"));
        availableCheckBox = new JCheckBox();
        inputPanel.add(availableCheckBox);

        return inputPanel;
    }
}