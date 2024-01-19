/**
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var kthFactor = function (n, k) {
  if (k === 1) {
    return 1;
  }
  let count = 1;
  for (let i = 2; i <= n; i++) {
    if (n % i === 0) {
      count += 1;
      if (count === k) {
        return i;
      }
    }
  }
  return -1;
};
