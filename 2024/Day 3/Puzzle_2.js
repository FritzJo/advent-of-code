/*
--- Part Two ---
As you scan through the corrupted memory, you notice that some of the conditional statements are also still intact. If you handle some of the uncorrupted conditional statements in the program, you might be able to get an even more accurate result.
There are two new instructions you'll need to handle:
    The do() instruction enables future mul instructions.
    The don't() instruction disables future mul instructions.
Only the most recent do() or don't() instruction applies. At the beginning of the program, mul instructions are enabled.
For example:
xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
This corrupted memory is similar to the example from before, but this time the mul(5,5) and mul(11,8) instructions are disabled because there is a don't() instruction before them. The other mul instructions function normally, including the one at the end that gets re-enabled by a do() instruction.
This time, the sum of the results is 48 (2*4 + 8*5).
Handle the new instructions; what do you get if you add up all of the results of just the enabled multiplications?

*/

function parseInput(text) {
    const regex = /(mul\(\d*,\d*\))|(do\(\))|(don't\(\))/g
    res = []
    text.split("\n").forEach( text => {
        const matches = text.matchAll(regex);
        for (const match of matches) {
            res.push(match[0]);
        }
    });
    return res
}


sum = 0;
fetch("./Day 3/input.txt")
  .then((res) => res.text())
  .then((text) => {
    p = parseInput(text)
    counter = 0
    var disabled = false;
    p.forEach (instruction => {
        console.log(instruction);
        if (instruction.includes("mul") && !disabled) {
            s = instruction.replace("mul(", "").replace(")","");
            v1 = s.split(",")[0];
            v2 = s.split(",")[1]
            sum += v1*v2;
        } else if (instruction.includes("don't")) {
            disabled = true;
        } else if (instruction.includes("do")) {
            disabled = false;
        }
    })
    console.log(sum)
   })
  .catch((e) => console.error(e));