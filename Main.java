import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
//		String str1 = "FRANCE";
//		String str2 = "french"; // return 16384

//		String str1 = "handshake";
//		String str2 = "shake hands"; // return 65536
		
//		String str1 = "aa1+aa2";
//		String str2 = "AAAA12"; // return 43690
//		
		String str1 = "E=M*C^2";
		String str2 = "e=m*c^2"; // return 65536
		
		
		System.out.println(new Solution().solution(str1, str2));
	}

}

class Solution {
	public int solution(String str1, String str2) {
		int answer = 0;

		str1 = str1.toLowerCase();
		HashMap<String, Integer> map1 = new HashMap<>();
		
		for (int i = 0; i < str1.length() - 1; i++) {
			String str = str1.substring(i, i+2);
			if (Character.isAlphabetic(str.charAt(0)) && Character.isAlphabetic(str.charAt(1))) {
				if (map1.containsKey(str)) {
					map1.put(str, map1.get(str) + 1);
				} else {
					map1.put(str, 1);
				}
			}
		}
		str2 = str2.toLowerCase();
		HashMap<String, Integer> map2 = new HashMap<>();
		
		for (int i = 0; i < str2.length() - 1; i++) {
			String str = str2.substring(i, i+2);
			if (Character.isAlphabetic(str.charAt(0)) && Character.isAlphabetic(str.charAt(1))) {
				if (map2.containsKey(str)) {
					map2.put(str, map2.get(str) + 1);
				} else {
					map2.put(str, 1);
				}
			}
		}
		
		if (map1.isEmpty() && map2.isEmpty()) {
			return 65536;
		}
		
		int unionCount = 0;
		int intersectionCount = 0;
		HashSet<String> union = new HashSet<>();
		union.addAll(map1.keySet());
		union.addAll(map2.keySet());
		for (Iterator<String> it = union.iterator(); it.hasNext(); ) {
			String key = it.next();
			if (map1.containsKey(key) && map2.containsKey(key)) {
				int s1 = map1.get(key);
				int s2 = map2.get(key);
				intersectionCount += Math.min(s1, s2);
				unionCount += Math.max(s1, s2);
			} else if (map1.containsKey(key)) {
				unionCount += map1.get(key);
			} else if (map2.containsKey(key)) {
				unionCount += map2.get(key);
			}
		}
		answer = (int) (65536 * ((float)intersectionCount / unionCount));
		
		return (int) answer;
	}
}