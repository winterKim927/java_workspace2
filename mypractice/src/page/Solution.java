package page;

public class Solution {
	    
	public int[] sol(int[] num_list) {
        int[] answer = {};
        int odd=0;
        int even=0;
        for(int i = 0; i<num_list.length; i++){
            if (num_list[i] % 2 == 0){
                even++;
            } else {
                odd++;
            }
        }
        answer[0] = even;
        answer[1] = odd;
        
        return answer;
   }
	
	public static void main(String[] args) {
		int[] num_list = {1,2,3,4,5};
		Solution s = new Solution();
		s.sol(num_list);
	}
}
