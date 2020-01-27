# Advent of Code 2019
# Day 2 - Puzzle: 2

def parse_intcode(code):
    array = []
    for value in code.split(","):
        array.append(int(value))
    return array


def interpret_intcode(code):
    pointer = 0
    while pointer <= len(code):
        # Read IntCode
        opcode = code[pointer]
        if opcode == 99:
            # print("Code: " + str(opcode))
            return code
        value1 = code[pointer + 1]
        value2 = code[pointer + 2]
        output = code[pointer + 3]
        # print("Code: " +str(opcode) + "; " + str(value1) + "; " + str(value2) + "; " + str(output))

        # Calculation
        if opcode == 1:
            code[output] = code[value1] + code[value2]
        if opcode == 2:
            code[output] = code[value1] * code[value2]

        # Move pointer to next instruction
        pointer += 4
    return code


def restore_state(code, noun, verb):
    code[1] = noun
    code[2] = verb
    return code


def bruteforce_result(target, limit, intcode):
    for x in range(limit):
        for y in range(limit):
            initial_state = intcode.copy()
            restored_state = restore_state(initial_state, x, y)
            result = interpret_intcode(restored_state)[0]
            if result == target:
                print("Valid inputs: " + str(x) + "; " + str(y))
                return 100 * x + y
    print("Can't find solution in range 0-" + str(limit))


if __name__ == "__main__":
    with open("AoC_Day_2_Input.txt") as f:
        intcode = f.readline()
    array = parse_intcode(intcode)
    print("Solution: " + str(bruteforce_result(19690720, 100, array)))
