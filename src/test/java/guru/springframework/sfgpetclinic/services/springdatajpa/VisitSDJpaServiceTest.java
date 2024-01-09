package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Visit SDJ Service Test - ")
class VisitSDJpaServiceTest {
    @Mock
    VisitRepository visitRepository;
    @InjectMocks
    VisitSDJpaService service;

    @Test
    @DisplayName("Find All")
    void findAllTest() {

        Set<Visit> visits = new HashSet<>();
        visits.add(new Visit());
        visits.add(new Visit());

        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> foundVisits = service.findAll();

        verify(visitRepository).findAll();

        assertThat(foundVisits).hasSize(2);
    }

    @Test
    @DisplayName("Find By Id")
    void findById() {
        Visit visit = new Visit();

        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1L);

        assertThat(foundVisit).isNotNull();
        verify(visitRepository).findById(anyLong());
    }

    @Test
    @DisplayName("Save")
    void save() {
        when(visitRepository.save(any(Visit.class))).thenReturn(new Visit());

        Visit savedVisit = service.save(new Visit());

        verify(visitRepository).save(any(Visit.class));
    }

    @Test
    @DisplayName("Delete")
    void delete() {
        service.delete(new Visit());

        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    @DisplayName("Delete By Id")
    void deleteById() {
        service.deleteById(1L);

        verify(visitRepository).deleteById(1L);
    }
}