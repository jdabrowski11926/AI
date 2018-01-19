package ai.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import ai.project.config.StorageConfig;
import ai.project.dao.User;
import ai.project.dao.UserRepository;
import ai.project.services.StorageService;


@SpringBootApplication
@EnableConfigurationProperties(StorageConfig.class)
public class ProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
	
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
        	//Dodawanie pierwszego uzytkownika do systemu
        	try {
    		User n = new User();
    		n.setName("Admin");
    		n.setEmail("admin@admin.adm");
    		n.setPassword("password");
    		userRepository.save(n);
        	}catch(Exception e){
        		//Admin juz istnieje nie trzeba go tworzyc
        		log.info("Skipped creation of Admin user");
        	}
            storageService.deleteAll();
            storageService.init();
        };
    }
}
