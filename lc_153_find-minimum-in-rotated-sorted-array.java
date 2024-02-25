class Solution {
  public int findMin(int[] nums) {
    int left = 0; //
    int right = nums.length - 1;
    int mid = -1;
    while (left < right) {
      // in fully sorted portion or whole array sorted
      // L[1,2,3,4,5]R
      // OR [5,6, L[1,2,3,4]R ]
      if (nums[left] < nums[right]) {
        right = left;
        break;
      }

      // this way because left + right maybe bigger than int range
      mid = left + (right - left) / 2;

      // mid is inside left sorted array
      // [ L[ {4,5,6,M=7},0,1,2]R ]
      if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else { // nums[mid] < right
        // mid is inside right sorted array
        // [ 4,5,6, L[7,0,M=1,2]R ]
        right = mid;
      }
    }
    return nums[left];
  }
}

class Solution {
  public int findMin(int[] nums) {
    int left = 0; //
    int right = nums.length - 1;
    int mid = -1;
    int minimum = nums[0];
    while (left <= right) {
      if (nums[left] < nums[right]) { // fully sorted
        // right = left;
        minimum = Math.min(minimum, nums[left]);
        break;
      }

      mid = (left + right) / 2;
      minimum = Math.min(minimum, nums[mid]);

      // nums[left] > nums[right])
      if (nums[left] <= nums[mid]) { // left sorted array [4...7] 0..2
        left = mid + 1; // as mid already considered above
      } else {
        right = mid - 1;
      }
    }
    return minimum;
  }
}

class Solution {
  //
  // 0 1 2 3 4 5 6
  // 4,5,6,7,0,1,2 | l=0,m=3,r=6
  // l=4 m=7 r=2 l<m l->m increasing from left l=m| l=3 m=4 r=6
  // l=7 m=0 r=2 m<r desc from right r=m | l=3 m=3 r=4
  // l=7 m=7 r=0
  //
  // 0 1 2 3 4
  // 3,4,5,1,2 | l=0 m=2 r=4
  // l=3 m=5 r=2 | l<m | l=m | l=2 m=3 r=4
  // l=5 m=1 r=2 | m<r | r=m | l=2 m=2 r=3
  // l=5 m=5 r=1 | l >=m | l=m+1 | l=3 m=3 r=3
  //
  // 0 1 2 3
  // 11,13,15,17 | l=0 m=1 r=3
  // l=11 m=13 r=17 |
  public int findMin(int[] nums) {
    int left = 0; //
    int right = nums.length - 1;
    int mid = -1;
    while (left < right) {
      mid = (left + right) / 2;
      if (nums[left] > nums[right]) {
        if (nums[left] < nums[mid]) {
          left = mid;
        } else if (nums[mid] < nums[right]) {
          right = mid;
        } else if (nums[mid] > nums[right]) {
          left = mid + 1;
        } else {
          right = mid;
        }
      } else {
        right = left;
      }
    }
    return nums[(left + right) / 2];
  }
}