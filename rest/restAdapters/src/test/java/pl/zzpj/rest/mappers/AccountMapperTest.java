package pl.zzpj.rest.mappers;

import org.junit.jupiter.api.Test;
import pl.zzpj.model.Currency;
import pl.zzpj.model.users.Account;
import pl.zzpj.model.users.Admin;
import pl.zzpj.model.users.Client;
import pl.zzpj.model.users.Employee;
import pl.zzpj.modelDto.CurrencyDto;
import pl.zzpj.modelDto.usersDto.AccountDto;
import pl.zzpj.modelDto.usersDto.AdminDto;
import pl.zzpj.modelDto.usersDto.ClientDto;
import pl.zzpj.modelDto.usersDto.EmployeeDto;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    @Test
    void mapToAccountDto() {
        //Client
        Client client = new Client();

        client.setLogin("clogin");
        client.setId(10L);
        client.setFirstName("cFirstName");
        client.setLastName("cLastName");
        client.setPassword("cPassword");
        client.setAccountNumber("cNumber");
        client.setAccountState(200);
        client.setDebt(500);
        client.setCurrency(Currency.EUR);

        AccountDto clientDto = AccountMapper.mapToAccountDto(client);

        assertTrue(clientDto instanceof ClientDto);

        assertEquals("clogin", clientDto.getLogin());
        assertEquals(10L, clientDto.getId());
        assertEquals("cFirstName", clientDto.getFirstName());
        assertEquals("cLastName", clientDto.getLastName());
        assertEquals("cPassword", clientDto.getPassword());
        assertEquals("cNumber", ((ClientDto) clientDto).getAccountNumber());
        assertEquals(200, ((ClientDto) clientDto).getAccountState());
        assertEquals(500, ((ClientDto) clientDto).getDebt());
        assertEquals(CurrencyDto.EUR, ((ClientDto) clientDto).getCurrency());

        //Admin
        Admin admin = new Admin();

        admin.setLogin("alogin");
        admin.setId(20L);
        admin.setFirstName("aFirstName");
        admin.setLastName("aLastName");
        admin.setPassword("aPassword");

        AccountDto adminDto = AccountMapper.mapToAccountDto(admin);

        assertTrue(adminDto instanceof AdminDto);

        assertEquals("alogin", adminDto.getLogin());
        assertEquals(20L, adminDto.getId());
        assertEquals("aFirstName", adminDto.getFirstName());
        assertEquals("aLastName", adminDto.getLastName());
        assertEquals("aPassword", adminDto.getPassword());

        //Employee
        Employee employee = new Employee();

        employee.setLogin("elogin");
        employee.setId(30L);
        employee.setFirstName("eFirstName");
        employee.setLastName("eLastName");
        employee.setPassword("ePassword");

        AccountDto employeeDto = AccountMapper.mapToAccountDto(employee);

        assertTrue(employeeDto instanceof EmployeeDto);

        assertEquals("elogin", employeeDto.getLogin());
        assertEquals(30L, employeeDto.getId());
        assertEquals("eFirstName", employeeDto.getFirstName());
        assertEquals("eLastName", employeeDto.getLastName());
        assertEquals("ePassword", employeeDto.getPassword());
    }

    @Test
    void mapToAccount() {
        //Client
        ClientDto clientDto = new ClientDto();
        clientDto.setLogin("clogin");
        clientDto.setId(10L);
        clientDto.setFirstName("cFirstName");
        clientDto.setLastName("cLastName");
        clientDto.setPassword("cPassword");
        clientDto.setAccountNumber("cNumber");
        clientDto.setAccountState(200);
        clientDto.setDebt(500);
        clientDto.setCurrency(CurrencyDto.EUR);

        Account client = AccountMapper.mapToAccount(clientDto);

        assertTrue(client instanceof Client);

        assertEquals("clogin", client.getLogin());
        assertEquals(10L, client.getId());
        assertEquals("cFirstName", client.getFirstName());
        assertEquals("cLastName", client.getLastName());
        assertEquals("cPassword", client.getPassword());
        assertEquals("cNumber", ((Client) client).getAccountNumber());
        assertEquals(200, ((Client) client).getAccountState());
        assertEquals(500, ((Client) client).getDebt());
        assertEquals(Currency.EUR, ((Client) client).getCurrency());

        //Admin
        AdminDto adminDto = new AdminDto();

        adminDto.setLogin("alogin");
        adminDto.setId(20L);
        adminDto.setFirstName("aFirstName");
        adminDto.setLastName("aLastName");
        adminDto.setPassword("aPassword");

        Account admin = AccountMapper.mapToAccount(adminDto);

        assertTrue(admin instanceof Admin);

        assertEquals("alogin", admin.getLogin());
        assertEquals(20L, admin.getId());
        assertEquals("aFirstName", admin.getFirstName());
        assertEquals("aLastName", admin.getLastName());
        assertEquals("aPassword", admin.getPassword());

        //Employee
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setLogin("elogin");
        employeeDto.setId(30L);
        employeeDto.setFirstName("eFirstName");
        employeeDto.setLastName("eLastName");
        employeeDto.setPassword("ePassword");

        Account employee = AccountMapper.mapToAccount(employeeDto);

        assertTrue(employee instanceof Employee);

        assertEquals("elogin", employee.getLogin());
        assertEquals(30L, employee.getId());
        assertEquals("eFirstName", employee.getFirstName());
        assertEquals("eLastName", employee.getLastName());
        assertEquals("ePassword", employee.getPassword());
    }
}
