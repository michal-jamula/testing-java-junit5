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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @Test
    //This method does not follow BDD style
    void doThrow() {
        BDDMockito.doThrow(new RuntimeException("Boom!")).when(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class,
                () -> specialtyRepository.delete(new Speciality()));

        verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByIdThrows() {
        //given
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("Boom!"));

        //when
        assertThrows(RuntimeException.class, () -> service.findById(1L));

        //then
        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void testDeleteBDD() {
        //given (this is used on a method that's meant to return void
        willThrow(new RuntimeException("delete exception!")).given(specialtyRepository).delete(any());

        //when
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        //then
        then(specialtyRepository).should().delete(any());
    }

    @Test
    void testSaveLambda() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpeciality = new Speciality();
        savedSpeciality.setId(1L);

        //need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpeciality);

        //when
        Speciality returnedSpeciality = service.save(speciality);

        //then
        assertThat(returnedSpeciality.getId()).isEqualTo(1L);
    }
}