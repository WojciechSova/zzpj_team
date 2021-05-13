package pl.zzpj.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpj.modelDto.usersDto.ClientDto;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private ClientDto from;
    private ClientDto to;
    private double amount;
    private Timestamp date;

}
