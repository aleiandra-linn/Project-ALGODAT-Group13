// NodeSong.java
public class NodeSong {
    public Song data;
    public NodeSong next;

    public NodeSong(Song s) {
        this.data = s;
        this.next = null;
    }
}
