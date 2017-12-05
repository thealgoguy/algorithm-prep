package main.ashu.dp;

import java.util.ArrayList;
import java.util.List;

//A variation of rod-cutting problem
//Given a dictionary and a random string, check if the string can be split into a sequence of valid dictionary words
public class WordBreakProblem {
	public static void main(String a []) {
		//String str = "fohhemkkaecojceoaejkkoedkofhmohkcjmkggcmnami";
	//	String s [] = {"kfomka","hecagbngambii","anobmnikj","c","nnkmfelneemfgcl","ah","bgomgohl","lcbjbg","ebjfoiddndih","hjknoamjbfhckb","eioldlijmmla","nbekmcnakif","fgahmihodolmhbi","gnjfe","hk","b","jbfgm","ecojceoaejkkoed","cemodhmbcmgl","j","gdcnjj","kolaijoicbc","liibjjcini","lmbenj","eklingemgdjncaa","m","hkh","fblb","fk","nnfkfanaga","eldjml","iejn","gbmjfdooeeko","jafogijka","ngnfggojmhclkjd","bfagnfclg","imkeobcdidiifbm","ogeo","gicjog","cjnibenelm","ogoloc","edciifkaff","kbeeg","nebn","jdd","aeojhclmdn","dilbhl","dkk","bgmck","ohgkefkadonafg","labem","fheoglj","gkcanacfjfhogjc","eglkcddd","lelelihakeh","hhjijfiodfi","enehbibnhfjd","gkm","ggj","ag","hhhjogk","lllicdhihn","goakjjnk","lhbn","fhheedadamlnedh","bin","cl","ggjljjjf","fdcdaobhlhgj","nijlf","i","gaemagobjfc","dg","g","jhlelodgeekj","hcimohlni","fdoiohikhacgb","k","doiaigclm","bdfaoncbhfkdbjd","f","jaikbciac","cjgadmfoodmba","molokllh","gfkngeebnggo","lahd","n","ehfngoc","lejfcee","kofhmoh","cgda","de","kljnicikjeh","edomdbibhif","jehdkgmmofihdi","hifcjkloebel","gcghgbemjege","kobhhefbbb","aaikgaolhllhlm","akg","kmmikgkhnn","dnamfhaf","mjhj","ifadcgmgjaa","acnjehgkflgkd","bjj","maihjn","ojakklhl","ign","jhd","kndkhbebgh","amljjfeahcdlfdg","fnboolobch","gcclgcoaojc","kfokbbkllmcd","fec","dljma","noa","cfjie","fohhemkka","bfaldajf","nbk","kmbnjoalnhki","ccieabbnlhbjmj","nmacelialookal","hdlefnbmgklo","bfbblofk","doohocnadd","klmed","e","hkkcmbljlojkghm","jjiadlgf","ogadjhambjikce","bglghjndlk","gackokkbhj","oofohdogb","leiolllnjj","edekdnibja","gjhglilocif","ccfnfjalchc","gl","ihee","cfgccdmecem","mdmcdgjelhgk","laboglchdhbk","ajmiim","cebhalkngloae","hgohednmkahdi","ddiecjnkmgbbei","ajaengmcdlbk","kgg","ndchkjdn","heklaamafiomea","ehg","imelcifnhkae","hcgadilb","elndjcodnhcc","nkjd","gjnfkogkjeobo","eolega","lm","jddfkfbbbhia","cddmfeckheeo","bfnmaalmjdb","fbcg","ko","mojfj","kk","bbljjnnikdhg","l","calbc","mkekn","ejlhdk","hkebdiebecf","emhelbbda","mlba","ckjmih","odfacclfl","lgfjjbgookmnoe","begnkogf","gakojeblk","bfflcmdko","cfdclljcg","ho","fo","acmi","oemknmffgcio","mlkhk","kfhkndmdojhidg","ckfcibmnikn","dgoecamdliaeeoa","ocealkbbec","kbmmihb","ncikad","hi","nccjbnldneijc","hgiccigeehmdl","dlfmjhmioa","kmff","gfhkd","okiamg","ekdbamm","fc","neg","cfmo","ccgahikbbl","khhoc","elbg","cbghbacjbfm","jkagbmfgemjfg","ijceidhhajmja","imibemhdg","ja","idkfd","ndogdkjjkf","fhic","ooajkki","fdnjhh","ba","jdlnidngkfffbmi","jddjfnnjoidcnm","kghljjikbacd","idllbbn","d","mgkajbnjedeiee","fbllleanknmoomb","lom","kofjmmjm","mcdlbglonin","gcnboanh","fggii","fdkbmic","bbiln","cdjcjhonjgiagkb","kooenbeoongcle","cecnlfbaanckdkj","fejlmog","fanekdneoaammb","maojbcegdamn","bcmanmjdeabdo","amloj","adgoej","jh","fhf","cogdljlgek","o","joeiajlioggj","oncal","lbgg","elainnbffk","hbdi","femcanllndoh","ke","hmib","nagfahhljh","ibifdlfeechcbal","knec","oegfcghlgalcnno","abiefmjldmln","mlfglgni","jkofhjeb","ifjbneblfldjel","nahhcimkjhjgb","cdgkbn","nnklfbeecgedie","gmllmjbodhgllc","hogollongjo","fmoinacebll","fkngbganmh","jgdblmhlmfij","fkkdjknahamcfb","aieakdokibj","hddlcdiailhd","iajhmg","jenocgo","embdib","dghbmljjogka","bahcggjgmlf","fb","jldkcfom","mfi","kdkke","odhbl","jin","kcjmkggcmnami","kofig","bid","ohnohi","fcbojdgoaoa","dj","ifkbmbod","dhdedohlghk","nmkeakohicfdjf","ahbifnnoaldgbj","egldeibiinoac","iehfhjjjmil","bmeimi","ombngooicknel","lfdkngobmik","ifjcjkfnmgjcnmi","fmf","aoeaa","an","ffgddcjblehhggo","hijfdcchdilcl","hacbaamkhblnkk","najefebghcbkjfl","hcnnlogjfmmjcma","njgcogemlnohl","ihejh","ej","ofn","ggcklj","omah","hg","obk","giig","cklna","lihaiollfnem","ionlnlhjckf","cfdlijnmgjoebl","dloehimen","acggkacahfhkdne","iecd","gn","odgbnalk","ahfhcd","dghlag","bchfe","dldblmnbifnmlo","cffhbijal","dbddifnojfibha","mhh","cjjol","fed","bhcnf","ciiibbedklnnk","ikniooicmm","ejf","ammeennkcdgbjco","jmhmd","cek","bjbhcmda","kfjmhbf","chjmmnea","ifccifn","naedmco","iohchafbega","kjejfhbco","anlhhhhg"};
	    String str = "leetcode";
	    String s [] = {"leet","code","sam","sung","man","mango","icecream","and","go","i","like","ice","cream"}; 
		System.out.println("Word length = "+str.length());
		List<String> dict = new ArrayList<String>();
	    for(int i=0; i<s.length; i++) dict.add(s[i]);
	    WordBreakProblem wb = new WordBreakProblem();
	    boolean possible = wb.wordBreak(str, dict);
	    if(possible) System.out.println("Yes, the string can be split into valid dictinary words");
	    else System.out.println("No, the string cannot be split into valid dictionary words");
	}

