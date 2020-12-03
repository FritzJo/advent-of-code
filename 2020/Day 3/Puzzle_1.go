package main

import (
	"bufio"
	"log"
	"os"
)

func readInput(filename string) [][]string {
	file, err := os.Open(filename)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	var lines [][]string
	for scanner.Scan() {
		row := scanner.Text()
		var rowSlice []string
		for _, field := range row {
			rowSlice = append(rowSlice, string(field))
		}
		lines = append(lines, rowSlice)
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	return lines
}

func calculatePath(pattern [][]string, down int, right int) int {
	i := 0
	j := 0
	trees := 0

	for i < len(pattern) {
		if pattern[i][j] == "#" {
			trees++
		}
		i = i + down
		j = (j + right) % len(pattern[0])
	}
	return trees
}

func part1() int {
	pattern := readInput("input.txt")
	return calculatePath(pattern, 1, 3)
}
