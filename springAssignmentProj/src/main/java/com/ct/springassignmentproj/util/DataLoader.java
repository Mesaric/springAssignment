package com.ct.springassignmentproj.util;

import com.ct.springassignmentproj.model.AppUser;
import com.ct.springassignmentproj.model.AppUserRole;
import com.ct.springassignmentproj.service.AppUserService;
import com.ct.springassignmentproj.model.Translation;
import com.ct.springassignmentproj.repository.TranslationRepository;
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
        translationRepository.save(new Translation("Hello world", "en"));
        translationRepository.save(new Translation("Pozdravljen svet", "sl"));
        translationRepository.save(new Translation("Ahoj svet", "sk"));
        translationRepository.save(new Translation("Hola mundo", "es"));
        translationRepository.save(new Translation("Hallo welt", "de"));
        translationRepository.save(new Translation("Bonjour le monde", "fr"));
        translationRepository.save(new Translation("Witaj swiecie", "pl"));
        translationRepository.save(new Translation("Halo dunia", "id"));
        translationRepository.save(new Translation("Ciao mondo", "it"));
        translationRepository.save(new Translation("Hallo wereld", "nl"));
        translationRepository.save(new Translation("Γεια σας κόσμε", "el"));
        translationRepository.save(new Translation("Привіт, світ", "uk"));

        appUserService.signUpUser(new AppUser("user@gmail.com", "123", AppUserRole.USER));
        appUserService.signUpUser(new AppUser("admin@gmail.com", "123", AppUserRole.ADMIN));
    }
}
