# Advent of Code 2019
# Day 1 - Puzzle: 2

def calculate_fuel(mass):
	return int(mass / 3) - 2
	
def read_input(filename):
	file = open(filename)
	values = []
	for line in file:
		values.append(int(line))
	return values
	
def calculate_additional_fuel(fuel):
	new_fuel = calculate_fuel(fuel)
	sum = 0
	while new_fuel > 0:
		sum += new_fuel
		new_fuel = calculate_fuel(new_fuel)
	return sum
	
def calculate_total_fuel(masses):
	sum = 0
	for mass in masses:
		required_fuel = calculate_fuel(mass)
		required_fuel += calculate_additional_fuel(required_fuel)
		sum += required_fuel
	return sum
	
if __name__ == "__main__":
	masses = read_input("AoC_Day_1_Input.txt")
	total_fuel = calculate_total_fuel(masses)
	print(total_fuel)