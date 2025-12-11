import java.io.BufferedReader;  
import java.io.InputStreamReader;

class Song {
    String title;
    String artist;
    String album;
    int duration;

    Song(String t, String ar, String al, int d) {
        title = t;
        artist = ar;
        album = al;
        duration = d;
    }

    void displayFull() {
        System.out.println(title + " | " + artist + " | " + album + " | " + duration + "s");
    }

    void displayShort() {
        System.out.println(title + " - " + artist);
    }
}

class NodeSong {
    Song data;
    NodeSong next;
    NodeSong(Song s) {
        data = s;
        next = null;
    }
}

class LLSong {
    NodeSong head;

    void add(Song s) {
        NodeSong n = new NodeSong(s);
        if (head == null) {
            head = n;
            return;
        }
        NodeSong c = head;
        while (c.next != null) c = c.next;
        c.next = n;
    }

    void display() {
        if (head == null) {
            System.out.println("(kosong)");
            return;
        }
        NodeSong c = head;
        int i = 1;
        while (c != null) {
            System.out.print(i + ". ");
            c.data.displayFull();
            c = c.next;
            i++;
        }
    }

    Song searchByTitle(String t) {
        NodeSong c = head;
        while (c != null) {
            if (c.data.title.equalsIgnoreCase(t)) return c.data;
            c = c.next;
        }
        return null;
    }
}

class QueueSong {
    NodeSong front;
    NodeSong rear;

    boolean isEmpty() {
        return front == null;
    }

    void enqueue(Song s) {
        NodeSong n = new NodeSong(s);
        if (rear == null) {
            front = rear = n;
            return;
        }
        rear.next = n;
        rear = n;
    }

    Song dequeue() {
        if (front == null) return null;
        Song s = front.data;
        front = front.next;
        if (front == null) rear = null;
        return s;
    }

    void display() {
        if (isEmpty()) {
            System.out.println("(antrian kosong)");
            return;
        }
        NodeSong c = front;
        while (c != null) {
            c.data.displayShort();
            c = c.next;
        }
    }
}

class StackSong {
    NodeSong top;

    void push(Song s) {
        NodeSong n = new NodeSong(s);
        n.next = top;
        top = n;
    }

    void display() {
        if (top == null) {
            System.out.println("(history kosong)");
            return;
        }
        NodeSong c = top;
        while (c != null) {
            c.data.displayShort();
            c = c.next;
        }
    }
}

class Playlist {
    String name;
    NodeSong head;

    Playlist(String n) {
        name = n;
    }

    void addSong(Song s) {
        NodeSong n = new NodeSong(s);
        if (head == null) {
            head = n;
            return;
        }
        NodeSong c = head;
        while (c.next != null) c = c.next;
        c.next = n;
    }

    void display() {
        System.out.println("Playlist: " + name);
        if (head == null) {
            System.out.println("(kosong)");
            return;
        }
        NodeSong c = head;
        int i = 1;
        while (c != null) {
            System.out.print(i + ". ");
            c.data.displayShort();
            c = c.next;
            i++;
        }
    }

    boolean removeAt(int index) {
        if (head == null || index < 1) return false;
        if (index == 1) {
            head = head.next;
            return true;
        }
        NodeSong c = head;
        int i = 1;
        while (c != null && i < index - 1) {
            c = c.next;
            i++;
        }
        if (c == null || c.next == null) return false;
        c.next = c.next.next;
        return true;
    }
}

class CountNode {
    String key;
    int count;
    CountNode next;
    CountNode(String k) {
        key = k;
        count = 1;
    }
}

class CountList {
    CountNode head;

    void addCount(String k) {
        CountNode c = head;
        while (c != null) {
            if (c.key.equals(k)) {
                c.count++;
                return;
            }
            c = c.next;
        }
        CountNode n = new CountNode(k);
        n.next = head;
        head = n;
    }

