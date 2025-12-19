// SpotifyApp.java
import java.util.Scanner;

public class SpotifyApp {
    static final int LEBAR = 122;
    static Scanner sc = new Scanner(System.in);

    static SongLibrary library = new SongLibrary();
    static SongQueue queue = new SongQueue();
    static HistoryList history = new HistoryList();
    static ArtistTree artistTree = new ArtistTree();
    static SongTree songTree = new SongTree();

    static PlaylistManager playlistManager = new PlaylistManager();

    static void loadInitialSongs() {
        library.addSong(new Song("Weird", "Bryant Barnes", "Lost Tides", 210));
        library.addSong(new Song("Homesick", "Bryant Barnes", "Quiet Rooms", 184));
        library.addSong(new Song("Ghost Town", "Bryant Barnes", "Days Gone", 199));
        library.addSong(new Song("Motion Picture Soundtrack", "Radiohead", "Kid A", 180));
        library.addSong(new Song("Nude", "Radiohead", "In Rainbows", 260));
        library.addSong(new Song("Daydreaming", "Radiohead", "A Moon Shaped Pool", 240));
        library.addSong(new Song("Ivy", "Frank Ocean", "Blonde", 250));
        library.addSong(new Song("Pink + White", "Frank Ocean", "Blonde", 210));
        library.addSong(new Song("Nikes", "Frank Ocean", "Blonde", 302));
        library.addSong(new Song("Anchor", "Novo Amor", "Bathing Beach", 197));
        library.addSong(new Song("Repeat Until Death", "Novo Amor", "Heiress", 216));
        library.addSong(new Song("Terraform", "Novo Amor", "Cannot Be", 195));
        library.addSong(new Song("Youth", "Daughter", "If You Leave", 281));
        library.addSong(new Song("Medicine", "Daughter", "Single", 230));
        library.addSong(new Song("Amsterdam", "Daughter", "If You Leave", 250));
        library.addSong(new Song("Change", "Deftones", "White Pony", 285));
        library.addSong(new Song("Sextape", "Deftones", "Diamond Eyes", 215));
        library.addSong(new Song("Digital Bath", "Deftones", "White Pony", 240));
        library.addSong(new Song("Holocene", "Bon Iver", "Bon Iver", 300));
        library.addSong(new Song("Skinny Love", "Bon Iver", "For Emma", 240));
        library.addSong(new Song("Perth", "Bon Iver", "Bon Iver", 265));
        library.addSong(new Song("Devil Town", "Cavetown", "Single", 192));
        library.addSong(new Song("Lemon Boy", "Cavetown", "Lemon Boy", 245));
        library.addSong(new Song("Juliet", "Cavetown", "Cavetown", 230));
        library.addSong(new Song("Stick Season", "Noah Kahan", "Stick Season", 238));
        library.addSong(new Song("Everywhere, Everything", "Noah Kahan", "Stick Season", 210));
        library.addSong(new Song("Northern Attitude", "Noah Kahan", "Stick Season", 244));
        library.addSong(new Song("See You Again", "Tyler, The Creator", "Flower Boy", 180));
        library.addSong(new Song("EARFQUAKE", "Tyler, The Creator", "IGOR", 180));
        library.addSong(new Song("GONE, GONE", "Tyler, The Creator", "IGOR", 200));
        library.addSong(new Song("HUMBLE.", "Kendrick Lamar", "DAMN.", 177));
        library.addSong(new Song("LOVE.", "Kendrick Lamar", "DAMN.", 214));
        library.addSong(new Song("Money Trees", "Kendrick Lamar", "GKMC", 350));
        library.addSong(new Song("Lose Yourself", "Eminem", "8 Mile", 326));
        library.addSong(new Song("Mockingbird", "Eminem", "Encore", 251));
        library.addSong(new Song("Stan", "Eminem", "The Marshall Mathers LP", 404));
        library.addSong(new Song("Videotape", "Radiohead", "In Rainbows", 260));
        library.addSong(new Song("Holocene - Live", "Bon Iver", "Live", 310));
        library.addSong(new Song("Light", "Bryant Barnes", "Quiet Rooms", 190));
        library.addSong(new Song("Sad Happy", "Cavetown", "Sleepyhead", 200));
        library.addSong(new Song("Chaos", "Deftones", "B-Sides", 201));
        library.addSong(new Song("Sunlight", "Novo Amor", "Repeat", 215));
        library.addSong(new Song("Forest Fire", "Daughter", "Music From", 250));
        library.addSong(new Song("Blonde Sunrise", "Frank Ocean", "Unreleased", 220));
        library.addSong(new Song("Snowhouse", "Noah Kahan", "Single", 240));
        library.addSong(new Song("Boredom", "Tyler, The Creator", "Flower Boy", 217));
    }

