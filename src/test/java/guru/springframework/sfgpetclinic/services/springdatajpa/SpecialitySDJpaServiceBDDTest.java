package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceBDDTest {
    @Mock
    private SpecialtyRepository specialtyRepository;
    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void findAll() {
        //given
        Set<Speciality> specialities = new HashSet<>();
        specialities.add(new Speciality());
        specialities.add(new Speciality());
        given(specialtyRepository.findAll()).willReturn(specialities);

        //when
        var foundSpecialities = service.findAll();

        //then
        then(specialtyRepository).should().findAll();
        then(specialtyRepository).shouldHaveNoMoreInteractions();
        assertThat(foundSpecialities).hasSize(2);
    }

    @Test
    void findByIDBDDTest() {

        //given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        //when
        Speciality foundSpeciality = service.findById(1L);

        //then
        assertThat(foundSpeciality).isNotNull();
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void saveSpecialtyBDDTest() {
        //when
        service.save(new Speciality());
        //then
        then(specialtyRepository).should().save(any(Speciality.class));
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteByObjectBDDTest() {
        //given
        Speciality speciality = new Speciality();

        //when
        service.delete(speciality);

        //then
        then(specialtyRepository).should().delete(any(Speciality.class));
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteByIdBDDTest() {
        //given

        //when
        service.deleteById(1L);
        service.deleteById(1L);
        //then
        then(specialtyRepository).should(times(2)).deleteById(anyLong());
    }
}