func isPalindrome(s string) bool {
	s = strings.ToUpper(s)
	i := 0
	j := len(s) - 1
	for k := 0; k < len(s); k++ {
		if i > j {
			return true
		}
		ic := rune(s[i])
		fmt.Println(fmt.Sprintf("ic %d %v %c", i, ic, s[i]))
		if !(ic >= 'a' && ic <= 'z' || ic >= 'A' && ic <= 'Z' || ic >= '0' && ic <= '9' ) {
			i++
			fmt.Println("ic continue")
			continue
		}
		jc := rune(s[j])
		fmt.Println(fmt.Sprintf("jc %d %v %c", j, jc, s[j]))
		if !(jc >= 'a' && jc <= 'z' || jc >= 'A' && jc <= 'Z' || jc >= '0' && jc <= '9') {
			j--
			fmt.Println("jc continue")
			continue
		}
		if ic != jc {
			return false
		}
		fmt.Println("match")
		i++
		j--
		continue
	}
	return true
}
