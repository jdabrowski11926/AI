package ai.project.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ai.project.dao.User;

public class CustomUserDetails extends User implements UserDetails{

	public CustomUserDetails(User user) {
		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return super.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override//Dziedziczone z klasu User ale wystepuje tez w UserDetails (dodalem to dla czytelnosci kodu).
	public String getPassword() {
		return super.getPassword();
	}

}