    void displayTop3() {
        if (head == null) {
            System.out.println("(kosong)");
            return;
        }
        CountNode t1 = null, t2 = null, t3 = null;
        CountNode c = head;
        while (c != null) {
            if (t1 == null || c.count > t1.count) {
                t3 = t2;
                t2 = t1;
                t1 = c;
            } else if (t2 == null || c.count > t2.count) {
                t3 = t2;
                t2 = c;
            } else if (t3 == null || c.count > t3.count) {
                t3 = c;
            }
            c = c.next;
        }
        if (t1 != null) System.out.println("1. " + t1.key + " (" + t1.count + ")");
        if (t2 != null) System.out.println("2. " + t2.key + " (" + t2.count + ")");
        if (t3 != null) System.out.println("3. " + t3.key + " (" + t3.count + ")");
    }
}

public class Main {

    static LLSong library = new LLSong();
    static QueueSong queue = new QueueSong();
    static StackSong history = new StackSong();
    static Playlist playlist = new Playlist("My Playlist");
    static Song currentSong = null;

    static CountList artistCount = new CountList();
    static CountList songCount = new CountList();

    public static void main(String[] args) {
        seedLibrary();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                showMenu();
                String ch = br.readLine();
                if (ch == null) ch = "";

                switch (ch.trim()) {

                    case "1":
                        sortBeforeDisplay();   // >>> TAMBAHAN PENTING
                        library.display();
                        break;

                    case "2":
                        if (currentSong == null) {
                            Song n = queue.dequeue();
                            if (n == null) {
                                System.out.println("Tidak ada lagu sedang diputar.");
                            } else {
                                playSong(n);
                            }
                        } else {
                            System.out.println("Lagu sedang diputar:");
                            currentSong.displayFull();
                        }
                        break;

                    case "3":
                        System.out.print("Judul: ");
                        String j = br.readLine().trim();
                        Song f = library.searchByTitle(j);
                        if (f == null) {
                            System.out.println("Tidak ditemukan.");
                        } else {
                            if (currentSong == null) {
                                playSong(f);
                            } else {
                                queue.enqueue(f);
                                System.out.println("Lagu sedang diputar, lagu baru masuk antrian.");
                            }
                        }
                        break;

                    case "4":
                        Song nx = queue.dequeue();
                        if (nx == null) System.out.println("Antrian kosong.");
                        else playSong(nx);
                        break;

                    case "5":
                        queue.display();
                        break;

                    case "6":
                        history.display();
                        break;

                    case "7":
                        playlist.display();
                        break;

                    case "8":
                        System.out.print("Judul: ");
                        Song ps = library.searchByTitle(br.readLine().trim());
                        if (ps != null) {
                            playlist.addSong(ps);
                            System.out.println("Ditambahkan ke playlist.");
                        }
                        break;

                    case "9":
                        playlist.display();
                        System.out.print("Hapus nomor: ");
                        int idx = Integer.parseInt(br.readLine());
                        if (playlist.removeAt(idx)) System.out.println("Dihapus.");
                        else System.out.println("Gagal menghapus.");
                        break;

                    case "10":
                        if (songCount.head == null) {
                            System.out.println("Belum pernah memutar lagu.");
                        } else {
                            songCount.displayTop3();
                        }
                        break;

                    case "11":
                        if (artistCount.head == null) {
                            System.out.println("Belum ada top artist, karena belum pernah memutar musik.");
                        } else {
                            artistCount.displayTop3();
                        }
                        break;

                    case "12":
                        sortMenu();
                        break;

                    case "0":
                        System.out.println("Keluar.");
                        return;

                    default:
                        System.out.println("Pilihan salah.");
                }

            } catch (Exception e) {
                System.out.println("Error.");
            }

