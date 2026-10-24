package 板1;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Map<Integer, Node> map;
    int capacity;
    Node virtualHead;
    Node virtualTail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.virtualHead = new Node(0, 0);
        this.virtualTail = new Node(0, 0);
        virtualHead.next = virtualTail;
        virtualTail.pre = virtualHead;
    }

    public int get(int key) {
        Node res = map.get(key);
        if (res == null) {
            return -1;
        }
        moveToHead(res);
        return res.val;
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if (n != null) {
            n.val = value;
            moveToHead(n);
            return;
        }
        Node node = new Node(key, value);
        addHead(node);
        map.put(key, node);
        if (map.size() > capacity) {
            Node d = deleteTail();
            map.remove(d.key);
        }
    }

    public void moveToHead(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = virtualHead;
        node.next = virtualHead.next;
        virtualHead.next.pre = node;
        virtualHead.next = node;
    }

    public void addHead(Node node) {
        node.pre = virtualHead;
        node.next = virtualHead.next;
        virtualHead.next.pre = node;
        virtualHead.next = node;
    }

    public Node deleteTail() {
        Node d = virtualTail.pre;
        virtualTail.pre = virtualTail.pre.pre;
        virtualTail.pre.next = virtualTail;
        return d;
    }
}

class Node {
    int key;
    int val;
    Node pre;
    Node next;

    public Node(int key, int val) {
        this.val = val;
        this.key = key;
    }
}