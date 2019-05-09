package tdd.tennis

/**
 * Represents a simplified Tennis Match with only one Set as defined by
 * "A match has one set and a set has many games"
 */
class Match( val playerA: Player, val playerB: Player ){

    var scoreMechanism:ScoreMechanism = StandardScoreMechanism(playerA, playerB)

    fun score(): String = scoreMechanism.computeSetScore()

    fun pointWonBy(playerName: String) {
        val (attacker, defendant) = when ( playerName ) {
            playerA.name -> playerA to playerB
            else -> playerB to playerA
        }

        computeScore(attacker, defendant)

        if ( isMatchTied() )
            scoreMechanism = TieBreakScoreMechanism( playerA, playerB )
    }

    fun computeScore( attacker: Player, defendant: Player ) {
        attacker.increaseGameScore()

        val diff = attacker.gameScore - defendant.gameScore
        if ( diff > 1 && attacker.gameScore >= scoreMechanism.minScoreToWinGame ) {
            attacker.wonAGame()
            defendant.looseAGame()
        }
    }

    fun isMatchTied() = playerA.setScore == 6 && playerB.setScore == 6
}