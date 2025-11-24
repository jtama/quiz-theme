package io.github.jtama.quiz.theme;

import io.quarkus.qute.i18n.Message;
import io.quarkus.qute.i18n.MessageBundle;

@MessageBundle(value = "quiz_theme", locale = "fr")
public interface ThemeMessageBundle {

    @Message("Alors c'est toi le GOAT ???")
    String game_title();

    @Message("Accueil")
    String home();

    @Message("Modules")
    String modules();

    @Message("Scores")
    String leaderboard();

    @Message("ROQ")
    String owner();

    @Message("Nom du joueur")
    String player_name();

    @Message("Choisissez un nom de variable")
    String player_name_holder();

    @Message("Démarrer le quiz")
    String start_quiz();

    @Message("Niveau")
    String level();

    @Message("Voir la réponse")
    String show_answer();

    @Message("Quiz en cours")
    String current_quiz();

    @Message("Score actuel")
    String current_score();

    @Message("Modules complétés")
    String completed_modules();

    @Message("Modules restant")
    String remaining_modules();

    @Message("Terminer le quiz (0 pts pour les modules restants)")
    String finish_quiz();

    @Message("Continuer le quiz")
    String continue_quiz();

    @Message("Classement")
    String ranking();

    @Message("Joueur")
    String gamer();

    @Message("Tout remettre à zéro")
    String reset();

}