    static void garis() { for (int i=0;i<LEBAR;i++) System.out.print("="); System.out.println(); }

    static void tengah(String teks) {
        String clean = teks.replaceAll("\033\\[[;\\d]*m", "");
        int kiri = (LEBAR - 2 - clean.length()) / 2;
        int kanan = LEBAR - 2 - kiri - clean.length();
        System.out.print("|");
        for (int i=0;i<kiri;i++) System.out.print(" ");
        System.out.print(teks);
        for (int i=0;i<kanan;i++) System.out.print(" ");
        System.out.println("|");
    }

    static void showBanner() {
        System.out.println();
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("            ██████╗ ██╗  ██║ ██╗   ██╗ ████████╗██╗  ██╗███╗   ███╗      ███████╗██╗      ██████╗ ██╗    ██╗ ");
        System.out.println("            ██╔══██╗██║  ██║ ╚██╗ ██╔╝ ╚══██╔══╝██║  ██║████╗ ████║      ██╔════╝██║     ██╔═══██╗██║    ██║ ");
        System.out.println("            ██████╔╝███████║  ╚████╔╝     ██║   ███████║██╔████╔██║      ███████╗██║     ██║   ██║██║ █╗ ██║ ");
        System.out.println("            ██╔══██╗██╔══██║   ╚██╔╝      ██║   ██╔══██║██║╚██╔╝██║      ██╔════╝██║     ██║   ██║██║███╗██║ ");
        System.out.println("            ██║  ██║██║  ██║    ██║       ██║   ██║  ██║██║ ╚═╝ ██║      ██║     ███████╗╚██████╔╝╚███╔███╔╝ ");
        System.out.println("            ╚═╝  ╚═╝╚═╝  ╚═╝    ╚═╝       ╚═╝   ╚═╝  ╚═╝╚═╝     ╚═╝      ╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝  ");
        System.out.println("                               ALIN     ARKAN     FATHAN     MAIL     WARITSA     KAMRUN     YUNDA");
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("                                                 RHYTHM FLOW  MUSIC PLAYER SYSTEM");
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n");
    }

    static void clearScreen() { System.out.print("\033[H\033[2J"); System.out.flush(); for (int i=0;i<5;i++) System.out.println(); }

    static int menuFitur() {
        while (true) {
            clearScreen(); showBanner(); garis(); tengah("=== PILIH FITUR ==="); garis();
            System.out.println("1. Library Lagu");
            System.out.println("2. Urutan Lagu");
            System.out.println("3. Playlist");
            System.out.println("4. Pemutar Musik");
            System.out.println("5. History & Top Lagu");
            System.out.println("6. Logout");
            garis();
            System.out.print("Pilih (1-6): ");
            String input = sc.nextLine();
            if (input.equals("1")) return 0;
            else if (input.equals("2")) return 1;
            else if (input.equals("3")) return 2;
            else if (input.equals("4")) return 3;
            else if (input.equals("5")) return 4;
            else if (input.equals("6")) return 5;
            else { System.out.println("Pilihan tidak valid, tekan Enter untuk coba lagi..."); sc.nextLine(); }
        }
    }

    static void showQueue() {
    clearScreen();
    showBanner();

    System.out.println("==========================================================================================================================");
    System.out.println("|                                                 === ANTRIAN LAGU ===                                                   |");
    System.out.println("==========================================================================================================================");

    if (queue.isEmpty()) {
        System.out.println("(Antrian kosong)");
    } else {
        SongQueue.QNode cur = queue.getFrontRaw();
        int i = 1;
        while (cur != null) {
            Song s = cur.song;
            System.out.printf("%d. %-30s | %-20s | %-20s\n",
                    i, s.title, s.artist, s.album);
            cur = cur.next;
            i++;
        }
    }

    System.out.println("==========================================================================================================================");
    System.out.println("Tekan Enter...");
    sc.nextLine();
}


