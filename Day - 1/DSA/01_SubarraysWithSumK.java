/*
 * Given an integer array and an integer k, find the number of
 * contiguous subarrays whose sum is exactly equal to k.
 *
 * Example:
 * Input:  arr = [1, 2, 3], k = 3
 * Output: 2
 *
 * Explanation:
 * The subarrays with sum 3 are [1, 2] and [3].
 */


import java.util.HashMap;
import java.util.Scanner;

class PrefixSum {

    public static int findSubArrays (int[] arr, int n, int k) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int prefixSum = 0;
        int count = 0;

        map.put(0, 1);

        for (int num : arr) {

            prefixSum += num;
            
            int needed = prefixSum - k;
            if (map.containsKey(needed)) {
                count += map.get(needed);
            }

            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter array elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter value of k: ");
        int k = sc.nextInt();

        int ans = findSubArrays(arr, n, k);
        System.out.println("Sub array count with sum = " + k + " is: " + ans);
    }
}