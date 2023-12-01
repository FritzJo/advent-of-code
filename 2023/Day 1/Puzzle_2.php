<?php 
/* --- Part Two ---

Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".

Equipped with this new information, you now need to find the real first and last digit on each line. For example:

two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen

In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.

What is the sum of all of the calibration values? 
*/
function getCalibrationNumber($input_line) {
    $input_line = preg_replace("/[abcdjklmpqyz]/", "", $input_line);
    $replacement_map = [
        // Edge Cases
        'oneight' => '18',
        'twone' => '21',
        'threeight' => '38',
        'fiveight' => '58',
        'sevenine' => '79',
        'eightwo' => '82',
        'nineight' => '98',
        // Remaining replacements
        'zero' => '0',
        'one' => '1',
        'two' => '2',
        'three' => '3',
        'four' => '4',
        'five' => '5',
        'six' => '6',
        'seven' => '7',
        'eight' => '8',
        'nine' => '9',
    ];
    $input_line = str_replace(array_keys($replacement_map), $replacement_map, $input_line);
    $nums = preg_replace("/[^0-9]/", "", $input_line);
    $c1 = $nums[0];
    $c2 = $nums[strlen($nums) - 1];
    return intval($c1 . $c2);
}
  
$lines = file('input.txt');

$sum = 0;
foreach($lines as $line) {
    echo getCalibrationNumber($line) . "\n";
    $sum += getCalibrationNumber($line);
}
echo "\nTotal: " . $sum
?>