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

func part1() int {
	pointer := 0
	accumulator := 0
	instructions := readInput("input.txt")
	interpreter := newInterpreter(instructions, accumulator)
	interpreter.executeCode(true)
	return pointer
}
