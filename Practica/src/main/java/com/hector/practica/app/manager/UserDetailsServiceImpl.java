package com.hector.practica.app.manager;


import com.hector.practica.app.model.Cliente;
import com.hector.practica.app.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByDni(dni);

        if(cliente == null) {
            throw new UsernameNotFoundException(String.format("El usuario %s no existe", dni));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        //se asigna el rol CUSTOMER
        authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
        //se crea el usuario con el dni y el password
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(cliente.getDni(), cliente.getPassword(), authorities);

        return userDetails;
    }
}
