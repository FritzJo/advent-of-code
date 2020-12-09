package main

import (
	"bufio"
	"log"
	"os"
	"strconv"
)

func readInput(filename string) []int {
	file, err := os.Open(filename)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	var lines []int
	for scanner.Scan() {
		row := scanner.Text()
		i, _ := strconv.Atoi(row)
		lines = append(lines, i)
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	return lines
}

func part1() int {
	numbers := readInput("input.txt")
	windowSize := 25
	result := 0
	for index, _ := range numbers {
		if index > windowSize {
			isPossible := false
			for i := -1 * windowSize; i < 0; i++ {
				for j := -1 * windowSize; j < 0; j++ {
					if numbers[index] == numbers[index+i]+numbers[index+j] && numbers[index+i] != numbers[index+j] {
						isPossible = true
					}
				}
			}
			if !isPossible {
				result = numbers[index]
			}
		}
	}
	return result
}
