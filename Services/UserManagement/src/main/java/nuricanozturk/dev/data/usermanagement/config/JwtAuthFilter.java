package nuricanozturk.dev.data.usermanagement.config;

import callofproject.dev.service.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nuricanozturk.dev.data.usermanagement.dal.UserServiceHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter
{
    private final UserServiceHelper m_serviceHelper;

    public JwtAuthFilter(UserServiceHelper userServiceHelper)
    {
        m_serviceHelper = userServiceHelper;
    }

    /**
     * Filter the request and check if the token is valid.
     *
     * @param request     The request.
     * @param response    The response.
     * @param filterChain The filter chain.
     * @throws ServletException If the servlet encounters difficulty.
     * @throws IOException      If the servlet encounters difficulty.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        var authenticationHeader = request.getHeader("Authorization");

        if (authenticationHeader != null && authenticationHeader.startsWith("Bearer "))
        {
            var token = authenticationHeader.substring(7);
            var username = JwtUtil.extractUsername(token); // from token

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                var user = m_serviceHelper.findByUsername(username);

                if (JwtUtil.isTokenValid(token, username) && username.equals(user.get().getUsername()))
                {
                    var authToken = new UsernamePasswordAuthenticationToken(user, null, null);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}