# Advent of Code 2019
# Day 8 - Puzzle: 2
	
def count_char(layer, digit):
	return layer.count(digit)

def render_image(layers):
	final_image = []
	for pixel_index in range(len(layers[0])):
		layer_index = 0
		while layer_index < len(layers):
			if layers[layer_index][pixel_index] == "0":
				final_image.append(" ")
				break
			elif layers[layer_index][pixel_index] == "1":
				final_image.append("X")
				break
			elif layers[layer_index][pixel_index] == "2":
				layer_index += 1
	return final_image
		

if __name__ == "__main__":
	with open("AoC_Day_8_Input.txt") as f:
		imgcode = f.readline()
		
	w = 25
	h = 6
	x = int(w * h)
	layers = [imgcode[y-x:y] for y in range(x, len(imgcode)+x,x)]
	image = render_image(layers)
	
	array = [image[y-w:y] for y in range(w, len(image)+w,w)]
	for line in array:
		print("".join(line))