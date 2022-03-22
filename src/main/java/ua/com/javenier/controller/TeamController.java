package ua.com.javenier.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.javenier.entity.Team;
import ua.com.javenier.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.findAll();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.findById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team newTeam = teamService.create(team);
        return new ResponseEntity<>(newTeam, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        Team updatedTeam = teamService.update(team);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        teamService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
