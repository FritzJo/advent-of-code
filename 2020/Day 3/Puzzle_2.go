package main

func part2() int {
	pattern := readInput("input.txt")
	right := [5]int{1, 3, 5, 7, 1}
	down := [5]int{1, 1, 1, 1, 2}

	result := 1
	for i, _ := range right {
		trees := calculatePath(pattern, down[i], right[i])
		result = result * trees
	}
	return result
}
