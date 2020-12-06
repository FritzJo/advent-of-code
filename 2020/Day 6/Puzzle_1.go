package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func readInput(filename string) string {
	// Read whole file at once, makes splitting easier than day 4
	lines, err := ioutil.ReadFile(filename)
	if err != nil {
		fmt.Print(err)
	}
	return string(lines)
}

func parseGroups(input string) int {
	sum := 0
	groups := strings.Split(input, "\n\n")
	for _, group := range groups {
		answers := map[string]bool{}
		persons := strings.Split(group, "\n")
		for _, person := range persons {
			for _, answer := range person {
				answers[string(answer)] = true
			}
		}
		sum += len(answers)
	}
	return sum
}

func part1() int {
	lines := readInput("input.txt")
	return parseGroups(lines)
}
