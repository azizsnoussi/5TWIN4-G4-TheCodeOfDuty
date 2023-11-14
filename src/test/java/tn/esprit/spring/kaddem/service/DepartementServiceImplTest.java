import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddDepartement() {
        Departement departement = new Departement();
        departement.setNomDepart("IT");
        // Add other properties as needed

        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        Departement savedDepartement = departementService.addDepartement(departement);

        assertThat(savedDepartement).isNotNull();
    }

    @Test
    void testRetrieveDepartement() {
        Departement departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("IT");
        // Add other properties as needed

        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(departement));

        Departement retrievedDepartement = departementService.retrieveDepartement(departement.getIdDepart());

        assertThat(retrievedDepartement).isNotNull();
    }

    @Test
    void testRetrieveAllDepartements() {
        List<Departement> departements = new ArrayList<>();
        Departement departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("IT");
        // Add other properties as needed
        departements.add(departement);

        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> retrievedDepartements = departementService.retrieveAllDepartements();

        assertThat(retrievedDepartements).isNotNull();
    }

    @Test
    void testDeleteDepartement() {
        Departement mockDepartement = new Departement();
        mockDepartement.setIdDepart(1);
        mockDepartement.setNomDepart("IT");
        // Add other properties as needed

        when(departementRepository.findById(1)).thenReturn(Optional.of(mockDepartement));
        doNothing().when(departementRepository).delete(mockDepartement);

        departementService.addDepartement(mockDepartement);

        Departement retrieved = departementService.retrieveDepartement(1);
        assertThat(mockDepartement).isEqualTo(retrieved);

        departementService.deleteDepartement(1);

        verify(departementRepository).delete(mockDepartement);
    }
}
