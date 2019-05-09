package tdd.tennis

/**
 * Represents a Player in a Tennis game.
 */
class Player( val name: String ) {

    var gameScore = 0
        private set

    var setScore = 0
        private set

    fun increaseGameScore(){
        gameScore++
    }

    fun wonAGame() {
        setScore++
        gameScore = 0
    }

    fun looseAGame(){
        gameScore = 0
    }
}