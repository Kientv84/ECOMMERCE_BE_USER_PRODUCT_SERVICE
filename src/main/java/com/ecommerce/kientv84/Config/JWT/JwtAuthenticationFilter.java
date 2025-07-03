package com.ecommerce.kientv84.Config.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component // Đánh dấu class là một component, cho phép spring quản lý bởi spring container và tự động tạo một instance cho class đó ==> cho phép inject và sử dụng
public class JwtAuthenticationFilter extends OncePerRequestFilter { //OncePerRequestFilter là một class trong spring sercurity,

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, //doFilterInternal() đảm bảo chỉ chạy 1 lần cho mỗi request Http
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // cát chuỗi từ 7 để lấy token
            try {
                String email = jwtUtil.validateToken(token); // nếu sai sẽ throw exception

                // Tạo đối tượng Authentication và gán vào SecurityContextHolder
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(email, null, null); // quyền tạm thời null

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Gán thông tin xác thực vào Spring Security
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } catch (Exception e) {
                // Token không hợp lệ => bỏ qua => Spring Security sẽ chặn sau
                System.out.println("Invalid token: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response); // Tiếp tục với request
    }
}

//Khi ta config như vậy thì đầu tiên cần gọi api login và sau đó token sẽ được tạo ra
// từ dó cần chèn vào authorize header để bởi spring sercurity có thể xác thực và chúng ta có thể gọi các api khác.
// và khi token hết hạn thì cần gọi lại api login tôi và dán lại.