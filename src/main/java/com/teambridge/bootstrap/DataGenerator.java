package com.teambridge.bootstrap;

import com.teambridge.dto.ProjectDTO;
import com.teambridge.dto.RoleDTO;
import com.teambridge.dto.UserDTO;
import com.teambridge.enums.Gender;
import com.teambridge.enums.Status;
import com.teambridge.service.ProjectService;
import com.teambridge.service.RoleService;
import com.teambridge.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * class to load initial data for the app
 */
@Component
public class DataGenerator implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final ProjectService projectService;

    public DataGenerator(RoleService roleService, UserService userService, ProjectService projectService) {
        this.roleService = roleService;
        this.userService = userService;
        this.projectService = projectService;
    }

    // When app is started, this run method will be executed first
    @Override
    public void run(String... args) throws Exception {

        RoleDTO adminRole = new RoleDTO(1L,"Admin");
        RoleDTO managerRole = new RoleDTO(2L,"Manager");
        RoleDTO employeeRole = new RoleDTO(3L,"Employee");

        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);

        UserDTO user1 = new UserDTO("Shaun","Hayns","shaun@teambridge.com",
                "345632890","Abc1", Gender.MALE, managerRole);
        UserDTO user2 = new UserDTO("Delisa","Smith","delisa@teambridge.com",
                "765632890","Abc1", Gender.FEMALE, adminRole);
        UserDTO user3 = new UserDTO("Craig","Jark","craig@teambridge.com",
                "095632890","Abc1", Gender.MALE, employeeRole);
        UserDTO user4 = new UserDTO("Elizabeth","Lerk","elizabeth@teambridge.com",
                "885632890","Abc1", Gender.FEMALE, managerRole);
        UserDTO user5 = new UserDTO("James","Knights","james@teambridge.com",
                "555632890","Abc1", Gender.MALE, adminRole);
        UserDTO user6 = new UserDTO("Kerem","Tok","kerem@teambridge.com",
                "111632890","Abc1", Gender.MALE, employeeRole);
        UserDTO user7 = new UserDTO("Maris","Ada","maria@teambridge.com",
                "995632890","Abc1", Gender.FEMALE, employeeRole);
        UserDTO user8 = new UserDTO("Bill","Tedd","bill@teambridge.com",
                "777632890","Abc1", Gender.MALE, managerRole);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);

        ProjectDTO project1 = new ProjectDTO("Spring MVC","PR001",user1, LocalDate.now(),LocalDate.now().plusDays(25),"Creating Controllers", Status.OPEN);
        ProjectDTO project2 = new ProjectDTO("Spring ORM","PR002",user2, LocalDate.now(),LocalDate.now().plusDays(10),"Creating Database", Status.IN_PROGRESS);
        ProjectDTO project3 = new ProjectDTO("Spring Container","PR003",user1, LocalDate.now(),LocalDate.now().plusDays(25),"Creating Container", Status.IN_PROGRESS);

        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);


    }
}
