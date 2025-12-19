// UrutanLaguMenu.java
import java.util.Scanner;

public class UrutanLaguMenu {
    static final int LEBAR = 122;
    static Scanner sc = new Scanner(System.in);

    static void garis() { System.out.println("=".repeat(LEBAR)); }

    static void tengah(String teks) {
        int kiri = (LEBAR - 2 - teks.length()) / 2;
        int kanan = LEBAR - 2 - kiri - teks.length();
        System.out.print("|");
        System.out.print(" ".repeat(Math.max(0, kiri)));
        System.out.print(teks);
        System.out.print(" ".repeat(Math.max(0, kanan)));
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

    public static void showUrutanLagu(SongLibrary lib) {
        SongSorter sorter = new SongSorter();
        NodeSong cur = lib.getHead();
        while (cur != null) { sorter.addSong(cur.data); cur = cur.next; }

        while (true) {
            clearScreen(); showBanner(); garis(); tengah("=== PILIH URUTAN LAGU ==="); garis();
            System.out.println("1. Urutkan berdasarkan Judul");
            System.out.println("2. Urutkan berdasarkan Artis");
            System.out.println("3. Urutkan berdasarkan Album");
            System.out.println("4. Kembali ke menu utama");
            garis();
            System.out.print("Pilih (1-4): ");
            String input = sc.nextLine();
            switch (input) {
                case "1": sorter.sortByTitle(); sorter.display(); break;
                case "2": sorter.sortByArtist(); sorter.display(); break;
                case "3": sorter.sortByAlbum(); sorter.display(); break;
                case "4": return;
                default: System.out.println("Pilihan tidak valid! Tekan Enter..."); sc.nextLine();
            }
            System.out.println("Tekan Enter untuk melanjutkan..."); sc.nextLine();
        }
    }

    static void clearScreen() { System.out.print("\033[H\033[2J"); System.out.flush(); System.out.println("\n".repeat(3)); }
}
