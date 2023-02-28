package ee.rainer.cardgame.game;

import ee.rainer.cardgame.database.GameRepository;
import ee.rainer.cardgame.database.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class GameService {

    private Card card;
    private LocalDateTime roundStartTime;
    private int correctAnswers;
    private int lives;
    private Long gameStartTime;
    private Player player;

    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;

    public Card getCard() {
        roundStartTime = LocalDateTime.now();

        if (card == null) {
            correctAnswers = 0;
            lives = 3;
            gameStartTime = System.currentTimeMillis();
            card = new Card();
        }
        return card;
    }

    public String savePlayer(String playerName) {
        Optional<Player> playerFound = playerRepository.findById(playerName);
        if (playerFound.isEmpty()) {
            Player newplayer = new Player(playerName, new Date(),0);
            player = playerRepository.save(newplayer);
        } else {
            player = playerFound.get();
        }
        return "OK";
    }

    public String userGuess(String userGuess) {
        LocalDateTime roundStartTimeLater = roundStartTime.plusSeconds(10);
        LocalDateTime actualTime = LocalDateTime.now();
        if (actualTime.isAfter(roundStartTimeLater)) {
            lives--;
            if (lives == 0) {
                finishAndSaveGame();
                return "Game over!";
            }
            return "Aeg läbi!";
        }
        Card newCard = new Card();
        String response;

        if (isGuessAndValueComparisonCorrect(userGuess, newCard)) {
            correctAnswers++;
            response = "Correct!";
        } else {
            lives--;
            response = "Wrong!";
            if (lives == 0) {
                finishAndSaveGame();
                return "Game over!";
            }
        }
        card = newCard;
        return response;
    }

    private boolean isGuessAndValueComparisonCorrect(String userGuess, Card newCard) {
        return userGuess.equals("lower") && card.getValue() > newCard.getValue() ||
                userGuess.equals("higher") && card.getValue() < newCard.getValue() ||
                userGuess.equals("equal") && card.getValue() == newCard.getValue();
    }

    private void finishAndSaveGame() {
        card = null;
        Long gameFinish = System.currentTimeMillis();
        Long diff = gameFinish - gameStartTime;

        Game game = new Game();
        game.setCorrectGuesses((correctAnswers));
        game.setDuration(diff);
        game.setPlayer(player);
        if (correctAnswers > player.getHighScore()) {
            player.setHighScore(correctAnswers);
            playerRepository.save(player);
        }
        gameRepository.save(game);
    }

}
