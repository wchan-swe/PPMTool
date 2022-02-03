package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception ex) {
            throw new ProjectIdException(String.format("Project ID %s already exists", project.getProjectIdentifier()));
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        // don't need try catch here there's no exceptions to be caught need if statement to throw exception
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException(String.format("Project ID %s does not exist", projectId));
        }

        return project;
    }
}
