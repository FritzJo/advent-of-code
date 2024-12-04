/*
--- Day 4: Ceres Search ---
"Looks like the Chief's not here. Next!" One of The Historians pulls out a device and pushes the only button on it. After a brief flash, you recognize the interior of the Ceres monitoring station!
As the search for the Chief continues, a small Elf who lives on the station tugs on your shirt; she'd like to know if you could help her with her word search (your puzzle input). She only has to find one word: XMAS.
This word search allows words to be horizontal, vertical, diagonal, written backwards, or even overlapping other words. It's a little unusual, though, as you don't merely need to find one instance of XMAS - you need to find all of them. Here are a few ways XMAS might appear, where irrelevant characters have been replaced with .:
..X...
.SAMX.
.A..A.
XMAS.S
.X....

The actual word search will be full of letters instead. For example:

MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX

In this word search, XMAS occurs a total of 18 times; here's the same word search again, but where letters not involved in any XMAS have been replaced with .:

....XXMAS.
.SAMXMS...
...S..A...
..A.A.MS.X
XMASAMX.MM
X.....XA.A
S.S.S.S.SS
.A.A.A.A.A
..M.M.M.MM
.X.X.XMASX

Take a look at the little Elf's word search. How many times does XMAS appear?

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
    for (let i = 0; i < p.length; i++) {
        for (let j = 0; j < p[i].length-2; j++) {
            const w = p[i][j] + p[i][j+1] + p[i][j+2] + p[i][j+3];
            if (w === "XMAS" || w === "SAMX")  {
                counter++;
            }
        }
    }

    // V
    for (let i = 0; i < p.length-3; i++) {
        for (let j = 0; j < p[i].length; j++) {
            const w = p[i][j] + p[i+1][j] + p[i+2][j] + p[i+3][j];
            if (w === "XMAS" || w === "SAMX")  {
                counter++;
            }
        }
    }

    // D1
    for (let i = 0; i < p.length-3; i++) {
        for (let j = 0; j < p[i].length-3; j++) {
            const w = p[i][j] + p[i+1][j+1] + p[i+2][j+2] + p[i+3][j+3];
            if (w === "XMAS" || w === "SAMX")  {
                counter++;
            }
        }
    }
    // D2
    for (let i = 3; i < p.length; i++) {
        for (let j = 0; j < p[i].length; j++) {
            const w = p[i][j] + p[i-1][j+1] + p[i-2][j+2] + p[i-3][j+3];
            if (w === "XMAS" || w === "SAMX")  {
                counter++;
            }
        }
    }
    
    console.log(counter);
   })
  .catch((e) => console.error(e));