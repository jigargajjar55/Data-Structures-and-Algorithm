package Stack;
import java.util.*;

public class ImplementStackusingQueues_LC225 {
    

    
    Queue<Integer> q;

    public ImplementStackusingQueues_LC225() {
        q = new LinkedList<>();
        
    }
    
    public void push(int x) {
        int size = q.size();
        q.offer(x);

        while(size > 0){
            int top = q.peek();
            q.poll();
            q.offer(top);
            size--;
        }
        
    }
    
    public int pop() {
        int top = q.poll();
        return top;
        
    }
    
    public int top() {
        int top = q.peek();
        return top;        
    }
    
    public boolean empty() {

        return q.isEmpty();
        
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */