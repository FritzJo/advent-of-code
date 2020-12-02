package main

import (
	"log"
	"strconv"
	"strings"
)

func isValidPasswordPart2(passwordLine string) bool {
	passwordSplit := strings.Split(passwordLine, " ")

	// Split limits again
	limits := passwordSplit[0]
	pos1, err := strconv.Atoi(strings.Split(limits, "-")[0])
	pos2, err := strconv.Atoi(strings.Split(limits, "-")[1])

	char := string(passwordSplit[1][0])
	passwd := passwordSplit[2]

	if err != nil {
		log.Fatal(err)
	}
	match1 := (string(passwd[pos1-1]) == char)
	match2 := (string(passwd[pos2-1]) == char)
	return ((match1 || match2) && !(match1 && match2))

}

func part2() int {
	lines := readInput("input.txt")
	passwordCounter := 0
	for _, element1 := range lines {
		if isValidPasswordPart2(element1) {
			passwordCounter++
		}
	}
	return passwordCounter
}
