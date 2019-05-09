package tdd.tennis

import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*

@DisplayName("Behavior: Standard Score Mechanism")
class StandardScoreMechanismBehaviorTest {

    val playerA = Player("Myself")
    val playerB = Player("Me")

    val mechanism = StandardScoreMechanism(playerA, playerB)

    @Test fun `Player A SHOULD score 15 WHEN he scores by the first time`() {
        playerA.increaseGameScore()
        assertEquals( "0-0, 15-0", mechanism.computeSetScore() )
    }

    @Test fun `Player B SHOULD score 15 WHEN he scores by the first time`() {
        playerB.increaseGameScore()
        assertEquals( "0-0, 0-15", mechanism.computeSetScore() )
    }

    @Test fun `Player A SHOULD score 30 WHEN player scores by the second time`() {
        playerA.increaseGameScore()
        playerA.increaseGameScore()
        assertEquals( "0-0, 30-0", mechanism.computeSetScore() )
    }

    @Test fun `Player A SHOULD score 40 WHEN player scores by the third time`() {
        for ( i in 0 until 3 )
            playerA.increaseGameScore()
        assertEquals( "0-0, 40-0", mechanism.computeSetScore() )
    }

    @Test fun `Set SHOULD be "deuce" WHEN both has at least 3 points and same score in the set`(){
        for ( i in 0 until 3 ) {
            playerA.increaseGameScore()
            playerB.increaseGameScore()
        }

        assertEquals( "0-0, Deuce", mechanism.computeSetScore() )
    }

    @Test fun `Player A SHOULD be in advantage WHEN he has at least 3 points, but only one more than his opponent`(){
        for ( i in 0 until 3 ) {
            playerA.increaseGameScore()
            playerB.increaseGameScore()
        }

        playerA.increaseGameScore()

        assertEquals( "0-0, Advantage Myself", mechanism.computeSetScore() )
    }

    @Test fun `Player B SHOULD be in advantage WHEN he has at least 3 points, but only one more than his opponent`(){
        for ( i in 0 until 3 ) {
            playerA.increaseGameScore()
            playerB.increaseGameScore()
        }

        playerB.increaseGameScore()

        assertEquals( "0-0, Advantage Me", mechanism.computeSetScore() )
    }

    @Test fun `SHOULD show set scores WHEN player have won previous games`(){
        playerA.wonAGame()
        playerA.wonAGame()
        playerB.wonAGame()

        assertEquals( "2-1", mechanism.computeSetScore() )
    }

    @Test fun `SHOULD show Player A as winner WHEN he've won 6 games`(){
        for ( i in 0 until 6 )
        playerA.wonAGame()
        playerB.wonAGame()

        assertEquals( "6-1, Myself is the Winner", mechanism.computeSetScore() )
    }
}