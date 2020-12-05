package main

func compare(X, Y []int) []int {
	m := make(map[int]int)

	for _, y := range Y {
		m[y]++
	}

	var ret []int
	for _, x := range X {
		if m[x] > 0 {
			m[x]--
			continue
		}
		ret = append(ret, x)
	}

	return ret
}

func getAllPossibleIDs() []int {
	result := []int{}
	for i := 0; i < 128; i++ {
		for j := 0; j < 8; j++ {
			result = append(result, i*8+j)
		}
	}
	return result
}

func part2() int {
	lines := readInput("input.txt")
	existingIDs := []int{}
	possibleIDs := getAllPossibleIDs()
	for _, line := range lines {
		id := getSeatID(line)
		existingIDs = append(existingIDs, id)
	}

	intersection := compare(possibleIDs, existingIDs)

	for index, _ := range intersection {
		if index > 0 && len(intersection)-index > 1 {
			if intersection[index+1]-intersection[index] > 1 && intersection[index]-intersection[index-1] > 1 {
				return intersection[index]
			}
		}
	}
	return 0
}