            System.out.println("Enter untuk lanjut...");
            try { br.readLine(); } catch (Exception e) {}
        }
    }

    // =====================================================
    //     >>> FITUR BARU: SORTING SEBELUM DISPLAY <<<
    // =====================================================
    static void sortBeforeDisplay() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Tampilkan semua lagu (urutkan A-Z berdasarkan):");
        System.out.println("1. Judul");
        System.out.println("2. Artis");
        System.out.println("3. Album");
        System.out.print("Pilih: ");

        String choose = br.readLine().trim();

        switch (choose) {
            case "1": sortByTitle();  break;
            case "2": sortByArtist(); break;
            case "3": sortByAlbum();  break;
            default:
                System.out.println("Pilihan salah. Ditampilkan tanpa sorting.");
        }
    }

    // =====================================================
    //           FITUR SORTING (ASLI DARI KODEMU)
    // =====================================================

    static void sortMenu() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Urutkan berdasarkan:");
        System.out.println("1. Judul");
        System.out.println("2. Artis");
        System.out.println("3. Album");
        System.out.print("Pilih: ");

        String ch = br.readLine().trim();

        switch (ch) {
            case "1": sortByTitle();  System.out.println("Diurutkan berdasarkan Judul."); break;
            case "2": sortByArtist(); System.out.println("Diurutkan berdasarkan Artis."); break;
            case "3": sortByAlbum();  System.out.println("Diurutkan berdasarkan Album."); break;
            default: System.out.println("Pilihan salah.");
        }
    }

    static void sortByTitle() {
        bubbleSort("title");
    }

    static void sortByArtist() {
        bubbleSort("artist");
    }

    static void sortByAlbum() {
        bubbleSort("album");
    }

    static void bubbleSort(String key) {
        if (library.head == null) return;

        boolean swapped;
        do {
            swapped = false;
            NodeSong c = library.head;
            while (c.next != null) {

                boolean needSwap = false;

                if (key.equals("title"))
                    needSwap = c.data.title.compareToIgnoreCase(c.next.data.title) > 0;
                else if (key.equals("artist"))
                    needSwap = c.data.artist.compareToIgnoreCase(c.next.data.artist) > 0;
                else if (key.equals("album"))
                    needSwap = c.data.album.compareToIgnoreCase(c.next.data.album) > 0;

                if (needSwap) {
                    Song temp = c.data;
                    c.data = c.next.data;
                    c.next.data = temp;
                    swapped = true;
                }
                c = c.next;
            }
        } while (swapped);
    }

    // =====================================================
    //           FUNGSI PEMUTARAN & MENU AWAL
    // =====================================================

    static void playSong(Song s) {
        currentSong = s;
        history.push(s);
        artistCount.addCount(s.artist);
        songCount.addCount(s.title);
        System.out.println("Memutar:");
        s.displayFull();
    }

    static void showMenu() {
        System.out.println("\n");
    System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
    System.out.println("            ██████╗ ██╗  ██║ ██╗   ██╗ ████████╗██╗  ██╗███╗   ███╗      ███████╗██╗      ██████╗ ██╗    ██╗ ");
    System.out.println("            ██╔══██ ██║  ██║ ╚██╗ ██╔╝ ╚══██╔══╝██║  ██║████╗ ████║      ██╔════╝██║     ██╔═══██╗██║    ██║ ");
    System.out.println("            ██████╔╝███████║  ╚████╔╝     ██║   ███████║██╔████╔██║      ███████╗██║     ██║   ██║██║ █╗ ██║ ");
    System.out.println("            ██╔══██╗██╔══██║   ╚██╔╝      ██║   ██╔══██║██║╚██╔╝██║      ██╔════╝██║     ██║   ██║██║███╗██║ ");
    System.out.println("            ██║  ██║██║  ██║    ██║       ██║   ██║  ██║██║ ╚═╝ ██║      ██║     ███████╗╚██████╔╝╚███╔███╔╝ ");
    System.out.println("            ╚═╝  ╚═ ╚═╝  ╚═╝    ╚═╝       ╚═╝   ╚═╝  ╚═╝╚═╝     ╚═╝      ╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝  ");
    System.out.println("                               ALIN     ARKAN     FATHAN     MAIL     WARITSA     KAMRUN     YUNDA");
    System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
    System.out.println("                                                 RHYTHM FLOW  MUSIC PLAYER SYSTEM");
    System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n");
        System.out.println("1. Semua lagu");
        System.out.println("2. Lihat lagu yang sedang diputar");
        System.out.println("3. Putar lagu baru");
        System.out.println("4. Lanjut ke lagu selanjutnya");
        System.out.println("5. Lihat antrian");
        System.out.println("6. History");
        System.out.println("7. Lihat playlist");
        System.out.println("8. Tambah lagu ke playlist");
        System.out.println("9. Hapus lagu dari playlist");
        System.out.println("10. Top 3 Lagu");
        System.out.println("11. Top 3 Artist");
        System.out.println("12. Urutkan lagu");
        System.out.println("0. Keluar");
        System.out.print("Pilih: ");
    }

    // =====================================================
    //                SEED DATA
    // =====================================================

    static void seedLibrary() {
        library.add(new Song("Weird", "Bryant Barnes", "Lost Tides", 210));
        library.add(new Song("Homesick", "Bryant Barnes", "Quiet Rooms", 184));
        library.add(new Song("Ghost Town", "Bryant Barnes", "Days Gone", 199));
        library.add(new Song("Motion Picture Soundtrack", "Radiohead", "Kid A", 180));
        library.add(new Song("Nude", "Radiohead", "In Rainbows", 260));
        library.add(new Song("Daydreaming", "Radiohead", "A Moon Shaped Pool", 240));
        library.add(new Song("Ivy", "Frank Ocean", "Blonde", 250));
        library.add(new Song("Pink + White", "Frank Ocean", "Blonde", 210));
        library.add(new Song("Nikes", "Frank Ocean", "Blonde", 302));
        library.add(new Song("Anchor", "Novo Amor", "Bathing Beach", 197));
        library.add(new Song("Repeat Until Death", "Novo Amor", "Heiress", 216));
        library.add(new Song("Terraform", "Novo Amor", "Cannot Be", 195));
        library.add(new Song("Youth", "Daughter", "If You Leave", 281));
        library.add(new Song("Medicine", "Daughter", "Single", 230));
        library.add(new Song("Amsterdam", "Daughter", "If You Leave", 250));
        library.add(new Song("Change", "Deftones", "White Pony", 285));
        library.add(new Song("Sextape", "Deftones", "Diamond Eyes", 215));
        library.add(new Song("Digital Bath", "Deftones", "White Pony", 240));
        library.add(new Song("Holocene", "Bon Iver", "Bon Iver", 300));
        library.add(new Song("Skinny Love", "Bon Iver", "For Emma", 240));
        library.add(new Song("Perth", "Bon Iver", "Bon Iver", 265));
        library.add(new Song("Devil Town", "Cavetown", "Single", 192));
        library.add(new Song("Lemon Boy", "Cavetown", "Lemon Boy", 245));
        library.add(new Song("Juliet", "Cavetown", "Cavetown", 230));
        library.add(new Song("Stick Season", "Noah Kahan", "Stick Season", 238));
        library.add(new Song("Everywhere, Everything", "Noah Kahan", "Stick Season", 210));
        library.add(new Song("Northern Attitude", "Noah Kahan", "Stick Season", 244));
        library.add(new Song("See You Again", "Tyler, The Creator", "Flower Boy", 180));
        library.add(new Song("EARFQUAKE", "Tyler, The Creator", "IGOR", 180));
        library.add(new Song("GONE, GONE", "Tyler, The Creator", "IGOR", 200));
        library.add(new Song("HUMBLE.", "Kendrick Lamar", "DAMN.", 177));
        library.add(new Song("LOVE.", "Kendrick Lamar", "DAMN.", 214));
        library.add(new Song("Money Trees", "Kendrick Lamar", "GKMC", 350));
        library.add(new Song("Lose Yourself", "Eminem", "8 Mile", 326));
        library.add(new Song("Mockingbird", "Eminem", "Encore", 251));
        library.add(new Song("Stan", "Eminem", "The Marshall Mathers LP", 404));
        library.add(new Song("Videotape", "Radiohead", "In Rainbows", 260));
        library.add(new Song("Holocene - Live", "Bon Iver", "Live", 310));
        library.add(new Song("Light", "Bryant Barnes", "Quiet Rooms", 190));
        library.add(new Song("Sad Happy", "Cavetown", "Sleepyhead", 200));
        library.add(new Song("Chaos", "Deftones", "B-Sides", 201));
        library.add(new Song("Sunlight", "Novo Amor", "Repeat", 215));
        library.add(new Song("Forest Fire", "Daughter", "Music From", 250));
        library.add(new Song("Blonde Sunrise", "Frank Ocean", "Unreleased", 220));
        library.add(new Song("Snowhouse", "Noah Kahan", "Single", 240));
        library.add(new Song("Boredom", "Tyler, The Creator", "Flower Boy", 217));
    }
}

