// PlaylistMenu.java (dirombak untuk tampilkan library table di atas playlist table saat options)
import java.util.Scanner;

public class PlaylistMenu {
    private PlaylistManager manager;
    private Scanner sc;
    private SongLibrary library;

    public PlaylistMenu(SongLibrary library) {
        manager = new PlaylistManager();
        sc = new Scanner(System.in);
        this.library = library;
    }

    private void displayPlaylists() {
        System.out.println("==========================================================================================================================");
        System.out.println("|                                                    === PLAYLIST ===                                                    |");
        System.out.println("==========================================================================================================================");

        Playlist cur = manager.head;
        int index = 1;

        if (cur == null) {
            System.out.println("(Belum ada playlist)");
        } else {
            while (cur != null) {
                System.out.printf("%d. %-40s (%d lagu)\n",
                        index,
                        cur.name,
                        cur.getSize());
                cur = cur.next;
                index++;
            }
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("+. Buat Playlist Baru");
        System.out.println("0. Kembali");
        System.out.println("==========================================================================================================================");
    }

    public void showMenu() {
        while (true) {
            clearScreen(); 
            showBanner();
            displayPlaylists();

            System.out.print("Pilih: ");
            String input = sc.nextLine();

            if (input.equals("0")) return;

            if (input.equals("+")) {
                createNewPlaylist();
                continue;
            }

            if (!input.matches("\\d+")) {
                System.out.println("Input harus angka! Tekan Enter...");
                sc.nextLine();
                continue;
            }
            int choice = Integer.parseInt(input);

            if (choice >= 1 && choice <= manager.getSize()) {
                showPlaylistOptions(manager.getPlaylist(choice - 1));
            } else {
                System.out.println("Pilihan salah, tekan Enter...");
                sc.nextLine();
            }
        }
    }

    private void createNewPlaylist() {
    System.out.print("Masukkan nama playlist baru: ");
    String name = sc.nextLine();
    
    // Cek manual apakah nama hanya berisi spasi atau kosong
    boolean isEmpty = true;
    for (int i = 0; i < name.length(); i++) {
        if (name.charAt(i) != ' ') {
            isEmpty = false;
            break;
        }
    }
    
    if (isEmpty) {
        System.out.println("Nama tidak boleh kosong! Tekan Enter...");
        sc.nextLine();
        return;
    }
    
    manager.addPlaylistByName(name);
    System.out.println("Playlist '" + name + "' berhasil dibuat! Tekan Enter...");
    sc.nextLine();
}

    private void showPlaylistOptions(Playlist playlist) {
        while (true) {
            clearScreen(); 
            showBanner();
            
            // Tampilkan daftar semua lagu dari library terlebih dahulu (untuk referensi tambah lagu)
            System.out.println("\n=== DAFTAR SEMUA LAGU DI LIBRARY (UNTUK REFERENSI) ===");
            library.displayAllTable();
            
            // tampilkan tabel playlist
            playlist.displayTable();

            System.out.println("\n1. Tambah Lagu");
            System.out.println("2. Hapus Lagu");
            System.out.println("3. Putar Playlist");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            String input = sc.nextLine();
            switch (input) {
                case "1": addSong(playlist); break;
                case "2": removeSong(playlist); break;
                case "3": playPlaylist(playlist); break;
                case "0": return;
                default: System.out.println("Pilihan salah, tekan Enter..."); sc.nextLine();
            }
        }
    }

    private void addSong(Playlist playlist) {
        System.out.print("Masukkan judul lagu: ");
        String title = sc.nextLine();
        Song s = library.findByTitle(title);
        if (s == null) {
            System.out.println("Lagu tidak ditemukan di dataset! Tekan Enter...");
            sc.nextLine(); return;
        }
        playlist.addSong(s);
        System.out.println("Lagu berhasil ditambahkan! Tekan Enter...");
        sc.nextLine();
    }

    private void removeSong(Playlist playlist) {
        System.out.print("Masukkan judul lagu: ");
        String title = sc.nextLine();
        if (!playlist.removeByTitle(title)) {
            System.out.println("Lagu tidak ada di playlist! Tekan Enter...");
        } else {
            System.out.println("Lagu berhasil dihapus! Tekan Enter...");
        }
        sc.nextLine();
    }

    private void playPlaylist(Playlist playlist) {
        if (playlist.head == null) {
            System.out.println("Playlist kosong! Tekan Enter...");
            sc.nextLine();
            return;
        }

        clearScreen();
        System.out.println("=".repeat(80));
        System.out.println("           ██████╗  ██╗       █████╗  ██╗   ██╗ ██╗ ███╗   ██╗   ██████╗  ");
        System.out.println("           ██╔══██╗ ██║      ██╔══██╗ ╚██╗ ██╔╝ ██║ ████╗  ██╗  ██╔════╝  ");
        System.out.println("           ██████╔╝ ██║      ███████║  ╚████╔╝  ██║ ██╔██╗ ██║  ██║  ███╗ ");
        System.out.println("           ██╔═══╝  ██║      ██╔══██║   ╚██╔╝   ██║ ██║╚██╗██║  ██║  ██╔╝ ");
        System.out.println("           ██║      ███████╗ ██║  ██║    ██║    ██║ ██║ ╚████║  ╚██████╔╝  ");
        System.out.println("           ╚═╝      ╚══════╝ ╚═╝  ╚═╝    ╚═╝    ╚═╝ ╚═╝  ╚═══╝   ╚═════╝   ");
        System.out.println("=".repeat(80));
        System.out.printf("                Memutar Playlist: %-30s\n", playlist.name);
        System.out.println("=".repeat(80));

        Song current = playlist.head;
        int no = 1;

        while (current != null) {
            System.out.printf("\n[%d/%d] Sedang memutar:\n", no, playlist.getSize());
            System.out.println("   Judul : " + current.title);
            System.out.println("   Artis : " + current.artist);
            System.out.println("   Album : " + current.album);
            System.out.printf("   Durasi: %02d:%02d\n", current.duration / 60, current.duration % 60);
            System.out.println("\n   Tekan Enter untuk lagu berikutnya | Ketik '0' untuk berhenti");

            String in = sc.nextLine();
            if (in.equals("0")) {
                System.out.println("Pemutaran dihentikan.");
                sc.nextLine();
                return;
            }

            current = current.next;
            no++;
        }

        System.out.println("\nPlaylist selesai diputar!");
        System.out.println("Tekan Enter untuk kembali...");
        sc.nextLine();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J"); System.out.flush();
        for (int i = 0; i < 5; i++) System.out.println();
    }

    private void showBanner() {
        System.out.println();
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("            ██████╗ ██╗  ██║ ██╗   ██║ ████████╗██╗  ██║███╗   ███╗      ███████╗██╗      ██████╗ ██╗    ██║ ");
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
}