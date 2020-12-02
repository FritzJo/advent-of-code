package main

import (
	"bufio"
	"log"
	"os"
	"strconv"
)

func readExpenses(filename string) []int {
	file, err := os.Open(filename)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	var lines []int
	for scanner.Scan() {
		i, err := strconv.Atoi(scanner.Text())
		if err != nil {
			log.Fatal(err)
		}
		lines = append(lines, i)
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	return lines
}

func part1() int {
	lines := readExpenses("input.txt")
	for _, element1 := range lines {
		for _, element2 := range lines {
			if element1+element2 == 2020 {
				return element1 * element2
			}
		}
	}
	return 0
}
