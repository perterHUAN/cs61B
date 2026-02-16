import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int sum = 0;
        for(int ele:L) {
            sum += ele;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> res = new ArrayList<>();
        for(int ele:L) {
            if(ele % 2 == 0) {
                res.add(ele);
            }
        }
        return res;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> res = new ArrayList<>();
        for(Integer ele:L1) {
            if(L2.contains(ele)) {
                res.add(ele);
            }
        }
        return res;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int count = 0;
        for(String ele:words) {
            for(char ch:ele.toCharArray()) {
                if(ch == c) {
                    count++;
                }
            }
        }
        return count;
    }
}
