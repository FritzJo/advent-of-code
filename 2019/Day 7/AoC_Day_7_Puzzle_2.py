# Advent of Code 2019
# Day 7 - Puzzle: 2

from itertools import permutations


class State:
    def __init__(self, memory, inputs, finished, pointer, input_index):
        self.memory = memory.copy()
        self.inputs = inputs.copy()
        self.finished = finished
        self.pointer = pointer
        self.input_index = input_index


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


def interpret_intcode(state):
    output = 0
    code = state.memory
    input = state.inputs
    pointer = state.pointer
    input_index = state.input_index

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
            return output, State(code, input, True, pointer, input_index)

        if opcode == "01":
            code[target_address] = str(parameters[0] + parameters[1])

        elif opcode == "02":
            code[target_address] = str(parameters[0] * parameters[1])

        elif opcode == "03":
            if input_index >= len(input):
                return output, State(code, input, False, pointer, input_index)
            else:
                code[target_address] = str(input[input_index])
                # print(input[input_index])
                input_index += 1

        elif opcode == "04":
            output = code[target_address]

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
            return output, State(code, input, True, pointer, input_index)

        # Move pointer
        pointer += length
    return output, State(code, input, True, pointer, input_index)


if __name__ == "__main__":
    with open("AoC_Day_7_Input.txt") as f:
        intcode = f.readline()
        # intcode = "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10"
        array = intcode.split(",")

        base_config = "56789"
        perms = [''.join(p) for p in permutations(base_config)]
        thruster_signals = []
        for config in perms:
            # Initialize states
            pointer = 0
            input_index = 0
            interpreters = []
            for i in config:
                interpreters.append(State(array, [int(i)], False, pointer, input_index))

            i = 0
            output = 0
            state = State([], [0], False, 0, 0)

            # Loop until amp_e finishes
            while not interpreters[-1].finished:
                i = i % len(config)
                interpreters[i].inputs.append(output)
                output, state = interpret_intcode(interpreters[i])
                interpreters[i] = state
                i += 1
            thruster_signals.append(output)
    print(max(thruster_signals))
