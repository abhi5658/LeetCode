func containsDuplicate(nums []int) bool {
  var entries = make(map[int]int)
  for _, num := range nums {
    _, exists := entries[num]
    if exists {
      return true
    }
    entries[num] = 1
  }
  return false
}