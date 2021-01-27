package gsand.game;

/**
 * Interface for custom events of the game.
 *  
 * @author gsand
 * @version 1.0
 */
interface EventListener 
{
    
    /**
     * Triggered when the game has advanced by one turn.
     * 
     * @return void
     */
    void onAdvanceTurn();

    /**
     * Triggered when the game has begun.
     * 
     * @return void
     */
    void onGameStarted();
    
    /**
     * Triggered when the game has ended.
     * 
     * @return void
     */
    void onGameEnded();

    /**
     * Triggered when the game has restarted.
     * 
     * @return void
     */
    void onGameRestarted();

    /**
     * Triggered when the game has been paused.
     * 
     * @return void
     */
    void onGamePaused();
    
    /**
     * Triggered when the game has been resumed.
     * 
     * @return void
     */
    void onGameResumed();
        
}
