package com.ct.springassignmentproj.dataloader;

import com.ct.springassignmentproj.appuser.AppUser;
import com.ct.springassignmentproj.appuser.AppUserRole;
import com.ct.springassignmentproj.appuser.AppUserService;
import com.ct.springassignmentproj.translation.Translation;
import com.ct.springassignmentproj.translation.TranslationRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/* DataLoader Component
 * Class that's used for adding a few entries for easier runtime testing
 */


@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final TranslationRepository translationRepository;
    private final AppUserService appUserService;

    @Override
    public void run(String... args) throws Exception {
        translationRepository.save(new Translation("Hello world", "EN"));
        translationRepository.save(new Translation("Pozdravljen svet", "SL"));
        translationRepository.save(new Translation("Ahoj svet", "SK"));
        translationRepository.save(new Translation("Hola mundo", "ES"));
        translationRepository.save(new Translation("Hallo welt", "DE"));

        translationRepository.save(new Translation("Bonjour le monde", "FR"));
        translationRepository.save(new Translation("Witaj swiecie", "PL"));
        translationRepository.save(new Translation("Halo dunia", "ID"));
        translationRepository.save(new Translation("Ciao mondo", "IT"));
        translationRepository.save(new Translation("Hallo wereld", "NL"));


        appUserService.signUpUser(new AppUser("user@gmail.com", "123", AppUserRole.USER));
        appUserService.signUpUser(new AppUser("admin@gmail.com", "123", AppUserRole.ADMIN));


    }
}
