package com.example.demo.services;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComicService {

    private final RestTemplate restTemplate;

    private final ComicRepository comicRepository;
    private final CommentRepository commentRepository;

    public ComicService(RestTemplate restTemplate, ComicRepository comicRepository, CommentRepository commentRepository) {
        this.restTemplate = restTemplate;
        this.comicRepository = comicRepository;
        this.commentRepository = commentRepository;
    }

    // Get all comics
    public List<Comic> getAllComics() {
        return comicRepository.findAll();
    }

    // Get comic by ID
    public Optional<Comic> getComicById(int id) {
        return comicRepository.findById(id);
    }

    public ComicDto getComicDto(int comicNumber) {
        String url = "https://xkcd.com/" + comicNumber + "/info.0.json";

        try {
            // Try to fetch the comic data from XKCD
            return restTemplate.getForObject(url, ComicDto.class);
        } catch (Exception e) {
            // Log the error (you could log more details depending on your logging framework)
            System.err.println("Error fetching comic " + comicNumber + ": " + e.getMessage());

            // Return null in case of an error
            return null;
        }
    }

    // Get comic by num
    public Optional<Comic> getComicByNum(int num) {
        return comicRepository.findByNum(num);
    }

    // Check if comic exists by num
    public boolean comicExistsByNum(int num) {
        return comicRepository.existsByNum(num);
    }

    // Check if comic exists by id
    public boolean comicExistsById(int id) {
        return comicRepository.existsById(id);
    }


    // Create a new comic
    public Comic createComic(Comic comic) {
        return comicRepository.save(comic);
    }

    // Update an existing comic
    public Optional<Comic> updateComic(int id, Comic updatedComic) {
        return comicRepository.findById(id).map(existingComic -> {
            existingComic.setNum(updatedComic.getNum());
            existingComic.setTitle(updatedComic.getTitle());
            // You can update other fields as needed
            return comicRepository.save(existingComic);
        });
    }

    // Delete a comic
    public boolean deleteComic(int id) {
        if (comicRepository.existsById(id)) {
            comicRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public Comic comicDtoToComic(ComicDto comicDto) {
        if (comicDto == null) {
            return null;
        }

        Comic comic = new Comic(comicDto.getTitle());
        comic.setNum(comicDto.getNum());
        // Set additional fields if needed, like imageUrl, altText, etc.
        // comic.setImg(comicDto.getImg());
        // comic.setAlt(comicDto.getAlt());

        return comic;
    }

    public ComicDto comicToComicDto(Comic comic) {
        if (comic == null) {
            return null;
        }

        ComicDto dto = new ComicDto();
        dto.setNum(comic.getNum());
        dto.setTitle(comic.getTitle());

        // Convert comments to DTO or String list (basic version below)
        List<String> commentTexts = new ArrayList<>();
        if (comic.getComments() != null) {
            for (Comment comment : comic.getComments()) {
                commentTexts.add(comment.getText());
            }
        }

        dto.setComments(comic.getComments());

        return dto;
    }



    // Add a comment to a comic
    public void addCommentToComic(int comicId, String text) {
        Optional<Comic> optionalComic = comicRepository.findByNum(comicId);

        if (optionalComic.isPresent()) {
            Comic comic = optionalComic.get();
            Comment comment = new Comment();
            comment.setText(text);
            comment.setComic(comic); // maintain bidirectional relationship
            comic.getComments().add(comment); // add comment to comic


            commentRepository.save(comment);

            comicRepository.save(comic); // persist changes (cascading assumed)

        }
    }

    // Get all comments for a comic
    public List<Comment> getCommentsForComic(int comicId) {
        return comicRepository.findById(comicId)
                .map(Comic::getComments)
                .orElse(List.of());
    }
}
