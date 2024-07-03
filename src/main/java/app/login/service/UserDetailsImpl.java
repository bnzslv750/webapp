package app.login.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import app.login.entity.UserEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

	private final UserEntity userEntity;
	
	// ユーザーに付与された権限を返す(ユーザ権限固定)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		return authorities;
	}
	
	// ユーザーの認証に使用されるパスワードを返す
	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}
	
	// ユーザーの認証に使用されるユーザー名(今回はID)を返す
	@Override
	public String getUsername() {
		return userEntity.getUsername();
	}
	
	 // ユーザーのアカウントの有効期限が切れていないことを返却
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // ユーザーがロック解除されていることを返却
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // ユーザーの資格情報（パスワード）の有効期限が切れていないことを返却
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // ユーザーが有効であることを返却
    @Override
    public boolean isEnabled() {
        return true;
    }


}
