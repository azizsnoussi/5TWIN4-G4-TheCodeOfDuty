package tn.esprit.spring.kaddem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ContratImplTest {

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    
    @BeforeEach
    void setUp() {
        assertThat(etudiantRepository).isNotNull(); 
    }

    @Test
    void testAddContratException() {

    	ContratServiceImpl c = new ContratServiceImpl();
    	Contrat contract = mock(Contrat.class);
    	assertThrows(Exception.class,()->c.addContrat(contract));
    
    }

    @Test
    void testRetrieveContrat() {
        int contratId = 1;
        Contrat existingContrat = new Contrat();
        existingContrat.setIdContrat(contratId);
        existingContrat.setDateDebutContrat(new Date());
        existingContrat.setSpecialite(Specialite.IA);
        existingContrat.setArchive(false);
        existingContrat.setMontantContrat(500);

        when(contratRepository.findById(contratId)).thenReturn(Optional.of(existingContrat));

        Contrat retrievedContrat = contratService.retrieveContrat(contratId);

        assertThat(retrievedContrat).isNotNull();
    }

    @Test
    void testRetrieveAllContrats() {
        List<Contrat> contratList = new ArrayList<>();
        Contrat contrat1 = new Contrat();
        contrat1.setIdContrat(1);
        contrat1.setDateDebutContrat(new Date());
        contrat1.setSpecialite(Specialite.IA);
        contrat1.setArchive(false);
        contrat1.setMontantContrat(500);
        contratList.add(contrat1);

        when(contratRepository.findAll()).thenReturn(contratList);

        List<Contrat> retrievedContrats = contratService.retrieveAllContrats();

        assertThat(retrievedContrats).isNotNull();
    }

    @Test
    void testRemoveContrat() {
        int contratId = 1;
        Contrat existingContrat = new Contrat();
        existingContrat.setIdContrat(contratId);
        existingContrat.setDateDebutContrat(new Date());
        existingContrat.setSpecialite(Specialite.IA);
        existingContrat.setArchive(false);
        existingContrat.setMontantContrat(500);

        when(contratRepository.findById(contratId)).thenReturn(Optional.of(existingContrat));
        doNothing().when(contratRepository).delete(existingContrat);

        contratService.removeContrat(contratId);

        verify(contratRepository).delete(existingContrat);
    }
}
