public class Playlist {
    public String name;
    public Song head; 
    public Playlist next; 

    public Playlist(String name) {
        this.name = name;
        this.head = null;
        this.next = null;
    }

    public void addSong(Song s) {
        Song copy = new Song(s.title, s.artist, s.album, s.duration);
        if (head == null) head = copy;
        else {
            Song cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = copy;
        }
    }

    public boolean removeByTitle(String title) {
        if (head == null) return false;
        if (head.title.equalsIgnoreCase(title)) { head = head.next; return true; }
        Song cur = head;
        while (cur.next != null) {
            if (cur.next.title.equalsIgnoreCase(title)) {
                cur.next = cur.next.next; return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public int getSize() {
        int c = 0; Song cur = head;
        while (cur != null) { c++; cur = cur.next; }
        return c;
    }

  public void displayTable() {
    int lebar = 120;
    String titleText = "=== PLAYLIST: " + name + " ===";
    int paddingKiri = (lebar - titleText.length()) / 2;
    int paddingKanan = lebar - titleText.length() - paddingKiri;

    for (int i = 0; i < lebar; i++) {
        System.out.print("=");
    }
    System.out.println();

 
    System.out.print("|");
    for (int i = 0; i < paddingKiri; i++) {
        System.out.print(" ");
    }
    System.out.print(titleText);
    for (int i = 0; i < paddingKanan; i++) {
        System.out.print(" ");
    }
    System.out.println("|");

    for (int i = 0; i < lebar; i++) {
        System.out.print("=");
    }
    System.out.println();

    System.out.printf("| %-3s | %-35s | %-20s | %-25s | %-6s |\n", "No", "Judul Lagu", "Artis", "Album", "Durasi");

    for (int i = 0; i < lebar; i++) {
        System.out.print("=");
    }
    System.out.println();

    if (head == null) {
        System.out.printf("| %-112s |\n", "(Playlist kosong)");
    } else {
        Song cur = head;
        int i = 1;
        while (cur != null) {
            System.out.printf("| %-3d | %-35s | %-20s | %-25s | %2d:%02d |\n",
                    i, cur.title, cur.artist, cur.album, cur.duration / 60, cur.duration % 60);
            cur = cur.next;
            i++;
        }
    }

    for (int i = 0; i < lebar; i++) {
        System.out.print("=");
    }
    System.out.println();
}
}