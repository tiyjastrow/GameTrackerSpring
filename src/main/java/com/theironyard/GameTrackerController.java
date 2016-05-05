package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * Created by johnjastrow on 5/4/16.
 */
@Controller
public class GameTrackerController {
    @Autowired
    GameRepository games;

//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public String home() {
//        return "home";
//    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {
        List<Game> gameList = (List<Game>) games.findAll();
        model.addAttribute("games", gameList);
        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(String gameName, String gamePlatform, String gameGenre, String gameYear) {
        if (gameGenre == null) gameGenre = "";
        if (gamePlatform == null) gamePlatform = "";
        if (gameName == null) gameName = "";

        int gameYearInt = 2000;
        if (gameYear != null && ! gameYear.isEmpty()) {
            gameYearInt = Integer.parseInt(gameYear);
        }

        Game game = new Game(gameName, gamePlatform, gameGenre, gameYearInt);
        games.save(game);
        return "redirect:/";
    }
}
