package solution;

import java.util.*;

// 332. Reconstruct Itinerary
class Solution2 {
    // origin -> list of destinations
    HashMap<String, LinkedList<String>> flightMap = new HashMap<>();
    LinkedList<String> result = null;

    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1). build the graph first
        for (List<String> ticket : tickets) {
            String origin = ticket.get(0);
            String dest = ticket.get(1);
            LinkedList<String> destList = flightMap.getOrDefault(origin, new LinkedList<String>());
            destList.add(dest);
            flightMap.put(origin, destList);
        }

        // Step 2). order the destinations
        for (String key : flightMap.keySet()) {
            Collections.sort(flightMap.get(key));
        }
        return GetEulerPath("JFK");
    }

    // Hierholzer’s Algorithm
    private List<String> GetEulerPath(String origin) {
        ArrayList<String> res = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        String cur = origin;
        stack.push(origin);
        while (!stack.isEmpty()) {
            if(flightMap.get(cur).size() != 0) {
                String des = flightMap.get(cur).pollFirst();
                stack.push(des);
                cur = des;
            } else {
                res.add(stack.pop());
                if (!stack.isEmpty())
                    cur = stack.peek();
            }
        }
        Collections.reverse(res);
        return res;
    }
}
