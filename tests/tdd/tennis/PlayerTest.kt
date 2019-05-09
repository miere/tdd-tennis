package tdd.tennis

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@DisplayName("Unit: Player")
class PlayerTest {

    val player = Player("Helden")

    @Test fun `Players SHOULD have no score after being constructed`(){
        assertEquals( 0, player.gameScore )
        assertEquals( 0, player.setScore )
    }

    @Test fun `SHOULD increase game score`()
    {
        player.increaseGameScore()
        assertEquals( 1, player.gameScore )
        assertEquals( 0, player.setScore )
    }

    @Test fun `SHOULD increase set score and reset game score WHEN win the game`()
    {
        player.increaseGameScore()
        player.wonAGame()
        assertEquals( 0, player.gameScore )
        assertEquals( 1, player.setScore )
    }

    @Test fun `Should reset game score when loose game`() {
        player.increaseGameScore()
        player.looseAGame()
        assertEquals( 0, player.gameScore )
        assertEquals( 0, player.setScore )
    }
}