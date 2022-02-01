public class SimpleBrain extends Brain
{
    public SimpleBrain(int x, int y, Player player, Terrain[][] map)
    {
        setX(x);
        setY(y);
        setPlayer(player);
        setMap(map);
    }

    @Override
    public String move()
    {
        return "E";
    }
}
