# Advent of Code 2019
# Day 3 - Puzzle: 2

def parse_direction(direction):
    dir = {  # x,  y
        "R": [1, 0],
        "L": [-1, 0],
        "D": [0, -1],
        "U": [0, 1]
    }
    return dir[direction]


def create_paths(path_string):
    x = 0
    y = 0
    path = []
    split = path_string.split(",")
    for movement in split:
        direction = parse_direction(movement[0])
        steps = int(movement[1:])
        for _ in range(steps):
            x += direction[0]
            y += direction[1]
            path.append((x, y))
    return path


def find_intersections(path1, path2):
    intersections = set(path1) & set(path2)
    return intersections


def calculate_distance(point, start=[0, 0]):
    return abs(point[0]) - abs(start[0]) + abs(point[1]) - abs(start[1])


def wire_delay(wire_path, point):
    return wire_path.index(point) + 1


if __name__ == "__main__":
    # Read input
    file = open("AoC_Day_3_Input.txt")
    values = []
    for line in file:
        values.append(line)
    wire1 = values[0]
    wire2 = values[1]

    # Calculate paths and intersections
    path1 = create_paths(wire1)
    path2 = create_paths(wire2)

    intersections = find_intersections(path1, path2)

    # Find closest intersection
    min_dist = 100000
    min_dist_intersec = []
    for intersection in intersections:
        distance = wire_delay(path1, intersection) + wire_delay(path2, intersection)
        if distance < min_dist:
            min_dist = distance
            min_dist_intersec = intersection
    print("Wire delay: " + str(min_dist))
    print("Intersection: " + str(min_dist_intersec))