    public boolean wordBreak(String s, List<String> wordDict) {
       // boolean possible =  split_back(s, s.length(), wordDict, "");
    	//boolean possible =  split_dp(s, wordDict);
    	boolean possible =  split_dp_linearSpace(s, wordDict);
        return possible;
    }
    //split and solve for increasing word length...bad time complexity
    public boolean split_dp(String s, List<String> dict) {
    	int n = s.length();
    	boolean dp [][] = new boolean[n][n];
    	//solve bottom-up
    	for(int len=1; len<=n; len++) {
    		for(int i=0; i+len<=n; i++) {
    			int j = i+len-1;
    			if(len==1) dp[i][j] = isWord(s.substring(i,i+1), dict);
    			else {
    				if(isWord(s.substring(i, j+1), dict)) dp[i][j] = true;
    				else {
    					int k;
    					for(k=i; k<j; k++) {
        		            if(dp[i][k] & dp[k+1][j]) {
        		            	dp[i][j] = true; break;        	
        		            }
        		        }
    					if(k==j) dp[i][j] = false;
    				}
    			}
    		}
    	}
    	return dp[0][n-1];
    }
    
    //linear space dp....optimization over previous dp version..leetcode accepted soln
    public boolean split_dp_linearSpace(String s, List<String> dict) {
    	int n = s.length();
    	boolean dp [] = new boolean[n+1];   //dp[i] = optimal splitting of prefix of length i
    	//solve bottom-up
    	for(int len=1; len<=n; len++) {
    		boolean f = false;
    		//generate prefixes and check
    		for(int i=0; i<=len; i++) {
    		   String prefix = s.substring(0, i);
    		   if(i==len) f = isWord(prefix, dict);
    		   else if(dp[i] && isWord(s.substring(i, len), dict)) {
    			   f = true; break;
    		   }
    		}
    		dp[len] = f;
    	}
    	return dp[n];
    }
    
    //this backtracking approach gives TLE if query word is large
    public boolean split_back(String s, int actualLength, List<String> dict, String answer) {
      if(s.length()==1) return false;
        //split and check
        int n = s.length();
        boolean flag = false;
        for(int i=0; i<n; i++) {
            String prefix = s.substring(0, i+1);
            String y = s.substring(i+1, n);
            //if(!split_back(x, dict)) continue;   bad idea instead of recursing for both splits, split at point where first part is a valid dict word and check for second part...like rod cutting problem
            if(!isWord(prefix, dict)) continue;
            //if on the last prefix, print the solution
            if(i==n-1) {
            	flag = true;
            	answer += s;
            	System.out.println(answer);
            }
            if(split_back(y, actualLength, dict, answer+prefix+" ")) {
            	//return true;  commenting to count all ways 
               flag = true;
            }
        }
        return flag;
    }
    
    public boolean isWord(String s, List<String> dict) {
        for(String word : dict) {
            if(s.equals(word)) return true;
        }
        return false;
    }
}

