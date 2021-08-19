package CounterStriker.core;

import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

import static CounterStriker.common.ExceptionMessages.*;
import static CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_GUN;
import static CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_PLAYER;

public class ControllerImpl implements Controller{
    private GunRepository guns;
    private PlayerRepository players;
    private Field fields;

    public ControllerImpl(){
        guns = new GunRepository();
        players = new PlayerRepository();
        fields = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;
        if(type.equals("Pistol")) {
             gun = new Pistol(name, bulletsCount);
            guns.add(gun);
        } else if(type.equals("Rifle")) {
            gun = new Rifle(name, bulletsCount);
            guns.add(gun);
        } else {
            throw new IllegalArgumentException(INVALID_GUN_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Gun gun = guns.findByName(gunName);
        if(gun == null){
            throw new NullPointerException(GUN_CANNOT_BE_FOUND);
        }
        Player player;
        if(type.equals("Terrorist")){
            player = new Terrorist(username, health, armor, gun);
            players.add(player);
        } else if(type.equals("CounterTerrorist")){
            player = new CounterTerrorist(username, health, armor, gun);
            players.add(player);
        } else {
            throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }
        return String.format(SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        return fields.start(players.getModels().stream().filter(Player::isAlive).collect(Collectors.toList()));
    }

    @Override
    public String report() {
        List<Player> sortedPlayers = players.getModels().stream().sorted((l, r) -> {
            int result = l.getClass().getSimpleName().compareTo(r.getClass().getSimpleName());
            if (result == 0) {
                result = Integer.compare(r.getHealth(), l.getHealth());
                if (result == 0) {
                    result = l.getUsername().compareTo(r.getUsername());
                }
            }
            return result;
        }).collect(Collectors.toList());
            StringBuilder builder = new StringBuilder();
        for (Player sortedPlayer : sortedPlayers) {
            builder.append(String.format("%s: %s", sortedPlayer.getClass().getSimpleName(), sortedPlayer.getUsername())).append(System.lineSeparator());
            builder.append(String.format("--Health: %d", sortedPlayer.getHealth())).append(System.lineSeparator());
            builder.append(String.format("--Armor: %d", sortedPlayer.getArmor())).append(System.lineSeparator());
            builder.append(String.format("--Gun: %s", sortedPlayer.getGun().getName())).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
