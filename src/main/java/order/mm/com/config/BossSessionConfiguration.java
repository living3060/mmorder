package order.mm.com.config;

/*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



//@Configuration
//@EnableWebSecurity
public class BossSessionConfiguration extends WebSecurityConfigurerAdapter {
    String[] ignore=new String[]{"/static/**","/api/**","/boss/user/login/**","/boss/public/**","/boss/order/**"};

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("admin").password("123456").roles("ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(ignore);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(ignore).permitAll();
        super.configure(http);
    }

}
*/
