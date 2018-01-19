package ai.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class LoginConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest()
			.fullyAuthenticated()
		.and().csrf().disable()
		.formLogin().loginPage("/login").permitAll()
		.and().logout().logoutSuccessUrl("/login?logout").permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication()
			.userDnPatterns("uid={0}")
			//.groupSearchBase("ou=groups")
			.contextSource().url("ldap://localhost:8389/dc=springframework,dc=org").and()
			.passwordCompare().passwordEncoder(new PlaintextPasswordEncoder())
			.passwordAttribute("userPassword");
	}
}
