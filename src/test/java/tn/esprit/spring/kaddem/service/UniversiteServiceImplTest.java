import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite();
        universite.setNomUniv("Example University");

        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite savedUniversite = universiteService.addUniversite(universite);

        assertThat(savedUniversite).isNotNull();
    }

    @Test
    void testRetrieveUniversite() {
        Universite universite = new Universite();
        universite.setIdUniv(1);
        universite.setNomUniv("Example University");

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Universite retrievedUniversite = universiteService.retrieveUniversite(universite.getIdUniv());

        assertThat(retrievedUniversite).isNotNull();
        assertThat(retrievedUniversite.getIdUniv()).isEqualTo(1); // Validate specific attributes
        assertThat(retrievedUniversite.getNomUniv()).isEqualTo("Example University");
    }

    @Test
    void testRetrieveAllUniversites() {
        Set<Universite> universites = new HashSet<>();
        Universite universite = new Universite();
        universite.setIdUniv(1);
        universite.setNomUniv("Example University");
        universites.add(universite);

        when(universiteRepository.findAll()).thenReturn(universites);

        Set<Universite> retrievedUniversites = universiteService.retrieveAllUniversites();

        assertThat(retrievedUniversites).isNotNull();
        assertThat(retrievedUniversites).hasSize(1); // Validate size
        assertThat(retrievedUniversites.iterator().next().getIdUniv()).isEqualTo(1); // Validate specific attributes
    }

    @Test
    void testDeleteUniversite() {
        Universite mockUniversite = new Universite();
        mockUniversite.setIdUniv(1);
        mockUniversite.setNomUniv("Example University");

        when(universiteRepository.findById(1)).thenReturn(Optional.of(mockUniversite));
        doNothing().when(universiteRepository).delete(mockUniversite);

        universiteService.addUniversite(mockUniversite);

        Universite retrieved = universiteService.retrieveUniversite(1);
        assertThat(mockUniversite).isEqualTo(retrieved);

        universiteService.deleteUniversite(1);

        verify(universiteRepository).delete(mockUniversite);
    }
}
