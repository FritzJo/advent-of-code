package main

func part2() int {
	numbers := readInput("input.txt")
	targetNumber := part1()
	for index, _ := range numbers {
		sum := 0
		offset := 0
		largest := 0
		smallest := 1000000000000000
		for ; sum < targetNumber; offset++ {
			if numbers[index+offset] > largest {
				largest = numbers[index+offset]
			}
			if numbers[index+offset] < smallest {
				smallest = numbers[index+offset]
			}

			sum += numbers[index+offset]
		}
		if sum == targetNumber {
			return smallest + largest
		}
	}
	return 0
}
