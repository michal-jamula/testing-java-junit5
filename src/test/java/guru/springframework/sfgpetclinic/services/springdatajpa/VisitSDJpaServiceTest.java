package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Visit SDJ Service BDD Tests : ")
class VisitSDJpaServiceTest {
    @Mock
    VisitRepository visitRepository;
    @InjectMocks
    VisitSDJpaService service;

    @Test
    @DisplayName("Test Should Find All Visits")
    void findAllTest() {
        //given
        Set<Visit> visits = new HashSet<>();
        visits.add(new Visit());
        visits.add(new Visit());
        given(visitRepository.findAll()).willReturn(visits);

        //when
        var foundVisits = service.findAll();

        //then

        then(visitRepository).should().findAll();
        then(visitRepository).shouldHaveNoMoreInteractions();
        assertThat(foundVisits).hasSize(2);
    }

    @Test
    @DisplayName("Test Find Visit By ID")
    void findById() {
        //given
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(new Visit()));

        //when
        var foundVisit = service.findById(1L);

        //then
        then(visitRepository).should().findById(anyLong());
        assertThat(foundVisit).isNotNull();
    }

    @Test
    @DisplayName("Test Save Visit")
    void save() {
        //given
        given(visitRepository.save(any(Visit.class))).willReturn(new Visit());
        //when
        Visit savedVisit = service.save(new Visit());
        //then
        then(visitRepository).should().save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @Test
    @DisplayName("Test Delete Visit")
    void delete() {
        //when
        service.delete(new Visit());
        //then
        then(visitRepository).should().delete(any(Visit.class));
        then(visitRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    @DisplayName("Test Delete Visit By ID")
    void deleteById() {
        //when
        service.deleteById(1L);

        //then
        then(visitRepository).should().deleteById(1L);
    }

    @Test
    @DisplayName("Test Multiple Delete Visit By ID")
    void multipleDeleteById() {
        //when
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        then(visitRepository).should(times(3)).deleteById(anyLong());
        then(visitRepository).shouldHaveNoMoreInteractions();
    }
}