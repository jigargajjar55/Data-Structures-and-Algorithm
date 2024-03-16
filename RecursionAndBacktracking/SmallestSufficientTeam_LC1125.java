package RecursionAndBacktracking;
import java.util.*;

public class SmallestSufficientTeam_LC1125 {

    
    List<Integer> output;

     public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {

        int size = people.size();
        int n = req_skills.length;
        Map<String, List<Integer>> skillsToPeople = new HashMap<>();
       
        //Creating mapping of skills that people have.
        for(int i=0; i<size; i++){
            for(String skill : people.get(i)){
                if(!skillsToPeople.containsKey(skill)){
                    skillsToPeople.put(skill, new ArrayList<>());
                }
                skillsToPeople.get(skill).add(i);
            }
        }

        Set<Integer> team = new HashSet<>();
        output = new ArrayList<>();
        int[] teamSize = new int[1];
        teamSize[0] = size;

        dfs(0,n,req_skills,skillsToPeople,team,teamSize);

        int[] result = new int[teamSize[0]];
        for(int i=0; i<teamSize[0]; i++){
            result[i] = output.get(i);
        }

        return result;
        
    }

    //Time: O(skills ^ No. Of People), Space:O(skills + skills) {Aux. Stack space and Hashmap}
    private void dfs(int index,int n,String[] req_skills,Map<String, List<Integer>> skillsToPeople,Set<Integer> team,int[] teamSize){

        //Base Case
        if(team.size() >= teamSize[0]){
            return;
        }

        if(index == n){
            teamSize[0] = team.size();
            output = new ArrayList<>(); 
            for(int person: team){
                output.add(person);
            }           
            return;
        }

        //Include and Exclude process loop wise
        for(int people : skillsToPeople.get(req_skills[index])){
            boolean isNewlyAdded = !team.contains(people);
            team.add(people);

            dfs(index+1,n,req_skills,skillsToPeople,team,teamSize);

            //Backtracking
            if(isNewlyAdded){
                team.remove(people);
            }
        }

    }
   
}