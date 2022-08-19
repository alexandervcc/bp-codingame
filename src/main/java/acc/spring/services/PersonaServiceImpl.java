package acc.spring.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.repository.PersonaRepository;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class PersonaServiceImpl {
    private PersonaRepository personaRepository;
}
