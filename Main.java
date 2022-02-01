// Justin Ha

import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Terrain[][] map1 = generateMap();                    // Generate map 1
        System.out.println("Map 1:");                        // Prints map to console
        printMap(map1);                                      //
        printResults(0, 5, new Player(), map1);        // Prints results of simple, complex, and supercomplex brain for the provided player type
        printResults(0, 5, new Sprinter(), map1);
        printResults(0, 5, new Lizard(), map1);
        printResults(0, 5, new Demon(), map1);

        Terrain[][] map2 = generateMap();                    // Generate map 2
        System.out.println("Map 2:");                        // Prints map to console
        printMap(map2);                                      //
        printResults(0, 5, new Player(), map2);        // Prints results of simple, complex, and supercomplex brain for the provided player type
        printResults(0, 5, new Sprinter(), map2);
        printResults(0, 5, new Lizard(), map2);
        printResults(0, 5, new Demon(), map2);
    }

    // generate map
    public static Terrain[][] generateMap()
    {
        //Terrain[][] map = { {new Terrain.Sand(), new Terrain.Grass()}, {new Terrain.Grass(), new Terrain.Sand()}}; // Test Map

        Random rand = new Random();

        Terrain[][] map = new Terrain[10][50];             // Sets size of the map to 10 x 50

        // Cycle through 2d array
        for (int row = 0; row < map.length; row++)
        {
            for (int col = 0; col < map[row].length; col++)
            {
                int random = rand.nextInt(10);     // Generate random terrain

                switch (random)                          // Assign random terrain to map 2d array
                {
                    case 0:                              // Empty case to give Grass 20% chance
                    case 1:
                        map[row][col] = new Grass();
                        break;
                    case 2:                              // Empty case to give Sand 20% chance
                    case 3:
                        map[row][col] = new Sand();
                        break;
                    case 4:                              // QuickSand 10% chance
                        map[row][col] = new QuickSand();
                        break;
                    case 5:                              // Empty Case to give Forest 20% chance
                    case 6:
                        map[row][col] = new Forest();
                        break;
                    case 7:                              // Water 10% chance
                        map[row][col] = new Water();
                        break;
                    case 8:                              // Mountain 10% chance
                        map[row][col] = new Mountain();
                        break;
                    case 9:                              // Ruins 10% chance
                        map[row][col] = new Ruins();
                        break;
                }
            }
        }

        return map;
    }

    // print map
    public static void printMap(Terrain[][] map)
    {
        // Cycle through 2d array
        for (int row = 0; row < map.length; row++)
        {
            for (int col = 0; col < map[row].length; col++)
            {
                if (row == 5 && col == 0)                       // Prints player as '*' if coords are (0,5)
                {
                    System.out.print("* ");
                }
                else
                {
                    System.out.print(map[row][col] + " ");
                }
            }
            System.out.println();
        }
    }

    public static String gameLoop(int x, int y, Player player, Terrain[][] map, Brain brain)
    {
        player.setFoodSupply(20);
        player.setWaterSupply(20);
        player.setStaminaSupply(20);

        do                                          // Game loop
        {
            switch (brain.move())
            {
                case "E":
                    x++;
                    player.enter(map[y][x]);
                    break;
                case "N":
                    y--;
                    player.enter(map[y][x]);
                    break;
                case"S":
                    y++;
                    player.enter(map[y][x]);
                    break;
                case "NE":
                    y--;
                    player.enter(map[y][x]);
                    x++;
                    player.enter(map[y][x]);
                    break;
                case "EN":
                    x++;
                    player.enter(map[y][x]);
                    y--;
                    player.enter(map[y][x]);
                    break;
                case "SE":
                    y++;
                    player.enter(map[y][x]);
                    x++;
                    player.enter(map[y][x]);
                    break;
                case "ES":
                    x++;
                    player.enter(map[y][x]);
                    y++;
                    player.enter(map[y][x]);
                    break;
            }
        } while (player.enter(map[y][x]));
        return "Row: " + y + " Col: " + x;
    }

    public static void printResults(int x, int y, Player player, Terrain[][] map)
    {
        SimpleBrain brain1 = new SimpleBrain(x, y, player, map);
        ComplexBrain brain2 = new ComplexBrain(x, y, player, map);
        SuperComplexBrain brain3 = new SuperComplexBrain(x, y, player, map);

        System.out.println(player.getClass() + ": ");
        System.out.println("Simple brain died at: " + gameLoop(x, y, player, map, brain1));
        System.out.println("Complex brain died at: " + gameLoop(x, y, player, map, brain2));
        System.out.println("SuperComplex brain died at: " + gameLoop(x, y, player, map, brain3));
    }
}
