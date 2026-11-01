/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {

    Map<Node,Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if(visited.get(node)!=null){
            return visited.get(node);
        }
        Node curr = new Node(node.val);
        visited.put(node,curr);

        for (Node n : node.neighbors){
          curr.neighbors.add(cloneGraph(n));

        }
        return curr;
    }
}