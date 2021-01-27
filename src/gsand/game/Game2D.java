package gsand.game;

import javafx.scene.Group;

/**
 * The game of life
 * 
 * @author gsand
 * @version 1.0
 */
public class Game2D extends Game
{
        
    private World world;
        
    /**
     * Initializes a new "game of life" in 2 dimensions.
     */
    public Game2D()
    {
        
        super();
                
    }

    /**
     * Initializes the world.
     * 
     * @return void
     */
    public void initWorld()
    {

        if(Settings.getIsWorldHexagonal())
        {
            
            this.world = new WorldHexagonal();
            
        }
        else
        {
            
            this.world = new WorldRectangular();
            
        }
        
        this.world.init();
        
        this.setLifeformsOnCells(this.world);
        
    }

    /**
     * Draws the game to the specified Group.
     * 
     * @param Group root
     * @return void
     */
    public void draw(Group root)
    {
        
        world.drawFields(root);
        
    }

    /**
     * Retrieves the world by the specified index.
     * In a 2D game, there is only one world.
     * 
     * @param int worldIndex
     * @return World
     */
    public World getWorldByIndex(int worldIndex)
    {
        
        return this.world;
        
    }
        
}
