func isAnagram(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}
	var source = make(map[rune]int)
	for _, c := range s {
		if count, exists := source[c]; exists {
			source[c] = count + 1
		} else {
			source[c] = count + 1
		}
	}
	for _, c := range t {
		if count, exists := source[c]; exists {
			if count == 1 { // count to be removed and char remove from map
				delete(source, c)
				continue
			}
			source[c] = count - 1
		} else { // not found
			return false
		}
	}
	if len(source) > 0 {
		return false
	}

	return true
}

func isAnagram(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}

	var counts [26]int
	for i := 0; i < len(s); i++ {
		counts[s[i]-'a']++
		counts[t[i]-'a']--
	}
	for _, val := range counts {
		if val != 0 {
			return false
		}
	}
	return true
}

func isAnagram(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}

	countMap := make(map[rune]int)
	// for i := 0; i < len(s); i++ {
	// 	c := rune(s[i])
	// 	if count, ok := countMap[c]; ok {
  //     if count == -1 {
  //       delete(countMap,c)
  //     }else{
	// 		countMap[c] = count + 1
  //     }
	// 	} else {
	// 		countMap[c] = 1
	// 	}

	// 	c = rune(t[i])
	// 	if count, ok := countMap[c]; ok {
	// 		if count == 1 {
	// 			delete(countMap, c)
	// 		} else {
	// 			countMap[c] = count - 1
	// 		}
	// 	} else {
	// 		countMap[c] = -1
	// 	}
	// }
	// fmt.Println(fmt.Sprintf("%v", countMap))

	// if len(countMap) == 0 {
	// 	return true
	// }
	// return false

  for _,c := range s {
    countMap[c]++
  }
  for _,c := range t {
    countMap[c]--
  }
  for _, count := range countMap{
    if count != 0{
      return false
    }
  }
  return true


}
