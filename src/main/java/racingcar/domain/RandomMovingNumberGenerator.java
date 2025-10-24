package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMovingNumberGenerator implements RandomNumberGenerator {
    @Override
    public int generate() {
        return Randoms.pickNumberInRange(0, 9);
    }
}
