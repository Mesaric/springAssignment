package com.ct.springAssignmentProj.dataloader;

import com.ct.springAssignmentProj.appuser.AppUser;
import com.ct.springAssignmentProj.appuser.AppUserRole;
import com.ct.springAssignmentProj.appuser.AppUserService;
import com.ct.springAssignmentProj.greeting.Greeting;
import com.ct.springAssignmentProj.greeting.GreetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/* DataLoader Component
 * Class that's used for adding a few entries for easier runtime testing
 */


@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final GreetingRepository greetingRepository;
    private final AppUserService appUserService;

    @Override
    public void run(String... args) throws Exception {
        greetingRepository.save(new Greeting("Hello world", "EN"));
        greetingRepository.save(new Greeting("Pozdravljen svet", "SL"));
        greetingRepository.save(new Greeting("Ahoj svet", "SK"));
        greetingRepository.save(new Greeting("Hola mundo", "ES"));
        greetingRepository.save(new Greeting("Hallo welt", "DE"));

        greetingRepository.save(new Greeting("Bonjour le monde", "FR"));
        greetingRepository.save(new Greeting("Witaj swiecie", "PL"));
        greetingRepository.save(new Greeting("Halo dunia", "ID"));
        greetingRepository.save(new Greeting("Ciao mondo", "IT"));
        greetingRepository.save(new Greeting("Hallo wereld", "NL"));


        appUserService.signUpUser(new AppUser("user@gmail.com", "123", AppUserRole.USER));
        appUserService.signUpUser(new AppUser("admin@gmail.com", "123", AppUserRole.ADMIN));


    }
}
