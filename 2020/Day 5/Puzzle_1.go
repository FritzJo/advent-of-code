package main

import (
	"bufio"
	"log"
	"os"
)

func readInput(filename string) []string {
	file, err := os.Open(filename)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	var lines []string
	for scanner.Scan() {
		row := scanner.Text()
		lines = append(lines, row)
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	return lines
}

func getNumber(input string, max int) int {
	min := 0
	for _, value := range input {
		current_range := max - min
		if string(value) == "F" || string(value) == "L" {
			max = min + int(current_range/2)
		} else if string(value) == "B" || string(value) == "R" {
			min = max - int(current_range/2)
		}
	}
	return min
}

func getSeatID(input string) int {
	rowNumber := getNumber(input[0:len(input)-3], 127)
	colNumber := getNumber(input[len(input)-3:len(input)], 7)
	return rowNumber*8 + colNumber
}

func part1() int {
	lines := readInput("input.txt")
	max := 0
	for _, line := range lines {
		id := getSeatID(line)
		if id > max {
			max = id
		}
	}
	return max
}
