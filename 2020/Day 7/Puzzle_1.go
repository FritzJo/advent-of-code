package main

import (
	"bufio"
	"log"
	"os"
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
		row := scanner.Text()
		lines = append(lines, row)
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	return lines
}

type rule struct {
	outside     string
	inside      []string
	insideCount []int
}

func parseRule(input string) rule {
	tokens := strings.Split(input, " ")
	outside := string(tokens[0]) + string(tokens[1])
	inside := []string{}
	insideCount := []int{}
	insideList := tokens[4:len(tokens)]
	insideString := strings.Join(insideList, " ")
	insideTokens := strings.Split(insideString, ",")
	for _, insideToken := range insideTokens {

		// Remove whitespace prefix
		if strings.HasPrefix(insideToken, " ") {
			insideToken = insideToken[1:len(insideToken)]
		}
		if strings.HasSuffix(insideToken, ".") {
			insideToken = insideToken[0 : len(insideToken)-1]
		}
		//fmt.Println("\t" + insideToken)
		bag := strings.Split(insideToken, " ")

		inside = append(inside, string(bag[1])+string(bag[2]))
		i, _ := strconv.Atoi(bag[0])
		insideCount = append(insideCount, i)
	}
	return rule{outside, inside, insideCount}
}

func canContain(rules []rule, initial string, target string) bool {
	if initial == "otherbags" {
		return false
	}

	if initial == target {
		return true
	}

	for _, rule := range rules {
		if rule.outside == initial {
			for _, inside := range rule.inside {
				result := canContain(rules, inside, target)
				if result {
					return result
				}
			}
			return false
		}
	}
	return false
}

func part1() int {
	lines := readInput("input.txt")
	rules := []rule{}
	for _, line := range lines {
		rules = append(rules, parseRule(line))

	}

	counter := 0
	for _, rule := range rules {
		if rule.outside != "shinygold" && canContain(rules, rule.outside, "shinygold") {
			//fmt.Println(rule.outside)
			counter++
		}
	}
	return counter
}
