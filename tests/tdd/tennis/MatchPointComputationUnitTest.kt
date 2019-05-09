package tdd.tennis

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Behavior: Match behavior")
class MatchPointComputationUnitTest {

    val playerA = Player("Myself")
    val playerB = Player("Me")

    val match = Match(playerA, playerB)

    @Test fun `Player A SHOULD score 15 WHEN he scores by the first time`() {
        match.pointWonBy( playerA.name )
        assertEquals( "0-0, 15-0", match.score() )
    }

    @Test fun `Player B SHOULD score 15 WHEN he scores by the first time`() {
        match.pointWonBy( playerB.name )
        assertEquals( "0-0, 0-15", match.score() )
    }

    @Test fun `Player A SHOULD score 30 WHEN player scores by the second time`() {
        match.pointWonBy( playerA.name )
        match.pointWonBy( playerA.name )
        assertEquals( "0-0, 30-0", match.score() )
    }

    @Test fun `Player A SHOULD score 40 WHEN player scores by the third time`() {
        for ( i in 0 until 3 )
            match.pointWonBy( playerA.name )
        assertEquals( "0-0, 40-0", match.score() )
    }

    @Test fun `Set SHOULD be "deuce" WHEN both has at least 3 points and same score in the set`(){
        for ( i in 0 until 3 ) {
            match.pointWonBy( playerA.name )
            match.pointWonBy( playerB.name )
        }

        assertEquals( "0-0, Deuce", match.score() )
    }

    @Test fun `Player A SHOULD be in advantage WHEN he has at least 3 points, but only one more than his opponent`(){
        simulateWonPoints( playerB, 3 )
        simulateWonPoints( playerA, 4 )

        assertEquals( "0-0, Advantage Myself", match.score() )
    }

    @Test fun `Player B SHOULD be in advantage WHEN he has at least 3 points, but only one more than his opponent`(){
        simulateWonPoints( playerA, 3 )
        simulateWonPoints( playerB, 4 )

        assertEquals( "0-0, Advantage Me", match.score() )
    }

    @Test fun `SHOULD show set scores WHEN player have won previous games`(){
        simulateWonSets(playerA, 2)
        simulateWonSets(playerB, 1)

        assertEquals( "2-1", match.score() )
    }

    @Test fun `SHOULD show Player A as winner WHEN he've won 6 games`(){
        simulateWonPoints(playerB, 4)
        for ( i in 0 until 6 )
            simulateWonPoints(playerA, 4)

        assertEquals( "6-1, Myself is the Winner", match.score() )
    }

    @Test fun `Player A SHOULD win the game WHEN he is the only one to score a point within the set`() {
        simulateWonPoints(playerA, 4)

        assertEquals( "1-0", match.score() )
    }

    @Test fun `Player B SHOULD win the game WHEN he is the only one to score a point within the set`() {
        simulateWonPoints(playerB, 4)

        assertEquals( "0-1", match.score() )
    }

    @Test fun `Player A SHOULD win the game WHEN he has more than 4 points, but 2 more than his opponent`(){
        simulateWonPoints(playerB, 3)
        simulateWonPoints(playerA, 5)

        assertEquals( "1-0", match.score() )
    }

    @Test fun `Player B SHOULD win the game WHEN he has at least 3 points, but 2 more than his opponent`(){
        simulateWonPoints(playerA, 3)
        simulateWonPoints(playerB, 5)

        assertEquals( "0-1", match.score() )
    }

    @Test fun `Player B SHOULD tie the set WHEN he wins the 6th game and his opponent already has 6 won games`(){
        simulateWonSets(playerA, 6)
        simulateWonSets(playerB, 5)
        assertEquals( "6-5", match.score() )
        assertFalse( match.isMatchTied() )

        simulateWonSets(playerB, 1)
        assertEquals( "6-6", match.score() )
        assertTrue( match.isMatchTied() )
    }

    @Test fun `SHOULD show correct tie break score`(){
        simulateWonSets(playerA, 6)
        simulateWonSets(playerB, 6)

        for (i in 0 until 32) {
            match.pointWonBy(playerA.name)
            match.pointWonBy(playerB.name)
        }
        match.pointWonBy(playerA.name)

        assertEquals( "6-6, 33-32", match.score() )
    }

    private fun simulateWonSets( player: Player, numberOfVictories: Int ) {
        for ( j in 0 until numberOfVictories )
            simulateWonPoints( player, 4 )
    }

    private fun simulateWonPoints(player: Player, numberOfVictories: Int ) {
        for ( j in 0 until numberOfVictories )
            match.pointWonBy(player.name)
    }
}