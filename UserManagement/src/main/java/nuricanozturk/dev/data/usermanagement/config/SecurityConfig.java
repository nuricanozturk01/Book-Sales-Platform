package nuricanozturk.dev.data.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig
{

    private final JwtAuthFilter m_jwtAuthFilter;
    private final UserAuthProvider m_authenticationProvider;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserAuthProvider authenticationProvider)
    {
        m_jwtAuthFilter = jwtAuthFilter;
        m_authenticationProvider = authenticationProvider;
    }

    /**
     * Customize security
     *
     * @param security represent the security
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception
    {
        return security
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(this::authorizationRequests)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(m_authenticationProvider)
                .addFilterBefore(m_jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }

    /**
     * Get the authentication manager
     *
     * @param authenticationConfiguration represent the authentication configuration
     * @return the authentication manager
     * @throws Exception if the authentication manager encounters difficulty
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Customize authorization requests
     *
     * @param requests represent the request
     */
    private void authorizationRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry requests)
    {
        requests.anyRequest().permitAll();
    }
}
