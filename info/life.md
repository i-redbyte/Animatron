# Game of Life

**Conway's Game of Life**

Implementation of Conway's Game of Life according to the rules:
The distribution of living cells at the beginning of the game is called the first generation. Each
subsequent generation is calculated based on the previous one according to these rules:

- in an empty (dead) cell that is adjacent to three living cells, life is born;
- if a living cell has two or three living neighbors, then that cell continues to live; otherwise (
  if there are fewer than two or more than three living neighbors), the cell dies (from "loneliness"
  or "overpopulation").

[wiki](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

![Game of Life](/info/gifs/life.gif)