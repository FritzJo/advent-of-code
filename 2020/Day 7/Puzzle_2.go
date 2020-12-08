package main

func calculateInside(rules []rule, initial string) int {
	result := 1

	if initial == "otherbags" {
		return 0
	}

	for _, rule := range rules {
		if rule.outside == initial {
			for index, inside := range rule.inside {
				if rule.insideCount[index] != 0 {
					result = result + calculateInside(rules, inside)*rule.insideCount[index]
				}
			}
			return result
		}
	}
	return 0
}

func part2() int {
	lines := readInput("input.txt")
	rules := []rule{}
	for _, line := range lines {
		rules = append(rules, parseRule(line))
	}

	return calculateInside(rules, "shinygold") - 1 //dont know why i'm off by 1
}
