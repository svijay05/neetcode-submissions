class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>();

        for (int i = 0; i < wordList.size(); i++) {
            wordSet.add(wordList.get(i));
        }

        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int length = 1;

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!q.isEmpty()) {
            // do level order traversal

        int levelSize = q.size();

         for (int l = 0; l < levelSize; l++) {
                String curr = q.poll();
                if(curr.equals(endWord)){
                    return length;
                }
                for (int i = 0; i < curr.length(); i++) {
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        StringBuilder sb = new StringBuilder(curr);
                        sb.setCharAt(i, ch);
                        String newString = sb.toString();

                        if (!visited.contains(newString) && wordSet.contains(newString)) {
                            visited.add(newString);
                            q.offer(newString);
                        }
                    }
                }
            }

            length++;
        }

        return 0;
    }
}
