// SongSorter.java
public class SongSorter {
    private NodeSong head;

    public SongSorter() { head = null; }

    // add copy to sorter list (at tail to preserve input order)
    public void addSong(Song s) {
        NodeSong n = new NodeSong(new Song(s.title, s.artist, s.album, s.duration));
        if (head == null) head = n;
        else {
            NodeSong cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = n;
        }
    }

    public NodeSong getHead() { return head; }

    // swap data of two NodeSong
    private void swapData(NodeSong a, NodeSong b) {
        Song tmp = a.data;
        a.data = b.data;
        b.data = tmp;
    }

    public void sortByTitle() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            NodeSong cur = head;
            while (cur.next != null) {
                if (cur.data.title.compareToIgnoreCase(cur.next.data.title) > 0) {
                    swapData(cur, cur.next); swapped = true;
                }
                cur = cur.next;
            }
        } while (swapped);
    }

    public void sortByArtist() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            NodeSong cur = head;
            while (cur.next != null) {
                int cmp = cur.data.artist.compareToIgnoreCase(cur.next.data.artist);
                if (cmp > 0 || (cmp == 0 && cur.data.title.compareToIgnoreCase(cur.next.data.title) > 0)) {
                    swapData(cur, cur.next); swapped = true;
                }
                cur = cur.next;
            }
        } while (swapped);
    }

    public void sortByAlbum() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            NodeSong cur = head;
            while (cur.next != null) {
                int cmpAlbum = cur.data.album.compareToIgnoreCase(cur.next.data.album);
                if (cmpAlbum > 0 ||
                   (cmpAlbum == 0 && cur.data.artist.compareToIgnoreCase(cur.next.data.artist) > 0) ||
                   (cmpAlbum == 0 && cur.data.artist.equalsIgnoreCase(cur.next.data.artist) &&
                    cur.data.title.compareToIgnoreCase(cur.next.data.title) > 0)) {
                    swapData(cur, cur.next); swapped = true;
                }
                cur = cur.next;
            }
        } while (swapped);
    }

    // display table
    public void display() {
        System.out.println("=".repeat(120));
        System.out.printf("| %-3s | %-35s | %-20s | %-25s | %-6s |\n", "No", "Judul Lagu", "Artis", "Album", "Durasi");
        System.out.println("=".repeat(120));
        if (head == null) {
            System.out.printf("| %-112s |\n", "(belum ada lagu)");
        } else {
            NodeSong cur = head; int i = 1;
            while (cur != null) {
                System.out.printf("| %-3d | %-35s | %-20s | %-25s | %2d:%02d |\n",
                        i, cur.data.title, cur.data.artist, cur.data.album, cur.data.duration / 60, cur.data.duration % 60);
                cur = cur.next; i++;
            }
        }
        System.out.println("=".repeat(120));
    }

    // search
    public void displaySearch(String keyword) {
        if (head == null) { System.out.println("(belum ada lagu)"); return; }
        NodeSong cur = head; boolean found = false; int i = 1;
        System.out.println("=".repeat(120));
        System.out.printf("| %-3s | %-35s | %-20s | %-25s | %-6s |\n", "No", "Judul Lagu", "Artis", "Album", "Durasi");
        System.out.println("=".repeat(120));
        while (cur != null) {
            if (cur.data.title.toLowerCase().contains(keyword.toLowerCase()) ||
                cur.data.artist.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("| %-3d | %-35s | %-20s | %-25s | %2d:%02d |\n",
                        i, cur.data.title, cur.data.artist, cur.data.album, cur.data.duration / 60, cur.data.duration % 60);
                found = true; i++;
            }
            cur = cur.next;
        }
        if (!found) {
            System.out.println("| Tidak ada lagu/artis yang sesuai keyword. ".concat(" ".repeat(80)) + "|");
        }
        System.out.println("=".repeat(120));
    }
}
