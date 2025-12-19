public class SongTree {
    private class Node {
        String title;
        int count;
        Node left, right, next;
        Node(String t) { 
            title = t; 
            count = 1; 
            left = right = next = null; }
    }

    private Node root;
    public void insert(Song s) { root = insertRec(root, s.title); }
    private Node insertRec(Node node, String title) {
        if (node == null) return new Node(title);
        int cmp = title.compareToIgnoreCase(node.title);
        if (cmp < 0) node.left = insertRec(node.left, title);
        else if (cmp > 0) node.right = insertRec(node.right, title);
        else node.count++;
        return node;
    }

    public void topN(int n) {
        NodeList list = new NodeList();
        inorderToList(root, list);
        Node sorted = null; 
        Node cur = list.head;
        while (cur != null) {
            Node next = cur.next; 
            cur.next = null;
            sorted = insertSorted(sorted, cur); cur = next;
        }
        System.out.println("\n=== TOP " + n + " LAGU ===");
        Node p = sorted; 
        int i = 1;
        while (p != null && i <= n) {
            System.out.println(i + ". " + p.title + " (" + p.count + " kali)");
            p = p.next; 
            i++;
        }
    }

    private void inorderToList(Node node, NodeList list) {
        if (node == null) return;
        inorderToList(node.left, list);
        Node copy = new Node(node.title); 
        copy.count = node.count;
        list.append(copy);
        inorderToList(node.right, list);
    }

    private Node insertSorted(Node head, Node node) {
        if (head == null || node.count > head.count) { 
            node.next = head; return node; 
        }
        Node c = head;
        while (c.next != null && c.next.count >= node.count) 
            c = c.next;
            node.next = c.next; 
            c.next = node; 
            return head;
    }
    private static class NodeList { 
        Node head; 

        void append(Node n){ 
            if(head == null) head = n; 
            else{ 
                Node c = head; 
                while(c.next != null) 
                    c = c.next; 
                    c.next = n; } 
        } }
}
