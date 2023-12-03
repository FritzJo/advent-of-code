<?php 
/*
--- Day 3: Gear Ratios ---
You and the Elf eventually reach a gondola lift station; he says the gondola lift will take you up to the water source, but this is as far as he can bring you. You go inside.
It doesn't take long to find the gondolas, but there seems to be a problem: they're not moving.

"Aaah!"

You turn around to see a slightly-greasy Elf with a wrench and a look of surprise. "Sorry, I wasn't expecting anyone! The gondola lift isn't working right now; it'll still be a while before I can fix it." You offer to help.
The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one. If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.
The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of numbers and symbols you don't really understand, but apparently any number adjacent to a symbol, even diagonally, is a "part number" and should be included in your sum. (Periods (.) do not count as a symbol.)

Here is an example engine schematic:

467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..

In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 114 (top right) and 58 (middle right). Every other number is adjacent to a symbol and so is a part number; their sum is 4361.

Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine schematic?

*/
function isSymbol($value){
    return (!is_numeric($value) && $value != ".");
}

function hasAdjecentSymbol($schematic, $start_x, $start_y) {
    if (!is_numeric($schematic[$start_y][$start_x])){
        return False;
    }
    $upper_border = ($start_y == 0) ? 0: -1;
    $lower_border = ($start_y == count($schematic) -1 ) ? 0: 1;
    $left_border  = ($start_x == 0) ? 0: -1;
    $right_border = ($start_x == count($schematic[$start_y])-1) ? 0: 1;

    // Expand bounding box to the right
    while (is_numeric($schematic[$start_y][$start_x + $right_border]) && $start_x + $right_border < count($schematic[$start_y])-1){
        $right_border += 1;
    }

    for ($i = $upper_border; $i <= $lower_border; $i++){
        for ($j = $left_border; $j <= $right_border; $j++){
            if (isSymbol($schematic[$start_y + $i][$start_x + $j])){
                return True;
            }
        }   
    }
    return False;
}

function getNumber($schematic, $start_x, $start_y) {
    $num = "";
    if (!is_numeric($schematic[$start_y][$start_x])){
        return $num;
    }
    $width = 0;
    while ($start_x + $width < count($schematic[$start_y]) && is_numeric($schematic[$start_y][$start_x + $width])){
        $num .= $schematic[$start_y][$start_x + $width];
        $width += 1;
    }
    return intval($num);
}

// Config
$lines = file('input.txt');
$schematic = []; 

$sum = 0;
foreach($lines as $line) {
    array_push($schematic, str_split(trim($line)));
}

$y_index = 0;
foreach($schematic as $s) {
    $x_index = 0;
    foreach($s as $v){
        if (is_numeric($v) && $x_index > 0 && is_numeric($schematic[$y_index][$x_index - 1])){
            echo $v . "";
        } else {
            if (is_numeric($v) && hasAdjecentSymbol($schematic, $x_index, $y_index)) {
                echo "X" . "";
                $sum += getNumber($schematic, $x_index, $y_index);
            } else {
                echo $v . "";
            }
        }
        $x_index += 1;
    }
    $y_index += 1;
    echo "\n";
}
echo $sum;
?>