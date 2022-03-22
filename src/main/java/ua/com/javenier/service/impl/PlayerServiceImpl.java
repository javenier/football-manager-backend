package ua.com.javenier.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.javenier.entity.Player;
import ua.com.javenier.entity.Team;
import ua.com.javenier.exception.EntityNotFoundException;
import ua.com.javenier.exception.NotEnoughMoneyException;
import ua.com.javenier.repository.PlayerRepository;
import ua.com.javenier.repository.TeamRepository;
import ua.com.javenier.service.PlayerService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Player create(Player entity, Long teamId) {
        Team team = teamRepository.getById(teamId);
        entity.setTeam(team);
        return playerRepository.save(entity);
    }

    @Override
    public Player transfer(Long playerId, Long newTeamId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty())
            throw new EntityNotFoundException("Player with id = " + playerId + " has not been found");
        Player player = playerOptional.get();
        Optional<Team> newTeamOptional = teamRepository.findById(newTeamId);
        if (newTeamOptional.isEmpty())
            throw new EntityNotFoundException("Team with id = " + newTeamId + " has not been found");
        Team newTeam = newTeamOptional.get();
        transferPaymentProcess(player, newTeam);
        player.setTeam(newTeam);
        return playerRepository.save(player);
    }

    @Override
    public Player update(Player entity) {
        if (playerRepository.existsById(entity.getId()))
            return playerRepository.save(entity);
        throw new EntityNotFoundException("Player with id = " + entity.getId() + " has not been found");
    }

    @Override
    public void delete(Long id) {
        if (playerRepository.existsById(id))
            playerRepository.deleteById(id);
        else
            throw new EntityNotFoundException("Player with id = " + id + " has not been found");
    }

    @Override
    public Player findById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent())
            return player.get();
        throw new EntityNotFoundException("Player with id = " + id + " has not been found");
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    private Long calcTransferSum(Player player) {
        long monthsOfExperience = Math.abs(
                ChronoUnit.MONTHS.between(player.getStartOfCareer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        LocalDate.now()));
        long transferCost = monthsOfExperience * 100000 / player.getAge();
        long fullSum = transferCost + transferCost * player.getTeam().getCommissionFee() / 100;
        return fullSum;
    }

    private void transferPaymentProcess(Player player, Team newTeam) {
        long transferSum = calcTransferSum(player);
        if (newTeam.getTransferBalance() < transferSum)
            throw new NotEnoughMoneyException("The team " + newTeam.getName()
                    + " has not got enough money to buy " +
                    player.getFirstName() + " " + player.getLastName());
        newTeam.setTransferBalance(newTeam.getTransferBalance() - transferSum);
        player.getTeam().setTransferBalance(player.getTeam().getTransferBalance() + transferSum);
    }
}
