package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTests;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.fauxspring.ModelMapTestImpl;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.naming.ldap.Control;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class VetControllerTest implements ControllerTests {

    private VetService vetService;
    SpecialtyService specialtyService;
    VetController vetController;
    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
       vetService = new VetMapService(specialtyService);
       vetController = new VetController(vetService);

        Vet vet1 = new Vet(1L, "John", "Smith", null);
        Vet vet2 = new Vet(2L, "Johnny", "Smithy", null);

        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    void listVets() {
        Model model = new ModelMapTestImpl();

        String view = vetController.listVets(model);

        Set modelAttribute = (Set) ((ModelMapTestImpl) model).getMap().get("vets");

        assertThat("vets/index").isEqualTo(view);
        assertThat(modelAttribute.size()).isEqualTo(2);
    }
}