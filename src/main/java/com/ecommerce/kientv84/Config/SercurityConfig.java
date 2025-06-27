package com.ecommerce.kientv84.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


//@Configuration đánh dấu đây là class config để quản lý các bean
@Configuration
public class SercurityConfig {

    //Anotation @Bean dùng để đánh dấu một method trả v một object để spring container nhận thấy và quản lý, dùng để custom Bean
    // Lúc này chúng ta có thể sd anotation @Autowired để inject và sử dụng
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Default hash by 10
    }


    // Cấu hình filter chain để tắt password mặc định của Spring Security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // SecurityFilterChain đại diện cho clas cấu hình bảo mật (Sercurity) trong Spring sercurity 6+
        // filterChain() chính là nơi bạn cấu hình cho phép hay chặn các request HTTP.
        // dùng http để cấu hình: Cho phép/không cho phép truy cập. Bảo mật password. Tắt bật các chức năng bảo mật như CSRF, CORS...
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF nếu là API, CSRF = Cross Site Request Forgery (Giả mạo request).
                //Được bật mặc định trong Spring Security, nhưng khi bạn làm REST API thì nên tắt đi.
                .formLogin(form -> form.disable())
                .authorizeHttpRequests(auth -> auth //cấu hình quyền truy cập cho các URL endpoint.
                        .requestMatchers("/system_user/**", "/authentication/**").permitAll() // cho phép gọi không cần login, Tất cả các request bắt đầu bằng /system_user/ và /auth/ sẽ được phép truy cập mà không cần login.
                        .anyRequest().authenticated() // các endpoint khác cần login, Các request khác bắt buộc phải đăng nhập (có xác thực).
                )

                .httpBasic(Customizer.withDefaults()); // có thể dùng hoặc không

        return http.build();
    }
}
