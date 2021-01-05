package co.edu.usbcali.banco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.Usuario;
import co.edu.usbcali.banco.util.FacesUtils;
import co.edu.usbcali.banco.vista.IDelegadoDeNegocio;

import java.util.ArrayList;
import java.util.List;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Component("bancoAutProvider")
public class BancoAutProvider implements AuthenticationProvider {
    /**
     * Security Implementation
     */
	
	@Autowired
	private IDelegadoDeNegocio delegadoDeNegocio;
	
    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(name);
        Usuario usuario = delegadoDeNegocio.consultarUsuarioPorId(name);
        
        if (name.equals("admin") && password.equals("admin")) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

            final UserDetails principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal,
                    password, grantedAuths);
            
            FacesUtils.putinSession("usuario", "admin");

            return auth;
        } else if (name.equals("comercial") && password.equals("12345")) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_COMERCIAL"));

            final UserDetails principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal,
                    password, grantedAuths);
            
            FacesUtils.putinSession("usuario", "comercial");

            return auth;
        } else if (cuenta!=null && password.equals(cuenta.getClave())) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

            final UserDetails principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal,
                    password, grantedAuths);
            
            FacesUtils.putinSession("usuario", cuenta);

            return auth;
        } else if (usuario!=null && password.equals(usuario.getClave())) {
        	if(usuario.getTipoUsuario().getTiusId() == 1L) {
        		final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_CAJERO"));

                final UserDetails principal = new User(name, password, grantedAuths);
                final Authentication auth = new UsernamePasswordAuthenticationToken(principal,
                        password, grantedAuths);
                
                FacesUtils.putinSession("usuario", usuario);
                return auth;
        	} else if(usuario.getTipoUsuario().getTiusId() == 2L) {
        		final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_COMERCIAL"));

                final UserDetails principal = new User(name, password, grantedAuths);
                final Authentication auth = new UsernamePasswordAuthenticationToken(principal,
                        password, grantedAuths);
                
                FacesUtils.putinSession("usuario", usuario);
                return auth;
        	} else if(usuario.getTipoUsuario().getTiusId() == 3L) {
        		final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

                final UserDetails principal = new User(name, password, grantedAuths);
                final Authentication auth = new UsernamePasswordAuthenticationToken(principal,
                        password, grantedAuths);
                
                FacesUtils.putinSession("usuario", usuario);
                return auth;
        	}
        	return null;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
