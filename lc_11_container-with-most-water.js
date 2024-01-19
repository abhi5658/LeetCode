/**
 * @param {number[]} height
 * @return {number}
 */
var maxArea = function (height) {
  let maximumArea = 0;
  let pointerA = 0;
  let pointerB = height.length - 1
  while (pointerA < pointerB) {
    const lengthA = height[pointerA]
    const lengthB = height[pointerB]
    const area = Math.min(lengthA, lengthB) * (pointerB - pointerA)
    if (area > maximumArea) {
      maximumArea = area
    }
    if (lengthA < lengthB) {
      pointerA++
    } else {
      pointerB--
    }
  }
  return maximumArea;
};

//   console.log(maxArea([1, 1]));