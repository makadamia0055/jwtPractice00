package org.zerock.api00.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.api00.domain.APIUser;
import org.zerock.api00.dto.APIUserDTO;
import org.zerock.api00.repository.APIUserRepository;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class APIUserDetailsService implements UserDetailsService {

    private final APIUserRepository apiUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("APIUserDetailsService apiUser-----------");

        Optional<APIUser> byId = apiUserRepository.findById(username);

        APIUser apiUser = byId.orElseThrow(() -> new UsernameNotFoundException("Cannot find mid" + username));



        APIUserDTO dto = new APIUserDTO(
                apiUser.getMid(),
                apiUser.getMpw(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        log.info(dto);

        return dto;
    }
}
