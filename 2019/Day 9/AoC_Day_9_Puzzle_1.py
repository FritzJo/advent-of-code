# Advent of Code 2019
# Day 9 - Puzzle: 1

# Intcode Interpreter
from collections import defaultdict

def instruction_length(opcode):
	lengths = {
		1: 4,	# ADD
		2: 4,	# MUL
		3: 2,	# SAV
		4: 2,	# PRT
		5: 3,	# JIT 1
		6: 3,	# JIF 0 
		7: 4,	# LESS
		8: 4,	# EQL
		9: 2,	# OFF
		99: 0
	}
	return lengths.get(opcode)

class State:
	def __init__(self, memory, inputs=[], finished=False, pointer=0, input_index=0, offset=0):
		self.memory = defaultdict(int)
		for v, i in enumerate(memory):
			self.memory[v] = int(i)
			
		self.inputs = inputs.copy()
		self.finished = finished
		self.pointer = pointer
		self.input_index = input_index
		self.offset = offset

class IntCodeInterpreter:
	def __init__(self, state):
		self.state = state
		self.memory = state.memory
		self.inputs = state.inputs
		self.pointer = state.pointer
		self.input_index = state.input_index
		self.offset = state.offset
		self.output = 0
		
	# Thx reddit!
	def get_param(self, param_num):
		imode = self.memory[self.pointer] // (10 * 10 ** param_num)
		val = self.memory[self.pointer + param_num]
		if imode % 10 == 0:
			return self.memory[val]
		if imode % 10 == 1:
			return val
		if imode % 10 == 2:
			return self.memory[val + self.offset]

	# Thx reddit!
	def set_param(self, param_num, set_to):
		imode = self.memory[self.pointer] // (10 * 10 ** param_num)
		val = self.memory[self.pointer + param_num]
		if imode % 10 == 0:
			self.memory[val] = set_to
		elif imode % 10 == 2:
			self.memory[val + self.offset] = set_to

	def mainLoop(self):
		while True:
			instruction = self.memory[self.pointer]
			opcode = int(str(instruction)[-2:])
			length = instruction_length(opcode)
			#print("OPCODE: " + str(opcode) + " : " + str(list(self.memory)[self.pointer+1 : self.pointer+length]) + "; Modes: " + str(instruction)[:-2])
			if opcode == 99:
				return self.output, State(self.memory, self.inputs, True, self.pointer, self.input_index, self.offset)
			
			elif opcode == 1:
				self.set_param(3, self.get_param(1) + self.get_param(2))
			
			elif opcode == 2:
				self.set_param(3, self.get_param(1) * self.get_param(2))
			
			elif opcode == 3:
				self.set_param(1, self.inputs[0])
				if self.input_index >= len(self.inputs):
					return self.output, State(self.memory, self.inputs, False, self.pointer, self.input_index, self.offset)
				else:
					self.set_param(1, self.inputs[self.input_index])
					self.input_index += 1
				
			elif opcode == 4:
				self.output = self.get_param(1)
				print(self.output)
			
			elif opcode == 5:
				if self.get_param(1) != 0:
					self.pointer = self.get_param(2)
					length = 0
					
			elif opcode == 6:
				if self.get_param(1) == 0:
					self.pointer = self.get_param(2)
					length = 0
			
			elif opcode == 7:
				if self.get_param(1) < self.get_param(2):
					self.set_param(3, 1)
				else:
					self.set_param(3, 0)
					
			elif opcode == 8:
				if self.get_param(1) == self.get_param(2):
					self.set_param(3, 1)
				else:
					self.set_param(3, 0)
		
			elif opcode == 9:
				# Took me 10 hours to find out that "self.offset += self.get_param(1)" isn't the same as "self.offset = self.get_param(1)"...
				self.offset += self.get_param(1)
			else:
				print("Invalid opcode: " + opcode)
				return self.output, State(self.memory, self.inputs, False, self.pointer, self.input_index, self.offset)
				
			self.pointer += length
			
if __name__ == "__main__":
	with open("AoC_Day_9_Input.txt") as f:
		intcode = f.readline()
		#intcode = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99" # Wrong
		#intcode = "1102,34915192,34915192,7,4,7,99,0" # Works
		#intcode = "104,1125899906842624,99" # Fails
		#intcode = "109,-1,4,1,99" #] outputs -1 x
		#intcode = "109,-1,104,1,99" #] outputs 1 x
		#intcode = "109,-1,204,1,99" #] outputs 109 x
		#intcode = "109,1,3,3,204,2,99" #] outputs the input x
		#intcode = "109,1,203,2,204,2,99" #] outputs the input x
		## intcode = "109,1,9, 2,204,-6,99" #] outputs 204
		## intcode = "109,1,109,9,204,-6,99" #] outputs 204
		#intcode = "109,1,209,-1,204,-106,99" #] outputs 204
		
		array = intcode.split(",")
		i = IntCodeInterpreter(State(array, [1]))
		#output, state = interpret_intcode()
		output, state = i.mainLoop()
		print("Out: " + str(output))