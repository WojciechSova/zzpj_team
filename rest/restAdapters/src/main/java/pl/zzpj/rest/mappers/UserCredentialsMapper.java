package pl.zzpj.rest.mappers;

import pl.zzpj.model.UserCredentials;
import pl.zzpj.dto.UserCredentialsDto;

public class UserCredentialsMapper {

    public static UserCredentialsDto mapToUserCredentialsDto(UserCredentials userCredentials) {
        UserCredentialsDto userCredentialsDto = new UserCredentialsDto();

        userCredentialsDto.setLogin(userCredentials.getLogin());
        userCredentialsDto.setPassword(userCredentials.getPassword());

        return userCredentialsDto;
    }

    public static UserCredentials mapToUserCredentials(UserCredentialsDto userCredentialsDto) {
        UserCredentials userCredentials = new UserCredentials();

        userCredentials.setLogin(userCredentialsDto.getLogin());
        userCredentials.setPassword(userCredentialsDto.getPassword());

        return userCredentials;
    }
}
