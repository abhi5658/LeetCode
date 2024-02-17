class Solution {
  public int maxProfit(int[] prices) {
    int buy = 0;
    int sell = 1;
    int profit = 0;
    while (sell < prices.length) {
      if (prices[buy] < prices[sell]) {
        int currentProfit = prices[sell] - prices[buy];
        profit = Math.max(currentProfit, profit);
      } else { // buy > sell - found a new minimum
        buy = sell;
      }
      sell++;
    }
    return profit;
  }
}