# Expects String array
def instruction_length(opcode):
	lengths = {
		"01": 4,
		"02": 4,
		"03": 2,
		"04": 2,
		"99": 0
	}
	return lengths.get(opcode, 9999999)

def interpret_intcode(code, input):
	pointer = 0
	while pointer <= len(code):
		# Parse instruction
		instruction = code[pointer]
		opcode = instruction[-2:]
		if len(opcode) < 2:
			opcode = "0" + opcode
		length = instruction_length(opcode)
		parameters = code[pointer + 1 : pointer + length]
		modes = instruction[:-2]
		
		# Fill missing modes with 0
		while len(modes) < len(parameters):
			modes = "0" + modes
		modes = [m for m in modes]
		modes.reverse()
		
		# Resolve parameters
		for index, (parameter, mode) in enumerate(zip(parameters, modes)):
			if mode == "0" and index != len(parameters) - 1:
				parameters[index] = code[int(parameter)]
		parameters = [int(x) for x in parameters]
		#print(opcode + ": " + str(parameters))
		
		# Exit if opcode is 99
		if opcode == "99":
			return code
		
		if opcode == "01":
			code[parameters[2]] = str(parameters[0] + parameters[1])

		elif opcode == "02":
			code[parameters[2]] = str(parameters[0] * parameters[1])
		
		elif opcode == "03":
			code[parameters[0]] = str(input)
			
		elif opcode == "04":
			print(code[parameters[0]])
		
		else:
			print("Invalid opcode: " + opcode)
			return code
			
		# Move pointer
		pointer += length
	return code
	
if __name__ == "__main__":
	with open("AoC_Day_5_Input.txt") as f:
		intcode = f.readline()
		array = intcode.split(",")
		print(interpret_intcode(array, 1))