package cinema.config;

import cinema.model.CinemaHall;
import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import cinema.service.impl.CinemaHallServiceImpl;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;
    private final CinemaHallServiceImpl cinemaHallService;

    public DataInitializer(RoleService roleService, UserService userService,
                           CinemaHallServiceImpl cinemaHallService) {
        this.roleService = roleService;
        this.userService = userService;
        this.cinemaHallService = cinemaHallService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);

        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);

        User bobUser = new User();
        bobUser.setEmail("bob@i.ua");
        bobUser.setPassword("12345678");
        bobUser.setRoles(Set.of(adminRole));
        userService.add(bobUser);

        User aliceUser = new User();
        aliceUser.setEmail("alice@i.ua");
        aliceUser.setPassword("87654321");
        aliceUser.setRoles(Set.of(userRole));
        userService.add(aliceUser);

        CinemaHall cinemaHallRed = new CinemaHall();
        cinemaHallRed.setDescription("red");
        cinemaHallRed.setCapacity(100);
        cinemaHallService.add(cinemaHallRed);

        CinemaHall cinemaHallGreen = new CinemaHall();
        cinemaHallGreen.setDescription("green");
        cinemaHallGreen.setCapacity(500);
        cinemaHallService.add(cinemaHallGreen);
    }
}
