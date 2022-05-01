package com.ultimate.springreact.runner;

import com.ultimate.springreact.model.Project;
import com.ultimate.springreact.model.User;
import com.ultimate.springreact.model.UserProject;
import com.ultimate.springreact.model.UserProjectId;
import com.ultimate.springreact.repository.ProjectRepository;
import com.ultimate.springreact.repository.UserProjectRepository;
import com.ultimate.springreact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FirstExecutionRunner implements CommandLineRunner {

  private UserRepository userRepository;
  private ProjectRepository projectRepository;
  private UserProjectRepository userProjectRepository;
  private PasswordEncoder encoder;

  @Autowired
  public FirstExecutionRunner(UserRepository userRepository, ProjectRepository projectRepository,
          UserProjectRepository userProjectRepository, PasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.projectRepository = projectRepository;
    this.userProjectRepository = userProjectRepository;
    this.encoder = encoder;
  }

  @Override
  @Transactional
  public void run(String... args) {
    if (userRepository.count() > 0) return;

    User admin = new User();
    admin.setEmail("admin@teste.com");
    admin.setAdmin(true);
    admin.setName("Administrador");
    admin.setPassword(encoder.encode("teste123"));
    userRepository.save(admin);

    User p1 = new User();
    p1.setEmail("p1@teste.com");
    p1.setAdmin(false);
    p1.setName("Programador Nº1");
    p1.setPassword(encoder.encode("teste123"));
    p1 = userRepository.save(p1);

    User p2 = new User();
    p2.setEmail("p2@teste.com");
    p2.setAdmin(false);
    p2.setName("Programador Nº2");
    p2.setPassword(encoder.encode("teste123"));
    p2 = userRepository.save(p2);

    Project project = new Project();
    project.setName("Projeto KD Minha Oficina");
    project = projectRepository.save(project);

    Project project2 = new Project();
    project2.setName("Projeto BMW");
    project2 = projectRepository.save(project2);

    UserProject up1 = new UserProject();
    up1.setUserProjectId(new UserProjectId(p1.getId(), project.getId()));
    up1.setUser(p1);
    up1.setProject(project);
    userProjectRepository.save(up1);

    UserProject up2 = new UserProject();
    up2.setUserProjectId(new UserProjectId(p2.getId(), project.getId()));
    up2.setUser(p2);
    up2.setProject(project);
    userProjectRepository.save(up2);

    UserProject up3 = new UserProject();
    up3.setUserProjectId(new UserProjectId(p2.getId(), project2.getId()));
    up3.setUser(p2);
    up3.setProject(project2);
    userProjectRepository.save(up3);
  }
}
