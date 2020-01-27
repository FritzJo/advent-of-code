# Advent of Code 2019
# Day 2 - Puzzle: 1

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
            print("Code: " + str(opcode))
            return code
        value1 = code[pointer + 1]
        value2 = code[pointer + 2]
        output = code[pointer + 3]
        print("Code: " + str(opcode) + "; " + str(value1) + "; " + str(value2) + "; " + str(output))

        # Calculation
        if opcode == 1:
            code[output] = code[value1] + code[value2]
        if opcode == 2:
            code[output] = code[value1] * code[value2]

        # Move pointer to next instruction
        pointer += 4
    return code


def restore_state(code):
    code[1] = 12
    code[2] = 2
    return code


if __name__ == "__main__":
    with open("AoC_Day_2_Input.txt") as f:
        intcode = f.readline()
    array = parse_intcode(intcode)
    restored_state = restore_state(array)
    result = interpret_intcode(restored_state)
    # print("Final state: " + str(result))
    print("\nCalculation result: \n" + str(result[0]))
