import com.islandboys.game.MGame;
import com.islandboys.game.model.Islander;
import com.islandboys.game.view.PlayScreen;

import org.junit.Test;
import static org.junit.Assert.*;


public class test {

    @Test
    public void isAliveIslander() {
        PlayScreen screen=new PlayScreen(new MGame(),1);
        Islander islander=  new Islander(screen);
        assertTrue(islander.getAlive());
        islander.setLive(5);
        assertFalse(islander.getAlive());

    }

}
