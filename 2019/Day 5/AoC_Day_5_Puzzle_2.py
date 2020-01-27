# Expects String array
def instruction_length(opcode):
    lengths = {
        "01": 4,  # ADD
        "02": 4,  # MUL
        "03": 2,  # SAV
        "04": 2,  # PRT
        "05": 3,  # JIT 1
        "06": 3,  # JIF 0
        "07": 4,  # LESS
        "08": 4,  # EQL
        "99": 0
    }
    return lengths.get(opcode, -1)


def interpret_intcode(code, input):
    pointer = 0
    while pointer <= len(code):
        # Parse instruction
        instruction = code[pointer]
        opcode = instruction[-2:]
        if len(opcode) < 2:
            opcode = "0" + opcode
        length = instruction_length(opcode)
        parameters = code[pointer + 1: pointer + length]
        modes = instruction[:-2]

        # Fill missing modes with 0
        while len(modes) < len(parameters):
            modes = "0" + modes
        modes = [m for m in modes]
        modes.reverse()

        # Resolve parameters
        if len(parameters) > 0:
            target_address = int(parameters[-1])

        for index, (parameter, mode) in enumerate(zip(parameters, modes)):
            if mode == "0":
                parameters[index] = code[int(parameter)]
        parameters = [int(x) for x in parameters]

        # print(opcode + ": " + str(parameters[:-1]) + " --> " + str(target_address))

        # Exit if opcode is 99
        if opcode == "99":
            return code

        if opcode == "01":
            code[target_address] = str(parameters[0] + parameters[1])
        # print("done!")
        elif opcode == "02":
            code[target_address] = str(parameters[0] * parameters[1])

        elif opcode == "03":
            code[target_address] = str(input)

        elif opcode == "04":
            print(code[target_address])

        elif opcode == "05":
            if parameters[0] != 0:
                pointer = int(parameters[1])
                length = 0

        elif opcode == "06":
            if parameters[0] == 0:
                pointer = int(parameters[1])
                length = 0

        elif opcode == "07":
            if parameters[0] < parameters[1]:
                code[target_address] = 1
            else:
                code[target_address] = 0

        elif opcode == "08":
            if parameters[0] == parameters[1]:
                code[target_address] = 1
            else:
                code[target_address] = 0

        else:
            print("Invalid opcode: " + opcode)
            return code

        # Move pointer
        pointer += length
    return code


if __name__ == "__main__":
    with open("AoC_Day_5_Input.txt") as f:
        intcode = f.readline()
        # intcode = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9"
        array = intcode.split(",")
        interpret_intcode(array, 5)
