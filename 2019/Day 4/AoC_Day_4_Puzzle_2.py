# Advent of Code 2019
# Day 4 - Puzzle: 2

from itertools import groupby


def is_valid(password):
    password = str(password)

    # Check if all numbers are in order
    if password != ''.join(sorted(password)):
        return False

    # Split the password into char groups
    groups = (["".join(g) for k, g in groupby(password)])

    # Map groups to their char count
    group_lengths = list(map(lambda x: len(x), groups))

    # Check if at least one group contains 2 chars
    return group_lengths.count(2) >= 1


def check_range(start, end):
    valid_passwords = []
    for password in range(start, end):
        if is_valid(password):
            valid_passwords.append(password)
    return valid_passwords


if __name__ == "__main__":
    range_start = 0
    range_end = 0
    passwords = check_range(range_start, range_end)
    print("Number of valid passwords: " + str(len(passwords)))
