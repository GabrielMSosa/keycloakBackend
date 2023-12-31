package com.keycloadegbaeduling.keycloadegbaeduling;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = new KeycloakAuthenticationProvider();
        //SimpleAuthorityMapper adds the prefix ROLE_ to keycloak roles.
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    //use below code for application.properties instead of WEB-INF/keycloack.json



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        super.configure(http);
        http.authorizeRequests()

                .antMatchers("/api/hello").permitAll()
                .antMatchers("/api/departments").hasRole("ADMIN")
                .antMatchers("/api/demo").hasRole("ADMIN")
                .antMatchers("/api/manager").hasRole("MANAGER")
                .antMatchers("/api/employees").hasRole("USER")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/access-denied-response");
    }

}