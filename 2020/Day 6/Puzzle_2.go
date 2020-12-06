package main

import (
	"strings"
)

func intersect(s1, s2 map[string]bool) map[string]bool {
	s_intersection := map[string]bool{}
	for k, _ := range s1 {
		if s2[k] {
			s_intersection[k] = true
		}
	}
	return s_intersection
}

func parseGroups2(input string) int {
	sum := 0
	groups := strings.Split(input, "\n\n")
	for _, group := range groups {
		answers_group := map[string]bool{}
		persons := strings.Split(group, "\n")
		for personIndex, person := range persons {
			answers_person := map[string]bool{}
			// First person in group defines the max set
			if personIndex == 0 {
				for _, answer := range person {
					answers_group[string(answer)] = true
				}
			} else {
				for _, answer := range person {
					answers_person[string(answer)] = true
				}
				answers_group = intersect(answers_group, answers_person)
			}
		}
		sum += len(answers_group)
	}
	return sum
}

func part2() int {
	lines := readInput("input.txt")
	return parseGroups2(lines)
}
