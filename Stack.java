public class Stack {
    private StackNode top;

    public Stack() { 
        top = null; 
    }

    public void push(Song s) { 
        StackNode n = new StackNode(new Song(s.title, s.artist, s.album, s.duration)); 
        n.next = top; 
        top = n; 
    }

    public Song pop() { 
        if (top==null) return null; 
        Song s = top.song; 
        top = top.next; 
        return s; 
    }

    public boolean isEmpty() { 
        return top==null; 
    }

    public void display() {
        StackNode cur = top;
        int i = 1;
        System.out.println("\n=== History Lagu ===");
        while (cur != null) {
            System.out.println(i + ". " + cur.song.title + " - " + cur.song.artist + " (" + cur.song.album + ")");
            cur = cur.next;
            i++;
        }
        if (i == 1) System.out.println("(Belum ada lagu diputar)");
    }
}