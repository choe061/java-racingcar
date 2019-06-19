package step3.game.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGameRecord {
    private final List<Cars> result;

    public RacingGameRecord(List<Cars> result) {
        this.result = result;
    }

    public static RacingGameRecord newGame(Cars cars) {
        List<Cars> result = new ArrayList<>();
        result.add(cars);

        return new RacingGameRecord(result);
    }

    public void record(Cars cars) {
        this.result.add(cars);
    }

    public List<Cars> getResult() {
        return result;
    }

    public Cars previousTurn(int previousTurnNo) {
        assertTurnNo(previousTurnNo, result.size());
        return result.get(previousTurnNo);
    }

    public List<Car> winners() {
        assertGameRecord(result.size() - 1);

        List<Car> finalTurn = result.get(result.size() - 1).getCars();
        int winnerPosition = winnerPosition(finalTurn);

        return finalTurn.stream()
                        .filter(car -> winnerPosition == car.getPosition())
                        .collect(Collectors.toList());
    }

    private int winnerPosition(List<Car> finalTurn) {
        return finalTurn.stream()
                        .mapToInt(Car::getPosition)
                        .max().orElse(0);
    }

    private void assertTurnNo(final int previousTurnNo, final int resultSize) {
        if (previousTurnNo < 0) {
            throw new IllegalArgumentException("previousTurnNo 는 0 이상의 index 값입니다.");
        }

        if (previousTurnNo >= resultSize) {
            throw new IndexOutOfBoundsException("현재 " + resultSize + " 턴 까지 실행되었습니다. 입력 값 : " + previousTurnNo);
        }
    }

    private void assertGameRecord(final int turnNo) {
        if (turnNo < 0) {
            throw new IndexOutOfBoundsException("현재 저장된 기록이 없습니다.");
        }
    }
}
