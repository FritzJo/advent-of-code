/*
--- Part Two ---
The engineers are surprised by the low number of safe reports until they realize they forgot to tell you about the Problem Dampener.
The Problem Dampener is a reactor-mounted module that lets the reactor safety systems tolerate a single bad level in what would otherwise be a safe report. It's like the bad level never happened!
Now, the same rules apply as before, except if removing a single level from an unsafe report would make it safe, the report instead counts as safe.

More of the above example's reports are now safe:
    7 6 4 2 1: Safe without removing any level.
    1 2 7 8 9: Unsafe regardless of which level is removed.
    9 7 6 2 1: Unsafe regardless of which level is removed.
    1 3 2 4 5: Safe by removing the second level, 3.
    8 6 4 4 1: Safe by removing the third level, 4.
    1 3 6 7 9: Safe without removing any level.

Thanks to the Problem Dampener, 4 reports are actually safe!
Update your analysis by handling situations where the Problem Dampener can remove a single level from unsafe reports. How many reports are now safe?


*/
function parseInput(text) {
    res = []
    text.split("\n").forEach( text =>
        res.push(text.split(" "))
    );
    return res
}

function processReport(report) {
    direction = report[0] - report[1] < 0 ? -1 : 1;
    for (let i = 0; i < report.length-1; i++) {
        change = (report[i] - report[i+1]) * direction
        //console.log(report[i] + " - Change: " + change)
        if ( change < 0 || change > 3 || change == 0) {
            return false;    
        }
    }
    return true
}

function processReportWithDampner(report) {
    reportOriginal = [...report];
    if (processReport(report)) {
        return true;
    } else {
        for (let s = 0; s < reportOriginal.length; s++) { 
            report.splice(s,1);
            if (processReport(report)) {
                return true;
            }
            report = [...reportOriginal];
        }
        return false
    }
}


fetch("./Day 2/input.txt")
  .then((res) => res.text())
  .then((text) => {
    p = parseInput(text)
    counter = 0
    p.forEach (report => {
        if (processReportWithDampner(report)) {
            counter++;
        }
    })
    console.log(counter)
   })
  .catch((e) => console.error(e));