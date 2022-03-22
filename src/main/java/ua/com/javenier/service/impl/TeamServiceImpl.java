package ua.com.javenier.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.javenier.entity.Team;
import ua.com.javenier.exception.EntityNotFoundException;
import ua.com.javenier.repository.TeamRepository;
import ua.com.javenier.service.TeamService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team create(Team entity) {
        return teamRepository.save(entity);
    }

    @Override
    public Team update(Team entity) {
        if (teamRepository.existsById(entity.getId()))
            return teamRepository.save(entity);
        throw new EntityNotFoundException("Team with id = " + entity.getId() + " has not been found");
    }

    @Override
    public void delete(Long id) {
        if (teamRepository.existsById(id))
            teamRepository.deleteById(id);
        else
            throw new EntityNotFoundException("Team with id = " + id + " has not been found");
    }

    @Override
    public Team findById(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isPresent())
            return team.get();
        throw new EntityNotFoundException("Team with id = " + id + " has not been found");
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }
}
