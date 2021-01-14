package tictactoe
import java.util.Scanner

fun main() {

    val field = arrayOf(CharArray(3){' '}, CharArray(3){' '}, CharArray(3){' '})

    printBoard(field)
    play(field)


}

fun play(field: Array<CharArray>) {
    var moveSymbol = 'X'

    while (true) {
        val userCoords = getUserMove(field)
        val yCoord = userCoords[0].toString().toInt() - 1
        val xCoord = userCoords[1].toString().toInt() - 1
        field[yCoord][xCoord] = moveSymbol
        when (moveSymbol) {
            'X' -> moveSymbol = 'O'
            'O' -> moveSymbol = 'X'
        }
        val gameResult = checkBoard(field)
        if (gameResult != "Game not finished" && gameResult != "Impossible") {
            printBoard(field)
            println(gameResult)
            break
        }
        printBoard(field)
    }

}

fun getUserMove(field: Array<CharArray>):CharArray {
    val userPrompt: String = "Enter the coordinates: "

    while (true) {
        print(userPrompt)
        val userInput = readLine()!!

        val moveResponse = validateUserMove(userInput, field)
        if (moveResponse == "valid") {
            val yCoord = userInput.split(" ")[0][0]
            val xCoord = userInput.split(" ")[1][0]
            return charArrayOf(yCoord, xCoord)
        } else println(moveResponse)
    }
}

fun validateUserMove(userInput: String, field: Array<CharArray>):String {
    if (userInput.split(" ").size != 2) {
        return "Please enter two coordinates separated by a space!"
    }


    val yCoord = userInput.split(" ")[0]
    val xCoord = userInput.split(" ")[1]

    if (yCoord.length > 1 || xCoord.length > 1) {
        return "You should enter numbers!"
    }

    val yCoordChar = yCoord[0]
    val xCoordChar = xCoord[0]

    if (!yCoordChar.isDigit() || !xCoordChar.isDigit()) {
        return "You Should enter numbers!"
    }

    if (yCoordChar < '1' || yCoordChar > '3' || xCoordChar < '1' || xCoordChar > '3') {
        return "Coordinates should be from 1 to 3!"
    }

    val yLoc = yCoordChar.toString().toInt() - 1
    val xLoc = xCoordChar.toString().toInt() - 1
//    println("X IS $xLoc")
//    println("Y IS $yLoc")
//    println("SKRAT")

    val coordValue = field[yLoc][xLoc]

    if (coordValue != ' ') {
        return "This cell is occupied! Choose another one!"
    }

    return "valid"
}

fun printBoard(field: Array<CharArray>) {
    println("---------")
    println("| ${field[0][0]} ${field[0][1]} ${field[0][2]} |")
    println("| ${field[1][0]} ${field[1][1]} ${field[1][2]} |")
    println("| ${field[2][0]} ${field[2][1]} ${field[2][2]} |")
    println("---------")
}

fun checkBoard(field: Array<CharArray>): String {
    var xWins = false
    var oWins = false

    val row1 = field[0]
    val row2 = field[1]
    val row3 = field[2]

    val xWinArray = charArrayOf('X', 'X', 'X')
    val oWinArray = charArrayOf('O', 'O', 'O')

    if (row1.contentEquals(xWinArray)) xWins = true
    if (row2.contentEquals(xWinArray)) xWins = true
    if (row3.contentEquals(xWinArray)) xWins = true

    if (row1.contentEquals(oWinArray)) oWins = true
    if (row2.contentEquals(oWinArray)) oWins = true
    if (row3.contentEquals(oWinArray)) oWins = true

    if (charArrayOf(row1[0], row2[0], row3[0]).contentEquals(xWinArray)) xWins = true
    if (charArrayOf(row1[1], row2[1], row3[1]).contentEquals(xWinArray)) xWins = true
    if (charArrayOf(row1[2], row2[2], row3[2]).contentEquals(xWinArray)) xWins = true

    if (charArrayOf(row1[0], row2[0], row3[0]).contentEquals(oWinArray)) oWins = true
    if (charArrayOf(row1[1], row2[1], row3[1]).contentEquals(oWinArray)) oWins = true
    if (charArrayOf(row1[2], row2[2], row3[2]).contentEquals(oWinArray)) oWins = true

    if (charArrayOf(row1[0], row2[1], row3[2]).contentEquals(xWinArray)) xWins = true
    if (charArrayOf(row3[0], row2[1], row1[2]).contentEquals(xWinArray)) xWins = true
    if (charArrayOf(row1[0], row2[1], row3[2]).contentEquals(oWinArray)) oWins = true
    if (charArrayOf(row3[0], row2[1], row1[2]).contentEquals(oWinArray)) oWins = true

    var xCount = 0
    var oCount = 0
    var emptyCount = 0

    for (row in field) {
        for (c in row) {
            if (c == 'X') xCount++
            if (c == 'O') oCount++
            if (c == ' ') emptyCount++
        }
    }

    val impossible = xWins && oWins || Math.abs(xCount - oCount) > 1

    var gameResult = ""

    if (impossible) {
        gameResult = "Impossible"
    } else if (xWins) {
        gameResult = "X wins"
    } else if (oWins) {
        gameResult = "O wins"
    } else if (emptyCount > 0) {
        gameResult = "Game not finished"
    } else {
        gameResult = "Draw"
    }

    return gameResult
}







