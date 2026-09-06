/*
 * Given the positions of houses and lamps on a straight street,
 * find the minimum radius of light required so that every house
 * is covered by at least one lamp.
 *
 * A house is covered if its distance from the nearest lamp is
 * less than or equal to the lamp's radius.
 *
 * Example:
 * Input:
 * Houses = [1, 3, 4]
 * Lamps  = [0, 2]
 *
 * Output:
 * 2
 *
 * Explanation:
 * House 1 → nearest lamp is at 0 or 2 → distance = 1
 * House 3 → nearest lamp is at 2       → distance = 1
 * House 4 → nearest lamp is at 2       → distance = 2
 *
 * Therefore, the minimum required radius is 2.
 */



import java.util.Arrays;

class MinimumLampRadius {

    static int findMinimumRadius(int[] houses, int[] lamps) {

        Arrays.sort(houses);
        Arrays.sort(lamps);

        int answer = 0;

        for (int house : houses) {

            int left = 0;
            int right = lamps.length - 1;

            while (left <= right) {

                int mid = left + (right - left) / 2;

                if (lamps[mid] == house) {
                    left = mid;
                    break;
                }

                if (lamps[mid] < house) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            int minDistance = Integer.MAX_VALUE;

            if (right >= 0) {
                minDistance = Math.min(
                    minDistance,
                    house - lamps[right]
                );
            }

            if (left < lamps.length) {
                minDistance = Math.min(
                    minDistance,
                    lamps[left] - house
                );
            }

            answer = Math.max(answer, minDistance);
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] houses = {1, 3, 4};
        int[] lamps = {0, 2};

        System.out.println(
            findMinimumRadius(houses, lamps)
        );
    }
}