package tn.esprit.spring.kaddem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EtudiantImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("John");
        etudiant.setPrenomE("Doe");
        etudiant.setOp(Option.GAMIX);

        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);

        Assertions.assertThat(savedEtudiant).isNotNull();
    }

    @Test
    void testRetrieveEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("John");
        etudiant.setPrenomE("Doe");
        etudiant.setOp(Option.GAMIX);

        Mockito.when(etudiantRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(etudiant));

        Etudiant savedEtudiant = etudiantService.retrieveEtudiant(etudiant.getIdEtudiant());

        Assertions.assertThat(savedEtudiant).isNotNull();
    }

    @Test
    void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("John");
        etudiant.setPrenomE("Doe");
        etudiant.setOp(Option.GAMIX);
        etudiants.add(etudiant);

        Mockito.when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> retrievedEtudiants = etudiantService.retrieveAllEtudiants();

        Assertions.assertThat(retrievedEtudiants).isNotNull();
    }

    /*@Test
    void testDeleteEtudiant() {
       
    	 Etudiant etudiant = new Etudiant();
         etudiant.setIdEtudiant(1);
         etudiant.setNomE("John");
         etudiant.setPrenomE("Doe");
         etudiant.setOp(Option.GAMIX);

         Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

         Mockito.doNothing().when(etudiantRepository).deleteById(Mockito.anyInt());

         etudiantService.addEtudiant(etudiant);

         Mockito.verify(etudiantRepository).save(etudiant);
         
         etudiantService.removeEtudiant(etudiant.getIdEtudiant());


         Mockito.verify(etudiantRepository).deleteById(etudiant.getIdEtudiant());
    }*/
}