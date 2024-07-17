func twoSum(nums []int, target int) []int {
	var dict = make(map[int]int)
	for index, num := range nums {
		if loc, exists := dict[target-num]; exists {
			return []int{loc, index}
		}
		dict[num] = index
	}
  return []int{0,0}
}