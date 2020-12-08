package main

import (
	"strings"
)

func fixInstructions(instructions []string) int {
	for index, instruction := range instructions {
		operation := strings.Split(instruction, " ")[0]
		argument := strings.Split(instruction, " ")[1]

		//switch instruction
		switch operation {
		case "nop":
			instructions[index] = "jmp " + argument
		case "jmp":
			instructions[index] = "nop " + argument
		default:

		}

		//execute new code
		accumulator := 0
		interpreter := newInterpreter(instructions, accumulator)

		if interpreter.executeCode(true) {
			return interpreter.accumulator
		}
		//switch back
		instructions[index] = operation + " " + argument
	}
	return 0
}

func part2() int {
	instructions := readInput("input.txt")
	return fixInstructions(instructions)
}
