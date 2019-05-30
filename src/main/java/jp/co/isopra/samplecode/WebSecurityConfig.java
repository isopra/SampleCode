package jp.co.isopra.samplecode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jp.co.isopra.samplecode.service.LoginService;

/**
 * ログイン認証の設定用クラス
 * @author masumi.sato
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginService loginService;

	/**
	 * セキュリティの設定
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	// ログインなしでアクセスできるURLと、保護するURLを定義
        http.authorizeRequests()
        .antMatchers("/member/register", "/login", "/authenticate", "/error").permitAll()
        .antMatchers("/**").hasRole("MEMBER") // 権限によるアクセス範囲の制御。
        .anyRequest().authenticated()
        .and()
        .formLogin() //ログインページを指定、だれでもアクセス可。
            .loginPage("/login")
			.loginProcessingUrl("/authenticate")
			.usernameParameter("login_id")
			.passwordParameter("password")
			.failureUrl("/login?error=1")
			.permitAll();
    }

    /**
     * パスワードの暗号化方式を定義
     * @return
     */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 認証で使用するサービスクラスと暗号化方式を設定
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		//
		auth.userDetailsService(loginService)
			.passwordEncoder(passwordEncoder());
	}
}
