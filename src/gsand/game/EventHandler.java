package gsand.game;

/**
 * Handler for custom events of the game.
 *  
 * @author gsand
 * @version 1.0
 */
public class EventHandler implements EventListener
{

    /**
     * Triggered when the game has begun.
     * 
     * @return void
     */
    public void onGameStarted()
    {
        
        System.out.println("The game has started!\n");
        
        this.printStatistics();
        
    }

    /**
     * Triggered when the game has ended.
     * 
     * @return void
     */
    public void onGameEnded()
    {

        boolean allPlantsEaten = App.getGameObject().getCountLifeforms(Plant.class) == 0;
        boolean allCarinivoresEaten = App.getGameObject().getCountLifeforms(Carnivore.class) == 0;
        boolean allHerbivoresEaten = App.getGameObject().getCountLifeforms(Herbivore.class) == 0;
        boolean allOmnivoresEaten = App.getGameObject().getCountLifeforms(Omnivore.class) == 0;

        System.out.println("\nGame over after " + (App.getGameObject().getCountGameTurns()) + " rounds!\n");

        if(allPlantsEaten)
        {

            System.out.println(" - All plants have been eaten.");
            
        }
        else
        {

            System.out.println(" - Only plants remain.");
            
        }
        
        if(allCarinivoresEaten)
        {

            System.out.println(" - All carnivores have been eaten.");
            
        }
        else
        {

            System.out.println(" - Only carnivores remain.");
            
        }
        
        if(allHerbivoresEaten)
        {

            System.out.println(" - All herbivores have been eaten.");
            
        }
        else
        {

            System.out.println(" - Only herbivores remain.");
            
        }
        
        if(allOmnivoresEaten)
        {

            System.out.println(" - All omnivores have been eaten.");
            
        }
        else
        {

            System.out.println(" - Only omnivores remain.");
            
        }

        System.out.println("\n");
        
        this.printStatistics();
        
    }

    /**
     * Triggered when the game has restarted.
     * 
     * @return void
     */
    public void onGameRestarted()
    {
        
        int cycleNumber = (App.getGameObject().getCountGameStarts());
        
        if(cycleNumber > 1)
        {

            System.out.println("\nThe game has been restarted, begin cycle #" + (cycleNumber) + "!");
            
        }
                
    }

    /**
     * Triggered when the game has been paused.
     * 
     * @return void
     */
    public void onGamePaused()
    {
        
        System.out.println("The game has been paused.");
        
    }

    /**
     * Triggered when the game has been resumed.
     * 
     * @return void
     */
    public void onGameResumed()
    {
        
        System.out.println("The game has been resumed.");
        
    }

    /**
     * Triggered when the game has advanced by one turn.
     * 
     * @return void
     */
    public void onAdvanceTurn()
    {
        
        System.out.println("The game has advanced by to turn #" + (App.getGameObject().getCountGameTurns()) + "\n");
        
        this.printStatistics();
        
    }
    
    /**
     * Prints out the current game statistics to the console.
     * 
     * @return void
     */
    private void printStatistics()
    {

        System.out.println("Current statistics\n======");
        
        System.out.println("Number of lifeforms: " + (App.getGameObject().getCountLifeforms()));
        System.out.println("Number of plants: " + (App.getGameObject().getCountLifeforms(Plant.class)));
        System.out.println("Number of animals: " + (App.getGameObject().getCountLifeforms(Animal.class)));
        System.out.println("Number of carnivores: " + (App.getGameObject().getCountLifeforms(Carnivore.class)));
        System.out.println("Number of herbivores: " + (App.getGameObject().getCountLifeforms(Herbivore.class)));
        System.out.println("Number of omnivores: " + (App.getGameObject().getCountLifeforms(Omnivore.class)));
        
        System.out.println("");
        
    }

}
