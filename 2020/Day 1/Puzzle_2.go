package main

func part2() int {
	lines := readExpenses("input.txt")
	for _, element1 := range lines {
		for _, element2 := range lines {
			for _, element3 := range lines {
				if element1+element2+element3 == 2020 {
					return element1 * element2 * element3
				}
			}
		}
	}
	return 0
}
