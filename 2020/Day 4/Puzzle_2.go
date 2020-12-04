package main

import (
	"regexp"
	"strconv"
)

func isValidPassportAdvanced(p Passport) bool {
	requiredFields := [7]string{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"}

	for _, requiredField := range requiredFields {
		if p[requiredField] == "" {
			return false
		}
		switch requiredField {
		case "byr":
			i, _ := strconv.Atoi(p[requiredField])
			if i > 2002 || i < 1920 {
				return false
			}
		case "iyr":
			i, _ := strconv.Atoi(p[requiredField])
			if i > 2020 || i < 2010 {
				return false
			}
		case "eyr":
			i, _ := strconv.Atoi(p[requiredField])

			if i > 2030 || i < 2020 {
				return false
			}
		case "hgt":
			value := p[requiredField]
			unit := string(value[len(value)-2:])
			value = string(value[0 : len(value)-2])
			if unit == "cm" {
				i, _ := strconv.Atoi(value)
				if i > 193 || i < 150 {
					return false
				}
			} else {
				i, _ := strconv.Atoi(value)
				if i > 76 || i < 59 {
					return false
				}
			}
		case "hcl":
			match, _ := regexp.MatchString("^#([0-9]|[a-f]){6}$", p[requiredField])
			if !match {
				return false
			}
		case "ecl":
			match, _ := regexp.MatchString("^(amb|blu|brn|gry|grn|hzl|oth)$", p[requiredField])
			if !match {
				return false
			}
		case "pid":
			match, _ := regexp.MatchString("^[0-9]{9}$", p[requiredField])
			if !match {
				return false
			}
		}
	}
	return true
}

func part2() int {
	lines := readInput("input.txt")
	passports := parsePassports(lines)
	counter := 0
	for _, passport := range passports {
		if isValidPassportAdvanced(passport) {
			counter++
		}
	}
	return counter
}
