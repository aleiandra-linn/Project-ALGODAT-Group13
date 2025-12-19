import java.util.Scanner;

public class MenuDisplay {
    static Song currentSong = null;
    static final int LEBAR = 122;
    static Scanner sc = new Scanner(System.in);

    static class User { String nama; String email; String password; User next; }
    static User usersHead = null;
    static int userCount = 0;

    static void garis() { for (int i=0;i<LEBAR;i++) System.out.print("="); System.out.println(); }

    static void tengah(String teks) {
        String clean = teks.replaceAll("\033\\[[;\\d]*m", "");
        int kiri = (LEBAR - 2 - clean.length()) / 2;
        int kanan = LEBAR - 2 - kiri - clean.length();
        System.out.print("|");
        for (int i = 0; i < kiri; i++) System.out.print(" ");
        System.out.print(teks);
        for (int i = 0; i < kanan; i++) System.out.print(" ");
        System.out.println("|");
    }

    static void showBanner() {
        System.out.println();
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("            ██████╗ ██╗  ██║ ██╗   ██╗ ████████╗██╗  ██╗███╗   ███╗      ███████╗██╗      ██████╗ ██╗    ██╗ ");
        System.out.println("            ██╔══██╗██║  ██║ ╚██╗ ██╔╝ ╚══██╔══╝██║  ██║████╗ ████║      ██╔════╝██║     ██╔═══██╗██║    ██║ ");
        System.out.println("            ██████╔╝███████║  ╚████╔╝     ██║   ███████║██╔████╔██║      ███████╗██║     ██║   ██║██║ █╗ ██║ ");
        System.out.println("            ██╔══██╗██╔══██║   ╚██╔╝      ██║   ██╔══██║██║╚██╔╝██║      ██╔════╝██║     ██║   ██║██║███╗██║ ");
        System.out.println("            ██║  ██║██║  ██║    ██║       ██║   ██║  ██║██║ ╚═╝ ██║      ██║     ███████╗╚██████╔╝╚███╔███╔╝ ");
        System.out.println("            ╚═╝  ╚═╝╚═╝  ╚═╝    ╚═╝       ╚═╝   ╚═╝  ╚═╝╚═╝     ╚═╝      ╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝  ");
        System.out.println("                         ALIN     ARKAN     FATHAN     MAIL     WARITSA     KAMRUN     YUNDA");
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("                                             RHYTHMFLOW  MUSIC PLAYER SYSTEM");
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n");
    }

    static void clearScreen() { System.out.print("\033[H\033[2J"); System.out.flush(); for (int i=0;i<5;i++) System.out.println(); }

    static int menu() {
        String green = "\033[32m"; String reset = "\033[0m";
        int pos = 0;
        while (true) {
            clearScreen(); showBanner(); garis(); tengah("WELCOME TO RHYTHM FLOW MUSIC PLAYER"); garis();
            tengah("~~~~~~~~~~~~~"); tengah(pos==0 ? green+"   Login   "+reset : "   Login   "); tengah("~~~~~~~~~~~~~");
            tengah("~~~~~~~~~~~~~"); tengah(pos==1 ? green+"  Registrasi  "+reset : "  Registrasi  "); tengah("~~~~~~~~~~~~~");
            tengah("~~~~~~~~~~~~~"); tengah(pos==2 ? green+"    Exit    "+reset : "    Exit    "); tengah("~~~~~~~~~~~~~");
            garis();
            System.out.println("Gunakan panah atas dan bawah untuk memilih lalu Enter untuk konfirmasi.");
            System.out.println("Atau tekan W = UP, S = DOWN, Enter = pilih");
            String input = sc.nextLine().toUpperCase();
            if (input.equals("W")) { pos--; if (pos < 0) pos = 2; }
            else if (input.equals("S")) { pos++; if (pos > 2) pos = 0; }
            else if (input.equals("")) return pos;
        }
    }

    static void registrasi() {
        clearScreen(); showBanner(); garis(); tengah("=== Registrasi ==="); garis();
        System.out.print("Nama: "); String nama = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String password = sc.nextLine();
        User cur = usersHead;
        while (cur != null) { if (cur.email.equals(email)) { System.out.println("Email sudah terdaftar! Tekan Enter untuk kembali."); sc.nextLine(); return; } cur = cur.next; }
        User u = new User(); u.nama = nama; u.email = email; u.password = password; u.next = usersHead; usersHead = u; userCount++;
        System.out.println("Registrasi berhasil! Tekan Enter untuk kembali ke menu."); sc.nextLine();
    }

    static boolean login() {
        clearScreen(); showBanner(); garis(); tengah("=== Login ==="); garis();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String password = sc.nextLine();
        User cur = usersHead;
        while (cur != null) {
            if (cur.email.equals(email) && cur.password.equals(password)) {
                System.out.println("Login berhasil! Selamat datang, " + cur.nama + "!");
                sc.nextLine();
                landingPage();
                return true;
            }
            cur = cur.next;
        }
        System.out.println("Email atau password salah! Tekan Enter untuk kembali ke menu."); sc.nextLine();
        return false;
    }

    static void landingPage() {
        clearScreen(); showBanner(); garis(); tengah("=== LANDING PAGE ==="); garis();
        // hardcode top lagu/artis in linked lists
        LinkedListString topLagu = new LinkedListString(); topLagu.add("1. Blinding Lights - The Weeknd"); topLagu.add("2. As It Was - Harry Styles"); topLagu.add("3. Levitating - Dua Lipa");
        tengah("Top 3 Lagu"); LinkedListString.Node tl = topLagu.head; while (tl != null) { System.out.println("  " + tl.value); tl = tl.next; } garis();
        LinkedListString topArtis = new LinkedListString(); topArtis.add("1. The Weeknd"); topArtis.add("2. Dua Lipa"); topArtis.add("3. Harry Styles");
        tengah("Top 3 Artis"); LinkedListString.Node ta = topArtis.head; while (ta != null) { System.out.println("  " + ta.value); ta = ta.next; } garis();
        System.out.println("Tekan Enter untuk masuk ke menu Spotify..."); sc.nextLine();
    }

    static class LinkedListString { static class Node { String value; Node next; Node(String v){value=v; next=null;} } Node head; void add(String v){ Node n=new Node(v); if(head==null) head=n; else{ Node c=head; while(c.next!=null) c=c.next; c.next=n; } } }

    public static void main(String[] args) {
        while (true) {
            int pilihan = menu();
            switch (pilihan) {
                case 0:
                    boolean sukses = login();
                    if (sukses) SpotifyApp.main(null);
                    break;
                case 1: registrasi(); break;
                case 2: System.out.println("Keluar..."); System.exit(0); break;
            }
        }
    }
}
