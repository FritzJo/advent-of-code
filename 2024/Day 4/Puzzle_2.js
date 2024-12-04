/*
--- Part Two ---
The Elf looks quizzically at you. Did you misunderstand the assignment?
Looking for the instructions, you flip over the word search to find that this isn't actually an XMAS puzzle; it's an X-MAS puzzle in which you're supposed to find two MAS in the shape of an X. One way to achieve that is like this:

M.S
.A.
M.S

Irrelevant characters have again been replaced with . in the above diagram. Within the X, each MAS can be written forwards or backwards.
Here's the same example from before, but this time all of the X-MASes have been kept instead:

.M.S......
..A..MSMS.
.M.S.MAA..
..A.ASMSM.
.M.S.M....
..........
S.S.S.S.S.
.A.A.A.A..
M.M.M.M.M.
..........

In this example, an X-MAS appears 9 times.
Flip the word search from the instructions back over to the word search side and try again. How many times does an X-MAS appear?

*/

function parseInput(text) {
    res = []
    text.split("\n").forEach( text => {
        res.push(text.split(''))
    });
    return res
}

var counter = 0;

fetch("./Day 4/input.txt")
  .then((res) => res.text())
  .then((text) => {

    p = parseInput(text)
    // H
    for (let i = 1; i < p.length-1; i++) {
        for (let j = 1; j < p[i].length-1; j++) {
            const w = p[i-1][j-1] + p[i][j] + p[i+1][j+1] + p[i-1][j+1] + p[i][j] + p[i+1][j-1];
            if (w === "MASMAS" || w === "SAMSAM" || w === "MASSAM" || w === "SAMMAS")  {
                counter++;
            }
        }
    }
    console.log(counter);
   })
  .catch((e) => console.error(e));