
public class Solution {


   public static void main(String[] args) {
      // TODO Auto-generated method stub
      int n = 3;
      String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
      int[] answer = new int[2];
      int endN;
      boolean flag = true;
      //끝말잇기가 맞지 않을때
      for (int i=0; i<words.length-1; i++) {
         if(!words[i].substring(words[i].length()-1).equals(words[i+1].substring(0,1))) {
        	 flag = false;
            if((i+1)%n==0) {
               answer[0]= 1;
               answer[1]= (int)((i+2)/n)+1;
            }
            else {
               answer[0]= (i+2)%n;
               answer[1]= ((i+2)/n);
            } 
         }
         //맞을때
         else if(words[i].substring(words[i].length()-1).equals(words[i+1].substring(0,1))){	
            //중복일 때
            for(int j=i+1; j<words.length; j++) {
               if(words[i].equals(words[j])) {
            	  flag = false;
                  endN = j+1;
                  if(endN%n==0) {
                     answer[0] = n;
                     answer[1] = endN/n;
                  } else {
                     answer[0] = endN % n;
                     answer[1] = (int)endN/n +1;
                  }

               }
            }
         }  
      }
      if(flag) {
    	  answer[0] = 0;
          answer[1] = 0;
      }
      System.out.println(answer[0]+","+answer[1]);
   }

}