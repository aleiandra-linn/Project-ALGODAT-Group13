// SongLibrary.java
public class SongLibrary {
    private NodeSong head;

    public SongLibrary() { head = null; }

    // menambahkan di akhir agar list terurut seperti input (opsional: menggunakan tail)
    public void addSong(Song s) {
        NodeSong n = new NodeSong(new Song(s.title, s.artist, s.album, s.duration));
        if (head == null) head = n;
        else {
            NodeSong cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = n;
        }
    }

    // expose head untuk util lain (hati-hati read-only asumsi)
    public NodeSong getHead() { return head; }

    public Song getByIndex(int indexOneBased) {
        NodeSong cur = head;
        int i = 1;
        while (cur != null) {
            if (i == indexOneBased) return cur.data;
            cur = cur.next; i++;
        }
        return null;
    }

    public Song findByTitle(String title) {
        NodeSong cur = head;
        while (cur != null) {
            if (cur.data.title.equalsIgnoreCase(title)) return cur.data;
            cur = cur.next;
        }
        return null;
    }

    // tampil table (format sesuai asli)
    public void displayAllTable() {
        System.out.println("=".repeat(120));
        System.out.printf("| %-3s | %-35s | %-20s | %-25s | %-6s |\n", "No", "Judul Lagu", "Artis", "Album", "Durasi");
        System.out.println("=".repeat(120));
        if (head == null) {
            System.out.printf("| %-112s |\n", "(belum ada lagu)");
        } else {
            NodeSong cur = head;
            int i = 1;
            while (cur != null) {
                System.out.printf("| %-3d | %-35s | %-20s | %-25s | %2d:%02d |\n",
                        i, cur.data.title, cur.data.artist, cur.data.album, cur.data.duration / 60, cur.data.duration % 60);
                cur = cur.next; i++;
            }
        }
        System.out.println("=".repeat(120));
    }

    // searching and display
    public void searchAndDisplay(String keyword) {
        NodeSong cur = head;
        boolean found = false;
        System.out.println("=".repeat(120));
        System.out.printf("| %-3s | %-35s | %-20s | %-25s | %-6s |\n", "No", "Judul Lagu", "Artis", "Album", "Durasi");
        System.out.println("=".repeat(120));
        int no = 1;
        while (cur != null) {
            if (cur.data.title.toLowerCase().contains(keyword.toLowerCase()) ||
                cur.data.artist.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("| %-3d | %-35s | %-20s | %-25s | %2d:%02d |\n",
                        no, cur.data.title, cur.data.artist, cur.data.album, cur.data.duration / 60, cur.data.duration % 60);
                found = true; no++;
            }
            cur = cur.next;
        }
        if (!found) {
            System.out.println("| Tidak ada lagu/artis yang sesuai keyword. ".concat(" ".repeat(80)) + "|");
        }
        System.out.println("=".repeat(120));
    }
}
