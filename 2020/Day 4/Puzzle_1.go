package main

import (
	"bufio"
	"log"
	"os"
	"strings"
)

type Passport map[string]string

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

func parsePassports(lines []string) []Passport {
	var passports []Passport
	var current_passport Passport
	current_passport = make(Passport)

	for _, element := range lines {
		if element == "" {
			passports = append(passports, current_passport)
			current_passport = make(Passport)
		} else {
			split_line := strings.Split(element, " ")
			for _, key := range split_line {
				split_key := strings.Split(key, ":")
				current_passport[split_key[0]] = split_key[1]
			}
		}
	}
	// Dont forget to append the last passport.... (took me 2h to find this bug)
	passports = append(passports, current_passport)
	return passports
}

func isValidPassport(p Passport) bool {
	requiredFields := [7]string{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"}

	for _, requiredField := range requiredFields {
		if p[requiredField] == "" {
			return false
		}
	}
	return true
}

func part1() int {
	lines := readInput("input.txt")
	passports := parsePassports(lines)
	counter := 0
	for _, passport := range passports {
		if isValidPassport(passport) {
			counter++
		}
	}
	return counter
}
