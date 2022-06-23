package com.mykare.healthapp.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mykare.healthapp.shared.Views;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.beans.Transient;
import java.util.Collection;

/**
 * <p>The class is for encapsulated data model of User and also act as Entity class</p>
 * @author vishnu.kp
 * @version 1.0
 * @since June, 15 2020
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class  User implements UserDetails
{
    private static final String NOTNULL_VALIDATION="{mykare.validation.constraints.NotNull.message}";

    private static final String PASS_NOTNULL_VALIDATION="{mykare.validation.constraints.NotNull.password.message}";

    private static final String GENDER_NOTNULL_VALIDATION="{mykare.validation.constraints.NotNull.gender.message}";

    private static final String EMAIL_VALIDATION="{mykare.validation.constraints.valid.email.message}";

    /*id it auto incremented*/
    @Id
    @GeneratedValue
    @JsonView(Views.Base.class)
    private int userId;

    /*Name of the User*/
    @NotNull(message = NOTNULL_VALIDATION)
    @Size(min=4 , max = 255)
    @JsonView(Views.Base.class)
    private String userName;

    /*Password of User ,no need to show the user*/
    @NotNull(message = PASS_NOTNULL_VALIDATION)
    @Size(min=8, max = 255)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;

    /*Email ID of the User*/
    @NotNull
    @Email(message = EMAIL_VALIDATION)
    @Column(unique = true)
    @Size(min=4, max = 255)
    @Pattern(regexp ="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    @JsonView(Views.Base.class)
    private String email;

    /*Gender of the User*/
    @NotNull(message = GENDER_NOTNULL_VALIDATION)
    @JsonView(Views.Base.class)
    private String gender;

    /**
     * @author vishnu.kp
     * @since June, 15 20222
     * @param email the email of the user
     * @param UserName the name of the user
     * @param gender the gender of user
     * @param password the password of user
     */
    public User(String email, String UserName, String gender, String password) {
        this.email = email;
        this.userName = UserName;
        this.gender = gender;
        this.password = password;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_USER");
    }

    @Override
    @Transient
    public String getUsername() {
        return this.email;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
}
