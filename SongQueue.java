public class SongQueue {

    public class QNode {
        Song song;
        QNode next;

        QNode(Song s) {
            song = s;
            next = null;
        }
    }

    private QNode front, rear;

    public SongQueue() {
        front = rear = null;
    }

    public void enqueue(Song s) {
        Song copy = new Song(s.title, s.artist, s.album, s.duration);
        QNode n = new QNode(copy);

        if (rear == null) {
            front = rear = n;
        } else {
            rear.next = n;
            rear = n;
        }
    }

    public Song dequeue() {
        if (front == null) return null;

        Song s = front.song;
        front = front.next;

        if (front == null)
            rear = null;

        return s;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public QNode getFrontRaw() {
        return front;
    }

    public void display() {
        if (front == null) {
            System.out.println("(Antrian kosong)");
            return;
        }

        QNode cur = front;
        int i = 1;

        System.out.println("=== Antrian Lagu ===");
        while (cur != null) {
            System.out.printf("%d. %s - %s (%s) [%02d:%02d]\n",
                    i,
                    cur.song.title,
                    cur.song.artist,
                    cur.song.album,
                    cur.song.duration / 60,
                    cur.song.duration % 60
            );

            cur = cur.next;
            i++;
        }
    }
}
