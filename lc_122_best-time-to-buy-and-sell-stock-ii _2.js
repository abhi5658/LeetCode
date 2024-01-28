/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
  let profit = 0, // 1
    unrealisedProfit = 0, // 1
    buy = prices[0]; // 0
  for (let i = 1; i < prices.length; i++) {
    const previousPrice = prices[i - 1]; // 0
    const currentPrice = prices[i]; // 1
    if (previousPrice > currentPrice) { // p > c // peak gone
      profit += unrealisedProfit;
      buy = currentPrice;
      unrealisedProfit = 0;
    } else if (currentPrice < buy) {  // c < b - decreasing line
      buy = currentPrice;
    } else if (previousPrice < currentPrice) { // c > b,p // increasing line
      unrealisedProfit = currentPrice - buy;
    }
  }
  return profit + unrealisedProfit
};