package ua.com.javenier.service;

import ua.com.javenier.entity.Team;

public interface TeamService extends BaseService<Team> {

    Team create(Team team);
}
