package pl.edu.pjwstk.s29748.s29748Bank.finances;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pjwstk.s29748.s29748Bank.account.Account;
import pl.edu.pjwstk.s29748.s29748Bank.account.AccountService;
import pl.edu.pjwstk.s29748.s29748Bank.account.AccountStorage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FinancesServiceTest {

    @Mock
    FinancesStorage financesStorage;

    @InjectMocks
    FinancesService financesService;

    @Test
    void shouldFindByFinancesID() {
        //given
        int testFinancesID = 1;
        when(financesStorage.getAllFinances()).thenReturn(List.of(
                new Finances(1, 4200d),
        new Finances(2, 3200d),
       new Finances(3, 2200d),
        new Finances(4, 1200d)));
        //when
        Finances finances = financesService.findByFinancesID(testFinancesID);
        //then
        assertThat(finances.getFinancesID()).isEqualTo(testFinancesID);

    }

    @Test
    void shouldWrongFinancesID() {
        int testFinancesID = 5;
        when(financesStorage.getAllFinances()).thenReturn(List.of(
                new Finances(1, 4200d),
                new Finances(2, 3200d),
                new Finances(3, 2200d),
                new Finances(4, 1200d)));

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> financesService.findByFinancesID(testFinancesID))
                .withMessage("Finances not found");
    }
}