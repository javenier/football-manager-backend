package ua.com.javenier.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.javenier.entity.Player;
import ua.com.javenier.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Player player = playerService.findById(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player, @RequestParam Long teamId) {
        Player newPlayer = playerService.create(player, teamId);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        Player updatedPlayer = playerService.update(player);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/transfer")
    public ResponseEntity<Player> transfer(@RequestParam Long playerId, @RequestParam Long newTeamId) {
        Player transferedPlayer = playerService.transfer(playerId, newTeamId);
        return new ResponseEntity<>(transferedPlayer, HttpStatus.OK);
    }
}