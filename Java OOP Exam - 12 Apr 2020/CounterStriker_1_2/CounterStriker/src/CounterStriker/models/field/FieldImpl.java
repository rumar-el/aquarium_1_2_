package CounterStriker.models.field;

import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static CounterStriker.common.OutputMessages.COUNTER_TERRORIST_WINS;
import static CounterStriker.common.OutputMessages.TERRORIST_WINS;

public class FieldImpl implements Field{
    List<Terrorist> terrorists;
    List<CounterTerrorist> counterTerrorists;

    public FieldImpl(){
        this.terrorists = new ArrayList<>();
        this.counterTerrorists = new ArrayList<>();
    }

    @Override
    public String start(Collection<Player> players) {
        for (Player player : players) {
            if(player.getClass().getSimpleName().equals("Terrorist")) {
                this.terrorists.add((Terrorist) player);
            }else if(player.getClass().getSimpleName().equals("CounterTerrorist")) {
                this.counterTerrorists.add((CounterTerrorist) player);
            }
        }
        while (terrorists.stream().anyMatch(Terrorist::isAlive) && counterTerrorists.stream().anyMatch(CounterTerrorist::isAlive)) {
            for (Terrorist terrorist : terrorists) {
                if(terrorist.isAlive()) {
                    for (CounterTerrorist counterTerrorist : counterTerrorists) {
                        int fire = terrorist.getGun().fire();
                        if(counterTerrorist.isAlive()) {
                            counterTerrorist.takeDamage(fire);
                        }
                    }
                }
            }

            for (CounterTerrorist counterTerrorist : counterTerrorists) {
                if(counterTerrorist.isAlive()) {
                    for (Terrorist terrorist : terrorists) {
                        int fire = counterTerrorist.getGun().fire();
                        if(terrorist.isAlive()) {
                            terrorist.takeDamage(fire);
                        }
                    }
                }
            }
        }
        if(terrorists.stream().allMatch(Player::isAlive)){
           return TERRORIST_WINS;
        }
        return COUNTER_TERRORIST_WINS;
    }
}
