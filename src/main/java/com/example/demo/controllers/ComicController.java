package com.example.demo.controllers;

import com.example.demo.model.Comic;
import com.example.demo.model.ComicDto;
import com.example.demo.services.ComicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class ComicController {

    private static final String[] COMIC_URLS = {
            "https://imgs.xkcd.com/comics/compiler_complaint.png",
            "https://imgs.xkcd.com/comics/exploits_of_a_mom.png",
            "https://imgs.xkcd.com/comics/compiling.png",
            "https://imgs.xkcd.com/comics/goto.png",
            "https://imgs.xkcd.com/comics/pointers.png"
    };

    private final ComicService comicService;

    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping("/comic")
    public String showComic(@RequestParam(name = "id", defaultValue = "0") int id, Model model) {
        id = Math.max(0, Math.min(id, COMIC_URLS.length - 1));
        model.addAttribute("comicUrl", COMIC_URLS[id]);
        model.addAttribute("comicId", id);
        return "comic";
    }

    @PostMapping("/comic/prev")
    public String prevComic(@RequestParam int id, Model model) {
        int prevId = (id - 1 + COMIC_URLS.length) % COMIC_URLS.length;
        model.addAttribute("comicUrl", COMIC_URLS[prevId]);
        model.addAttribute("comicId", prevId);
        return "comic";
    }

    @PostMapping("/comic/next")
    public String nextComic(@RequestParam int id, Model model) {
        int nextId = (id + 1) % COMIC_URLS.length;
        model.addAttribute("comicUrl", COMIC_URLS[nextId]);
        model.addAttribute("comicId", nextId);
        return "comic";
    }

    @PostMapping("/comic/jump")
    public String jumpComic(@RequestParam int id, Model model) {
        if (id >= 1 && id < COMIC_URLS.length + 1) {
            model.addAttribute("comicUrl", COMIC_URLS[id - 1]);
            model.addAttribute("comicId", id - 1);
        }
        return "comic";
    }

    @PostMapping("/comic/save")
    public String jumpComic2(@RequestParam int id, Model model) {
        if (id >= 1 && id < COMIC_URLS.length + 1) {
            model.addAttribute("comicUrl", COMIC_URLS[id - 1]);
            model.addAttribute("comicId", id - 1);
        }
        return "comic";
    }





    @GetMapping({"/comic/xkcd", "/comic/xkcd/", "/comic/xkcd/{id}"})
    public String getComicFromXkcd(@PathVariable(required = false) String id, Model model) {
        int comicNum;

        if (id != null && !id.isEmpty()) {
            try {
                comicNum = Integer.parseInt(id);
                if (comicNum < 1 || comicNum > 3090) {
                    model.addAttribute("error", "❌ Comic not found. Please provide a valid ID between 1 and 3090.");
                    return "comicWithId";
                }
            } catch (NumberFormatException e) {
                model.addAttribute("error", "❌ Invalid Comic ID format. Please enter a number.");
                return "comicWithId";
            }
        } else {
            // No ID: show random comic
            comicNum = ThreadLocalRandom.current().nextInt(1, 3090); // 1 to 3089 inclusive
            return "redirect:/comic/xkcd/" + comicNum;
        }

        ComicDto comicDTO = comicService.getComicDto(comicNum);

        if (comicDTO == null) {
            model.addAttribute("error", "❌ Comic not found. Please try again with a different ID.");
        } else {
            model.addAttribute("comic", comicDTO);
            if (comicService.getComicById(comicNum).isEmpty()) {
                comicService.createComic(comicService.comicDtoToComic(comicDTO));
            }
//            comicService.saveComic(comicService.getComic(comicNum)); // Optional: Save to DB
        }

        return "comicWithId";
    }


    @PostMapping("/comic/xkcd/{id}/comment")
    public String addComment(@PathVariable int id, @RequestParam String text, Model model) {

        comicService.addCommentToComic(id, text);

        Optional<Comic> comicById = comicService.getComicByNum(id);

        ComicDto comicDTO = comicService.getComicDto(id);

        comicDTO.setComments(comicById.map(Comic::getComments).orElse(null));

//        ComicDto comicDto = comicService.comicToComicDto(comicById.orElse(null));


        model.addAttribute("comic", comicDTO);

        return "comicWithId";

    }
}
