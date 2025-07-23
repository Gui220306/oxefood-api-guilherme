package br.com.ifpe.oxefood.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.ifpe.oxefood.modelo.acesso.Perfil;
import br.com.ifpe.oxefood.modelo.segurança.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean // indica que o método retorna um bean gerenciado pelo Spring (bean é um objeto que o Spring gerencia)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      //vai definir o que pode ou não ser acessado na aplicação, e quais filtros serão usados (Obs: por padrão, o Spring Security já deixa tudo privado, então é necessário liberar o que pode ser acessado)
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(c -> c.disable())
            .authorizeHttpRequests(authorize -> authorize
              //Está permitindo que o método POST do controller de cliente seja acessado sem autenticação 
                .requestMatchers(HttpMethod.POST, "/api/cliente").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth").permitAll()
                // permite retornar todos os produtos sem autenticação
                .requestMatchers(HttpMethod.GET, "/api/produto").permitAll()
                  // permite acessar o post dos funcionários sem autenticação
                .requestMatchers(HttpMethod.POST, "/api/funcionario").permitAll()

                // Permite alterar produtos sem autenticação (o asterisco indica que qualquer id pode ser passado)
                .requestMatchers(HttpMethod.PUT, "/api/produto/*").permitAll()
            
                 .requestMatchers(HttpMethod.GET, "/api/produto/").hasAnyAuthority(
                   Perfil.ROLE_CLIENTE,
                   Perfil.ROLE_FUNCIONARIO_ADMIN,
                   Perfil.ROLE_FUNCIONARIO_USER) //Consulta de produto

               .requestMatchers(HttpMethod.POST, "/api/produto").hasAnyAuthority(
                   Perfil.ROLE_FUNCIONARIO_ADMIN,
                   Perfil.ROLE_FUNCIONARIO_USER) //Cadastro de produto

               .requestMatchers(HttpMethod.PUT, "/api/produto/*").hasAnyAuthority(
                   Perfil.ROLE_FUNCIONARIO_ADMIN,
                   Perfil.ROLE_FUNCIONARIO_USER) //Alteração de produto
                  
               .requestMatchers(HttpMethod.DELETE, "/api/produto/*").hasAnyAuthority(
                   Perfil.ROLE_FUNCIONARIO_ADMIN) //Exclusão de produto


                .requestMatchers(HttpMethod.GET, "/api-docs/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()

                .anyRequest().authenticated()

            )
            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )            
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
    
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);    
        return source;
    }
}

