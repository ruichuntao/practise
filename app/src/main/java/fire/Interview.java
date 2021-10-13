package fire;

import java.util.HashMap;
import java.util.Map;

public class Interview {

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 1; // 最大值
        dp[0][1] = 1; // 最小值
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] * nums[i - 1], Math.max(nums[i - 1], dp[i - 1][1] * nums[i - 1]));
            dp[i][1] = Math.min(dp[i - 1][1] * nums[i - 1], Math.min(nums[i - 1], dp[i - 1][0] * nums[i - 1]));
            ans = Math.max(ans, dp[i][0]);
        }
        return ans;
    }

    int get(int[] nums, int k) {
        int n = nums.length;
        int cnt = 0;
        int l = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            while (map.size() > k && l < n) {
                int tmp = map.get(nums[l]);
                if (tmp == 1) map.remove(nums[l]);
                else map.put(nums[l], tmp - 1);
                l++;
            }
            cnt += i - l;
        }
        return cnt;
    }

    public int subarraysWithKDistinct(int[] nums, int k) {
        return get(nums, k) - get(nums, k - 1);
    }

    public static void main(String[] args) {
        Interview i = new Interview();
        System.out.println(i.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
    }
}
