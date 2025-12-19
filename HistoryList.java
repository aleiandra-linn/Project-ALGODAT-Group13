public class HistoryList {
    public class Node {
        Song song;
        int count;
        Node next;
        Node(Song s) { 
            song = s; 
            count = 1; 
            next = null; }
    }

    public Node head;
    public void add(Song s) {
        Node cur = head;//membuat pointer bantu tanpa mengubah posisi head
        while (cur != null) {
            if (cur.song.title.equals(s.title) && cur.song.artist.equals(s.artist)) {
                cur.count++;//cek apakah ada judul dan artis yang sama, maka pemutaran bertambah
                return;
            }
            cur = cur.next;//berpindah node utnuk pengecekan
        }
        Node n = new Node(new Song(s.title, s.artist, s.album, s.duration));//masukan node baru
        n.next = head;
        head = n;
    }

    // tampil newest -> oldest (head newest because add puts at head)
    public void displayAllNewestFirst() {
        if (head == null) {
            System.out.println("(History kosong)");
            return;
        }
        Node cur = head; 
        int no = 1;//penomoran
        System.out.println("=== HISTORY LAGU ===");
        while (cur != null) {
            System.out.printf("%2d. %s - %s (%s) [%02d:%02d]\n",
                no, cur.song.title, cur.song.artist, cur.song.album, 
                cur.song.duration/60, cur.song.duration%60);//menit & detik
                cur = cur.next;
                no++;
        }
    }

   
}
