package com.ankit;

public class Clinks {

	public static void main(String[] args) throws java.io.IOException {
		java.io.File f = new java.io.File("Clinks.txt");
		java.io.File f2 = new java.io.File("list.txt");

		String s = "";

		java.io.FileReader fr2 = new java.io.FileReader(f2);
		java.io.BufferedReader br2 = new java.io.BufferedReader(fr2);

		while ((s = br2.readLine()) != null) {
			java.io.FileReader fr = new java.io.FileReader(f);
			java.io.BufferedReader br = new java.io.BufferedReader(fr);

			String fsName = s.replace('/', '-') + ".csv";
			java.io.File file = new java.io.File(f.getParent(), fsName);
			java.io.FileWriter fw = new java.io.FileWriter(file, false);
			java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
			java.io.PrintWriter outFile = new java.io.PrintWriter(bw);

			int counter = 0;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '/') {
					counter++;
				}
			}
			counter++;
			// System.out.println("counter+1="+counter);

			String LR[] = { "", "" };
			String word = "";
			int countWI = 0;
			int countT2O = 0;
			int countO2T = 0;
			java.util.Map<String, Integer> withItself = new java.util.HashMap<String, Integer>();
			java.util.Map<String, Integer> target2Other = new java.util.HashMap<String, Integer>();
			java.util.Map<String, Integer> other2Target = new java.util.HashMap<String, Integer>();
			String s1 = "";
			while ((s1 = br.readLine()) != null) {
				// System.out.println(s1);
				if (s1.contains(s)) {
//					 System.out.println(s1);
					LR = s1.split(" ");

					if (LR[0].contains(s) && LR[1].contains(s)) {
						// System.out.println( LR[0] + " " + LR[1] );
						word = LR[0].substring(0, nthOccurrence(LR[0], '/', counter));
						// System.out.println( LR[0] + " " + word);
						countWI = withItself.containsKey(word) ? withItself.get(word) : 0;
						withItself.put(word, countWI + 1);

					} else if (LR[0].contains(s)) {
						// System.out.println(LR[0] + " " + LR[1]);
						word = LR[1].substring(0, nthOccurrence(LR[1], '/', counter));
						// System.out.println( LR[0] + " " + word);
						countT2O = target2Other.containsKey(word) ? target2Other.get(word) : 0;
						target2Other.put(word, countT2O + 1);

					} else if (LR[1].contains(s)) {
//						 System.out.println( LR[0] + " " + LR[1] );
//						if (LR[0].contains("mozilla/toolkit")) {
//							System.out.println( LR[0] + " " + LR[1] );
//						}
						word = LR[0].substring(0, nthOccurrence(LR[0], '/', counter));
						// System.out.println( word + " " + LR[1] );
						countO2T = other2Target.containsKey(word) ? other2Target.get(word) : 0;
						other2Target.put(word, countO2T + 1);

					}
				}
			}
//			System.out.println(s + " @@@@> " + s);
			outFile.println(s + " @@@@> " + s);
			for (String name : withItself.keySet()) {
				String key = name.toString();
				String value = withItself.get(name).toString();
//				System.out.println(key + "," + value);
				outFile.println(key + "," + value);
			}
//			System.out.println(s + " @@@@> Others");
			outFile.println(s + " @@@@> Others");
			for (String name : target2Other.keySet()) {
				String key = name.toString();
				String value = target2Other.get(name).toString();
//				System.out.println(key + "," + value);
				outFile.println(key + "," + value);
			}
//			System.out.println("Others @@@@> " + s);
			outFile.println("Others @@@@> " + s);
			for (String name : other2Target.keySet()) {
				String key = name.toString();
				String value = other2Target.get(name).toString();
//				System.out.println(key + "," + value);
				outFile.println(key + "," + value);
			}
			outFile.println();
			outFile.close();
			br.close();
		}
		br2.close();
	}

	public static int nthOccurrence(String str, char c, int n) {
		int pos = str.indexOf(c, 0);
		int pos2=0;
//		System.out.println("pos=" + pos);
		while (n-- > 0 && pos != -1 /* && pos < str.length() */ ) {
			pos2 = str.indexOf(c, pos + 1);
			if (pos2 != -1)
			{
				pos=pos2;
			}
			else
			{
//				System.out.println(str + "pos=" + pos);
			}
//			System.out.println("pos=" + pos);
		}
//		System.out.println("pos=" + pos);
		return pos;
	}
}
