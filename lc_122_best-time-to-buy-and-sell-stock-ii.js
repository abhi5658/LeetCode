/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) { // Feb 18, 2021 18:24
  // let diff = 0
  let maxProfit = 0
  for (let i = 0; i < prices.length - 1; i++) {
    if (prices[i + 1] - prices[i] > 0) {
      maxProfit += (prices[i + 1] - prices[i]);
    }
  }
  return maxProfit;

};