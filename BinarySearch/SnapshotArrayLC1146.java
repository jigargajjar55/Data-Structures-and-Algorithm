package BinarySearch;
import java.util.*;

/*
 Intuition
Instead of copy the whole array,
we can only record the changes of set.


Explanation
The idea is, the whole array can be large,
and we may take the snap tons of times.
(Like you may always ctrl + S twice)

Instead of record the history of the whole array,
we will record the history of each cell.
And this is the minimum space that we need to record all information.

For each A[i], we will record its history.
With a snap_id and a its value.

When we want to get the value in history, just binary search the time point.


Complexity
Time O(log(Snap))
Space O(N * Snap)

SnapshotArray(int length) is O(N) time
set(int index, int val) is  O(log(Snap)) in Java
snap() is O(1)
get(int index, int snap_id) is O(log(Snap))
 */

class SnapshotArray {
    List<TreeMap<Integer, Integer>> snapMap;
    int snapId = 0;

    public SnapshotArray(int length) {

        snapMap = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            snapMap.add(new TreeMap<>());
            snapMap.get(i).put(0, 0);
        }
    }

    public void set(int index, int val) {
        snapMap.get(index).put(snapId, val);
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snap_id) {

        return snapMap.get(index).floorEntry(snap_id).getValue();

    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */