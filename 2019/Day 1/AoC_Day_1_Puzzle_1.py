# Advent of Code 2019
# Day 1 - Puzzle: 1

def calculate_fuel(mass):
	return int(mass / 3) - 2
	
def read_input(filename):
	file = open(filename)
	values = []
	for line in file:
		values.append(int(line))
	return values
	
def calculate_total_fuel(masses):
	sum = 0
	for mass in masses:
		sum += calculate_fuel(mass)
	return sum
	
if __name__ == "__main__":
	masses = read_input("AoC_Day_1_Input.txt")
	total_fuel = calculate_total_fuel(masses)
	print(total_fuel)