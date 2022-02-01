public class QuickSand extends Terrain
{
    @Override
    public double waterCost()
    {
        return 1.5;
    }

    @Override
    public double staminaCost()
    {
        return 3;
    }

    @Override
    public String toString()
    {
        return "Q";
    }
}
