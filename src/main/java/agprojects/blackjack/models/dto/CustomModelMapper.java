package agprojects.blackjack.models.dto;

import agprojects.blackjack.models.Player;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomModelMapper {


    ModelMapper modelMapper = new ModelMapper();

    public Player convertFromPlayerDTO(PlayerDTO playerDTO){
        return modelMapper.map(playerDTO, Player.class);
    }

    public PlayerDTO convertFromPlayer(Player player){
         modelMapper = new org.modelmapper.ModelMapper();
        return modelMapper.map(player, PlayerDTO.class);
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
