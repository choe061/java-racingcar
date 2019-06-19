package step3.game.dto;

import java.util.List;

import step3.game.domain.Car;
import step3.game.domain.Cars;

public class RacingGameResultModel {
    private final List<Cars> cars;
    private final List<Car> winners;

    public RacingGameResultModel(List<Cars> cars, List<Car> winners) {
        this.cars = cars;
        this.winners = winners;
    }

    public List<Cars> getCars() {
        return cars;
    }

    public List<Car> getWinners() {
        return winners;
    }
}
