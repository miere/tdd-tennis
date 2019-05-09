package tdd.tennis

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Behavior: Tie Break Score Mechanism")
class TieBreakScoreMechanismBehaviorTest {

    val playerA = Player("Myself")
    val playerB = Player("Me")

    val mechanism = TieBreakScoreMechanism(playerA, playerB)

    @Test
    fun `SHOULD show numbered points WHEN players have score in the game`(){
        for ( i in 0 until 5 )
            playerA.increaseGameScore()
        for ( i in 0 until 3 )
            playerB.increaseGameScore()

        assertEquals( "0-0, 5-3", mechanism.computeSetScore() )
    }

    @Test fun `SHOULD show Player A as winner WHEN he've won 6 games`(){
        for ( i in 0 until 6 )
            playerA.wonAGame()
        playerB.wonAGame()

        assertEquals( "6-1, Myself is the Winner", mechanism.computeSetScore() )
    }
}