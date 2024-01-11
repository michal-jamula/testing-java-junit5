package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.BDDMockito.*;
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
    @Mock
    Model model;
    @InjectMocks
    OwnerController controller;
    @Mock
    BindingResult bindingResult;
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;


    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invocation -> {
                    List<Owner> ownerList = new ArrayList<>();

                    String name = invocation.getArgument(0);

                    if (name.equals("%Jones%")) {
                        ownerList.add(new Owner(1L, "Daniel", "Jones"));
                        return ownerList;
                    } else if (name.equals("%DontFindMe%")) {
                        return ownerList;
                    } else if (name.equals("%FindMe%")){
                        ownerList.add(new Owner(1L, "Daniel", "Jones"));
                        ownerList.add(new Owner(2L, "Daniel2", "Jones2"));
                        return ownerList;
                    }

                    throw new RuntimeException("Invalid Argument, something wrong with your mocks bro");
                });
    }



    //Same implementation as above, only with a @Captor annotation
    @Test
    void processFindFormWildcardStringWithAnnotation() {
        //given
        Owner owner = new Owner(1L, "Daniel", "Jones");

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);


        //then
        assertThat("%Jones%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
        verifyZeroInteractions(model);
    }

    @Test
    void processFindFormWildcardStringNotFound() {
        //given
        Owner owner = new Owner(1L, "Daniel", "DontFindMe");

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
        verifyZeroInteractions(model);
    }

    @Test
    void processFindFormWildcardStringFound() {
        //given
        Owner owner = new Owner(1L, "Daniel", "FindMe");
        InOrder inOrder = inOrder(ownerService, model);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, model);
        verifyZeroInteractions(ownerService);


        //then
        assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);

        //inOrder assertions
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model).addAttribute(anyString(), anyList());
        verifyNoMoreInteractions(model);
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