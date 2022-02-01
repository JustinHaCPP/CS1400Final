public class Brain
{
    private int x, y;
    private Player player;
    private Terrain[][] map;

    // Getters
    public int getX() {return x;}
    public int getY() {return y;}
    public Player getPlayer() {return player;}
    public Terrain[][] getMap() {return map;}

    // Setters
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setPlayer(Player player) {this.player = player;}
    public void setMap(Terrain[][] map) {this.map = map;}

    public String move()
    {
        return "";
    }
}
