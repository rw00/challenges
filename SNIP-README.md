# challenges

## SNIP

### Sawmills in LegoLand
There are tree trunks floating down a canal. Each tree trunk is precisely either 1, 2, 3 or more blocks in length. \
In other words, these tree trunks are measured in units of "block" and not in centimeters.

Down the stream, there's a sawmill machine that is faulty. It cuts every 3 blocks.

Assuming the tree trunks are floating one after the other without any space in between, 
and given the following information about the worth of wood blocks by length: \
1 block  = -1 $ \
2 blocks =  3 $ \
3 blocks =  1 $ \
calculate the order of cutting these tree trunks to gain the maximum profit.

To visualize the problem, consider this example:
```
Top-view visualization: - - --
Trunks block lengths:   1 1 2

Sawing order:     1  1  2
Sawn wood output: 1  1  1  1
Yielded profit:  -1 -1 -1 -1 = -4

Sawing order:     1  2  1
Sawn wood output: 1  2  1
Yielded profit:  -1  3 -1 = 1
```
Note: \
in the top-view visualization, there is actually no space between the trunks. \
I showed it like that for clarity only.

---
The input to this problem is composed of different cases and each case describes several sawmills instead of one. \
This may be confusing but the logic above is the essence of the problem.

The input to the problem follows this structure:
```
On the first line, there is an integer Z, the number of parallel sawmills in this case.
On the following Z lines, there is
    first an integer E, the number of tree trunks floating towards this sawmill; 
    followed on the same line by E positive integers, each indicating the length of the tree trunks.
On the last line, there is 0 to indicate the end of input.
```

Here is an example:
```
1
3 2 3 1
3
3 1 2 1
2 1 2
2 1 4
0
```

Here is the expected output:
```
Case 1
Max profit: 4
Order: [1 3 2] [2 3 1]
Case 2
Max profit: 8
Order: [1 2 1] [2 1 1], [1 2] [2 1], [1 4]
```
