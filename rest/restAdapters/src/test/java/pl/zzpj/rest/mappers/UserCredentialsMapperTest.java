package pl.zzpj.rest.mappers;

import org.junit.jupiter.api.Test;
import pl.zzpj.model.UserCredentials;
import pl.zzpj.modelDto.UserCredentialsDto;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialsMapperTest {

    @Test
    void mapToUserCredentialsDto() {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin("login");
        userCredentials.setPassword("pass");

        UserCredentialsDto userCredentialsDto = UserCredentialsMapper.mapToUserCredentialsDto(userCredentials);

        assertEquals("login", userCredentialsDto.getLogin());
        assertEquals("pass", userCredentialsDto.getPassword());
    }

    @Test
    void mapToUserCredentials() {
        UserCredentialsDto userCredentialsDto = new UserCredentialsDto();
        userCredentialsDto.setLogin("login");
        userCredentialsDto.setPassword("pass");

        UserCredentials userCredentials = UserCredentialsMapper.mapToUserCredentials(userCredentialsDto);

        assertEquals("login", userCredentials.getLogin());
        assertEquals("pass", userCredentials.getPassword());
    }
}
