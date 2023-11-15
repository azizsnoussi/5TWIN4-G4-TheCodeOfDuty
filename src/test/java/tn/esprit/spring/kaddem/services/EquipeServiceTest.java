package tn.esprit.spring.kaddem.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class EquipeServiceTest {
    @InjectMocks
    public EquipeServiceImpl EquipeServiceImpl;
    @Mock
    public EquipeRepository equipeRepository;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void TestaddEquipe() {
        Equipe equipe = new Equipe();

        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe resultEt = EquipeServiceImpl.addEquipe(equipe);

        assertEquals(equipe, resultEt);
    }
    @Test
    void testRetrieveAllequipes() {
        List<Equipe> equipes = new ArrayList<>();
        Equipe equipe1 = new Equipe();
        Equipe equipe2 = new Equipe();
        equipes.add(equipe1);
        equipes.add(equipe2);

        when(equipeRepository.findAll()).thenReturn(equipes);


        List<Equipe> resultEt = EquipeServiceImpl.retrieveAllEquipes();


        verify(equipeRepository).findAll();


        assertEquals(equipes, resultEt);
    }

}
