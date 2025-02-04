package Unknown;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MusicEntryTest {

    @Test
    public void testTestToString() {
        MusicEntry entry = new MusicEntry("John Doe", "Studio A", "Pop", true);
        String expected = "Artist: John Doe, Studio: Studio A, Category: Pop, Available: true";
        assertEquals(entry.toString(), expected, "toString() method should return the correct formatted string");
    }

    @Test
    public void testGetArtistName() {
        MusicEntry entry = new MusicEntry("John Doe", "Studio A", "Pop", true);
        assertEquals(entry.getArtistName(), "John Doe", "Artist name should be John Doe");
    }

    @Test
    public void testGetRecordingStudio() {
        MusicEntry entry = new MusicEntry("John Doe", "Studio A", "Pop", true);
        assertEquals(entry.getRecordingStudio(), "Studio A", "Recording studio should be Studio A");
    }

    @Test
    public void testGetCategory() {
        MusicEntry entry = new MusicEntry("John Doe", "Studio A", "Pop", true);
        assertEquals(entry.getCategory(), "Pop", "Category should be Pop");
    }

    @Test
    public void testIsAvailable() {
        MusicEntry entry = new MusicEntry("John Doe", "Studio A", "Pop", true);
        assertTrue(entry.isAvailable(), "Availability should be true");
    }
}