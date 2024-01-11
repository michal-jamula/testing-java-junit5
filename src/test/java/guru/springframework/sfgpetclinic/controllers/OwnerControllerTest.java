package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito.*;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";
    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController controller;
    @Mock
    BindingResult bindingResult;
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    //This method can also be done with annotation, look at the method below this for example
    @Test
    void processFindFormWildcardString() {
        //given
        Owner owner = new Owner(1L, "Daniel", "Jones");
        List<Owner> ownerList = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%Jones%").isEqualToIgnoringCase(captor.getValue());
    }

    //Same implementation as above, only with a @Captor annotation
    @Test
    void processFindFormWildcardStringWithAnnotation() {
        //given
        Owner owner = new Owner(1L, "Daniel", "Jones");
        List<Owner> ownerList = new ArrayList<>();
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%Jones%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    }




    @Test
    @DisplayName("Process creation form should return create/update form when Binding result has errors")
    void processCreationFormHasErrors() {
        //given
        Owner owner = new Owner(5L, "Daniel", "Jones");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String result = controller.processCreationForm(owner, bindingResult);

        //then
        assertThat(result).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
    }

    @Test
    @DisplayName("Process creation form should return redirect/owners/5")
    void processCreationFormNoErrors() {
        //given
        Owner owner = new Owner(5L, "Daniel", "Jones");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);

        //when
        String result = controller.processCreationForm(owner, bindingResult);

        //then
        assertThat(result).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
    }
}