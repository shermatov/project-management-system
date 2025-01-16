package com.shermatov.project_management_system.repository;

import com.shermatov.project_management_system.model.Project;
import com.shermatov.project_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

//    List<Project> findAllByOwner(User owner);
    List<Project> findByNameContainingAndTeamContains(String partialName, User user);

//    @Query("select p from Project p join p.team t where t=:user")
//    List<Project> findProjectByTeam(@Param("user") User user);

    List<Project> findByTeamContainingOrOwner(User user, User owner);
}
