# Advent of Code 2019
# Day 6 - Puzzle: 1

def create_orbit_map(list_of_orbits):
    orbit_map = {'COM': 0}
    # orbit_map = collections.defaultdict(set)
    while len(list_of_orbits) > 0:
        for orbit in list_of_orbits:
            inner = orbit.split(")")[0].strip()
            outer = orbit.split(")")[1].strip()
            if inner in orbit_map:
                orbit_map[outer] = orbit_map[inner] + 1
                list_of_orbits.remove(orbit)
    return orbit_map


def checksum(x):
    return sum(x.values())


if __name__ == "__main__":
    f = open("AoC_Day_6_Input.txt")
    orbits = f.readlines()
    list_of_orbits = create_orbit_map(orbits)
    print(checksum(list_of_orbits))
