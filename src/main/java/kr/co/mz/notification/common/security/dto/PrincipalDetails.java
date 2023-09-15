package kr.co.mz.notification.common.security.dto;

import kr.co.mz.notification.domain.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
public class PrincipalDetails implements UserDetails {

    private UserEntity userEntity;

    public PrincipalDetails(UserEntity userEntity) {        // PrincipalDetails 안에 User 정보를 넣기 위해 생성자에 셋팅!
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 단수 role 형태일때
        Collection<GrantedAuthority> collections = new ArrayList<>();
        collections.add(() -> String.valueOf(userEntity.getRole()));
        return collections;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getName();
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
}
