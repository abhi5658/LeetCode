/**
 * @param {number[][]} intervals
 * @return {number[][]}
 */
var merge = function (intervals) {
  const output = [];
  intervals.sort((a, b) => a[0] - b[0]);
  let previousStartTime = intervals[0][0];
  let previousEndTime = intervals[0][1];

  for (let i = 1; i < intervals.length; i++) {
    const currentInterval = intervals[i];
    if (currentInterval[0] - previousEndTime > 0) {
      output.push([previousStartTime, previousEndTime]);
      previousStartTime = currentInterval[0];
      previousEndTime = currentInterval[1];
    } else {
      previousEndTime = Math.max(previousEndTime, currentInterval[1]);
    }
  }
  output.push([previousStartTime, previousEndTime]);
  return output;
};