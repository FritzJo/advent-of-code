<?php 
/*
--- Part Two ---

The engineer finds the missing part and installs it in the engine! As the engine springs to life, you jump in the closest gondola, finally ready to ascend to the water source.
You don't seem to be going very fast, though. Maybe something is still wrong? Fortunately, the gondola has a phone labeled "help", so you pick it up and the engineer answers.
Before you can explain the situation, she suggests that you look out the window. There stands the engineer, holding a phone in one hand and waving with the other. You're going so slowly that you haven't even left the station. You exit the gondola.
The missing part wasn't the only issue - one of the gears in the engine is wrong. A gear is any * symbol that is adjacent to exactly two part numbers. Its gear ratio is the result of multiplying those two numbers together.
This time, you need to find the gear ratio of every gear and add them all up so that the engineer can figure out which gear needs to be replaced.

Consider the same engine schematic again:

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

In this schematic, there are two gears. The first is in the top left; it has part numbers 467 and 35, so its gear ratio is 16345. The second gear is in the lower right; its gear ratio is 451490. (The * adjacent to 617 is not a gear because it is only adjacent to one part number.) Adding up all of the gear ratios produces 467835.

What is the sum of all of the gear ratios in your engine schematic?

*/

function getAdjecentNumbers($schematic, $start_x, $start_y) {
    $upper_border = ($start_y == 0) ? 0: -1;
    $lower_border = ($start_y == count($schematic) -1 ) ? 0: 1;
    $left_border  = ($start_x == 0) ? 0: -1;
    $right_border = ($start_x == count($schematic[$start_y])-1) ? 0: 1;

    $count = 0;
    $numbers = [];
    for ($i = $upper_border; $i <= $lower_border; $i++){
        for ($j = $left_border; $j <= $right_border; $j++){
            if (is_numeric($schematic[$start_y + $i][$start_x + $j])){
                list($number, $width, $width_l) = getNumber($schematic, $start_x + $j, $start_y + $i);
                //echo "\nStarting from: " . $schematic[$start_y + $i][$start_x + $j] . "\n";
                //echo "Number: " . $number . ", " . $width_l . " to " . $width . "\n";
                array_push($numbers, $number);
                
                // Skip rest of number
                $j += $width;
                $count += 1;
            }
        }   
    }
    return array($count, $numbers);
}

function getNumber($schematic, $start_x, $start_y) {
    $num = "";
    if (!is_numeric($schematic[$start_y][$start_x])){
        return $num;
    }

    // Explore to the right
    $width = 0;
    while ($start_x + $width < count($schematic[$start_y]) && is_numeric($schematic[$start_y][$start_x + $width])){
        $num .= $schematic[$start_y][$start_x + $width];
        $width += 1;
    }


    // Explore to the left
    $width_l = -1;
    while ($start_x + $width_l >= 0 && is_numeric($schematic[$start_y][$start_x + $width_l])){
        $num = $schematic[$start_y][$start_x + $width_l] . $num;
        $width_l -= 1;
    }


    return array(intval($num), $width -1, $width_l +1);
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
        if ($v == "*") {
            list($count, $numbers) = getAdjecentNumbers($schematic, $x_index, $y_index);
            if ($count == 2){
                echo "Found gear with: ";
                foreach($numbers as $n){
                    echo $n . " ";
                }
                $r = array_product($numbers);
                echo "\nRatio: " . $r . "\n\n";
                $sum += $r;
            }
        }
        $x_index += 1;
    }
    $y_index += 1;
}
echo "Sum: " . $sum;
?>