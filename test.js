/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
  let profit = 0, //
    buy = prices[0]; //
  for (let i = 1; i < prices.length; i++) {
    const previousPrice = prices[i - 1]; //
    const currentPrice = prices[i]; //
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
// var maxProfit = function (prices) {
//   // let diff = 0
//   let maxProfit = 0
//   for (let i = 0; i < prices.length - 1; i++) {
//     if (prices[i + 1] - prices[i] > 0) {
//       maxProfit += (prices[i + 1] - prices[i]);
//     }
//   }
//   return maxProfit;

// };

console.log(maxProfit([7, 6, 4, 3, 1]));