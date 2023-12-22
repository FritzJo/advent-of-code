<?php 
/*
--- Part Two ---

As the race is about to start, you realize the piece of paper with race times and record distances you got earlier actually just has very bad kerning. There's really only one race - ignore the spaces between the numbers on each line.
So, the example from before:

Time:      7  15   30
Distance:  9  40  200

...now instead means this:

Time:      71530
Distance:  940200

Now, you have to figure out how many ways there are to win this single race. In this example, the race lasts for 71530 milliseconds and the record distance you need to beat is 940200 millimeters. You could hold the button anywhere from 14 to 71516 milliseconds and beat the record, a total of 71503 ways!
How many ways can you beat the record in this one much longer race?
*/

function parse_records($line1, $line2) {
    preg_match_all('!\d+!', $line1, $matches1);
    preg_match_all('!\d+!', $line2, $matches2);
    $races = [];
    for($i = 0; $i<count($matches1[0]); $i++) {
        $values = [];
        array_push($values, $matches1[0][$i]);
        array_push($values, $matches2[0][$i]);
        array_push($races, $values);
    }
    return $races;
}

function calculate_distance($time, $time_max) {
    if ($time == 0) {
        return 0;
    }
    if ($time >= $time_max) {
        return 0;
    }
    $duration = $time_max - $time;
    return $duration * $time;
}

function get_wins($race) {
    $n_wins = 0;
    for($i = 1; $i < $race[0]; $i++) {
        $d = calculate_distance($i, $race[0]);
        if ($d > $race[1]){
            //echo "$i --> $d\n";
            $n_wins++;
        }
    }
    return $n_wins;
}

$lines = file('input.txt');

$sum = 1;

$first_map = True;
$parsed_seeds = [];
$r = parse_records($lines[0], $lines[1]);
print_r($r);
foreach($r as $race){
    $w = get_wins($race);
    echo $w . " wins\n";
    $sum = $sum * $w;
}
echo "Sum: $sum";
// Just remove all whitespaces from the input
?>