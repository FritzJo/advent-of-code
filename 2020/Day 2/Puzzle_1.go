package main

import (
	"bufio"
	"log"
	"os"
	"regexp"
	"strconv"
	"strings"
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
		lines = append(lines, scanner.Text())
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	return lines
}

func isValidPassword(passwordLine string) bool {
	passwordSplit := strings.Split(passwordLine, " ")

	// Split limits again
	limits := passwordSplit[0]
	min, err := strconv.Atoi(strings.Split(limits, "-")[0])
	max, err := strconv.Atoi(strings.Split(limits, "-")[1])

	char := string(passwordSplit[1][0])
	passwd := passwordSplit[2]

	if err != nil {
		log.Fatal(err)
	}

	// Check if password is valid with regex
	r := regexp.MustCompile(char)
	matches := r.FindAllStringIndex(passwd, -1)
	return (len(matches) >= min && len(matches) <= max)
}

func part1() int {
	lines := readInput("input.txt")
	passwordCounter := 0
	for _, element1 := range lines {
		if isValidPassword(element1) {
			passwordCounter++
		}
	}
	return passwordCounter
}
