import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite();
        universite.setName("Sample University");
        // Add other properties as needed

        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite savedUniversite = universiteService.addUniversite(universite);

        assertThat(savedUniversite).isNotNull();
    }

    @Test
    void testUpdateUniversite() {
        Universite universite = new Universite();
        universite.setIdUniversite(1);
        universite.setName("Updated University");
        // Add other properties as needed

        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite updatedUniversite = universiteService.updateUniversite(universite);

        assertThat(updatedUniversite).isNotNull();
        assertThat(updatedUniversite.getName()).isEqualTo("Updated University");
    }

    @Test
    void testRetrieveUniversite() {
        Universite universite = new Universite();
        universite.setIdUniversite(1);
        universite.setName("Sample University");
        // Add other properties as needed

        when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(universite));

        Universite retrievedUniversite = universiteService.retrieveUniversite(universite.getIdUniversite());

        assertThat(retrievedUniversite).isNotNull();
    }

    @Test
    void testDeleteUniversite() {
        Universite mockUniversite = new Universite();
        mockUniversite.setIdUniversite(1);
        mockUniversite.setName("Sample University");
        // Add other properties as needed

        when(universiteRepository.findById(1)).thenReturn(Optional.of(mockUniversite));
        doNothing().when(universiteRepository).delete(mockUniversite);

        universiteService.deleteUniversite(1);

        verify(universiteRepository).delete(mockUniversite);
    }

    @Test
    void testAssignUniversiteToDepartement() {
        Universite universite = new Universite();
        universite.setIdUniversite(1);
        universite.setName("Sample University");

        Departement departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("IT");

        when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(universite));
        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(1, 1);

        assertThat(universite.getDepartements()).contains(departement);
        verify(universiteRepository).save(universite);
    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        Universite universite = new Universite();
        universite.setIdUniversite(1);
        universite.setName("Sample University");

        Set<Departement> departements = new HashSet<>();
        Departement departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("IT");
        departements.add(departement);

        universite.setDepartements(departements);

        when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(universite));

        Set<Departement> retrievedDepartements = universiteService.retrieveDepartementsByUniversite(1);

        assertThat(retrievedDepartements).isNotNull();
        assertThat(retrievedDepartements.size()).isEqualTo(1);
    }
}
