# Advent of Code 2020
# Day 1 - Puzzle: 1

def read_file(filename):
    with open(filename) as f:
        lines = f.readlines()
        return lines

def test_data():
    expenses = """1721
    979
    366
    299
    675
    1456"""
    return expenses.splitlines()

def find_2020_expenses(data):
    for x in data:
        for y in data:
            #print()
            x = int(x)
            y = int(y)
            if x + y == 2020:
                return x * y
    return 0

# data = test_data()
data = read_file('Input_1.txt')
print(find_2020_expenses(data))

