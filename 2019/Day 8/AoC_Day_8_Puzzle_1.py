# Advent of Code 2019
# Day 8 - Puzzle: 1

def layer_length(w, h):
    return int(w * h)


def count_char(layer, digit):
    return layer.count(digit)


if __name__ == "__main__":
    with open("AoC_Day_8_Input.txt") as f:
        imgcode = f.readline()

    x = layer_length(25, 6)
    layers = [imgcode[y - x:y] for y in range(x, len(imgcode) + x, x)]
    c_layers = []
    for layer in layers:
        zeros = count_char(layer, "0")
        c_layers.append([zeros, layer])
    c_layers.sort(key=lambda x: x[0])

    ones = count_char(c_layers[0][1], "1")
    twos = count_char(c_layers[0][1], "2")
    print(ones * twos)
