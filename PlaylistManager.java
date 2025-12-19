// PlaylistManager.java (tambah 2 lagu default ke Liked Songs)
public class PlaylistManager {
    public Playlist head;
    private int size;

    public PlaylistManager() {
        head = null; size = 0;
        addPlaylistByName("Liked Songs");
        
        // Tambahkan 2 lagu default ke Liked Songs
        Playlist liked = head;
        liked.addSong(new Song("Weird", "Bryant Barnes", "Lost Tides", 210));
        liked.addSong(new Song("Homesick", "Bryant Barnes", "Quiet Rooms", 184));
    }

    public void addPlaylist(Playlist p) {
        if (head == null) head = p;
        else {
            Playlist cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = p;
        }
        size++;
    }

    public void addPlaylistByName(String name) { addPlaylist(new Playlist(name)); }

    public Playlist getPlaylist(int idxZeroBased) {
        Playlist cur = head; int i = 0;
        while (cur != null) {
            if (i == idxZeroBased) return cur;
            cur = cur.next; i++;
        }
        return null;
    }

    public int getSize() { return size; }

    public void displayAllPlaylists() {
    System.out.println("==========================================================================================================================");
    System.out.println("|                                                    === PLAYLIST ===                                                    |");
    System.out.println("==========================================================================================================================");

    Playlist cur = head;
    int idx = 1;

    while (cur != null) {
        System.out.printf("%d. %-40s (%d lagu)\n", idx, cur.name, cur.getSize());
        idx++;
        cur = cur.next;
    }

    System.out.println("--------------------------------------------------------------------------------------------------------------------------");
    System.out.println("0. Kembali");
    System.out.println("==========================================================================================================================");
}

}