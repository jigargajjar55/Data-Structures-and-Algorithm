package Stack;

import java.util.*;

public class NumberofAtoms_LC726 {
    

    // https://www.youtube.com/watch?v=iuK05gGBzJc
    //Time: O(N ^ 2)
    //Space: O(N ^ 2)
    public String countOfAtoms(String formula) {

        int n = formula.length();
        Stack<Map<String, Integer>> st = new Stack<>();
        st.push(new HashMap<>());

        int index = 0;

        while(index < n){

            char currCh = formula.charAt(index);
            if(currCh == '('){
                st.push(new HashMap<>());

            }else if(currCh == ')'){

                StringBuilder countStr = new StringBuilder();

                while((index+1) < n && Character.isDigit(formula.charAt(index+1))){
                    countStr.append(formula.charAt(index+1));
                    index += 1;
                }
                int count = 1;

                if(countStr.length() > 0){
                    count = Integer.parseInt(countStr.toString());
                }

                Map<String, Integer> curr_Map = st.pop();
                Map<String, Integer> prev_Map = st.peek();

                for(String key : curr_Map.keySet()){

                    int value = (curr_Map.get(key) * count);

                    prev_Map.put(key, prev_Map.getOrDefault(key, 0) + value);

                }


            }else{

                StringBuilder sb = new StringBuilder();
                sb.append(currCh);               

                while((index+1) < n && Character.isLowerCase(formula.charAt(index+1))){
                    sb.append(formula.charAt(index+1));
                    index += 1;
                }

                StringBuilder countStr = new StringBuilder();

                while((index+1) < n && Character.isDigit(formula.charAt(index+1))){
                    countStr.append(formula.charAt(index+1));
                    index += 1;
                }
                String element = sb.toString();
                int count = 1;

                if(countStr.length() > 0){
                    count = Integer.parseInt(countStr.toString());
                }

                Map<String, Integer> curr_Map = st.peek();
                curr_Map.put(element, curr_Map.getOrDefault(element, 0) + count);

            }

            index += 1;
        }

        Map<String, Integer> map = st.peek();
        List<String> keyList = new ArrayList<>(map.keySet());

        Collections.sort(keyList);
        StringBuilder result = new StringBuilder();

        for(int i=0; i<keyList.size(); i++){
            String key = keyList.get(i);
            int value = map.get(key);
            //System.out.println(value);

            result.append(key);
            if(value > 1){
                result.append(value);
            }
        }

        return result.toString();




        
    }
}