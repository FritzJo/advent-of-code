package main

import (
	"fmt"
	"strconv"
	"strings"
)

type Instruction struct {
	operation string
	argument  int
}

type Interpreter struct {
	accumulator int
	code        []Instruction
}

func newInterpreter(code []string, accumulator int) *Interpreter {
	i := Interpreter{accumulator: accumulator}
	for _, instruction := range code {
		split := strings.Split(instruction, " ")
		arg, _ := strconv.Atoi(split[1])
		inst := Instruction{operation: split[0], argument: arg}
		i.code = append(i.code, inst)
	}
	return &i
}

// returns difference to next pointer
func (i *Interpreter) executeInstruction(instruction Instruction) int {
	switch instruction.operation {
	case "nop":
		return 1
	case "acc":
		i.accumulator += instruction.argument
		return 1
	case "jmp":
		return instruction.argument
	default:
		fmt.Println("Invalid instruction!")
		return -999
	}
}

func (inter *Interpreter) executeCode(findLoop bool) bool {
	executedInstructions := map[int]bool{}
	for i := 0; i < len(inter.code); {
		if findLoop {
			if executedInstructions[i] {
				//fmt.Printf("Loop! Accumulator value = %d\n", inter.accumulator)
				return false
			} else {
				executedInstructions[i] = true
			}
		}
		//fmt.Printf("-> %s %d\n", inter.code[i].operation, inter.code[i].argument)
		i += inter.executeInstruction(inter.code[i])
	}
	fmt.Printf("Done: Accumulator value = %d\n", inter.accumulator)
	return true
}