    static void libraryLagu() {
        SongSorter sorter = new SongSorter();
        NodeSong cur = library.getHead();
        while (cur != null) { sorter.addSong(cur.data); cur = cur.next; }
        sorter.display();
        while (true) {
            System.out.println("\nCari Lagu / Artis (ketik 'exit' untuk kembali): ");
            System.out.print("Masukkan keyword: ");
            String keyword = sc.nextLine();
            if (keyword.equalsIgnoreCase("exit")) break;
            sorter.displaySearch(keyword);
        }
        System.out.println("Tekan Enter untuk kembali ke menu...");
        sc.nextLine();
    }

    static void UrutanLagu() { UrutanLaguMenu.showUrutanLagu(library); }

    static void Playlist() {
        PlaylistMenu menu = new PlaylistMenu(library);
        menu.showMenu();
    }

    static Song nowPlaying = null;

    static Song currentSong = null;

static void PemutarLagu() {
    while (true) {
        clearScreen(); showBanner(); garis();
        tengah("=== PEMUTAR MUSIK ==="); garis();
        library.displayAllTable();

        System.out.println("0. Kembali ke menu utama");
        System.out.print("Pilih lagu untuk diputar (masukkan nomor): ");
        String input = sc.nextLine();

        if (!input.matches("\\d+")) { 
            System.out.println("Input tidak valid! Enter...");
            sc.nextLine(); 
            continue;
        }

        int idx = Integer.parseInt(input);
        if (idx == 0) break;

        Song selected = library.getByIndex(idx);
        if (selected == null) { 
            System.out.println("Pilihan di luar range! Enter...");
            sc.nextLine(); 
            continue; 
        }

        // 🔥 Jika belum ada lagu yang diputar
        if (currentSong == null) {
            currentSong = selected;           // tidak di-dequeue
            System.out.println("Sekarang memutar: " + currentSong.title + " - " + currentSong.artist);
            history.add(currentSong);
            artistTree.insert(currentSong);
            songTree.insert(currentSong);
        } 
        // 🔥 Jika sudah ada lagu berjalan → tambahkan ke queue
        else {
            queue.enqueue(selected);
            System.out.println("'" + selected.title + "' ditambahkan ke antrian.");
        }

        // ===================== MENU PEMUTAR ======================
        while (true) {
            System.out.println("\n=== Menu Pemutar ===");
            System.out.println("Lagu saat ini: " + currentSong.title + " - " + currentSong.artist);
            System.out.println("1. Lihat Antrian Lagu");
            System.out.println("2. Putar Lagu Selanjutnya");
            System.out.println("3. Tambahkan Lagu ke Antrian");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih: ");
            String opsi = sc.nextLine();

            if (opsi.equals("1")) {
                showQueue();
            }
            else if (opsi.equals("2")) {
                if (queue.isEmpty()) {
                    System.out.println("Antrian kosong!");
                } else {
                    currentSong = queue.dequeue();  // 🔥 pindah ke lagu berikutnya
                    System.out.println("Sekarang memutar: " + currentSong.title + " - " + currentSong.artist);
                    
                    history.add(currentSong);
                    artistTree.insert(currentSong);
                    songTree.insert(currentSong);
                }
            }
            else if (opsi.equals("3")) {
                break; // kembali ke daftar lagu → bisa pilih lagu lagi
            }
            else if (opsi.equals("0")) {
                return;
            }
            else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }
}

    static void HistorydanToplagu() {
        history.displayAllNewestFirst();
        songTree.topN(3);
        artistTree.topN(3);
        System.out.println("\nTekan Enter untuk kembali...");
        sc.nextLine();
    }

    public static void main(String[] args) {
        loadInitialSongs();
        System.out.println("Login berhasil! Tekan Enter untuk lanjut...");
        sc.nextLine();
        while (true) {
            int pilihan = menuFitur();
            switch (pilihan) {
                case 0 -> libraryLagu();
                case 1 -> UrutanLagu();
                case 2 -> Playlist();
                case 3 -> PemutarLagu();
                case 4 -> HistorydanToplagu();
                case 5 -> { System.out.println("Logout..."); sc.nextLine(); return; }
            }
        }
    }
}
