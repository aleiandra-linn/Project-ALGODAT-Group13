public class ArtistTree {
    private class Node {//khusus class ini
        String artist;
        int count;//jumlah pemutaran
        Node left, right;//tree
        Node next; 
        Node(String a){//node baru 
            artist = a; 
            count = 1; //default node baru diputar 1x
            left = right = next = null; }
    }

    private Node root;

    public void insert(Song s) { 
        root = insertRec(root, s.artist); //rekursif
    }//tree kosong, jadikan node baru dan root aman. klo ada count++

    public Node insertRec(Node node, String artist) {//tambah artis di bst, hitung count
        if (node == null) return new Node(artist);
        int cmp = artist.compareToIgnoreCase(node.artist);//perbandingan nama artis
        if (cmp < 0) node.left = insertRec(node.left, artist);//masuk left
        else if (cmp > 0) node.right = insertRec(node.right, artist);//kanan
        else node.count++;//kalau ada, pemutaran++ node ga nambah
        return node;//tersambung, child tetap kesambung
    }

    public void topN(int n) {
        NodeList list = new NodeList();//buat list kosong belum urut
        inorderToList(root, list);//ambil data
        Node sorted = null;
        Node cur = list.head;
        while (cur != null) {
            Node next = cur.next; 
            cur.next = null;
            sorted = insertSorted(sorted, cur); 
            cur = next;
        }
        System.out.println("\n=== TOP " + n + " ARTIS ===");
        Node p = sorted; 
        int i = 1;
        while (p != null && i <= n) {
            System.out.println(i + ". " + p.artist + " (" + p.count + " kali)");
            p = p.next; 
            i++;
        }
    }

    public void inorderToList(Node node, NodeList list) {//traversal karena alfabet
        if (node == null) return;
        inorderToList(node.left, list);//kunjungi kiri
        Node copy = new Node(node.artist); //salin, biar tidak merubah tree
        copy.count = node.count;//salin
        list.append(copy);
        inorderToList(node.right, list);//kunjungi kanan
    }

    public Node insertSorted(Node head, Node node) {//ngurutin 
        if (head == null || node.count > head.count) { //cek angka count
            node.next = head; return node;//node jadi head baru 
        }
        Node c = head;
        while (c.next != null && c.next.count >= node.count) 
            c = c.next;//telusur ke kanan, selama node masih besar atau sama
        node.next = c.next; //node baru nunjuk node setelah c, sisip
        c.next = node;//c nunjuk node baru
        return head;
    }

    private static class NodeList { //nyimpan hasil traversal
        //masukkan node baru ke terakhir
        Node head; 
        void append(Node n){ 
            if(head==null) head=n; 
            else{ Node c=head; 
                while(c.next!=null) 
                    c=c.next; 
                    c.next=n; 
                } 
        } 
    }
}
