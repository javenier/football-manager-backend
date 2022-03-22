package ua.com.javenier.service;

import ua.com.javenier.entity.Player;

public interface PlayerService extends BaseService<Player> {

    Player create(Player player, Long teamId);
    Player transfer(Long playerId, Long newTeamId);
}
