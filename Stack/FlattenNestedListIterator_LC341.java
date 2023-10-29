package Stack;

import java.util.*;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested
    // list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class FlattenNestedListIterator_LC341 {

    public class NestedIterator implements Iterator<Integer> {

        List<Integer> list;
        int itr;
        int size;

        //First we will flatten nested list, then we can call for next() and hasNext() call
        // Time: O(N){No. of elements}
        // Space: O(H){Aux. Stack space}
        private void getAllValues(List<Integer> list, List<NestedInteger> nestedList) {

            for (NestedInteger element : nestedList) {

                if (element.isInteger()) {
                    list.add(element.getInteger());
                } else {
                    getAllValues(list, element.getList());
                }
            }
        }

        public NestedIterator(List<NestedInteger> nestedList) {

            this.list = new ArrayList<>();

            getAllValues(list, nestedList);

            this.itr = 0;
            this.size = list.size();

        }

        // Time: O(1)
        @Override
        public Integer next() {

            Integer value = null;

            if (hasNext()) {

                value = list.get(itr);
                itr++;
            }
            return value;
        }

        // Time: O(1)
        @Override
        public boolean hasNext() {

            if (itr < size) {
                return true;
            } else {
                return false;
            }

        }
    }

}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */