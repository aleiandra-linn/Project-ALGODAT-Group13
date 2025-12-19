// Song.java
public class Song {
    public String title;
    public String artist;
    public String album;
    public int duration; // detik
    public Song next; // pointer linked list

    public Song(String title, String artist, String album, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.next = null;
    }

    public void displayShort() {
        System.out.printf("%s - %s (%s) [%ds]\n", title, artist, album, duration);
    }

    public String formatMMSS() {
        int m = duration / 60;
        int s = duration % 60;
        return String.format("%02d:%02d", m, s);
    }
}
